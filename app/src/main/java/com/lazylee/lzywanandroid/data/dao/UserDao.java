package com.lazylee.lzywanandroid.data.dao;

import com.lazylee.lzywanandroid.data.entity.User;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {

    @Query("Select * From User Where username Like :userName")
    User findByUserName(String userName);

    @Query("Select * From User Where username Like :userName And password Like :password")
    User findByUserNameAndPassword(String userName,String password);

    @Insert
    void insertUser(User user);

    @Delete
    void deleteUser(User user);
}
