package com.lazylee.lzywanandroid.ui.activity.search

import android.content.Intent
import android.os.Build
import android.os.Bundle


import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout


import com.lazylee.lzywanandroid.R
import com.lazylee.lzywanandroid.ui.activity.web.WebActivity
import com.lazylee.lzywanandroid.ui.adapter.ArticleAdapter
import com.lazylee.lzywanandroid.ui.adapter.HotKeyAdapter
import com.lazylee.lzywanandroid.ui.adapter.SearchHistoryAdapter
import com.lazylee.lzywanandroid.data.entity.Article
import com.lazylee.lzywanandroid.data.entity.Page
import com.lazylee.lzywanandroid.tools.Logger
import com.lazylee.lzywanandroid.ui.view.LzyToast
import com.lazylee.lzywanandroid.ui.view.divider.ArticleRecycleDivider

import java.util.ArrayList

import com.lazylee.lzywanandroid.tools.StateHelper.hideSoftKeyboard
import com.lazylee.lzywanandroid.ui.adapter.BaseAdapterListener


class SearchActivity : AppCompatActivity(), SearchContract.View, View.OnClickListener {


    private var mPresenter: SearchContract.Presenter? = null
    private val mHotKeyAdapter: HotKeyAdapter? = null
    private var isCloseBtnShowing = true
    private var isOptionsViewShowing = false
    private var isResultViewShowing = false
    private var isEmptyResultShowing = false
    private var isHotKeyObtained = false
    private var isHistoryObtained = false

    private var mSearchView: RelativeLayout? = null
    private var mBackBtn: ImageButton? = null
    private var mCloseBtn: ImageButton? = null
    private var mSearchBtn: ImageButton? = null
    private var mDividerView: View? = null
    private var mProgressBar: ProgressBar? = null
    private var mResultView: RecyclerView? = null
    private var mHotKeyView: ChipGroup? = null
    private var mHistoryView: RecyclerView? = null
    private var mOptionsView: LinearLayout? = null
    private var mSearchEdit: EditText? = null
    private var mEmptyResultView: FrameLayout? = null

    private var mResultAdapter: ArticleAdapter? = null
    private var articles = ArrayList<Article>()
    private var mHistoryAdapter: SearchHistoryAdapter? = null

    private fun initViews() {
        mSearchView = findViewById(R.id.searchView)
        mBackBtn = findViewById(R.id.backBtn)
        mCloseBtn = findViewById(R.id.closeBtn)
        mSearchBtn = findViewById(R.id.searchBtn)
        mDividerView = findViewById(R.id.dividerView)
        mProgressBar = findViewById(R.id.progressBar)
        mResultView = findViewById(R.id.recycler_result)
        mHotKeyView = findViewById(R.id.reflow_group)
        mHistoryView = findViewById(R.id.recycler_history)
        mOptionsView = findViewById(R.id.search_options)
        mSearchEdit = findViewById(R.id.searchEdit)
        mEmptyResultView = findViewById(R.id.empty_result_view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_activity)
        initViews()
        setPresenter(SearchPresenter(this))
        showSearchAndCloseBtn(false)
        showOptionsView(true)
        setSearchBarElevation()
        mSearchEdit!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                Logger.d(TAG, "beforeTextChanged : s = " + s + "s.length = " + s.toString().length)
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                Logger.d(TAG, "onTextChanged : s = " + s + "s.length = " + s.length)
                if (s.length == 0) {
                    showSearchAndCloseBtn(false)
                } else if (s.length >= 1) {
                    showSearchAndCloseBtn(true)
                }
            }

            override fun afterTextChanged(s: Editable) {
                Logger.d(TAG, "afterTextChanged : s = $s")
            }
        })
        //响应soft keyboard  中“搜索”按钮的点击
        mSearchEdit!!.setOnEditorActionListener { v, actionId, event ->
            var handled = false
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                mPresenter!!.search(v.text.toString())
                hideSoftKeyboard(mSearchEdit!!)
                handled = true
            }
            handled
        }
        mCloseBtn!!.setOnClickListener(this)
        mBackBtn!!.setOnClickListener(this)
        mSearchView!!.setOnClickListener(this)
        mHotKeyView!!.isSingleSelection = true
        mResultAdapter = ArticleAdapter(articles)
        mResultAdapter!!.setItemClickListener (object: BaseAdapterListener.OnItemClickListener{
            override fun onClick(view: View, position: Int) {
                val intent = Intent(view.context, WebActivity::class.java)
                intent.putExtra("link", articles[position].link)
                startActivity(intent)
            }
        })
        mResultView!!.layoutManager = LinearLayoutManager(this)
        mResultView!!.adapter = mResultAdapter
        mResultView!!.addItemDecoration(ArticleRecycleDivider(resources
                .getColor(R.color.colorRecycleDivider)))


        mHistoryAdapter = SearchHistoryAdapter()
        mHistoryView!!.layoutManager = LinearLayoutManager(this)
        mHistoryView!!.adapter = mHistoryAdapter
        mHistoryView!!.addItemDecoration(ArticleRecycleDivider(resources
                .getColor(R.color.colorRecycleDivider)))
    }

    override fun onStart() {
        super.onStart()
        if (!isHotKeyObtained) {
            mPresenter!!.getHotKey()
            isHotKeyObtained = true
        }
        if (!isHistoryObtained) {
            mPresenter!!.getSearchHistory(mHistoryAdapter)
            isHistoryObtained = true
        }

    }

    private fun setSearchBarElevation() {
        // NOTE : elevation 设置失效问题， 要给控件或者layout设置background
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mSearchView!!.elevation = 4f
        }
    }


    override fun setPresenter(presenter: SearchContract.Presenter) {
        this.mPresenter = presenter
    }

    override fun showMessage(msg: String, type: Int) {
        LzyToast.showToast(msg, type)
    }

    override fun showMessage(msg: String) {
        LzyToast.showMessage(msg, 1500)
    }

    override fun showProgressBar(show: Boolean) {
        mProgressBar!!.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun showSearchAndCloseBtn(show: Boolean) {
        if (isCloseBtnShowing == show) return
        mCloseBtn!!.visibility = if (show) View.VISIBLE else View.GONE
        mSearchBtn!!.visibility = if (show) View.VISIBLE else View.GONE
        mDividerView!!.visibility = if (show) View.VISIBLE else View.GONE
        isCloseBtnShowing = show
    }

    override fun showOptionsView(show: Boolean) {
        if (isOptionsViewShowing == show) return
        mOptionsView!!.visibility = if (show) View.VISIBLE else View.GONE
        isOptionsViewShowing = show
    }

    override fun showResultView(show: Boolean) {
        if (isResultViewShowing == show) return
        mResultView!!.visibility = if (show) View.VISIBLE else View.GONE
        isResultViewShowing = show
    }

    override fun showEmptyResultView(show: Boolean) {
        if (isEmptyResultShowing == show) return
        mEmptyResultView!!.visibility = if (show) View.VISIBLE else View.GONE
        isEmptyResultShowing = show
    }

    override fun addChip(chipText: String) {
        val chip = layoutInflater.inflate(R.layout.recycle_hotkey_item, mHotKeyView, false) as Chip
        chip.chipText = chipText
        mHotKeyView!!.addView(chip)
    }

    override fun addSearchResult(page: Page) {
        mResultAdapter!!.updateArticles(page.datas!!)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.backBtn ->
                //TODO 查看软键盘的状态，当软键盘显示时，先隐藏软键盘
                onBackPressed()
            R.id.closeBtn -> mSearchEdit!!.text = null
            R.id.searchBtn -> {
            }
            else -> {
            }
        }
    }

    companion object {

        private const val TAG = "SearchActivity"
    }
}
