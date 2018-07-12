package com.github.abhrp.remote.mapper

interface ModelMapper<in M, out E> {
    fun mapFromModel(model: M): E
}