package com.github.abhrp.presentation.model

data class MovieView(val posterPath: String?, val adult: Boolean, val overview: String, val releaseDate: String, val genreIds: List<Int>,
                     val id: Int, val originalTitle: String, val originalLanguage: String, val title: String, val backDropPath: String?,
                     val popularity: Double, val voteCount: Int, val video: Boolean, val voteAverage: Double, val isShortlisted: Boolean)