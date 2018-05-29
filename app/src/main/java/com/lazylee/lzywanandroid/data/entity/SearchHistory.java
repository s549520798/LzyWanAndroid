package com.lazylee.lzywanandroid.data.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class SearchHistory {

    @Unique
    private String key;
    private Long date;



    @Generated(hash = 1696560968)
    public SearchHistory(String key, Long date) {
        this.key = key;
        this.date = date;
    }
    @Generated(hash = 1905904755)
    public SearchHistory() {
    }


    
    public String getKey() {
        return this.key;
    }
    public void setKey(String key) {
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
