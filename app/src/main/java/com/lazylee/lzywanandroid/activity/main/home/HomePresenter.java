package com.lazylee.lzywanandroid.activity.main.home;

import com.lazylee.lzywanandroid.adapter.ArticleAdapter;
import com.lazylee.lzywanandroid.data.entity.Page;
import com.lazylee.lzywanandroid.data.greendao.DaoMaster;
import com.lazylee.lzywanandroid.net.Api;
import com.lazylee.lzywanandroid.net.ServiceResult;
import com.lazylee.lzywanandroid.net.WanAndroidService;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
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

    private WanAndroidService wanAndroidService;

    private boolean isOver;  //判断是否没有更多的请求页数
    private static int requestPage;  //下一次请求的页数。

    HomePresenter(HomeContarct.View view) {
        this.mView = view;
        mView.setPresenter(this);
        initWanAndroidService();
    }



    @Override
    public void initArticles(ArticleAdapter adapter) {
        mView.showProgressIndicator(true);
        //初始化首页文章
        wanAndroidService.getArticles(0)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ServiceResult<Page>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ServiceResult<Page> pageServiceResult) {
                        if (pageServiceResult.getErrorCode() < 0){
                            mView.showMessage(pageServiceResult.getErrorMsg());
                        }else {
                            Page page = pageServiceResult.getData();
                            isOver = page.isOver();
                            requestPage = page.getCurPage();
                            if (!page.getDatas().isEmpty()){
                                adapter.initArticles(page.getDatas());
                                mView.showProgressIndicator(false);
                                mView.showStateView(false);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showMessage(e.getMessage());
                        mView.showProgressIndicator(false);
                        mView.showStateEmptyView(true);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void updateArticles(final ArticleAdapter adapter,int page) {
        //上拉刷新
        wanAndroidService.getArticles(page)
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
                            adapter.updateArticles(page.getDatas());
                            mView.showUpLoadMore(false);
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
    public void loadMoreArticles(ArticleAdapter adapter, int page) {
        //TODO 下拉 加载更多
    }

    private void initWanAndroidService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        wanAndroidService = retrofit.create(WanAndroidService.class);
    }
}
