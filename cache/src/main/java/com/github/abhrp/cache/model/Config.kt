package com.github.abhrp.cache.model

import android.arch.persistence.room.Entity
import com.github.abhrp.cache.db.ConfigConstants.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class Config(var lastCachedTime: Long)