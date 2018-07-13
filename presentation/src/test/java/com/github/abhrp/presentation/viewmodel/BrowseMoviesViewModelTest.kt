package com.github.abhrp.presentation.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.abhrp.domain.interactor.list.GetMovies
import com.github.abhrp.domain.interactor.shortlist.RemoveMovie
import com.github.abhrp.domain.interactor.shortlist.ShortlistMovie
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
class BrowseMoviesViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val getMovies = mock<GetMovies>()
    private val shortlistMovie = mock<ShortlistMovie>()
    private val removeMovie = mock<RemoveMovie>()
    private val mapper = mock<MovieViewMapper>()

    private val viewmodel = BrowseMoviesViewModel(getMovies, shortlistMovie, removeMovie, mapper)

    @Captor
    private val captor = argumentCaptor<DisposableObserver<List<Movie>>>()

    @Test
    fun fetchMoviesExecutesUseCase() {
        viewmodel.fetchMovies()
        verify(getMovies, times(1)).execute(any(), eq(null))
    }

    @Test
    fun fetchMoviesReturnsSuccess() {
        val movies = MovieViewFactory.makeMoviesList(2)
        val movieViews = MovieViewFactory.makeMovieViewsList(2)

        stubProjectToProjectViewMapper(movies[0], movieViews[0])
        stubProjectToProjectViewMapper(movies[1], movieViews[1])

        viewmodel.fetchMovies()
        verify(getMovies).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(movies)

        assertEquals(ResourceState.SUCCESS, viewmodel.getMovies().value?.status)
    }

    @Test
    fun fetchMoviesReturnsData() {
        val movies = MovieViewFactory.makeMoviesList(2)
        val movieViews = MovieViewFactory.makeMovieViewsList(2)

        stubProjectToProjectViewMapper(movies[0], movieViews[0])
        stubProjectToProjectViewMapper(movies[1], movieViews[1])

        viewmodel.fetchMovies()
        verify(getMovies).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(movies)

        assertEquals(movieViews, viewmodel.getMovies().value?.data)
    }

    @Test
    fun fetchMoviesReturnsError() {
        viewmodel.fetchMovies()
        verify(getMovies).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException())

        assertEquals(ResourceState.ERROR, viewmodel.getMovies().value?.status)
    }

    @Test
    fun fetchMoviesReturnsMessageForError() {
        val message = DataFactory.randomString()
        viewmodel.fetchMovies()
        verify(getMovies).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException(message))

        assertEquals(message, viewmodel.getMovies().value?.error)
    }


    private fun stubProjectToProjectViewMapper(movie: Movie, movieView: MovieView) {
        whenever(mapper.mapToView(movie))
                .thenReturn(movieView)
    }

}