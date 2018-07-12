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

class RemoveShortlistMovieTest {

    lateinit var removeMovie: RemoveMovie
    @Mock
    lateinit var movieRepository: MovieRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        removeMovie = RemoveMovie(movieRepository, postExecutionThread)
    }

    private fun stubShortlistMovie(completable: Completable) {
        whenever(movieRepository.shortListMovie(any()))
                .thenReturn(completable)
    }

    @Test
    fun removeShortListedMovieCompletes() {
        stubShortlistMovie(Completable.complete())
        val testObserver = removeMovie.buildUseCaseCompletable(RemoveMovie.Params.forMovie(MoviesDataFactory.randomInt())).test()
        testObserver.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun removeShortListedMovieThrowsException() {
        removeMovie.buildUseCaseCompletable().test()
    }


}