package com.github.abhrp.data.mapper

import com.github.abhrp.data.model.MovieEntity
import com.github.abhrp.domain.model.Movie
import javax.inject.Inject

class MovieEntityMapper @Inject constructor() : EntityMapper<MovieEntity, Movie> {

    override fun mapFromEntity(entity: MovieEntity): Movie =
            Movie(entity.posterPath, entity.adult, entity.overview, entity.releaseDate, entity.genreIds, entity.id, entity.originalTitle, entity.originalLanguage, entity.originalTitle, entity.backDropPath, entity.popularity, entity.voteCount, entity.video, entity.voteAverage)

    override fun mapToEntity(domain: Movie): MovieEntity =
            MovieEntity(domain.posterPath, domain.adult, domain.overview, domain.releaseDate, domain.genreIds, domain.id, domain.originalTitle, domain.originalLanguage, domain.originalTitle, domain.backDropPath, domain.popularity, domain.voteCount, domain.video, domain.voteAverage)

}