package com.github.abhrp.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieModel(@Json(name = "poster_path") val posterPath: String?, @Json(name = "adult") val adult: Boolean, @Json(name = "overview") val overview: String, @Json(name = "release_date") val releaseDate: String, @Json(name = "genre_ids") val genreIds: List<Int>,
                      val id: Int, @Json(name = "original_title") val originalTitle: String = "", @Json(name = "original_language") val originalLanguage: String, val title: String, @Json(name = "backdrop_path") val backDropPath: String?,
                      val popularity: Double, @Json(name = "vote_count") val voteCount: Int, val video: Boolean, @Json(name = "vote_average") val voteAverage: Double, val isShortlisted: Boolean?)