package com.github.abhrp.data.factory

import com.github.abhrp.data.model.MovieEntity
import com.github.abhrp.domain.model.Movie

object MovieFactory {
    fun makeMovieEntity(): MovieEntity = MovieEntity(DataFactory.randomString(), DataFactory.randomBoolean(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomIntList(4), DataFactory.randomInt(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomDouble(), DataFactory.randomInt(), DataFactory.randomBoolean(), DataFactory.randomDouble())
    fun makeMovie(): Movie = Movie(DataFactory.randomString(), DataFactory.randomBoolean(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomIntList(4), DataFactory.randomInt(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomDouble(), DataFactory.randomInt(), DataFactory.randomBoolean(), DataFactory.randomDouble())
}