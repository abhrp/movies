package com.github.abhrp.data.store

import com.nhaarman.mockito_kotlin.mock
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class MovieDataStoreFactoryTest {
    private val cacheStore = mock<MoviesCacheDataStore>()
    private val remoteStore = mock<MoviesRemoteDataStore>()
    private val factory = MoviesDataStoreFactory(cacheStore, remoteStore)

    @Test
    fun getRemoteDataStoreIfCacheIsExpired() {
        assertEquals(remoteStore, factory.getDataStore(true, true))
    }
}