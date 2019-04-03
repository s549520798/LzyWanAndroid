package com.lazylee.lzywanandroid.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 用户entity
 * Created by lazylee on 2018/3/16.
 */
@Entity
class User {
    @PrimaryKey
    var id: Long = 0

    @ColumnInfo(name = "username")
    var userName: String? = null

    var password: String? = null
    var email: String? = null
    var icon: String? = null
    var type: Int = 0
    var collectIds: List<Int>? = null

    override fun toString(): String {
        return "User{" +
                "id=" + id +
                ", username='" + userName + '\''.toString() +
                ", password='" + password + '\''.toString() +
                ", email='" + email + '\''.toString() +
                ", icon='" + icon + '\''.toString() +
                ", type=" + type +
                '}'.toString()
    }
}
