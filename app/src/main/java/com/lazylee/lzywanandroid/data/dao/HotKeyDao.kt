package com.lazylee.lzywanandroid.data.dao

import com.lazylee.lzywanandroid.data.entity.HotKey

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HotKeyDao {

    @get:Query("Select * From HotKey")
    val all: List<HotKey>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHotKeys(vararg hotKeys: HotKey)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHotKey(hotKey: HotKey)
}
