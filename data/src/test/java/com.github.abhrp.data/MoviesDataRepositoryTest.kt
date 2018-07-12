package com.github.abhrp.data

import com.github.abhrp.data.factory.MovieFactory
import com.github.abhrp.data.mapper.MovieEntityMapper
import com.github.abhrp.data.model.MovieEntity
import com.github.abhrp.data.repository.MoviesCache
import com.github.abhrp.data.repository.MoviesDataStore
import com.github.abhrp.data.store.MoviesDataStoreFactory
import com.github.abhrp.domain.model.Movie
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class MoviesDataRepositoryTest {
    private val mapper = mock<MovieEntityMapper>()
    private val factory = mock<MoviesDataStoreFactory>()
    private val cache = mock<MoviesCache>()
    private val store = mock<MoviesDataStore>()
    private val repository = MoviesDataRepository(cache, factory, mapper)

    @Before
    fun setup() {
        stubFactoryGetDataStore()
        stubFactoryGetCacheDataStore()
        stubCacheAreMoviesCached(Single.just(false))
        stubCacheIsExpired(Single.just(false))
        saveMoviesToCache(Completable.complete())
    }

    @Test
    fun getMoviesComplete() {
        stubGetMovies(Observable.just(listOf(MovieFactory.makeMovieEntity(), MovieFactory.makeMovieEntity(), MovieFactory.makeMovieEntity())))
        stubMapper(MovieFactory.makeMovie(), any())

        val testObserver = repository.getMovies().test()
        testObserver.assertComplete()
    }

    @Test
    fun getShortListedMoviesComplete() {
        stubGetShortlistedMovies(Observable.just(listOf(MovieFactory.makeMovieEntity(), MovieFactory.makeMovieEntity(), MovieFactory.makeMovieEntity())))
        stubMapper(MovieFactory.makeMovie(), any())

        val testObserver = repository.getShortListedMovies().test()
        testObserver.assertComplete()
    }

    @Test
    fun getMoviesReturnsData() {
        val movieEntity = MovieFactory.makeMovieEntity()
        val movie = MovieFactory.makeMovie()

        stubGetMovies(Observable.just(listOf(movieEntity)))
        stubMapper(movie, movieEntity)

        val testObserver = repository.getMovies().test()
        testObserver.assertValue(listOf(movie))
    }

    @Test
    fun getShortlistedMoviesReturnsData() {
        val movieEntity = MovieFactory.makeMovieEntity()
        val movie = MovieFactory.makeMovie()

        stubGetShortlistedMovies(Observable.just(listOf(movieEntity)))
        stubMapper(movie, movieEntity)

        val testObserver = repository.getShortListedMovies().test()
        testObserver.assertValue(listOf(movie))
    }

    @Test
    fun shortlistMovie() {
        stubShortlistMovie(Completable.complete())

        val observer = cache.setMovieAsShortlisted(any()).test()
        observer.assertComplete()
    }

    @Test
    fun removeMovie() {
        stubRemoveMovie(Completable.complete())

        val observer = cache.removeMovieFromShortlist(any()).test()
        observer.assertComplete()
    }


    private fun stubMapper(model: Movie, entity: MovieEntity) {
        whenever(mapper.mapFromEntity(entity))
                .thenReturn(model)
    }

    private fun stubGetMovies(observable: Observable<List<MovieEntity>>) {
        whenever(store.getMovies())
                .thenReturn(observable)
    }

    private fun stubGetShortlistedMovies(observable: Observable<List<MovieEntity>>) {
        whenever(store.getShortlistedMovies())
                .thenReturn(observable)
    }

    private fun stubFactoryGetDataStore() {
        whenever(factory.getDataStore(any(), any()))
                .thenReturn(store)
    }

    private fun stubCacheIsExpired(expired: Single<Boolean>) {
        whenever(cache.isCacheExpired())
                .thenReturn(expired)
    }

    private fun stubCacheAreMoviesCached(cached: Single<Boolean>) {
        whenever(cache.areMoviesCached())
                .thenReturn(cached)
    }

    private fun stubFactoryGetCacheDataStore() {
        whenever(factory.getCacheDataStore())
                .thenReturn(store)
    }

    private fun saveMoviesToCache(completable: Completable) {
        whenever(store.saveMovies(any()))
                .thenReturn(completable)
    }

    private fun stubShortlistMovie(completable: Completable) {
        whenever(cache.setMovieAsShortlisted(any()))
                .thenReturn(completable)
    }

    private fun stubRemoveMovie(completable: Completable) {
        whenever(cache.removeMovieFromShortlist(any()))
                .thenReturn(completable)
    }
}