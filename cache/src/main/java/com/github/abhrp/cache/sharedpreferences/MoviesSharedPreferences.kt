package com.github.abhrp.cache.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class MoviesSharedPreferences @Inject constructor(private val context: Context) {
    companion object {
        private const val PREF_NAME = "com.github.abhrp.cache.preferences"

        private const val PREF_KEY_LAST_CACHE = "last_cache"
    }

    private val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    var lastCacheTime: Long
        get() = sharedPreferences.getLong(PREF_KEY_LAST_CACHE, 0L)
        set(lastCache) = sharedPreferences.edit().putLong(PREF_KEY_LAST_CACHE, lastCache).apply()
}