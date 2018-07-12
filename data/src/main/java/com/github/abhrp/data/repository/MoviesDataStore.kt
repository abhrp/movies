package com.github.abhrp.data.repository

import com.github.abhrp.data.model.MovieEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface MoviesDataStore {
    fun getMovies(): Observable<List<MovieEntity>>
    fun getShortlistedMovies(): Observable<List<MovieEntity>>
    fun setMovieAsShortlisted(movieId: Int): Completable
    fun removeMovieFromShortlist(movieId: Int): Completable
    fun clearMovies(): Completable
    fun saveMovies(movies: List<MovieEntity>): Completable
}