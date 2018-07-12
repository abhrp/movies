package com.github.abhrp.domain.interactor.list

import com.github.abhrp.domain.executor.PostExecutionThread
import com.github.abhrp.domain.model.Movie
import com.github.abhrp.domain.repository.MovieRepository
import com.github.abhrp.domain.test.MoviesDataFactory
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


class GetMoviesTest {

    private lateinit var getMovies: GetMovies
    @Mock
    lateinit var movieRepository: MovieRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getMovies = GetMovies(movieRepository, postExecutionThread)
    }

    @Test
    fun getMoviesCompletes() {
        stubGetMovies(Observable.just(MoviesDataFactory.makeMoviesList(3)))
        val testObserver = getMovies.buildUseCaseObservable().test()
        testObserver.assertComplete()
    }

    @Test
    fun getMoviesReturnsData() {
        val movies = MoviesDataFactory.makeMoviesList(3)
        stubGetMovies(Observable.just(movies))
        val testObserver = getMovies.buildUseCaseObservable().test()
        testObserver.assertValue(movies)
    }

    private fun stubGetMovies(observable: Observable<List<Movie>>) {
        whenever(movieRepository.getMovies())
                .thenReturn(observable)
    }
}