package com.github.abhrp.cache

import com.github.abhrp.cache.db.MovieDatabase
import com.github.abhrp.cache.mapper.CachedMovieMapper
import com.github.abhrp.cache.sharedpreferences.MoviesSharedPreferences
import com.github.abhrp.data.model.MovieEntity
import com.github.abhrp.data.repository.MoviesCache
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class MovieCacheImpl @Inject constructor(private val movieDatabase: MovieDatabase, private val mapper: CachedMovieMapper, private val sharedPreferences: MoviesSharedPreferences) : MoviesCache {
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
                .map { it ->
                    it.map {
                        mapper.mapToEntity(it)
                    }
                }
    }

    override fun getShortlistedMovies(): Observable<List<MovieEntity>> {
        return movieDatabase
                .getCachedMoviesDao()
                .getShortlistedMovies()
                .toObservable()
                .map { it ->
                    it.map {
                        mapper.mapToEntity(it)
                    }
                }
    }

    override fun setMovieAsShortlisted(movieId: Int): Completable {
        return Completable.defer {
            movieDatabase.getCachedMoviesDao().setShortlistedMovie(true, movieId)
            Completable.complete()
        }
    }

    override fun removeMovieFromShortlist(movieId: Int): Completable {
        return Completable.defer {
            movieDatabase.getCachedMoviesDao().setShortlistedMovie(false, movieId)
            Completable.complete()
        }
    }

    override fun areMoviesCached(): Single<Boolean> {
        return movieDatabase.getCachedMoviesDao().getAllMovies().isEmpty
                .map { !it }
    }

    override fun setLastCacheTime(cacheTime: Long): Completable {
        return Completable.defer {
            sharedPreferences.lastCacheTime = cacheTime
            Completable.complete()
        }
    }

    override fun isCacheExpired(): Single<Boolean> {
        val currentTime = System.currentTimeMillis()
        val expirationTime = (60 * 9 * 1000).toLong()
        val isExpired = currentTime - sharedPreferences.lastCacheTime > expirationTime
        return Single.just(isExpired)
    }
}