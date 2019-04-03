package com.lazylee.lzywanandroid.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * commonly used website
 * Created by lazylee on 2018/3/23.
 */
@Entity
class CommonWebsite {
    @PrimaryKey
    var id: Long = 0
    var icon: String? = null
    var link: String? = null
    var name: String? = null
    var order: Int = 0
    var visible: Int = 0

    override fun toString(): String {
        return "CommonWebsite{" +
                "id=" + id +
                ", icon='" + icon + '\''.toString() +
                ", link='" + link + '\''.toString() +
                ", name='" + name + '\''.toString() +
                ", order=" + order +
                ", visible=" + visible +
                '}'.toString()
    }
}
