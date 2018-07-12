package com.github.abhrp.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.github.abhrp.cache.db.ConfigConstants.QUERY_SELECT_ALL
import com.github.abhrp.cache.model.Config
import io.reactivex.Flowable

@Dao
interface ConfigDao {
    @Query(QUERY_SELECT_ALL)
    fun getConfig(): Flowable<Config>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLastCachedTime(config: Config)
}