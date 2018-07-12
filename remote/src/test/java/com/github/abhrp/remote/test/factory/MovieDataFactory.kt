package com.github.abhrp.remote.test.factory


import com.github.abhrp.data.model.MovieEntity
import com.github.abhrp.remote.model.MovieModel
import com.github.abhrp.remote.model.MoviesResponseModel

object MovieDataFactory {

    fun makeMovie(): MovieModel {
        return MovieModel(DataFactory.randomString(), DataFactory.randomBoolean(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomIntList(4), DataFactory.randomInt(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomDouble(), DataFactory.randomInt(), DataFactory.randomBoolean(), DataFactory.randomDouble(), DataFactory.randomBoolean())
    }

    fun makeMovieEntity(): MovieEntity {
        return MovieEntity(DataFactory.randomString(), DataFactory.randomBoolean(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomIntList(4), DataFactory.randomInt(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomDouble(), DataFactory.randomInt(), DataFactory.randomBoolean(), DataFactory.randomDouble(), DataFactory.randomBoolean())
    }

    fun makeMovieResponse(): MoviesResponseModel {
        return MoviesResponseModel(listOf(makeMovie(), makeMovie(), makeMovie()), DataFactory.randomInt(), DataFactory.randomInt(), DataFactory.randomInt(), DataFactory.randomBoolean(), DataFactory.randomString(), DataFactory.randomInt())
    }
}