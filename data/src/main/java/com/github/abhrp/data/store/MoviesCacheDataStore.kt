package com.github.abhrp.data.store

import com.github.abhrp.data.model.MovieEntity
import com.github.abhrp.data.repository.MoviesCache
import com.github.abhrp.data.repository.MoviesDataStore
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class MoviesCacheDataStore @Inject constructor(private val moviesCache: MoviesCache) : MoviesDataStore {

    override fun getMovies(): Observable<List<MovieEntity>> = moviesCache.getMovies()

    override fun getShortlistedMovies(): Observable<List<MovieEntity>> = moviesCache.getShortlistedMovies()

    override fun setMovieAsShortlisted(movieId: Int): Completable = moviesCache.setMovieAsShortlisted(movieId)

    override fun removeMovieFromShortlist(movieId: Int): Completable = moviesCache.removeMovieFromShortlist(movieId)

    override fun clearMovies(): Completable = moviesCache.clearMovies()

    override fun saveMovies(movies: List<MovieEntity>): Completable = moviesCache.saveMovies(movies).andThen(moviesCache.setLastCacheTime(System.currentTimeMillis()))
}