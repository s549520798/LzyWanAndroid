package com.lazylee.lzywanandroid.data.dao

import com.lazylee.lzywanandroid.data.entity.User

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Query("Select * From User Where username Like :userName")
    fun findByUserName(userName: String): User

    @Query("Select * From User Where username Like :userName And password Like :password")
    fun findByUserNameAndPassword(userName: String, password: String): User

    @Insert
    fun insertUser(user: User)

    @Delete
    fun deleteUser(user: User)
}
