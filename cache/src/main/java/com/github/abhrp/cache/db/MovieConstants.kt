package com.github.abhrp.cache.db

object MovieConstants {
    const val TABLE_NAME = "movie"
    const val SHORTLISTED_COLUMN = "is_shortlisted"
    const val MOVIE_ID = "movie_id"
    const val QUERY_SELECT_ALL = "select * from $TABLE_NAME"
    const val QUERY_SELECT_MOVIE = "select * from $TABLE_NAME where $MOVIE_ID = :movieId"
    const val QUERY_SELECT_SHORTLISTED_MOVIES = "select * from $TABLE_NAME where $SHORTLISTED_COLUMN = 1"
    const val QUERY_DELETE_MOVIE = "delete from $TABLE_NAME where $MOVIE_ID = :movieId"
    const val QUERY_DELETE = "delete from $TABLE_NAME"
    const val UPDATE_SHORTLISTED_MOVIE = "update $TABLE_NAME set $SHORTLISTED_COLUMN = :isSaved where $MOVIE_ID = :movieId"
}