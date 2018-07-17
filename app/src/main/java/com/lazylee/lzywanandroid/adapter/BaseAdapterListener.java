package com.lazylee.lzywanandroid.adapter;

import android.view.View;


public interface BaseAdapterListener {



    interface OnItemClickListener {
        void onClick(View view, int position);
    }

    interface OnItemLongClickListener{
        boolean onLongClick(View view,int position);
    }

    void setItemClickListener(OnItemClickListener itemClickListener);
    void setItemLongClickListener(OnItemLongClickListener itemLongClickListener);
}
