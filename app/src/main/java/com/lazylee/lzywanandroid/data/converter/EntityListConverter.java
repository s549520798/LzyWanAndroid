package com.lazylee.lzywanandroid.data.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lazylee.lzywanandroid.data.entity.Tag;

import java.lang.reflect.Type;
import java.util.List;

import androidx.room.Ignore;
import androidx.room.TypeConverter;


public class EntityListConverter {

    private Gson mGson;

    public EntityListConverter() {
    }

    @Ignore
    public EntityListConverter(Gson mGson) {
        this.mGson = mGson;
    }

    @TypeConverter
    public String integerToString(List<Integer> list) {
        return list != null ? mGson.toJson(list) : "";
    }

    @TypeConverter
    public List<Integer> toIntegerList(String s) {
        Type listType = new TypeToken<List<Integer>>() {
        }.getType();
        return mGson.fromJson(s, listType);
    }


    @TypeConverter
    public String tagToString(List<Tag> list) {
        return list != null ? mGson.toJson(list) : "";
    }

    @TypeConverter
    public List<Tag> toTagList(String s) {
        Type listType = new TypeToken<List<Tag>>() {
        }.getType();
        return mGson.fromJson(s, listType);
    }


}
