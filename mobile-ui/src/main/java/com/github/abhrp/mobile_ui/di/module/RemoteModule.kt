package com.github.abhrp.mobile_ui.di.module

import com.github.abhrp.data.repository.MoviesRemote
import com.github.abhrp.mobile_ui.BuildConfig
import com.github.abhrp.remote.MoviesRemoteImpl
import com.github.abhrp.remote.service.MovieDbApiService
import com.github.abhrp.remote.service.MovieDbApiServiceFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RemoteModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providesMoviesApiService(): MovieDbApiService {
            return MovieDbApiServiceFactory.getMovieDbApiService(BuildConfig.DEBUG)
        }
    }

    @Binds
    abstract fun bindsMoviesRemote(moviesRemoteImpl: MoviesRemoteImpl): MoviesRemote
}