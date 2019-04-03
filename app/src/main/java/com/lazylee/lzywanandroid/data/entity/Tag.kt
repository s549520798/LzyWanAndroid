package com.lazylee.lzywanandroid.data.entity


import androidx.room.Entity
import androidx.room.Ignore

/**
 * tag
 *
 *
 * Created by lazylee on 2018/4/13.
 */
@Entity(primaryKeys = ["name", "url"])
class Tag {

    var name = ""
    var url = ""

    constructor() {}

    @Ignore
    constructor(name: String, url: String) {
        this.name = name
        this.url = url
    }

    override fun toString(): String {
        return "Tag{" +
                "name='" + name + '\''.toString() +
                ", url='" + url + '\''.toString() +
                '}'.toString()
    }
}
