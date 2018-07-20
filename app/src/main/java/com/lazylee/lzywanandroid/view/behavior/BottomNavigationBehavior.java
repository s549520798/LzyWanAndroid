package com.lazylee.lzywanandroid.view.behavior;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.content.Context;

import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewPropertyAnimator;

/**
 * behavior for bottomNavigationView, when recyclerView or ScrollView is
 * Scrolling, hide or show BottomNavigation.
 * Created by lazylee on 2018/4/16.
 */

public class BottomNavigationBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {

    private static final String TAG = BottomNavigationView.class.getSimpleName();

    protected static final int ENTER_ANIMATION_DURATION = 225;
    protected static final int EXIT_ANIMATION_DURATION = 175;

    private static final int STATE_SCROLLED_DOWN = 1;
    private static final int STATE_SCROLLED_UP = 2;

    private int height = 0;
    private int currentState = STATE_SCROLLED_UP;
    private ViewPropertyAnimator currentAnimator;

    public BottomNavigationBehavior() {
    }

    public BottomNavigationBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onLayoutChild(@NonNull CoordinatorLayout parent, @NonNull V child, int layoutDirection) {
        height = child.getMeasuredHeight();
        return super.onLayoutChild(parent, child, layoutDirection);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V child, @NonNull View directTargetChild,
                                       @NonNull View target, int axes, int type) {
        //如果是水平滑动的话，返回true
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V child, @NonNull View target, int dxConsumed,
                               int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        Log.d(TAG, "onNestedScroll: dy = " + dyConsumed);
        if (currentState != STATE_SCROLLED_DOWN && dyConsumed > 0) {
            slideDown(child);
        } else if (currentState != STATE_SCROLLED_UP && dyConsumed < 0) {
            slideUp(child);
        }
    }

    protected void slideUp(V child) {
        if (currentAnimator != null) {
            currentAnimator.cancel();
            child.clearAnimation();
        }
        currentState = STATE_SCROLLED_UP;
        animateChildTo(
                child, 0, ENTER_ANIMATION_DURATION, AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
    }

    protected void slideDown(V child) {
        if (currentAnimator != null) {
            currentAnimator.cancel();
            child.clearAnimation();
        }
        currentState = STATE_SCROLLED_DOWN;
        animateChildTo(
                child, height, EXIT_ANIMATION_DURATION, AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR);
    }

    private void animateChildTo(V child, int targetY, long duration, TimeInterpolator interpolator) {
        currentAnimator =
                child
                        .animate()
                        .translationY(targetY)
                        .setInterpolator(interpolator)
                        .setDuration(duration)
                        .setListener(
                                new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        currentAnimator = null;
                                    }
                                });
    }
}
