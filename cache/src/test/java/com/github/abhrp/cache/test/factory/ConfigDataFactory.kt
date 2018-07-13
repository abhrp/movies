package com.github.abhrp.cache.test.factory

import com.github.abhrp.cache.model.Config

object ConfigDataFactory {
    fun makeConfig(): Config {
        return Config(DataFactory.randomLong())
    }
}