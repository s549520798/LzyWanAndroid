package com.lazylee.lzywanandroid.adapter.viewholder;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.lazylee.lzywanandroid.R;
import com.lazylee.lzywanandroid.data.entity.HotKey;
import com.lazylee.lzywanandroid.view.LzyChip;


public class HotKeyViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = "HotKeyViewHolder";
    LzyChip chip;

    public HotKeyViewHolder(View itemView) {
        super(itemView);
        chip = itemView.findViewById(R.id.hot_key_chip);
    }

    public void bindView(HotKey hotKey) {
        chip.setChipText(hotKey.getName());
    }
}
