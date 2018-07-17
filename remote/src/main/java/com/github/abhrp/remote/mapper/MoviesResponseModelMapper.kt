package com.github.abhrp.remote.mapper

import com.github.abhrp.data.model.MovieEntity
import com.github.abhrp.remote.constants.MoviesDbConstants
import com.github.abhrp.remote.model.MovieModel
import javax.inject.Inject

class MoviesResponseModelMapper @Inject constructor() : ModelMapper<MovieModel, MovieEntity> {
    override fun mapFromModel(model: MovieModel): MovieEntity {
        return MovieEntity(getFullImagePosterPath(model.posterPath), model.adult, model.overview, model.releaseDate, model.genreIds, model.id, model.originalTitle, model.originalLanguage, model.originalTitle, model.backDropPath, model.popularity, model.voteCount, model.video, model.voteAverage, model.isShortlisted)
    }

    private fun getFullImagePosterPath(posterPath: String?) = MoviesDbConstants.IMAGE_URL + posterPath
}