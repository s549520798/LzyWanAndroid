package com.lazylee.lzywanandroid.data.dao

import com.lazylee.lzywanandroid.data.entity.Tag

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TagDao {

    @get:Query("Select * From tag")
    val all: List<Tag>

    fun getTagsByArticle(articleId: Long): List<Tag>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tag: Tag)
}
