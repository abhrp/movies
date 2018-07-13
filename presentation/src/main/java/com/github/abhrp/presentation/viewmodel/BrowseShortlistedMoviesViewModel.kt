package com.github.abhrp.presentation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.github.abhrp.domain.interactor.shortlist.GetShortlistedMovies
import com.github.abhrp.domain.model.Movie
import com.github.abhrp.presentation.mapper.MovieViewMapper
import com.github.abhrp.presentation.model.MovieView
import com.github.abhrp.presentation.state.Resource
import com.github.abhrp.presentation.state.ResourceState
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class BrowseShortlistedMoviesViewModel @Inject constructor(
        private val getShortlistedMovies: GetShortlistedMovies,
        private val mapper: MovieViewMapper) : ViewModel() {

    private val liveData: MutableLiveData<Resource<List<MovieView>>> = MutableLiveData()

    override fun onCleared() {
        super.onCleared()
        getShortlistedMovies.disposeAll()
    }

    fun getShortListedMovies(): LiveData<Resource<List<MovieView>>> {
        return liveData
    }

    fun fetchShortlistedMovies() {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        return getShortlistedMovies.execute(MoviesSubscriber())
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

}