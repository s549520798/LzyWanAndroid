package com.lazylee.lzywanandroid.data.entity;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;

/**
 * tag
 * <p>
 * Created by lazylee on 2018/4/13.
 */
@Entity(primaryKeys = {"name", "url"})
public class Tag {

    @NonNull
    private String name = "";
    @NonNull
    private String url = "";

    public Tag() {
    }

    @Ignore
    public Tag(@NonNull String name, @NonNull String url) {
        this.name = name;
        this.url = url;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
