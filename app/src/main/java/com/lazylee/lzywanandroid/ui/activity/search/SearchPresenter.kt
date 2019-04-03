package com.lazylee.lzywanandroid.ui.activity.search

import android.util.Log


import com.lazylee.lzywanandroid.App
import com.lazylee.lzywanandroid.ui.adapter.SearchHistoryAdapter
import com.lazylee.lzywanandroid.data.AppDatabase
import com.lazylee.lzywanandroid.data.dao.HotKeyDao
import com.lazylee.lzywanandroid.data.dao.SearchHistoryDao
import com.lazylee.lzywanandroid.data.entity.HotKey
import com.lazylee.lzywanandroid.data.entity.Page
import com.lazylee.lzywanandroid.data.entity.SearchHistory
import com.lazylee.lzywanandroid.net.Api
import com.lazylee.lzywanandroid.net.ServiceResult
import com.lazylee.lzywanandroid.net.WanAndroidService

import java.util.Date

import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

import com.lazylee.lzywanandroid.tools.StateHelper.isNetworkAvailable

class SearchPresenter(private val mView: SearchContract.View) : SearchContract.Presenter {
    private val wanAndroidService: WanAndroidService

    init {
        mView.setPresenter(this)
        val retrofit = Retrofit.Builder()
                .baseUrl(Api.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        wanAndroidService = retrofit.create(WanAndroidService::class.java!!)
    }

    override fun search(s: String) {
        mView.showProgressBar(true)
        mView.showOptionsView(false)
        mView.showResultView(false)
        mView.showEmptyResultView(false)
        val historyDao = AppDatabase.getInstance(App.instance!!.context)!!.searchHistoryDao()
        historyDao.insertSearchHistory(SearchHistory(s, Date().time))
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
        wanAndroidService.search(0, s)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<ServiceResult<Page>> {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(pageServiceResult: ServiceResult<Page>) {
                        val page = pageServiceResult.data
                        Log.d(TAG, "onNext: page size" + page!!.datas!!.size)
                        if (page.datas!!.isEmpty()) {
                            mView.showEmptyResultView(true)
                        } else {
                            mView.addSearchResult(page)
                            mView.showResultView(true)
                        }

                    }

                    override fun onError(e: Throwable) {
                        mView.showMessage(e.message.toString())
                        mView.showProgressBar(false)
                    }

                    override fun onComplete() {
                        mView.showProgressBar(false)
                    }
                })
    }

    override fun getHotKey() {
        val hotKeyDao = AppDatabase.getInstance(App.instance!!.context)!!.hotKeyDao()
        if (isNetworkAvailable) {
            wanAndroidService.hotKeys
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Observer<ServiceResult<List<HotKey>>> {
                        override fun onSubscribe(d: Disposable) {

                        }

                        override fun onNext(listServiceResult: ServiceResult<List<HotKey>>) {
                            if (listServiceResult.errorCode < 0) {
                                mView.showMessage(listServiceResult.errorMsg)
                            } else {
                                val hotKeys = listServiceResult.data
                                for (hotkey in hotKeys!!) {
                                    mView.addChip(hotkey.name)
                                    hotKeyDao.insertHotKey(hotkey)
                                }
                            }
                        }

                        override fun onError(e: Throwable) {
                            mView.showMessage(e.message.toString())
                        }

                        override fun onComplete() {

                        }
                    })

        } else {
            //TODO 在数据库中查找 hot key
            val list = hotKeyDao.all
            if (list.isNotEmpty()) {
                for (hotkey in list) {
                    mView.addChip(hotkey.name)
                }
            } else {
                mView.showMessage("无法连接到网络！")
            }

        }

    }

    override fun getSearchHistory(adapter: SearchHistoryAdapter?) {
        val historyDao = AppDatabase.getInstance(App.instance!!.context)!!.searchHistoryDao()
        val histories = historyDao.all
        adapter!!.updateHistories(histories)
    }

    companion object {

        private val TAG = "SearchPresenter"
    }
}
