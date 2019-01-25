package com.lazylee.lzywanandroid.data.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;

@Entity(primaryKeys = {"key"})
public class SearchHistory {

    @NonNull
    private String key = "";
    private Long date;

    @Ignore
    public SearchHistory(@NonNull String key, Long date) {
        this.key = key;
        this.date = date;
    }

    public SearchHistory() {
    }

    public @NonNull String getKey() {
        return this.key;
    }

    public void setKey(@NonNull String key) {
        this.key = key;
    }


    @Override
    public String toString() {
        return "SearchHistory{" +
                ", key='" + key + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public Long getDate() {
        return this.date;
    }

    public void setDate(Long date) {
        this.date = date;
    }
}
