package com.github.abhrp.cache

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import android.content.Context
import com.github.abhrp.cache.db.MovieDatabase
import com.github.abhrp.cache.mapper.CachedMovieMapper
import com.github.abhrp.cache.sharedpreferences.MoviesSharedPreferences
import com.github.abhrp.cache.test.factory.MovieDataFactory
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class MovieCacheImplTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var context: Context

    private val database = Room
            .inMemoryDatabaseBuilder(RuntimeEnvironment.application.applicationContext, MovieDatabase::class.java)
            .allowMainThreadQueries()
            .build()

    private val cachedMovieMapper = CachedMovieMapper()

    private lateinit var sharedPreferences: MoviesSharedPreferences
    private lateinit var cache: MovieCacheImpl

    @Before
    fun setup() {
        sharedPreferences = MoviesSharedPreferences(context)
        cache = MovieCacheImpl(database, cachedMovieMapper, sharedPreferences)
    }



    @Test
    fun deleteMoviesCompletes() {
        val testObserver = cache.clearMovies().test()
        testObserver.assertComplete()
    }

    @Test
    fun saveMoviesCompletes() {
        val movies = listOf(MovieDataFactory.makeMovieEntity())
        val testObserver = cache.saveMovies(movies).test()
        testObserver.assertComplete()
    }

    @Test
    fun getMoviesReturnsData() {
        val movies = listOf(MovieDataFactory.makeMovieEntity())
        cache.saveMovies(movies).test()
        val testObserver = cache.getMovies().test()
        testObserver.assertValue(movies)
    }

    @Test
    fun getSavedMoviesReturnsData() {
        val movies = listOf(MovieDataFactory.makeShortlistedMovieEntity())
        cache.saveMovies(movies).test()
        val testObserver = cache.getShortlistedMovies().test()
        testObserver.assertValue(movies)
    }

    @Test
    fun setShortlistedMovieCompletes() {
        val movie = MovieDataFactory.makeMovieEntity()
        cache.saveMovies(listOf(movie)).test()
        val testObserver = cache.setMovieAsShortlisted(movie.id).test()
        testObserver.assertComplete()
    }

    @Test
    fun setRemoveMovieCompletes() {
        val movie = MovieDataFactory.makeMovieEntity()
        cache.saveMovies(listOf(movie)).test()
        val testObserver = cache.removeMovieFromShortlist(movie.id).test()
        testObserver.assertComplete()
    }

    @Test
    fun areMoviesCachedReturnsData() {
        val movie = MovieDataFactory.makeMovieEntity()
        cache.saveMovies(listOf(movie)).test()
        val testObserver = cache.areMoviesCached().test()
        testObserver.assertValue(true)
    }

    @Test
    fun setLastCacheTime() {
        val testObserver = cache.setLastCacheTime(System.currentTimeMillis()).test()
        testObserver.assertComplete()
    }

    @Test
    fun isMoviesCacheExpiredReturnsExpired() {
        val testObserver = cache.isCacheExpired().test()
        testObserver.assertValue(true)
    }

    @Test
    fun isMoviesCacheExpiredReturnsNotExpired() {
        cache.setLastCacheTime(System.currentTimeMillis()).test()
        val testObserver = cache.isCacheExpired().test()
        testObserver.assertValue(false)
    }
}