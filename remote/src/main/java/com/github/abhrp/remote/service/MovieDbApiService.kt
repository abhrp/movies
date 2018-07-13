package com.github.abhrp.remote.service

import com.github.abhrp.remote.constants.MoviesDbConstants
import com.github.abhrp.remote.model.MoviesResponseModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDbApiService {
    @GET("list/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String = MoviesDbConstants.API_KEY): Observable<MoviesResponseModel>
}