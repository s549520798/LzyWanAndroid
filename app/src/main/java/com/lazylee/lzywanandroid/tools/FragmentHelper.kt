package com.lazylee.lzywanandroid.tools


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 * fragment helper
 * Created by lazylee on 2018/4/9.
 */

object FragmentHelper {

    fun getCurrentVisibleFragment(manager: FragmentManager): Fragment? {
        val fragments = manager.fragments
        if (fragments != null && !fragments.isEmpty()) {
            for (fragment in fragments) {
                if (fragment != null && fragment.isVisible) {
                    return fragment
                }
            }
        }
        return null
    }

    fun getFragmentByTag(fragmentManager: FragmentManager, tag: String): Fragment? {
        return fragmentManager.findFragmentByTag(tag)
    }
}
