package com.github.abhrp.mobile_ui.mapper

import com.github.abhrp.mobile_ui.model.Movie
import com.github.abhrp.presentation.model.MovieView
import javax.inject.Inject

class MovieMapper @Inject constructor() : ViewMapper<MovieView, Movie> {
    override fun mapToView(presentationModel: MovieView): Movie {
        return Movie(presentationModel.posterPath, presentationModel.adult, presentationModel.overview, presentationModel.releaseDate, presentationModel.genreIds, presentationModel.id, presentationModel.originalTitle, presentationModel.originalLanguage, presentationModel.title, presentationModel.backDropPath, presentationModel.popularity, presentationModel.voteCount, presentationModel.video, presentationModel.voteAverage, presentationModel.isShortlisted)
    }
}