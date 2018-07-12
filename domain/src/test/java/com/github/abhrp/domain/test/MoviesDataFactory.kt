package com.github.abhrp.domain.test

import com.github.abhrp.domain.model.Movie
import java.util.*

object MoviesDataFactory {
    fun randomString() = UUID.randomUUID().toString()

    fun randomDouble() = Math.random()

    fun randomInt() = (Math.random() * 1000).toInt()

    fun randomBoolean() = Math.random() < 0.5

    fun randomIntList(count: Int): List<Int> {
        val numbers = mutableListOf<Int>()
        repeat(count) {
            numbers.add(randomInt())
        }
        return numbers
    }

    fun makeMovie(): Movie = Movie(randomString(), randomBoolean(), randomString(), randomString(), randomIntList(3), randomInt(), randomString(), randomString(), randomString(), randomString(), randomDouble(), randomInt(), randomBoolean(), randomDouble())

    fun makeMoviesList(count: Int): List<Movie> {
        val movies = mutableListOf<Movie>()
        repeat(count) {
            movies.add(makeMovie())
        }
        return movies
    }

}