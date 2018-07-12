package com.github.abhrp.data.repository

import com.github.abhrp.data.model.MovieEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface MoviesCache {
    fun clearMovies(): Completable
    fun saveMovies(movies: List<MovieEntity>): Completable
    fun getMovies(): Observable<List<MovieEntity>>
    fun getShortlistedMovies(): Observable<List<MovieEntity>>
    fun setMovieAsShortlisted(movieId: Int): Completable
    fun removeMovieFromShortlist(movieId: Int): Completable
    fun areMoviesCached(): Single<Boolean>
    fun setLastCacheTime(cacheTime: Long): Completable
    fun isCacheExpired(): Single<Boolean>
}