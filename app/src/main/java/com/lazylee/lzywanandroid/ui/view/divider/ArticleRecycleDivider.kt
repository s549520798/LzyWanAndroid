package com.lazylee.lzywanandroid.ui.view.divider

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable

import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView

import android.view.View

/**
 * divider for home fragment's recycler view
 * Created by lazylee on 2018/4/12.
 */

class ArticleRecycleDivider @JvmOverloads constructor(@ColorInt color: Int, private val dividerHeight: Int = 2) : RecyclerView.ItemDecoration() {

    private val mDrawable: Drawable

    init {
        this.mDrawable = ColorDrawable(color)
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,
                                state: RecyclerView.State) {
        val position = parent.getChildLayoutPosition(view)
        val childCount = parent.adapter!!.itemCount

        if (isFirstRow(position)) {
            outRect.set(0, dividerHeight, 0, dividerHeight / 2)
        } else if (isLastRow(position, childCount)) {
            outRect.set(0, dividerHeight / 2, 0, dividerHeight)
        } else {
            outRect.set(0, dividerHeight / 2, 0, dividerHeight / 2)
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        c.save()
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val left = child.left + child.paddingLeft
            val top = child.bottom
            val right = child.right - child.paddingRight
            val bottom = top + dividerHeight
            mDrawable.setBounds(left, top, right, bottom)
            mDrawable.draw(c)
        }
        c.restore()
    }

    /**
     * 判断该项是否为第一行
     *
     * @return 是否是第一行
     */
    private fun isFirstRow(position: Int): Boolean {
        return position == 0
    }

    /**
     * 判断该项是否是最后一行
     *
     * @return 是否是最后一行
     */
    private fun isLastRow(position: Int, childCount: Int): Boolean {
        return position == childCount - 1
    }
}
/**
 * @param color divider color
 */
