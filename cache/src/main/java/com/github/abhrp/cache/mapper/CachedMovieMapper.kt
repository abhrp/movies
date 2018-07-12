package com.github.abhrp.cache.mapper

import com.github.abhrp.cache.model.CachedMovie
import com.github.abhrp.data.model.MovieEntity

class CachedMovieMapper : CacheMapper<CachedMovie, MovieEntity> {

    override fun mapToEntity(cacheModel: CachedMovie): MovieEntity {
        return MovieEntity(cacheModel.posterPath, cacheModel.adult, cacheModel.overview, cacheModel.releaseDate, cacheModel.genreIds, cacheModel.id, cacheModel.originalTitle, cacheModel.originalLanguage, cacheModel.originalTitle, cacheModel.backDropPath, cacheModel.popularity, cacheModel.voteCount, cacheModel.video, cacheModel.voteAverage, cacheModel.isSaved)
    }

    override fun mapFromEntity(entity: MovieEntity): CachedMovie {
        return CachedMovie(entity.id, entity.posterPath, entity.adult, entity.overview, entity.releaseDate, entity.genreIds, entity.originalTitle, entity.originalLanguage, entity.originalTitle, entity.backDropPath, entity.popularity, entity.voteCount, entity.video, entity.voteAverage, entity.isSaved)
    }
}