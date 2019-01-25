package com.lazylee.lzywanandroid.data;

import android.content.Context;

import com.lazylee.lzywanandroid.data.dao.ArticleDao;
import com.lazylee.lzywanandroid.data.dao.HotKeyDao;
import com.lazylee.lzywanandroid.data.dao.SearchHistoryDao;
import com.lazylee.lzywanandroid.data.dao.UserDao;
import com.lazylee.lzywanandroid.data.entity.Article;
import com.lazylee.lzywanandroid.data.entity.HotKey;
import com.lazylee.lzywanandroid.data.entity.SearchHistory;
import com.lazylee.lzywanandroid.data.entity.Tag;
import com.lazylee.lzywanandroid.data.entity.User;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, Article.class, Tag.class, SearchHistory.class, HotKey.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "WanAndroidDatabane";
    /***********创建单例实例***********************/
    private static volatile AppDatabase mInstance;

    public static AppDatabase getInstance(Context context) {
        if (mInstance == null) {
            synchronized (AppDatabase.class) {
                if (mInstance == null) {
                    mInstance = buildDatabase(context);
                }
            }
        }
        return mInstance;
    }

    private static AppDatabase buildDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                .build();
    }


    public abstract UserDao userDao();

    public abstract ArticleDao articleDao();

    public abstract HotKeyDao hotKeyDao();

    public abstract SearchHistoryDao searchHistoryDao();
}
