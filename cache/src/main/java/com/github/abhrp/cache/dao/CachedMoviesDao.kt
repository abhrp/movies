package com.github.abhrp.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.github.abhrp.cache.db.MovieConstants.QUERY_DELETE
import com.github.abhrp.cache.db.MovieConstants.QUERY_DELETE_MOVIE
import com.github.abhrp.cache.db.MovieConstants.QUERY_SELECT_ALL
import com.github.abhrp.cache.db.MovieConstants.QUERY_SELECT_MOVIE
import com.github.abhrp.cache.db.MovieConstants.QUERY_SELECT_SHORTLISTED_MOVIES
import com.github.abhrp.cache.db.MovieConstants.UPDATE_SHORTLISTED_MOVIE
import com.github.abhrp.cache.model.CachedMovie
import io.reactivex.Flowable

@Dao
interface CachedMoviesDao {

    @Query(QUERY_SELECT_ALL)
    fun getAllMovies(): Flowable<List<CachedMovie>>

    @Query(QUERY_SELECT_MOVIE)
    fun getMovie(movieId: Int): Flowable<CachedMovie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<CachedMovie>)

    @Query(QUERY_DELETE)
    fun deleteAll()

    @Query(QUERY_DELETE_MOVIE)
    fun deleteMovie(movieId: Int)

    @Query(QUERY_SELECT_SHORTLISTED_MOVIES)
    fun getShortlistedMovies(): Flowable<List<CachedMovie>>

    @Query(UPDATE_SHORTLISTED_MOVIE)
    fun setShortlistedMovie(isSaved: Boolean, movieId: Int)
}