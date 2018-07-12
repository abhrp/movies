package com.github.abhrp.data.store

import com.github.abhrp.data.repository.MoviesDataStore
import javax.inject.Inject

open class MoviesDataStoreFactory @Inject constructor(
        private val moviesCacheDataStore: MoviesCacheDataStore,
        private val moviesRemoteDataStore: MoviesRemoteDataStore
) {
    open fun getDataStore(moviesCached: Boolean, cacheExpired: Boolean): MoviesDataStore = if (moviesCached && !cacheExpired) moviesCacheDataStore else moviesRemoteDataStore
    open fun getCacheDataStore(): MoviesDataStore = moviesCacheDataStore
}