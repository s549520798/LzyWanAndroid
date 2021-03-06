package com.lazylee.lzywanandroid.ui.activity.search;

import androidx.annotation.NonNull;
import android.util.Log;




import com.lazylee.lzywanandroid.App;
import com.lazylee.lzywanandroid.ui.adapter.SearchHistoryAdapter;
import com.lazylee.lzywanandroid.data.AppDatabase;
import com.lazylee.lzywanandroid.data.dao.HotKeyDao;
import com.lazylee.lzywanandroid.data.dao.SearchHistoryDao;
import com.lazylee.lzywanandroid.data.entity.HotKey;
import com.lazylee.lzywanandroid.data.entity.Page;
import com.lazylee.lzywanandroid.data.entity.SearchHistory;
import com.lazylee.lzywanandroid.net.Api;
import com.lazylee.lzywanandroid.net.ServiceResult;
import com.lazylee.lzywanandroid.net.WanAndroidService;

import java.util.Date;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.lazylee.lzywanandroid.tools.StateHelper.isNetworkAvailable;

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
        mView.showOptionsView(false);
        mView.showResultView(false);
        mView.showEmptyResultView(false);
        SearchHistoryDao historyDao = AppDatabase.getInstance(App.getInstance().getContext()).searchHistoryDao();
        historyDao.insertSearchHistory(new SearchHistory(s,new Date().getTime()));
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
                        Log.d(TAG, "onNext: page size" + page.getDatas().size());
                        if (page == null || page.getDatas().size() == 0){
                            mView.showEmptyResultView(true);
                        }else {
                            mView.addSearchResult(page);
                            mView.showResultView(true);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showMessage(e.getMessage());
                        mView.showProgressBar(false);
                    }

                    @Override
                    public void onComplete() {
                        mView.showProgressBar(false);
                    }
                });
    }

    @Override
    public void getHotKey() {
        HotKeyDao hotKeyDao = AppDatabase.getInstance(App.getInstance().getContext()).hotKeyDao();
        if (isNetworkAvailable()) {
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
                                    hotKeyDao.insertHotKey(hotkey);
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
            List<HotKey> list = hotKeyDao.getAll();
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
    public void getSearchHistory(SearchHistoryAdapter adapter) {
        SearchHistoryDao historyDao = AppDatabase.getInstance(App.getInstance().getContext()).searchHistoryDao();
        List<SearchHistory> histories = historyDao.getAll();
        adapter.updateHistories(histories);
    }
}
