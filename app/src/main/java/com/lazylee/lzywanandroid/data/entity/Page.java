package com.lazylee.lzywanandroid.data.entity;



import java.util.List;

import androidx.room.Entity;

/**
 * page entity
 * Created by lazylee on 2018/3/23.
 */
@Entity
public class Page {

    private int curPage;     //curPage 从1开始。但是 http 请求时是从0开始。 请求的是0 但返回的页数是1
    private int offset;
    private int size;
    private int pageCount;
    private int total;
    private boolean over;
    private List<Article> datas;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public List<Article> getDatas() {
        return datas;
    }

    public void setDatas(List<Article> datas) {
        this.datas = datas;
    }

    @Override
    public String toString() {
        return "Page{" +
                "curPage=" + curPage +
                ", offset=" + offset +
                ", size=" + size +
                ", pageCount=" + pageCount +
                ", total=" + total +
                ", over=" + over +
                ", datas=" + datas +
                '}';
    }
}
