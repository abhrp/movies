package com.github.abhrp.cache

import com.github.abhrp.cache.db.MovieDatabase
import com.github.abhrp.cache.mapper.CachedMovieMapper
import com.github.abhrp.cache.model.Config
import com.github.abhrp.data.model.MovieEntity
import com.github.abhrp.data.repository.MoviesCache
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class MovieCacheImpl @Inject constructor(private val movieDatabase: MovieDatabase, private val mapper: CachedMovieMapper) : MoviesCache {
    override fun clearMovies(): Completable {
        return Completable.defer {
            movieDatabase.getCachedMoviesDao().deleteAll()
            Completable.complete()
        }
    }

    override fun saveMovies(movies: List<MovieEntity>): Completable {
        return Completable.defer {
            movieDatabase.getCachedMoviesDao().insertMovies(movies.map { mapper.mapFromEntity(it) })
            Completable.complete()
        }
    }

    override fun getMovies(): Observable<List<MovieEntity>> {
        return movieDatabase
                .getCachedMoviesDao()
                .getAllMovies()
                .toObservable()
                .map {
                    it.map {
                        mapper.mapToEntity(it)
                    }
                }
    }

    override fun getShortlistedMovies(): Observable<List<MovieEntity>> {
        return movieDatabase
                .getCachedMoviesDao()
                .getSavedMovies()
                .toObservable()
                .map {
                    it.map {
                        mapper.mapToEntity(it)
                    }
                }
    }

    override fun setMovieAsShortlisted(movieId: Int): Completable {
        return Completable.defer {
            movieDatabase.getCachedMoviesDao().setSaveMovie(true, movieId)
            Completable.complete()
        }
    }

    override fun removeMovieFromShortlist(movieId: Int): Completable {
        return Completable.defer {
            movieDatabase.getCachedMoviesDao().setSaveMovie(false, movieId)
            Completable.complete()
        }
    }

    override fun areMoviesCached(): Single<Boolean> {
        return movieDatabase.getCachedMoviesDao().getAllMovies().isEmpty
                .map { !it }
    }

    override fun setLastCacheTime(cacheTime: Long): Completable {
        return Completable.defer {
            movieDatabase.getConfigDao().insertLastCachedTime(Config(cacheTime))
            Completable.complete()
        }
    }

    override fun isCacheExpired(): Single<Boolean> {
        val currentTime = System.currentTimeMillis()
        val expirationTime = (60 * 10 * 1000).toLong()
        return movieDatabase.getConfigDao().getConfig()
                .single(Config(lastCachedTime = 0))
                .map {
                    currentTime - it.lastCachedTime > expirationTime
                }
    }
}