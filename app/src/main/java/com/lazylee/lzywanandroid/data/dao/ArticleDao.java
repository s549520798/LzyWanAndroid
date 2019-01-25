package com.lazylee.lzywanandroid.data.dao;

import com.lazylee.lzywanandroid.data.entity.Article;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface ArticleDao {

    @Query("Select * From Article")
    List<Article> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertArticle(Article article);
}
