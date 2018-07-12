package com.github.abhrp.remote.mapper

import com.github.abhrp.data.model.MovieEntity
import com.github.abhrp.remote.model.MovieModel

class MoviesResponseModelMapper : ModelMapper<MovieModel, MovieEntity> {
    override fun mapFromModel(model: MovieModel): MovieEntity {
        return MovieEntity(model.posterPath, model.adult, model.overview, model.releaseDate, model.genreIds, model.id, model.originalTitle, model.originalLanguage, model.originalTitle, model.backDropPath, model.popularity, model.voteCount, model.video, model.voteAverage, model.isSaved)
    }
}