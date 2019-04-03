package com.lazylee.lzywanandroid.ui.activity.main.project.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


import com.lazylee.lzywanandroid.R
import com.lazylee.lzywanandroid.data.entity.ProjectCategory


class ProjectVPFragment : Fragment() {
    private var mCategory: ProjectCategory? = null

    private var mTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            val bundle = arguments
            mCategory = bundle!!.getParcelable(CATEGORY)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.project_viewpager_fragment, container, false)
        mTextView = rootView.findViewById(R.id.textView)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (mCategory != null) {
            //Log.d(TAG, "onViewCreated: category" + mCategory.toString());
            mTextView!!.text = mCategory!!.name
        }
    }

    companion object {

        private val TAG = "ProjectVPFragment"

        private val CATEGORY = "category"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return letter_a new instance of fragment ProjectVPFragment.
         */

        fun newInstance(category: ProjectCategory): ProjectVPFragment {
            val fragment = ProjectVPFragment()
            val bundle = Bundle()
            bundle.putParcelable(CATEGORY, category)
            fragment.arguments = bundle
            return fragment
        }
    }
}// Required empty public constructor
