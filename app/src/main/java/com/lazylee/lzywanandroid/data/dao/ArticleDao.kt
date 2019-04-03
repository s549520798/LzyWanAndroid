package com.lazylee.lzywanandroid.data.dao

import com.lazylee.lzywanandroid.data.entity.Article

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ArticleDao {

    @get:Query("Select * From Article")
    val all: List<Article>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticle(article: Article)
}
