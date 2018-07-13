package com.github.abhrp.mobile_ui.di.module

import android.app.Application
import com.github.abhrp.cache.MovieCacheImpl
import com.github.abhrp.cache.db.MovieDatabase
import com.github.abhrp.data.repository.MoviesCache
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class CacheModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providesDatabase(application: Application): MovieDatabase {
            return MovieDatabase.getInstance(application)
        }
    }

    @Binds
    abstract fun bindsMoviesCache(movieCacheImpl: MovieCacheImpl): MoviesCache
}