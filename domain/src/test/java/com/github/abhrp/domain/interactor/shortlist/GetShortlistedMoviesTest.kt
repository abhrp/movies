package com.github.abhrp.domain.interactor.shortlist

import com.github.abhrp.domain.executor.PostExecutionThread
import com.github.abhrp.domain.model.Movie
import com.github.abhrp.domain.repository.MovieRepository
import com.github.abhrp.domain.test.MoviesDataFactory
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class GetShortlistedMoviesTest {
    private lateinit var getShortlistedMovies: GetShortlistedMovies

    @Mock
    lateinit var movieRepository: MovieRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getShortlistedMovies = GetShortlistedMovies(movieRepository, postExecutionThread)
    }

    private fun stubGetMovies(observable: Observable<List<Movie>>) {
        whenever(movieRepository.getMovies())
                .thenReturn(observable)
    }

    @Test
    fun getMoviesFromShorlistCompleted() {
        stubGetMovies(Observable.just(MoviesDataFactory.makeMoviesList(3)))
        val testObserver = getShortlistedMovies.buildUseCaseObservable().test()
        testObserver.assertComplete()
    }

    @Test
    fun getShorlistedMoviesReturnsData() {
        val movies = MoviesDataFactory.makeMoviesList(3)
        stubGetMovies(Observable.just(movies))
        val testObserver = getShortlistedMovies.buildUseCaseObservable().test()
        testObserver.assertValue(movies)
    }
}