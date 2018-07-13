package com.github.abhrp.presentation.mapper

import com.github.abhrp.domain.model.Movie
import com.github.abhrp.presentation.model.MovieView
import javax.inject.Inject

class MovieViewMapper @Inject constructor() : Mapper<Movie, MovieView> {

    override fun mapToView(domain: Movie): MovieView {
        return MovieView(domain.posterPath, domain.adult, domain.overview, domain.releaseDate, domain.genreIds, domain.id, domain.originalTitle, domain.originalLanguage, domain.title, domain.backDropPath, domain.popularity, domain.voteCount, domain.video, domain.voteAverage, domain.isShortlisted)
    }
}