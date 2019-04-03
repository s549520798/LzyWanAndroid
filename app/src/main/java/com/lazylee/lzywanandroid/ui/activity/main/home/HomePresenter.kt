package com.lazylee.lzywanandroid.ui.activity.main.home

import com.lazylee.lzywanandroid.ui.adapter.ArticleAdapter
import com.lazylee.lzywanandroid.data.entity.Page
import com.lazylee.lzywanandroid.net.Api
import com.lazylee.lzywanandroid.net.ServiceResult
import com.lazylee.lzywanandroid.net.WanAndroidService
import com.lazylee.lzywanandroid.ui.view.LzyToast

import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * home fragment presenter
 * Created by lazylee on 2018/4/10.
 */

class HomePresenter internal constructor(private val mView: HomeContract.View) : HomeContract.Presenter {

    private var wanAndroidService: WanAndroidService? = null

    private var isOver: Boolean = false  //判断是否没有更多的请求页数

    init {
        mView.setPresenter(this)
        initWanAndroidService()
    }


    override fun initArticles(adapter: ArticleAdapter?) {
        mView.showProgressIndicator(true)
        //初始化首页文章
        wanAndroidService!!.getArticles(0)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<ServiceResult<Page>> {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(pageServiceResult: ServiceResult<Page>) {
                        if (pageServiceResult.errorCode < 0) {
                            mView.showMessage(pageServiceResult.errorMsg, LzyToast.TYPE_ERROR)
                        } else {
                            val page = pageServiceResult.data
                            isOver = page!!.isOver
                            requestPage = page.curPage
                            if (!page.datas!!.isEmpty()) {
                                adapter!!.initArticles(page.datas!!)
                                mView.showProgressIndicator(false)
                                mView.showStateView(false)
                            }
                        }
                    }

                    override fun onError(e: Throwable) {
                        mView.showMessage(e.message.toString(), LzyToast.TYPE_ERROR)
                        mView.showProgressIndicator(false)
                        mView.showStateEmptyView(true)
                    }

                    override fun onComplete() {

                    }
                })

    }

    override fun updateArticles(adapter: ArticleAdapter?) {
        //上拉刷新
        wanAndroidService!!.getArticles(0)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<ServiceResult<Page>> {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(pageServiceResult: ServiceResult<Page>) {
                        val page = pageServiceResult.data
                        if (page != null && !page.datas!!.isEmpty()) {
                            adapter!!.updateArticles(page.datas!!)
                            mView.showUpLoadMore(false)
                        }
                    }

                    override fun onError(e: Throwable) {
                        mView.showMessage(e.message.toString(), LzyToast.TYPE_ERROR)
                    }

                    override fun onComplete() {

                    }
                })

    }

    override fun loadMoreArticles(adapter: ArticleAdapter?) {
        //TODO 下拉 加载更多
        wanAndroidService!!.getArticles(requestPage)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<ServiceResult<Page>> {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(pageServiceResult: ServiceResult<Page>) {
                        if (pageServiceResult.errorCode < 0) {
                            mView.showMessage(pageServiceResult.errorMsg, LzyToast.TYPE_ERROR)
                        } else {
                            val page = pageServiceResult.data
                            isOver = page!!.isOver
                            requestPage = page.curPage
                            if (!page.datas!!.isEmpty()) {
                                adapter!!.loadMoreArticles(page.datas!!)
                            }
                        }
                    }

                    override fun onError(e: Throwable) {
                        mView.showMessage(e.message.toString(), LzyToast.TYPE_ERROR)
                    }

                    override fun onComplete() {

                    }
                })
    }

    private fun initWanAndroidService() {
        val retrofit = Retrofit.Builder()
                .baseUrl(Api.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        wanAndroidService = retrofit.create<WanAndroidService>(WanAndroidService::class.java!!)
    }

    companion object {
        private var requestPage = 0  //下一次请求的页数。
    }
}
