package com.lazylee.lzywanandroid.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lazylee.lzywanandroid.R;
import com.lazylee.lzywanandroid.data.entity.HotKey;
import com.lazylee.lzywanandroid.view.LzyChip;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HotKeyViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.hot_key_chip) LzyChip chip;

    public HotKeyViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindView(HotKey hotKey) {
        chip.setChipText(hotKey.getName());
    }
}