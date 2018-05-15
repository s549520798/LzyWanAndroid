package com.lazylee.lzywanandroid.activity.search;

import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;


import com.google.android.material.chip.ChipGroup;
import com.lazylee.lzywanandroid.R;
import com.lazylee.lzywanandroid.adapter.HotKeyAdapter;
import com.lazylee.lzywanandroid.tools.Logger;
import com.lazylee.lzywanandroid.view.LzyToast;



public class SearchActivity extends AppCompatActivity implements SearchContract.View, View.OnClickListener {

    private static final String TAG = "SearchActivity";


    private SearchContract.Presenter mPresenter;
    private HotKeyAdapter mHotKeyAdapter;
    private boolean isCloseBtnShowing = true;
    private boolean isOptionsViewShowing = false;
    private boolean isResultViewShowing = false;

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

    private void initViews(){
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
                handled = true;
            }
            return handled;
        });
        mCloseBtn.setOnClickListener(this);
        mBackBtn.setOnClickListener(this);
        mSearchView.setOnClickListener(this);

        mHotKeyAdapter = new HotKeyAdapter();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        mHotKeyView.setSingleSelection(true);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.getHotKey(mHotKeyAdapter);
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

        if (isResultViewShowing != show) return;
        mResultView.setVisibility(show ? View.GONE : View.VISIBLE);
        isResultViewShowing = !show;
    }

    @Override
    public void showResultView(boolean show) {
        showOptionsView(!show);
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
