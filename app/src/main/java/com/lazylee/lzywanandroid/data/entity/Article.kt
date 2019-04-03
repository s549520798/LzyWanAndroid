package com.lazylee.lzywanandroid.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * article entity
 * Created by lazylee on 2018/3/23.
 */
@Entity
class Article {
    @PrimaryKey
    var id: Long = 0
    var apkLink: String? = null
    var author: String? = null
    var chapterId: Long = 0
    var chapterName: String? = null
    var isCollect: Boolean = false
    var courseId: Long = 0
    var desc: String? = null
    var envelopePic: String? = null
    var isFresh: Boolean = false
    var link: String? = null
    var niceDate: String? = null
    var origin: String? = null
    var projectLink: String? = null
    var publishTime: Long = 0
    var superChapterId: Long = 0      //一级分类ID
    var superChapterName: String? = null  //一级分类名称
    var title: String? = null
    var type: Int = 0
    var visible: Int = 0
    var zan: Int = 0
    var tags: List<Tag>? = null


    override fun toString(): String {
        return "Article{" +
                "id=" + id +
                ", apkLink='" + apkLink + '\''.toString() +
                ", author='" + author + '\''.toString() +
                ", chapterId=" + chapterId +
                ", chapterName='" + chapterName + '\''.toString() +
                ", collect=" + isCollect +
                ", courseId=" + courseId +
                ", desc='" + desc + '\''.toString() +
                ", envelopePic='" + envelopePic + '\''.toString() +
                ", fresh=" + isFresh +
                ", link='" + link + '\''.toString() +
                ", niceDate='" + niceDate + '\''.toString() +
                ", origin='" + origin + '\''.toString() +
                ", projectLink='" + projectLink + '\''.toString() +
                ", publishTime=" + publishTime +
                ", superChapterId=" + superChapterId +
                ", superChapterName='" + superChapterName + '\''.toString() +
                ", title='" + title + '\''.toString() +
                ", type=" + type +
                ", visible=" + visible +
                ", zan=" + zan +
                ", tags=" + tags +
                '}'.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val article = other as Article?
        return id == article!!.id
    }

    override fun hashCode(): Int {

        return (id xor id.ushr(32)).toInt()
    }
}
