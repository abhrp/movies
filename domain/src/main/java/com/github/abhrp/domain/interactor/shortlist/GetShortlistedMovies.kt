package com.github.abhrp.domain.interactor.shortlist

import com.github.abhrp.domain.executor.PostExecutionThread
import com.github.abhrp.domain.interactor.ObservableUseCase
import com.github.abhrp.domain.model.Movie
import com.github.abhrp.domain.repository.MovieRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetShortlistedMovies @Inject constructor(private val movieRepository: MovieRepository,
                                               postExecutionThread: PostExecutionThread) :
        ObservableUseCase<List<Movie>, Nothing>(postExecutionThread) {

    public override fun buildUseCaseObservable(params: Nothing?): Observable<List<Movie>> {
        return movieRepository.getMovies()
    }
}