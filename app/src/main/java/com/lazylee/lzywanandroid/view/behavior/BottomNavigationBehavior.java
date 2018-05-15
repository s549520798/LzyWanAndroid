package com.lazylee.lzywanandroid.view.behavior;

import android.content.Context;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * behavior for bottomNavigationView, when recyclerView or ScrollView is
 * Scrolling, hide or show BottomNavigation.
 * Created by lazylee on 2018/4/16.
 */

public class BottomNavigationBehavior<T extends ViewGroup> extends CoordinatorLayout.Behavior<BottomNavigationView> {

    private static final String TAG = BottomNavigationView.class.getSimpleName();

    public BottomNavigationBehavior() {
    }

    public BottomNavigationBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
