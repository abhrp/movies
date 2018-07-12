package com.github.abhrp.domain.response

import com.github.abhrp.domain.model.Movie

data class MovieListResponse(val results: List<Movie>, val page: Int, val totalResults: Int, val totalPages: Int, val success: Boolean, val statusMessage: String?, val statusCode: Int)