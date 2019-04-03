package com.lazylee.lzywanandroid.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * hot key 热搜词
 * Created by lazylee on 2018/3/23.
 */
@Entity
class HotKey {
    @PrimaryKey
    var id: Long = 0
    var link: String? = null
    var name: String = ""
    var order: Int = 0
    var visible: Int = 0

    override fun toString(): String {
        return "HotKey{" +
                "id=" + id +
                ", link='" + link + '\''.toString() +
                ", name='" + name + '\''.toString() +
                ", order=" + order +
                ", visible=" + visible +
                '}'.toString()
    }
}
