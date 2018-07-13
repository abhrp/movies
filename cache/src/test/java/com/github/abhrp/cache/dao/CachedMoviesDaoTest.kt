package com.github.abhrp.cache.dao

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import com.github.abhrp.cache.db.MovieDatabase
import com.github.abhrp.cache.test.factory.MovieDataFactory
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class CachedMoviesDaoTest {

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
    fun getMoviesReturnsData() {
        val movies = MovieDataFactory.makeCachedMovie()
        database.getCachedMoviesDao().insertMovies(listOf(movies))

        val testObserver = database.getCachedMoviesDao().getAllMovies().test()
        testObserver.assertValue(listOf(movies))
    }

    @Test
    fun deleteMoviesClearsTable() {
        val movies = MovieDataFactory.makeCachedMovie()
        database.getCachedMoviesDao().insertMovies(listOf(movies))
        database.getCachedMoviesDao().deleteAll()

        val testObserver = database.getCachedMoviesDao().getAllMovies().test()
        testObserver.assertValue(emptyList())
    }

    @Test
    fun getSavedMoviesReturnsData() {
        val movies = MovieDataFactory.makeCachedMovie()
        val savedMovie = MovieDataFactory.makeShortlistedCachedMovie()
        database.getCachedMoviesDao().insertMovies(listOf(movies, savedMovie))

        val testObserver = database.getCachedMoviesDao().getShortlistedMovies().test()
        testObserver.assertValue(listOf(savedMovie))
    }

    @Test
    fun setSaveMovieReturnsData() {
        val movies = MovieDataFactory.makeCachedMovie()
        database.getCachedMoviesDao().insertMovies(listOf(movies))
        database.getCachedMoviesDao().setShortlistedMovie(true, movies.id)
        movies.isShortlisted = true
        val testObserver = database.getCachedMoviesDao().getShortlistedMovies().test()
        testObserver.assertValue(listOf(movies))
    }

    @Test
    fun setRemoveMovieReturnsData() {
        val movies = MovieDataFactory.makeShortlistedCachedMovie()
        database.getCachedMoviesDao().insertMovies(listOf(movies))
        database.getCachedMoviesDao().setShortlistedMovie(false, movies.id)
        movies.isShortlisted = false
        val testObserver = database.getCachedMoviesDao().getShortlistedMovies().test()
        testObserver.assertValue(emptyList())
    }
}