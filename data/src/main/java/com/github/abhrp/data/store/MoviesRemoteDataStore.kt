package com.github.abhrp.data.store

import com.github.abhrp.data.model.MovieEntity
import com.github.abhrp.data.repository.MoviesDataStore
import com.github.abhrp.data.repository.MoviesRemote
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class MoviesRemoteDataStore @Inject constructor(private val moviesRemote: MoviesRemote) : MoviesDataStore {

    override fun getMovies(): Observable<List<MovieEntity>> = moviesRemote.getMovies()

    override fun getShortlistedMovies(): Observable<List<MovieEntity>> = throw UnsupportedOperationException("Getting shortlisted movies from remote is not supported...")

    override fun setMovieAsShortlisted(movieId: Int): Completable = throw UnsupportedOperationException("Shortlisting movie on remote is not supported...")

    override fun removeMovieFromShortlist(movieId: Int): Completable = throw UnsupportedOperationException("Removing shortlisted movie from remote is not supported...")

    override fun clearMovies(): Completable = throw UnsupportedOperationException("Clearing movies from remote is not supported...")

    override fun saveMovies(movies: List<MovieEntity>): Completable = throw UnsupportedOperationException("Saving movies on remote is not supported...")
}