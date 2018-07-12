package com.github.abhrp.data.repository

import com.github.abhrp.data.model.MovieEntity
import io.reactivex.Observable

interface MoviesRemote {
    fun getMovies(): Observable<List<MovieEntity>>
}