package com.lazylee.lzywanandroid.data.dao;

import com.lazylee.lzywanandroid.data.entity.SearchHistory;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface SearchHistoryDao {

    @Query("Select * From searchhistory")
    List<SearchHistory> getAll();

    @Update
    void updateSearchHistory(SearchHistory searchHistory);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSearchHistory(SearchHistory searchHistory);
}
