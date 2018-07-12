package com.github.abhrp.domain.interactor.shortlist

import com.github.abhrp.domain.executor.PostExecutionThread
import com.github.abhrp.domain.repository.MovieRepository
import com.github.abhrp.domain.test.MoviesDataFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ShortlistMovieTest {

    lateinit var shortlistMovie: ShortlistMovie
    @Mock
    lateinit var movieRepository: MovieRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        shortlistMovie = ShortlistMovie(movieRepository, postExecutionThread)
    }

    private fun stubShortlistMovie(completable: Completable) {
        whenever(movieRepository.shortListMovie(any()))
                .thenReturn(completable)
    }

    @Test
    fun shortListMovieCompletes() {
        stubShortlistMovie(Completable.complete())
        val testObserver = shortlistMovie.buildUseCaseCompletable(ShortlistMovie.Params.forMovie(MoviesDataFactory.randomInt())).test()
        testObserver.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun shortListMovieThrowsException() {
        shortlistMovie.buildUseCaseCompletable().test()
    }


}