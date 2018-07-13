package com.github.abhrp.cache.dao

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import com.github.abhrp.cache.db.MovieDatabase
import com.github.abhrp.cache.test.factory.ConfigDataFactory
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class ConfigDaoTest {
    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val database = Room
            .inMemoryDatabaseBuilder(RuntimeEnvironment.application.applicationContext, MovieDatabase::class.java)
            .allowMainThreadQueries()
            .build()

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testConfigReturnsData() {
        val config = ConfigDataFactory.makeConfig()
        database.getConfigDao().insertLastCachedTime(config)
        val testObserver = database.getConfigDao().getConfig().test()
        testObserver.assertValue(config)
    }
}