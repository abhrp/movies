package com.github.abhrp.cache.test.factory

import com.github.abhrp.cache.model.CachedMovie
import com.github.abhrp.data.model.MovieEntity

object MovieDataFactory {
    fun makeCachedMovie(): CachedMovie {
        return CachedMovie(DataFactory.randomInt(), DataFactory.randomString(), DataFactory.randomBoolean(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomIntList(4), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomDouble(), DataFactory.randomInt(), DataFactory.randomBoolean(), DataFactory.randomDouble(), false)
    }

    fun makeShortlistedCachedMovie(): CachedMovie {
        return CachedMovie(DataFactory.randomInt(), DataFactory.randomString(), DataFactory.randomBoolean(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomIntList(4), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomDouble(), DataFactory.randomInt(), DataFactory.randomBoolean(), DataFactory.randomDouble(), true)
    }

    fun makeMovieEntity(): MovieEntity {
        return MovieEntity(DataFactory.randomString(), DataFactory.randomBoolean(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomIntList(4), DataFactory.randomInt(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomDouble(), DataFactory.randomInt(), DataFactory.randomBoolean(), DataFactory.randomDouble(), false)
    }

    fun makeShortlistedMovieEntity(): MovieEntity {
        return MovieEntity(DataFactory.randomString(), DataFactory.randomBoolean(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomIntList(4), DataFactory.randomInt(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomDouble(), DataFactory.randomInt(), DataFactory.randomBoolean(), DataFactory.randomDouble(), true)
    }
}