package com.github.abhrp.cache.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.github.abhrp.cache.db.MovieConstants
import com.github.abhrp.cache.db.MovieConstants.MOVIE_ID
import com.github.abhrp.cache.db.MovieConstants.SHORTLISTED_COLUMN

@Entity(tableName = MovieConstants.TABLE_NAME)
data class CachedMovie(
        @PrimaryKey
        @ColumnInfo(name = MOVIE_ID)
        var id: Int,
        var posterPath: String?,
        var adult: Boolean,
        var overview: String,
        var releaseDate: String,
        var genreIds: List<Int>,
        var originalTitle: String,
        var originalLanguage: String,
        var title: String,
        var backDropPath: String?,
        var popularity: Double,
        var voteCount: Int,
        var video: Boolean,
        var voteAverage: Double,
        @ColumnInfo(name = SHORTLISTED_COLUMN)
        var isShortlisted: Boolean
)