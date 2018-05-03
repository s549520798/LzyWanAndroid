package com.lazylee.lzywanandroid.activity.search;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.lazylee.lzywanandroid.adapter.HotKeyAdapter;
import com.lazylee.lzywanandroid.data.entity.HotKey;
import com.lazylee.lzywanandroid.net.Api;
import com.lazylee.lzywanandroid.net.ServiceResult;
import com.lazylee.lzywanandroid.net.WanAndroidService;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchPresenter implements SearchContract.Presenter {

    private static final String TAG = "SearchPresenter";
    private SearchContract.View mView;

    public SearchPresenter(SearchContract.View view) {
        this.mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void search(@NonNull String s) {
        mView.showMessage("search action clicked : " + s);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }

    @Override
    public void getHotKey(final RecyclerView.Adapter adapter) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        WanAndroidService wanAndroidService = retrofit.create(WanAndroidService.class);
        wanAndroidService.getHotKeys()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ServiceResult<List<HotKey>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ServiceResult<List<HotKey>> listServiceResult) {
                        if (listServiceResult.getErrorCode() < 0) {
                            mView.showMessage(listServiceResult.getErrorMsg());
                        } else {
                            List<HotKey> hotKeys = listServiceResult.getData();
                            if (adapter instanceof HotKeyAdapter) {
                                ((HotKeyAdapter) adapter).addHotkeys(hotKeys);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showMessage(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void getSearchHistory() {

    }
}
