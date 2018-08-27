package com.lazylee.lzywanandroid.view.divider;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

import android.support.annotation.ColorInt;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * divider for home fragment's recycler view
 * Created by lazylee on 2018/4/12.
 */

public class ArticleRecycleDivider extends RecyclerView.ItemDecoration {

    private Drawable mDrawable;
    private int mDividerHeight;

    /**
     * @param color divider color
     */
    public ArticleRecycleDivider(@ColorInt int color) {
        this(color, 2);
    }

    public ArticleRecycleDivider(@ColorInt int color, int height) {
        this.mDrawable = new ColorDrawable(color);
        this.mDividerHeight = height;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildLayoutPosition(view);
        int childCount = parent.getAdapter().getItemCount();

        if (isFirstRow(position)){
            outRect.set(0,mDividerHeight,0,mDividerHeight/2);
        }else if (isLastRow(position,childCount)){
            outRect.set(0,mDividerHeight/2,0,mDividerHeight);
        }else {
            outRect.set(0,mDividerHeight/2,0,mDividerHeight/2);
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        c.save();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final int left = child.getLeft() + child.getPaddingLeft();
            final int top = child.getBottom();
            final int right = child.getRight() - child.getPaddingRight();
            final int bottom = top + mDividerHeight;
            mDrawable.setBounds(left, top, right, bottom);
            mDrawable.draw(c);
        }
        c.restore();
    }

    /**
     * 判断该项是否为第一行
     *
     * @return 是否是第一行
     */
    private boolean isFirstRow(int position) {
        return position == 0;
    }

    /**
     * 判断该项是否是最后一行
     *
     * @return 是否是最后一行
     */
    private boolean isLastRow(int position, int childCount) {
        return position == childCount - 1;
    }

    private int getDividerHeight() {
        return mDividerHeight;
    }
}
