package com.lazylee.lzywanandroid.data.entity

import androidx.room.PrimaryKey

/**
 * banner 实体类
 * Created by lazylee on 2018/3/23.
 */

class Banner {
    @PrimaryKey
    var id: Long = 0
    var order: Int = 0
    var type: Int = 0
    var isVisible: Int = 0
    var desc: String? = null
    var imagePath: String? = null
    var title: String? = null
    var url: String? = null

    override fun toString(): String {
        return "Banner{" +
                "id=" + id +
                ", order=" + order +
                ", type=" + type +
                ", isVisible=" + isVisible +
                ", desc='" + desc + '\''.toString() +
                ", imagePath='" + imagePath + '\''.toString() +
                ", title='" + title + '\''.toString() +
                ", url='" + url + '\''.toString() +
                '}'.toString()
    }
}
