package com.lazylee.lzywanandroid.ui.activity.main.guide

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.lazylee.lzywanandroid.R


/**
 * guide fragment in main activity
 * Created by lazylee on 2018/4/9.
 */

class GuideFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.guide_fragment, container, false)
        return view
    }

    companion object {

        val TAG = "GuideFragment"
        fun newInstance(): GuideFragment {
            val guideFragment = GuideFragment()
            return guideFragment
        }
    }
}
