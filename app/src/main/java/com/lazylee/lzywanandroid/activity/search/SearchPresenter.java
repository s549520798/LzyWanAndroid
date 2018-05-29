package com.lazylee.lzywanandroid.activity.search;

import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;


import com.lazylee.lzywanandroid.App;
import com.lazylee.lzywanandroid.data.entity.HotKey;
import com.lazylee.lzywanandroid.data.entity.Page;
import com.lazylee.lzywanandroid.data.entity.SearchHistory;
import com.lazylee.lzywanandroid.data.greendao.DaoSession;
import com.lazylee.lzywanandroid.data.greendao.HotKeyDao;
import com.lazylee.lzywanandroid.data.greendao.SearchHistoryDao;
import com.lazylee.lzywanandroid.net.Api;
import com.lazylee.lzywanandroid.net.ServiceResult;
import com.lazylee.lzywanandroid.net.WanAndroidService;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.lazylee.lzywanandroid.tools.StateHelper.isNetworkAvaiable;

public class SearchPresenter implements SearchContract.Presenter {

    private static final String TAG = "SearchPresenter";
    private SearchContract.View mView;
    private WanAndroidService wanAndroidService;

    public SearchPresenter(SearchContract.View view) {
        this.mView = view;
        mView.setPresenter(this);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        wanAndroidService = retrofit.create(WanAndroidService.class);
    }

    @Override
    public void search(@NonNull String s) {
        mView.showProgressBar(true);
        SearchHistoryDao historyDao = App.getInstance().getDaoSession().getSearchHistoryDao();
        historyDao.insertOrReplace(new SearchHistory(s,new Date().getTime()));
//测试接口返回结果
//        mView.showMessage("search action clicked : " + s);
//                    new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    OkHttpClient client = new OkHttpClient();
//                    RequestBody body = new FormBody.Builder()
//                            .add("k",s)
//                            .build();
//                    Request request = new Request.Builder()
//                            .url(Api.API_SEARCH)
//                            .post(body)
//                            .build();
//                    Response response = null;
//                    Message message = new Message();
//                    try {
//                        response = client.newCall(request).execute();
//                        if(response.isSuccessful()){
//                            String s = response.body().string();
//                            ResponseBody responseBody = response.body();
//                            Log.d(TAG, "run: " + s);
//                        }else {
//
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            }).start();
        wanAndroidService.search(0,s)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ServiceResult<Page>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ServiceResult<Page> pageServiceResult) {
                        Page page = pageServiceResult.getData();
                        mView.addSearchResult(page);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showProgressBar(false);
                    }

                    @Override
                    public void onComplete() {
                        mView.showProgressBar(false);
                        mView.showResultView(true);
                    }
                });
    }

    @Override
    public void getHotKey() {
        HotKeyDao hotKeyDao = App.getInstance().getDaoSession().getHotKeyDao();
        if (isNetworkAvaiable()) {
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
                                for (HotKey hotkey : hotKeys
                                        ) {
                                    mView.addChip(hotkey.getName());
                                    hotKeyDao.insertOrReplace(hotkey);
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

        } else {
            //TODO 在数据库中查找 hot key
            List<HotKey> list = hotKeyDao.queryBuilder().list();
            if (list != null && list.size() != 0) {
                for (HotKey hotkey : list
                        ) {
                    mView.addChip(hotkey.getName());
                }
            } else {
                mView.showMessage("无法连接到网络！");
            }

        }

    }

    @Override
    public void getSearchHistory() {

    }
}
