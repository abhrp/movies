package com.github.abhrp.remote.mapper


import com.github.abhrp.remote.test.factory.MovieDataFactory
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class MoviesResponseModelMapperTest {
    private val mapper = MoviesResponseModelMapper()

    @Test
    fun testMapper() {
        val model = MovieDataFactory.makeMovie()
        val entity = mapper.mapFromModel(model)
        assertEquals(model.id, entity.id)
    }
}