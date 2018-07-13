package com.github.abhrp.mobile_ui.di.module

import com.github.abhrp.domain.executor.PostExecutionThread
import com.github.abhrp.mobile_ui.UIThread
import com.github.abhrp.mobile_ui.activities.BrowseMoviesActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UiModule {

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UIThread): PostExecutionThread

    @ContributesAndroidInjector
    abstract fun contributesBrowseMoviesActivity(): BrowseMoviesActivity

}