package com.lazylee.lzywanandroid.activity.main.home;

import android.support.v7.widget.RecyclerView;

import com.lazylee.lzywanandroid.adapter.ArticleAdapter;
import com.lazylee.lzywanandroid.entity.Page;
import com.lazylee.lzywanandroid.entity.User;
import com.lazylee.lzywanandroid.net.Api;
import com.lazylee.lzywanandroid.net.ServiceResult;
import com.lazylee.lzywanandroid.net.WanAndroidService;
import com.lazylee.lzywanandroid.tools.Logger;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * home fragment presenter
 * Created by lazylee on 2018/4/10.
 */

public class HomePresenter implements HomeContarct.Presenter {
    private HomeContarct.View mView;

    public HomePresenter(HomeContarct.View view) {
        this.mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void loadArticles(final ArticleAdapter adapter) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        WanAndroidService wanAndroidService = retrofit.create(WanAndroidService.class);
        wanAndroidService.getArticles(1)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ServiceResult<Page>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ServiceResult<Page> pageServiceResult) {
                        Page page = pageServiceResult.getData();
                        if (page != null &&!page.getDatas().isEmpty()){
                            mView.showStateView(false);
                            adapter.updateArticles(page.getDatas());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
