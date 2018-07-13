package com.github.abhrp.presentation.test.factory

import com.github.abhrp.domain.model.Movie
import com.github.abhrp.presentation.model.MovieView

object MovieViewFactory {
    fun makeMovieView(): MovieView {
        return MovieView(DataFactory.randomString(), DataFactory.randomBoolean(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomIntList(4), DataFactory.randomInt(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomDouble(), DataFactory.randomInt(), DataFactory.randomBoolean(), DataFactory.randomDouble(), DataFactory.randomBoolean())
    }

    fun makeMovieDomainModel(): Movie {
        return Movie(DataFactory.randomString(), DataFactory.randomBoolean(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomIntList(4), DataFactory.randomInt(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomDouble(), DataFactory.randomInt(), DataFactory.randomBoolean(), DataFactory.randomDouble(), DataFactory.randomBoolean())
    }

    fun makeShortlistedMovieView(): MovieView {
        return MovieView(DataFactory.randomString(), DataFactory.randomBoolean(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomIntList(4), DataFactory.randomInt(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomDouble(), DataFactory.randomInt(), DataFactory.randomBoolean(), DataFactory.randomDouble(), true)
    }

    fun makeShortlistedMovieDomainModel(): Movie {
        return Movie(DataFactory.randomString(), DataFactory.randomBoolean(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomIntList(4), DataFactory.randomInt(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomDouble(), DataFactory.randomInt(), DataFactory.randomBoolean(), DataFactory.randomDouble(), true)
    }

    fun makeMovieViewsList(count: Int): List<MovieView> {
        val moviesList = mutableListOf<MovieView>()
        repeat(count) {
            moviesList.add(makeMovieView())
        }
        return moviesList
    }

    fun makeShortlistedMovieViewsList(count: Int): List<MovieView> {
        val moviesList = mutableListOf<MovieView>()
        repeat(count) {
            moviesList.add(makeShortlistedMovieView())
        }
        return moviesList
    }

    fun makeMoviesList(count: Int): List<Movie> {
        val moviesList = mutableListOf<Movie>()
        repeat(count) {
            moviesList.add(makeMovieDomainModel())
        }
        return moviesList
    }

    fun makeShortlistedMoviesList(count: Int): List<Movie> {
        val moviesList = mutableListOf<Movie>()
        repeat(count) {
            moviesList.add(makeShortlistedMovieDomainModel())
        }
        return moviesList
    }
}