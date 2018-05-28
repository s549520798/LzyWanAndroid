package com.lazylee.lzywanandroid.data.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * hot key 热搜词
 * Created by lazylee on 2018/3/23.
 */
@Entity
public class HotKey {
    @Id
    private long id;
    private String link;
    @Unique
    private String name;
    private int order;
    private int visible;

    @Generated(hash = 1302646808)
    public HotKey(long id, String link, String name, int order, int visible) {
        this.id = id;
        this.link = link;
        this.name = name;
        this.order = order;
        this.visible = visible;
    }

    @Generated(hash = 222431971)
    public HotKey() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    @Override
    public String toString() {
        return "HotKey{" +
                "id=" + id +
                ", link='" + link + '\'' +
                ", name='" + name + '\'' +
                ", order=" + order +
                ", visible=" + visible +
                '}';
    }
}
