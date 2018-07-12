package com.github.abhrp.cache.mapper

interface CacheMapper<C, E> {
    fun mapToEntity(cacheModel: C): E
    fun mapFromEntity(entity: E): C
}