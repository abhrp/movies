package com.github.abhrp.data.store

import com.github.abhrp.data.factory.MovieFactory
import com.github.abhrp.data.model.MovieEntity
import com.github.abhrp.data.repository.MoviesRemote
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class MoviesRemoteDataStoreTest {
    private val remote = mock<MoviesRemote>()
    private val store = MoviesRemoteDataStore(remote)


    @Test
    fun getMoviesCompletes() {
        stubGetMovies(Observable.just(listOf(MovieFactory.makeMovieEntity())))
        val testObserver = store.getMovies().test()
        testObserver.assertComplete()
    }

    @Test
    fun getMoviesReturnsData() {
        val data = listOf(MovieFactory.makeMovieEntity())
        stubGetMovies(Observable.just(data))
        val testObserver = store.getMovies().test()
        testObserver.assertValue(data)
    }

    @Test(expected = UnsupportedOperationException::class)
    fun getShortlistedMoviesThrowsException() {
        store.getShortlistedMovies().test()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun setShortlistedMoviesThrowsException() {
        store.setMovieAsShortlisted(any()).test()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun removeShortlistedMoviesThrowsException() {
        store.removeMovieFromShortlist(any()).test()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun clearMoviesThrowsException() {
        store.clearMovies().test()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun saveMoviesThrowsException() {
        store.saveMovies(any()).test()
    }

    private fun stubGetMovies(observable: Observable<List<MovieEntity>>) {
        whenever(remote.getMovies()).thenReturn(observable)
    }

}