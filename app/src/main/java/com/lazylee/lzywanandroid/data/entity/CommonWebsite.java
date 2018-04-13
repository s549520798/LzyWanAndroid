package com.lazylee.lzywanandroid.data.entity;

/**
 * commonly used website
 * Created by lazylee on 2018/3/23.
 */

public class CommonWebsite {

    private long id;
    private String icon;
    private String link;
    private String name;
    private int order;
    private int visible;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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
        return "CommonWebsite{" +
                "id=" + id +
                ", icon='" + icon + '\'' +
                ", link='" + link + '\'' +
                ", name='" + name + '\'' +
                ", order=" + order +
                ", visible=" + visible +
                '}';
    }
}
