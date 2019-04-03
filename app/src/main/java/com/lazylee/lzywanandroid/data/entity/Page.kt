package com.lazylee.lzywanandroid.data.entity


import androidx.room.Entity

/**
 * page entity
 * Created by lazylee on 2018/3/23.
 */
@Entity
class Page {

    var curPage: Int = 0     //curPage 从1开始。但是 http 请求时是从0开始。 请求的是0 但返回的页数是1
    var offset: Int = 0
    var size: Int = 0
    var pageCount: Int = 0
    var total: Int = 0
    var isOver: Boolean = false
    var datas: List<Article>? = null

    override fun toString(): String {
        return "Page{" +
                "curPage=" + curPage +
                ", offset=" + offset +
                ", size=" + size +
                ", pageCount=" + pageCount +
                ", total=" + total +
                ", over=" + isOver +
                ", datas=" + datas +
                '}'.toString()
    }
}
