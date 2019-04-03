package com.lazylee.lzywanandroid.ui.activity.main.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView

import com.lazylee.lzywanandroid.R
import com.lazylee.lzywanandroid.ui.activity.web.WebActivity
import com.lazylee.lzywanandroid.ui.adapter.ArticleAdapter
import com.lazylee.lzywanandroid.data.entity.Article
import com.lazylee.lzywanandroid.ui.adapter.BaseAdapterListener
import com.lazylee.lzywanandroid.ui.view.AppbarRefreshLayout
import com.lazylee.lzywanandroid.ui.view.LzyToast
import com.lazylee.lzywanandroid.ui.view.divider.ArticleRecycleDivider

import java.util.ArrayList


/**
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment(), HomeContract.View, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    private var mPresenter: HomeContract.Presenter? = null
    private var mAdapter: ArticleAdapter? = null
    private val articles = ArrayList<Article>()

    private var mRecyclerView: RecyclerView? = null
    private var mStateView: ConstraintLayout? = null
    private var mEmptyImage: ImageView? = null
    private var mTvLoadAgain: TextView? = null
    private var mStateProgressBar: ProgressBar? = null
    private var mRefreshLayout: AppbarRefreshLayout? = null

    override val isStateViewShow: Boolean
        get() = mStateView!!.visibility == View.VISIBLE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {

        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val mRootView = inflater.inflate(R.layout.home_fragment, container, false)
        mRecyclerView = mRootView.findViewById(R.id.recycle_home_frag)
        mStateView = mRootView.findViewById(R.id.state_layout_root)
        mEmptyImage = mRootView.findViewById(R.id.state_image)
        mTvLoadAgain = mRootView.findViewById(R.id.state_load_again)
        mStateProgressBar = mRootView.findViewById(R.id.state_progress_bar)
        mRefreshLayout = mRootView.findViewById(R.id.refresh_home_frag)

        mTvLoadAgain!!.setOnClickListener(this)
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter = HomePresenter(this)
        mAdapter = ArticleAdapter(articles)
        mAdapter!!.setItemClickListener(object: BaseAdapterListener.OnItemClickListener{
            override fun onClick(view: View, position: Int) {
                val intent = Intent(view.context, WebActivity::class.java)
                intent.putExtra("link", articles[position].link)
                startActivity(intent)
            }
        })
        //mAdapter.setItemLongClickListener((view12, position) -> false);

        mRecyclerView!!.layoutManager = LinearLayoutManager(context)
        mRecyclerView!!.adapter = mAdapter
        mRecyclerView!!.addItemDecoration(ArticleRecycleDivider(resources
                .getColor(R.color.colorRecycleDivider)))
        mRecyclerView!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING && !recyclerView.canScrollVertically(1)) {
                    //TODO 加载更过操作
                    mPresenter!!.loadMoreArticles(mAdapter)
                }
            }
        })
        mRefreshLayout!!.setOnRefreshListener(this)
        showStateView(true)
        showStateEmptyView(false)
        showProgressIndicator(true)
        mPresenter!!.initArticles(mAdapter)
    }

    override fun onRefresh() {

        mPresenter!!.updateArticles(mAdapter)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun setPresenter(presenter: HomeContract.Presenter) {
        this.mPresenter = presenter
    }

    override fun showMessage(msg: String, type: Int) {
        LzyToast.showToast(msg, type)
    }

    override fun showMessage(msg: String) {
        LzyToast.showMessage(msg)
    }


    override fun showStateView(show: Boolean) {
        mStateView!!.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun showStateEmptyView(show: Boolean) {
        if (isStateViewShow) {
            mStateProgressBar!!.visibility = if (show) View.GONE else View.VISIBLE
            mEmptyImage!!.visibility = if (show) View.VISIBLE else View.GONE
            mTvLoadAgain!!.visibility = if (show) View.VISIBLE else View.GONE
        }
    }

    override fun showUpLoadMore(show: Boolean) {
        if (mRefreshLayout!!.isRefreshing && show) {

        } else {
            mRefreshLayout!!.isRefreshing = show
        }
    }

    override fun showDownLoadMore(show: Boolean) {

    }

    override fun showProgressIndicator(show: Boolean) {
        if (isStateViewShow) {
            mEmptyImage!!.visibility = if (show) View.GONE else View.VISIBLE
            mTvLoadAgain!!.visibility = if (show) View.GONE else View.VISIBLE
            mStateProgressBar!!.visibility = if (show) View.VISIBLE else View.GONE
        }
    }


    override fun onClick(view: View) {
        when (view.id) {
            R.id.state_load_again -> if (isStateViewShow) {
                mPresenter!!.initArticles(mAdapter)
            }
            else -> {
            }
        }
    }

    companion object {

        val TAG = "HomeFragment"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return letter_a new instance of fragment HomeFragment.
         */
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}// Required empty public constructor
