package com.lazylee.lzywanandroid.data.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * tag
 *
 * Created by lazylee on 2018/4/13.
 */
@Entity
public class Tag {

    private String name;
    private String url;

    public Tag() {
    }


    @Generated(hash = 1594565114)
    public Tag(String name, String url) {
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
