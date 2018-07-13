package com.github.abhrp.cache.mapper

import com.github.abhrp.cache.model.CachedMovie
import com.github.abhrp.cache.test.factory.MovieDataFactory
import com.github.abhrp.data.model.MovieEntity
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class MoviesCacheMapperTest {
    private val cachedMovieMapper = CachedMovieMapper()

    @Test
    fun testMapToEntity() {
        val cacheModel = MovieDataFactory.makeCachedMovie()
        val entity = cachedMovieMapper.mapToEntity(cacheModel)
        assertEqualData(cacheModel, entity)
    }

    @Test
    fun testMapFromEntity() {
        val entity = MovieDataFactory.makeMovieEntity()
        val cacheModel = cachedMovieMapper.mapFromEntity(entity)
        assertEqualData(cacheModel, entity)
    }

    private fun assertEqualData(cacheModel: CachedMovie, entity: MovieEntity) {
        assertEquals(cacheModel.id, entity.id)
        assertEquals(cacheModel.title, entity.title)
    }
}