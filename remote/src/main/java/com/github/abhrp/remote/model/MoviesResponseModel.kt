package com.github.abhrp.remote.model

import com.google.gson.annotations.SerializedName

data class MoviesResponseModel(val results: List<MovieModel>, val page: Int, @SerializedName("total_results") val totalResults: Int, @SerializedName("total_pages") val totalPages: Int, val success: Boolean, @SerializedName("status_message") val statusMessage: String?, @SerializedName("status_code") val statusCode: Int)