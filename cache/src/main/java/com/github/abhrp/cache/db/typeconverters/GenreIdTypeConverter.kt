package com.github.abhrp.cache.db.typeconverters

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreIdTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun genreIdsListToString(genreIds: List<Int>?): String {
        genreIds?.let {
            return gson.toJson(it)
        }
        return ""
    }

    @TypeConverter
    fun stringToGenreIdsList(genreIds: String?): List<Int> {
        genreIds?.let {
            val listType = object : TypeToken<List<Int>>() {}.type
            return gson.fromJson(it, listType)
        }
        return ArrayList()
    }
}