package com.lazylee.lzywanandroid.data.converter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lazylee.lzywanandroid.data.entity.Tag

import java.lang.reflect.Type

import androidx.room.Ignore
import androidx.room.TypeConverter


class EntityListConverter {

    private val mGson: Gson = Gson()

    constructor() {}

    @TypeConverter
    fun integerToString(list: List<Int>?): String {
        return if (list != null) mGson.toJson(list) else ""
    }

    @TypeConverter
    fun toIntegerList(s: String): List<Int>? {
        val listType = object : TypeToken<List<Int>>() {

        }.type
        return mGson.fromJson<List<Int>>(s, listType)
    }


    @TypeConverter
    fun tagToString(list: List<Tag>?): String {
        return if (list != null) mGson.toJson(list) else ""
    }

    @TypeConverter
    fun toTagList(s: String): List<Tag>? {
        val listType = object : TypeToken<List<Tag>>() {

        }.type
        return mGson.fromJson<List<Tag>>(s, listType)
    }


}
