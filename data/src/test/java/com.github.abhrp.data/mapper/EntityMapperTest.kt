package com.github.abhrp.data.mapper

import com.github.abhrp.data.factory.MovieFactory
import com.github.abhrp.data.model.MovieEntity
import com.github.abhrp.domain.model.Movie
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class EntityMapperTest {
    val mapper = MovieEntityMapper()

    @Test
    fun testMappingFromEntity() {
        val entity = MovieFactory.makeMovieEntity()
        val model = mapper.mapFromEntity(entity)
        assertEqualData(entity, model)

    }

    private fun assertEqualData(entity: MovieEntity, model: Movie) {
        assertEquals(entity.posterPath, model.posterPath)
        assertEquals(entity.adult, model.adult)
        assertEquals(entity.overview, model.overview)
        assertEquals(entity.releaseDate, model.releaseDate)
    }
}