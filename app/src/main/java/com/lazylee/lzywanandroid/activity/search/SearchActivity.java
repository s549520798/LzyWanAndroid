package com.lazylee.lzywanandroid.activity.search;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;


import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.lazylee.lzywanandroid.R;
import com.lazylee.lzywanandroid.adapter.ArticleAdapter;
import com.lazylee.lzywanandroid.adapter.HotKeyAdapter;
import com.lazylee.lzywanandroid.adapter.SearchHistoryAdapter;
import com.lazylee.lzywanandroid.data.entity.Article;
import com.lazylee.lzywanandroid.data.entity.Page;
import com.lazylee.lzywanandroid.tools.Logger;
import com.lazylee.lzywanandroid.view.LzyToast;
import com.lazylee.lzywanandroid.view.divider.ArticleRecycleDivider;

import java.util.ArrayList;

import static com.lazylee.lzywanandroid.tools.StateHelper.hideSoftKeyboard;


public class SearchActivity extends AppCompatActivity implements SearchContract.View, View.OnClickListener {

    private static final String TAG = "SearchActivity";


    private SearchContract.Presenter mPresenter;
    private HotKeyAdapter mHotKeyAdapter;
    private boolean isCloseBtnShowing = true;
    private boolean isOptionsViewShowing = false;
    private boolean isResultViewShowing = false;
    private boolean isEmptyResultShowing = false;
    private boolean isHotKeyObtained = false;
    private boolean isHistoryObtained = false;

    private RelativeLayout mSearchView;
    private ImageButton mBackBtn;
    private ImageButton mCloseBtn;
    private ImageButton mSearchBtn;
    private View mDividerView;
    private ProgressBar mProgressBar;
    private RecyclerView mResultView;
    private ChipGroup mHotKeyView;
    private RecyclerView mHistoryView;
    private LinearLayout mOptionsView;
    private EditText mSearchEdit;
    private FrameLayout mEmptyResultView;

    private ArticleAdapter mResultAdapter;
    ArrayList<Article> articles = new ArrayList<>();
    private SearchHistoryAdapter mHistoryAdapter;

    private void initViews() {
        mSearchView = findViewById(R.id.searchView);
        mBackBtn = findViewById(R.id.backBtn);
        mCloseBtn = findViewById(R.id.closeBtn);
        mSearchBtn = findViewById(R.id.searchBtn);
        mDividerView = findViewById(R.id.dividerView);
        mProgressBar = findViewById(R.id.progressBar);
        mResultView = findViewById(R.id.recycler_result);
        mHotKeyView = findViewById(R.id.reflow_group);
        mHistoryView = findViewById(R.id.recycler_history);
        mOptionsView = findViewById(R.id.search_options);
        mSearchEdit = findViewById(R.id.searchEdit);
        mEmptyResultView = findViewById(R.id.empty_result_view);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        initViews();
        setPresenter(new SearchPresenter(this));
        showSearchAndCloseBtn(false);
        showOptionsView(true);
        setSearchBarElevation();
        mSearchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Logger.d(TAG, "beforeTextChanged : s = " + s + "s.length = " + s.toString().length());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Logger.d(TAG, "onTextChanged : s = " + s + "s.length = " + s.length());
                if (s.length() == 0) {
                    showSearchAndCloseBtn(false);
                } else if (s.length() >= 1) {
                    showSearchAndCloseBtn(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                Logger.d(TAG, "afterTextChanged : s = " + s);
            }
        });
        //响应soft keyboard  中“搜索”按钮的点击
        mSearchEdit.setOnEditorActionListener((v, actionId, event) -> {
            boolean handled = false;
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                mPresenter.search(v.getText().toString());
                hideSoftKeyboard(mSearchEdit);
                handled = true;
            }
            return handled;
        });
        mCloseBtn.setOnClickListener(this);
        mBackBtn.setOnClickListener(this);
        mSearchView.setOnClickListener(this);
        mHotKeyView.setSingleSelection(true);
        mResultAdapter = new ArticleAdapter(articles);
        mResultView.setLayoutManager(new LinearLayoutManager(this));
        mResultView.setAdapter(mResultAdapter);
        mResultView.addItemDecoration(new ArticleRecycleDivider(getResources()
                .getColor(R.color.colorRecycleDivider)));

        mHistoryAdapter = new SearchHistoryAdapter();
        mHistoryView.setLayoutManager(new LinearLayoutManager(this));
        mHistoryView.setAdapter(mHistoryAdapter);
        mHistoryView.addItemDecoration(new ArticleRecycleDivider(getResources()
                .getColor(R.color.colorRecycleDivider)));
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!isHotKeyObtained) {
            mPresenter.getHotKey();
            isHotKeyObtained = true;
        }
        if (!isHistoryObtained){
            mPresenter.getSearchHistory(mHistoryAdapter);
            isHistoryObtained = true;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void setSearchBarElevation() {
        // NOTE : elevation 设置失效问题， 要给控件或者layout设置background
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mSearchView.setElevation(4);
        }
    }


    @Override
    public void setPresenter(SearchContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showMessage(String msg, int type) {
        LzyToast.showToast(msg, type);
    }

    @Override
    public void showMessage(String msg) {
        LzyToast.showMessage(msg, 1500);
    }

    @Override
    public void showProgressBar(boolean show) {
        mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showSearchAndCloseBtn(boolean show) {
        if (isCloseBtnShowing == show) return;
        mCloseBtn.setVisibility(show ? View.VISIBLE : View.GONE);
        mSearchBtn.setVisibility(show ? View.VISIBLE : View.GONE);
        mDividerView.setVisibility(show ? View.VISIBLE : View.GONE);
        isCloseBtnShowing = show;
    }

    @Override
    public void showOptionsView(boolean show) {
        if (isOptionsViewShowing == show) return;
        mOptionsView.setVisibility(show ? View.VISIBLE : View.GONE);
        isOptionsViewShowing = show;
    }

    @Override
    public void showResultView(boolean show) {
        if (isResultViewShowing == show) return;
        mResultView.setVisibility(show ? View.VISIBLE : View.GONE);
        isResultViewShowing = show;
    }

    @Override
    public void showEmptyResultView(boolean show) {
        if (isEmptyResultShowing == show) return;
        mEmptyResultView.setVisibility(show ? View.VISIBLE : View.GONE);
        isEmptyResultShowing = show;
    }

    @Override
    public void addChip(String chipText) {
        Chip chip = (Chip) getLayoutInflater().inflate(R.layout.recycle_hotkey_item, mHotKeyView, false);
        chip.setChipText(chipText);
        mHotKeyView.addView(chip);
    }

    @Override
    public void addSearchResult(Page page) {
        mResultAdapter.updateArticles(page.getDatas());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backBtn:
                //TODO 查看软键盘的状态，当软键盘显示时，先隐藏软键盘
                onBackPressed();
                break;
            case R.id.closeBtn:
                mSearchEdit.setText(null);
                break;
            case R.id.searchBtn:

                break;
            default:
                break;
        }
    }
}
