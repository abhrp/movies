package com.github.abhrp.remote

import com.github.abhrp.data.model.MovieEntity
import com.github.abhrp.data.repository.MoviesRemote
import com.github.abhrp.remote.mapper.MoviesResponseModelMapper
import com.github.abhrp.remote.service.MovieDbApiService
import io.reactivex.Observable
import javax.inject.Inject

class MoviesRemoteImpl @Inject constructor(private val moviesDbApiService: MovieDbApiService, private val moviesResponseModelMapper: MoviesResponseModelMapper) : MoviesRemote {

    override fun getMovies(): Observable<List<MovieEntity>> {
        return moviesDbApiService.getPopularMovies().map { response ->
            response.results.map {
                moviesResponseModelMapper.mapFromModel(it)
            }
        }
    }

}