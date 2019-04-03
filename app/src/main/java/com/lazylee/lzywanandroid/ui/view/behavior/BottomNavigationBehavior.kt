package com.lazylee.lzywanandroid.ui.view.behavior

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.TimeInterpolator
import android.content.Context
import com.google.android.material.animation.AnimationUtils
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewPropertyAnimator

/**
 * behavior for bottomNavigationView, when recyclerView or ScrollView is
 * Scrolling, hide or show BottomNavigation.
 * Created by lazylee on 2018/4/16.
 */

class BottomNavigationBehavior<V : View> : CoordinatorLayout.Behavior<V> {

    private var height = 0
    private var currentState = STATE_SCROLLED_UP
    private var currentAnimator: ViewPropertyAnimator? = null

    constructor() {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    override fun onLayoutChild(parent: CoordinatorLayout, child: V, layoutDirection: Int): Boolean {
        height = child.measuredHeight
        return super.onLayoutChild(parent, child, layoutDirection)
    }

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: V, directTargetChild: View,
                                     target: View, axes: Int, type: Int): Boolean {
        //如果是水平滑动的话，返回true
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL
    }

    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout, child: V, target: View, dxConsumed: Int,
                                dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, type: Int) {
        Log.d(TAG, "onNestedScroll: dy = $dyConsumed")
        if (currentState != STATE_SCROLLED_DOWN && dyConsumed > 0) {
            slideDown(child)
        } else if (currentState != STATE_SCROLLED_UP && dyConsumed < 0) {
            slideUp(child)
        }
    }

    protected fun slideUp(child: V) {
        if (currentAnimator != null) {
            currentAnimator!!.cancel()
            child.clearAnimation()
        }
        currentState = STATE_SCROLLED_UP
        animateChildTo(
                child, 0, ENTER_ANIMATION_DURATION.toLong(), AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR)
    }

    protected fun slideDown(child: V) {
        if (currentAnimator != null) {
            currentAnimator!!.cancel()
            child.clearAnimation()
        }
        currentState = STATE_SCROLLED_DOWN
        animateChildTo(
                child, height, EXIT_ANIMATION_DURATION.toLong(), AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR)
    }

    private fun animateChildTo(child: V, targetY: Int, duration: Long, interpolator: TimeInterpolator) {
        currentAnimator = child
                .animate()
                .translationY(targetY.toFloat())
                .setInterpolator(interpolator)
                .setDuration(duration)
                .setListener(
                        object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                currentAnimator = null
                            }
                        })
    }

    companion object {

        private val TAG = BottomNavigationView::class.java!!.getSimpleName()

        protected val ENTER_ANIMATION_DURATION = 225
        protected val EXIT_ANIMATION_DURATION = 175

        private val STATE_SCROLLED_DOWN = 1
        private val STATE_SCROLLED_UP = 2
    }
}
