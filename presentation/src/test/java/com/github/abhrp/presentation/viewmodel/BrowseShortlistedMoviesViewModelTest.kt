package com.github.abhrp.presentation.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.abhrp.domain.interactor.shortlist.GetShortlistedMovies
import com.github.abhrp.domain.model.Movie
import com.github.abhrp.presentation.mapper.MovieViewMapper
import com.github.abhrp.presentation.model.MovieView
import com.github.abhrp.presentation.state.ResourceState
import com.github.abhrp.presentation.test.factory.DataFactory
import com.github.abhrp.presentation.test.factory.MovieViewFactory
import com.nhaarman.mockito_kotlin.*
import io.reactivex.observers.DisposableObserver
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Captor
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class BrowseShortlistedMoviesViewModelTest {
    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val getShortlistedMovies = mock<GetShortlistedMovies>()
    private val mapper = mock<MovieViewMapper>()

    private val viewmodel = BrowseShortlistedMoviesViewModel(getShortlistedMovies, mapper)

    @Captor
    private val captor = argumentCaptor<DisposableObserver<List<Movie>>>()

    @Test
    fun fetchShortlistedMoviesExecutesUseCase() {
        viewmodel.fetchShortlistedMovies()
        verify(getShortlistedMovies, times(1)).execute(any(), eq(null))
    }

    @Test
    fun fetchShortlistedMoviesReturnsSuccess() {
        val movies = MovieViewFactory.makeShortlistedMoviesList(2)
        val movieViews = MovieViewFactory.makeShortlistedMovieViewsList(2)

        stubProjectToProjectViewMapper(movies[0], movieViews[0])
        stubProjectToProjectViewMapper(movies[1], movieViews[1])

        viewmodel.fetchShortlistedMovies()
        verify(getShortlistedMovies).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(movies)

        assertEquals(ResourceState.SUCCESS, viewmodel.getShortListedMovies().value?.status)
    }

    @Test
    fun fetchShortlistedMoviesReturnsData() {
        val movies = MovieViewFactory.makeShortlistedMoviesList(2)
        val movieViews = MovieViewFactory.makeShortlistedMovieViewsList(2)

        stubProjectToProjectViewMapper(movies[0], movieViews[0])
        stubProjectToProjectViewMapper(movies[1], movieViews[1])

        viewmodel.fetchShortlistedMovies()
        verify(getShortlistedMovies).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(movies)

        assertEquals(movieViews, viewmodel.getShortListedMovies().value?.data)
    }

    @Test
    fun fetchShortlistedMoviesReturnsError() {
        viewmodel.fetchShortlistedMovies()
        verify(getShortlistedMovies).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException())

        assertEquals(ResourceState.ERROR, viewmodel.getShortListedMovies().value?.status)
    }

    @Test
    fun fetchShortlistedMoviesReturnsMessageForError() {
        val message = DataFactory.randomString()
        viewmodel.fetchShortlistedMovies()
        verify(getShortlistedMovies).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException(message))

        assertEquals(message, viewmodel.getShortListedMovies().value?.error)
    }


    private fun stubProjectToProjectViewMapper(movie: Movie, movieView: MovieView) {
        whenever(mapper.mapToView(movie))
                .thenReturn(movieView)
    }
}