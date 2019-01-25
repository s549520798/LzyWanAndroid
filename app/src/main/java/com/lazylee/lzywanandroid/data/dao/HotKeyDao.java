package com.lazylee.lzywanandroid.data.dao;

import com.lazylee.lzywanandroid.data.entity.HotKey;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface HotKeyDao {

    @Query("Select * From HotKey")
    List<HotKey> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertHotKeys(HotKey... hotKeys);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertHotKey(HotKey hotKey);
}
