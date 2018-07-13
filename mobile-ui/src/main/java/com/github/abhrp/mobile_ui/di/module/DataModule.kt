package com.github.abhrp.mobile_ui.di.module

import com.github.abhrp.data.MoviesDataRepository
import com.github.abhrp.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindsMoviesDataRepository(moviesDataRepository: MoviesDataRepository): MovieRepository
}