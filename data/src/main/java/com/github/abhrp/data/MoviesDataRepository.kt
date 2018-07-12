package com.github.abhrp.data

import com.github.abhrp.data.mapper.MovieEntityMapper
import com.github.abhrp.data.repository.MoviesCache
import com.github.abhrp.data.store.MoviesDataStoreFactory
import com.github.abhrp.domain.model.Movie
import com.github.abhrp.domain.repository.MovieRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class MoviesDataRepository @Inject constructor(
        private val moviesCache: MoviesCache,
        private val moviesDataStoreFactory: MoviesDataStoreFactory,
        private val mapper: MovieEntityMapper
) : MovieRepository {

    override fun getMovies(): Observable<List<Movie>> {
        return Observable.zip(moviesCache.areMoviesCached().toObservable(),
                moviesCache.isCacheExpired().toObservable(),
                BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { areCached, isExpired ->
                    Pair(areCached, isExpired)
                })
                .flatMap {
                    moviesDataStoreFactory.getDataStore(it.first, it.second).getMovies()
                }
                .flatMap { movies ->
                    moviesDataStoreFactory.getCacheDataStore().saveMovies(movies).andThen(Observable.just(movies))
                }
                .map { it ->
                    it.map {
                        mapper.mapFromEntity(it)
                    }
                }
    }

    override fun shortListMovie(movieId: Int): Completable {
        return moviesDataStoreFactory.getCacheDataStore().setMovieAsShortlisted(movieId)
    }

    override fun removeMovie(movieId: Int): Completable {
        return moviesDataStoreFactory.getCacheDataStore().removeMovieFromShortlist(movieId)
    }

    override fun getShortListedMovies(): Observable<List<Movie>> {
        return moviesDataStoreFactory.getCacheDataStore().getShortlistedMovies()
                .map {
                    it.map {
                        mapper.mapFromEntity(it)
                    }
                }
    }
}