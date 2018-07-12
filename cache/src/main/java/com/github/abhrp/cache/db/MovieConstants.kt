package com.github.abhrp.cache.db

object MovieConstants {
    const val TABLE_NAME = "movie"
    const val SAVED_COLUMN = "is_saved"
    const val MOVIE_ID = "movie_id"
    const val QUERY_SELECT_ALL = "select * from $TABLE_NAME"
    const val QUERY_SELECT_MOVIE = "select * from $TABLE_NAME where $MOVIE_ID = :movieId"
    const val QUERY_SELECT_SAVED_MOVIES = "select * from $TABLE_NAME where $SAVED_COLUMN = 1"
    const val QUERY_DELETE_MOVIE = "delete from $TABLE_NAME where $MOVIE_ID = :movieId"
    const val QUERY_DELETE = "delete from $TABLE_NAME"
    const val UPDATE_SAVED_MOVIE = "update $TABLE_NAME set $SAVED_COLUMN = :isSaved where $MOVIE_ID = :movieId"
}