package com.github.abhrp.presentation.mapper

import com.github.abhrp.presentation.test.factory.MovieViewFactory
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class MovieViewMapperTest {
    private val mapper = MovieViewMapper()

    @Test
    fun testMapToMovieView() {
        val movie = MovieViewFactory.makeMovieDomainModel()
        val movieView = mapper.mapToView(movie)

        assertEquals(movie.id, movieView.id)
        assertEquals(movie.title, movieView.title)
    }
}