package com.lazylee.lzywanandroid.data.entity

import androidx.room.Entity
import androidx.room.Ignore

@Entity(primaryKeys = arrayOf("key"))
class SearchHistory {

    var key = ""
    var date: Long? = null

    @Ignore
    constructor(key: String, date: Long?) {
        this.key = key
        this.date = date
    }

    constructor() {}


    override fun toString(): String {
        return "SearchHistory{" +
                ", key='" + key + '\''.toString() +
                ", date='" + date + '\''.toString() +
                '}'.toString()
    }
}
