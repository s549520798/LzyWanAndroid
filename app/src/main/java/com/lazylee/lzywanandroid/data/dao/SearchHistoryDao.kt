package com.lazylee.lzywanandroid.data.dao

import com.lazylee.lzywanandroid.data.entity.SearchHistory

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface SearchHistoryDao {

    @get:Query("Select * From searchhistory")
    val all: List<SearchHistory>

    @Update
    fun updateSearchHistory(searchHistory: SearchHistory)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSearchHistory(searchHistory: SearchHistory)
}
