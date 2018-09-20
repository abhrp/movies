package com.github.abhrp.remote.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MoviesResponseModel(@Json(name = "results") val results: List<MovieModel>, val page: Int, @Json(name = "total_results") val totalResults: Int, @Json(name = "total_pages") val totalPages: Int, val success: Boolean?, @Json(name = "status_message") val statusMessage: String?, @Json(name = "status_code") val statusCode: Int?)