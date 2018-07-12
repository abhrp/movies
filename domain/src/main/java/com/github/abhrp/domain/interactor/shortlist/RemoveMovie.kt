package com.github.abhrp.domain.interactor.shortlist

import com.github.abhrp.domain.executor.PostExecutionThread
import com.github.abhrp.domain.interactor.CompletableUseCase
import com.github.abhrp.domain.repository.MovieRepository
import io.reactivex.Completable
import javax.inject.Inject

class RemoveMovie @Inject constructor(private val movieRepository: MovieRepository, postExecutionThread: PostExecutionThread) : CompletableUseCase<RemoveMovie.Params>(postExecutionThread) {

    data class Params constructor(val movieId: Int) {
        companion object {
            fun forMovie(movieId: Int): Params = Params(movieId)
        }
    }

    public override fun buildUseCaseCompletable(params: Params?): Completable {
        params?.let {
            return movieRepository.removeMovie(it.movieId)
        }
        throw IllegalArgumentException("Params can't be null!")
    }
}