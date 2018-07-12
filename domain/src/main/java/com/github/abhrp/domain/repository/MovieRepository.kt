package com.github.abhrp.domain.repository

import com.github.abhrp.domain.model.Movie
import io.reactivex.Completable
import io.reactivex.Observable

interface MovieRepository {
    fun getMovies(): Observable<List<Movie>>

    fun shortListMovie(movieId: Int): Completable

    fun removeMovie(movieId: Int): Completable

    fun getShortListedMovies(): Observable<List<Movie>>
}