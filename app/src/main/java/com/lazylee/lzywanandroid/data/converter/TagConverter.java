package com.lazylee.lzywanandroid.data.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lazylee.lzywanandroid.data.entity.Tag;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.List;

/**
 * convert  tag list to json
 * Created by lazylee on 2018/4/13.
 */

public class TagConverter implements PropertyConverter<List<Tag>,String> {

    @Override
    public List<Tag> convertToEntityProperty(String databaseValue) {
        Gson gson = new Gson();
        return gson.fromJson(databaseValue,new TypeToken<List<Tag>>(){}.getType());
    }

    @Override
    public String convertToDatabaseValue(List<Tag> entityProperty) {
        Gson gson = new Gson();
        return gson.toJson(entityProperty);
    }
}
