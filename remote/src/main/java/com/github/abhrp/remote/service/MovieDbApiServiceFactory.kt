package com.github.abhrp.remote.service

import com.github.abhrp.remote.constants.MoviesDbConstants
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object MovieDbApiServiceFactory {

    fun getMovieDbApiService(isDebug: Boolean): MovieDbApiService {
        val okHttpClient = makeOkHttpClient(makeHttpLoggingInterceptor(isDebug))
        return makeMovieDbApiService(okHttpClient)
    }

    private fun makeMovieDbApiService(okHttpClient: OkHttpClient): MovieDbApiService {
        val retrofit = Retrofit.Builder().baseUrl(MoviesDbConstants.BASE_URL).client(okHttpClient).addConverterFactory(MoshiConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
        return retrofit.create(MovieDbApiService::class.java)
    }

    private fun makeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).connectTimeout(120, TimeUnit.SECONDS).readTimeout(120, TimeUnit.SECONDS).build()

    private fun makeHttpLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return logging
    }
}