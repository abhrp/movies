package com.github.abhrp.data.store

import com.github.abhrp.data.factory.DataFactory
import com.github.abhrp.data.factory.MovieFactory
import com.github.abhrp.data.model.MovieEntity
import com.github.abhrp.data.repository.MoviesCache
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MoviesCacheDataStoreTest {
    private val cache = mock<MoviesCache>()
    private val store = MoviesCacheDataStore(cache)

    @Test
    fun getMoviesCompletes() {
        getMoviesFromCache(Observable.just(listOf(MovieFactory.makeMovieEntity())))
        val testObserver = store.getMovies().test()
        testObserver.assertComplete()
    }

    @Test
    fun getMoviesCallsCache() {
        getMoviesFromCache(Observable.just(listOf(MovieFactory.makeMovieEntity())))
        store.getMovies().test()
        verify(cache).getMovies()
    }

    @Test
    fun getMoviesReturnsData() {
        val data = listOf(MovieFactory.makeMovieEntity())
        getMoviesFromCache(Observable.just(data))

        val testObserver = store.getMovies().test()
        testObserver.assertValue(data)
    }

    @Test
    fun getShortlistedMoviesCompletes() {
        getShortlistedMoviesFromCache(Observable.just(listOf(MovieFactory.makeMovieEntity())))
        val testObserver = store.getShortlistedMovies().test()
        testObserver.assertComplete()
    }

    @Test
    fun getShortlistedMoviesReturnsData() {
        val data = listOf(MovieFactory.makeMovieEntity())
        getShortlistedMoviesFromCache(Observable.just(data))

        val testObserver = store.getShortlistedMovies().test()
        testObserver.assertValue(data)
    }

    @Test
    fun saveMoviesCompletes() {
        saveMovies(Completable.complete())
        setLastCacheTime(Completable.complete())
        val testObserver = store.saveMovies(listOf(MovieFactory.makeMovieEntity())).test()
        testObserver.assertComplete()
    }

    @Test
    fun saveMoviesCallsCache() {
        val data = listOf(MovieFactory.makeMovieEntity())
        saveMovies(Completable.complete())
        setLastCacheTime(Completable.complete())
        store.saveMovies(data).test()
        verify(cache).saveMovies(data)
    }

    @Test
    fun clearMoviesCompletes() {
        clearMovies(Completable.complete())
        val testObserver = store.clearMovies().test()
        testObserver.assertComplete()
    }

    @Test
    fun clearMoviesCallCache() {
        clearMovies(Completable.complete())
        store.clearMovies().test()
        verify(cache).clearMovies()
    }

    @Test
    fun shortListMovieCompletes() {
        shortlistMovieInCache(Completable.complete())
        val testObserver = store.setMovieAsShortlisted(DataFactory.randomInt()).test()
        testObserver.assertComplete()
    }

    @Test
    fun shortListMovieCallsCache() {
        shortlistMovieInCache(Completable.complete())
        val movieId = DataFactory.randomInt()
        store.setMovieAsShortlisted(movieId).test()
        verify(cache).setMovieAsShortlisted(movieId)
    }

    @Test
    fun removeMovieCompletes() {
        removeMovieFromCache(Completable.complete())
        val testObserver = store.removeMovieFromShortlist(DataFactory.randomInt()).test()
        testObserver.assertComplete()
    }

    @Test
    fun removeMovieCallsCache() {
        removeMovieFromCache(Completable.complete())
        val movieId = DataFactory.randomInt()
        store.removeMovieFromShortlist(movieId).test()
        verify(cache).removeMovieFromShortlist(movieId)
    }

    private fun getMoviesFromCache(observable: Observable<List<MovieEntity>>) {
        whenever(cache.getMovies()).thenReturn(observable)
    }

    private fun getShortlistedMoviesFromCache(observable: Observable<List<MovieEntity>>) {
        whenever(cache.getShortlistedMovies()).thenReturn(observable)
    }

    private fun removeMovieFromCache(completable: Completable) {
        whenever(cache.removeMovieFromShortlist(any())).thenReturn(completable)
    }

    private fun shortlistMovieInCache(completable: Completable) {
        whenever(cache.setMovieAsShortlisted(any())).thenReturn(completable)
    }

    private fun saveMovies(completable: Completable) {
        whenever(cache.saveMovies(any())).thenReturn(completable)
    }

    private fun clearMovies(completable: Completable) {
        whenever(cache.clearMovies()).thenReturn(completable)
    }

    private fun setLastCacheTime(completable: Completable) {
        whenever(cache.setLastCacheTime(any())).thenReturn(completable)
    }
}