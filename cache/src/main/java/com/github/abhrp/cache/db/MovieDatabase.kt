package com.github.abhrp.cache.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.github.abhrp.cache.dao.CachedMoviesDao
import com.github.abhrp.cache.dao.ConfigDao
import com.github.abhrp.cache.db.typeconverters.GenreIdTypeConverter
import com.github.abhrp.cache.model.CachedMovie
import com.github.abhrp.cache.model.Config

@Database(entities = arrayOf(CachedMovie::class, Config::class), version = 1)
@TypeConverters(GenreIdTypeConverter::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun getCachedMoviesDao(): CachedMoviesDao
    abstract fun getConfigDao(): ConfigDao

    private var INSTANCE: MovieDatabase? = null
    private val lock = Any()

    fun getInstance(context: Context): MovieDatabase {
        if (INSTANCE == null) {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, MovieDatabase::class.java, "movies.db").build()
                }
                return INSTANCE as MovieDatabase
            }
        }
        return INSTANCE as MovieDatabase
    }
}