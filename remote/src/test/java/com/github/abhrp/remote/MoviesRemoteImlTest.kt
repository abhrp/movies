package com.github.abhrp.remote

import com.github.abhrp.data.model.MovieEntity
import com.github.abhrp.remote.mapper.MoviesResponseModelMapper
import com.github.abhrp.remote.model.MovieModel
import com.github.abhrp.remote.model.MoviesResponseModel
import com.github.abhrp.remote.service.MovieDbApiService
import com.github.abhrp.remote.test.factory.MovieDataFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MoviesRemoteImlTest {

    private val mapper = mock<MoviesResponseModelMapper>()
    private val service = mock<MovieDbApiService>()

    private val remoteImpl = MoviesRemoteImpl(service, mapper)

    @Test
    fun getMoviesCompletes() {
        stubMovieResponse(Observable.just(MovieDataFactory.makeMovieResponse()))
        stubMapper(any(), MovieDataFactory.makeMovieEntity())

        val testObserver = remoteImpl.getMovies().test()
        testObserver.assertComplete()
    }

    @Test
    fun getMoviesApiCalled() {
        stubMovieResponse(Observable.just(MovieDataFactory.makeMovieResponse()))
        stubMapper(any(), MovieDataFactory.makeMovieEntity())
        remoteImpl.getMovies().test()
        verify(service).getPopularMovies()
    }

    @Test
    fun getMoviesReturnsData() {
        val response = MovieDataFactory.makeMovieResponse()
        stubMovieResponse(Observable.just(response))
        val entities = mutableListOf<MovieEntity>()
        response.results.forEach {
            val entity = MovieDataFactory.makeMovieEntity()
            entities.add(entity)
            stubMapper(it, entity)
        }
        val testObserver = remoteImpl.getMovies().test()
        testObserver.assertValue(entities)
    }

    private fun stubMovieResponse(observable: Observable<MoviesResponseModel>) {
        whenever(service.getPopularMovies()).thenReturn(observable)
    }

    private fun stubMapper(model: MovieModel, movieEntity: MovieEntity) {
        whenever(mapper.mapFromModel(model)).thenReturn(movieEntity)
    }

}