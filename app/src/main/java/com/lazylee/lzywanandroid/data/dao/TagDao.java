package com.lazylee.lzywanandroid.data.dao;

import com.lazylee.lzywanandroid.data.entity.Tag;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface TagDao {

    @Query("Select * From tag")
    List<Tag> getAll();

    List<Tag> getTagsByArticle(long articleId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Tag tag);
}
