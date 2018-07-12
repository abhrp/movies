package com.github.abhrp.data.mapper

interface EntityMapper<E, D> {
    fun mapFromEntity(entity: E): D
    fun mapToEntity(domain: D): E
}