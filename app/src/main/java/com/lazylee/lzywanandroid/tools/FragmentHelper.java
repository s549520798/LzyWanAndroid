package com.lazylee.lzywanandroid.tools;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.List;

/**
 * fragment helper
 * Created by lazylee on 2018/4/9.
 */

public class FragmentHelper {

    public static androidx.fragment.app.Fragment getCurrentVisibleFragment(@NonNull androidx.fragment.app.FragmentManager manager){
        List<androidx.fragment.app.Fragment> fragments = manager.getFragments();
        if (fragments != null && !fragments.isEmpty()) {
            for (androidx.fragment.app.Fragment fragment : fragments) {
                if (fragment != null && fragment.isVisible()) {
                    return fragment;
                }
            }
        }
        return null;
    }

    @Nullable
    public static Fragment getFragmentByTag(@NonNull androidx.fragment.app.FragmentManager fragmentManager, @NonNull String tag) {
        return fragmentManager.findFragmentByTag(tag);
    }
}
