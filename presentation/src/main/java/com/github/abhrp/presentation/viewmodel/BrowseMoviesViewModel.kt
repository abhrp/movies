package com.github.abhrp.presentation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.github.abhrp.domain.interactor.list.GetMovies
import com.github.abhrp.domain.interactor.shortlist.RemoveMovie
import com.github.abhrp.domain.interactor.shortlist.ShortlistMovie
import com.github.abhrp.domain.model.Movie
import com.github.abhrp.presentation.mapper.MovieViewMapper
import com.github.abhrp.presentation.model.MovieView
import com.github.abhrp.presentation.state.Resource
import com.github.abhrp.presentation.state.ResourceState
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver

import javax.inject.Inject

class BrowseMoviesViewModel @Inject constructor(
        private val getMovies: GetMovies,
        private val shortlistMovie: ShortlistMovie,
        private val removeMovie: RemoveMovie,
        private val mapper: MovieViewMapper) : ViewModel() {

    private val liveData: MutableLiveData<Resource<List<MovieView>>> = MutableLiveData()

    override fun onCleared() {
        getMovies.disposeAll()
        super.onCleared()
    }

    fun getMovies(): LiveData<Resource<List<MovieView>>> {
        return liveData
    }

    fun fetchMovies() {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        return getMovies.execute(MoviesSubscriber())
    }

    fun shortlistMovie(movieId: Int) {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        return shortlistMovie.execute(ShorlistMovieSubscriber(), ShortlistMovie.Params.forMovie(movieId))
    }

    fun removeMovie(movieId: Int) {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        return removeMovie.execute(ShorlistMovieSubscriber(), RemoveMovie.Params.forMovie(movieId))
    }

    inner class MoviesSubscriber : DisposableObserver<List<Movie>>() {
        override fun onComplete() {

        }

        override fun onNext(t: List<Movie>) {
            liveData.postValue(Resource(ResourceState.SUCCESS, t.map { mapper.mapToView(it) }, null))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }
    }

    inner class ShorlistMovieSubscriber : DisposableCompletableObserver() {
        override fun onComplete() {
            liveData.postValue(Resource(ResourceState.SUCCESS, liveData.value?.data, null))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, liveData.value?.data, e.localizedMessage))
        }
    }
}