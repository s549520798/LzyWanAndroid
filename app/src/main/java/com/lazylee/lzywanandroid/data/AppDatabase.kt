package com.lazylee.lzywanandroid.data

import android.content.Context

import com.lazylee.lzywanandroid.data.converter.EntityListConverter
import com.lazylee.lzywanandroid.data.dao.ArticleDao
import com.lazylee.lzywanandroid.data.dao.HotKeyDao
import com.lazylee.lzywanandroid.data.dao.SearchHistoryDao
import com.lazylee.lzywanandroid.data.dao.UserDao
import com.lazylee.lzywanandroid.data.entity.Article
import com.lazylee.lzywanandroid.data.entity.HotKey
import com.lazylee.lzywanandroid.data.entity.SearchHistory
import com.lazylee.lzywanandroid.data.entity.Tag
import com.lazylee.lzywanandroid.data.entity.User

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = arrayOf(User::class, Article::class, Tag::class, SearchHistory::class, HotKey::class), version = 1, exportSchema = false)
@TypeConverters(EntityListConverter::class)
abstract class AppDatabase : RoomDatabase() {


    abstract fun userDao(): UserDao

    abstract fun articleDao(): ArticleDao

    abstract fun hotKeyDao(): HotKeyDao

    abstract fun searchHistoryDao(): SearchHistoryDao

    companion object {

        private val DATABASE_NAME = "WanAndroidDatabase"
        /***********创建单例实例 */
        @Volatile
        private var mInstance: AppDatabase? = null
        // For Singleton instantiation
        private val LOCK = Any()

        fun getInstance(context: Context): AppDatabase? {
            if (mInstance == null) {
                synchronized(LOCK) {
                    if (mInstance == null) {
                        mInstance = buildDatabase(context)
                    }
                }
            }
            return mInstance
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder<AppDatabase>(context, AppDatabase::class.java!!, DATABASE_NAME)
                    .build()
        }
    }
}
