package com.lazylee.lzywanandroid.entity;

import java.util.List;

/**
 * 用户entity
 * Created by lazylee on 2018/3/16.
 */

public class User {

    private long id;
    private String username;
    private String password;
    private String email;
    private String icon;
    private int type;
    private List<Integer> collectIds;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", icon='" + icon + '\'' +
                ", type=" + type +
                '}';
    }
}
