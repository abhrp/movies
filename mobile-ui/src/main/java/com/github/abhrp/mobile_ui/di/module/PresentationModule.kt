package com.github.abhrp.mobile_ui.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.github.abhrp.mobile_ui.di.ViewModelFactory
import com.github.abhrp.mobile_ui.di.annotation.ViewModelKey
import com.github.abhrp.presentation.viewmodel.BrowseMoviesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(BrowseMoviesViewModel::class)
    abstract fun bindsBrowseMoviesViewModel(browseMoviesViewModel: BrowseMoviesViewModel): ViewModel

    @Binds
    abstract fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}