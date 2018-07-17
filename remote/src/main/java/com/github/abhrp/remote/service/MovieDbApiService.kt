package com.github.abhrp.remote.service

import com.github.abhrp.remote.constants.Keys
import com.github.abhrp.remote.model.MoviesResponseModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDbApiService {
    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String = Keys.API_KEY): Observable<MoviesResponseModel>
}