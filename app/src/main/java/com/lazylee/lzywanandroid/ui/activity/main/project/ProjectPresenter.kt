package com.lazylee.lzywanandroid.ui.activity.main.project

import android.util.Log

import com.lazylee.lzywanandroid.ui.activity.main.project.fragment.ProjectVPFragment
import com.lazylee.lzywanandroid.data.entity.ProjectCategory
import com.lazylee.lzywanandroid.net.Api
import com.lazylee.lzywanandroid.net.ServiceResult
import com.lazylee.lzywanandroid.net.WanAndroidService
import com.lazylee.lzywanandroid.ui.view.LzyToast

import java.util.ArrayList

import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ProjectPresenter(private val mView: ProjectContract.View) : ProjectContract.Presenter {

    private var wanAndroidService: WanAndroidService? = null

    init {
        mView.setPresenter(this)
        initWanAndroidService()
    }


    override fun initProjectType(fragments: ArrayList<ProjectVPFragment>, categories: ArrayList<ProjectCategory>) {
        mView.showProgressBar(true)
        wanAndroidService!!.projectCategories
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<ServiceResult<List<ProjectCategory>>> {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(listServiceResult: ServiceResult<List<ProjectCategory>>) {
                        if (listServiceResult.errorCode >= 0) {
                            categories.addAll(listServiceResult.data!!)
                            for (category in categories) {
                                fragments.add(ProjectVPFragment.newInstance(category))
                            }
                            mView.addTabs(categories)
                        } else {
                            mView.showMessage(listServiceResult.errorMsg, LzyToast.TYPE_ERROR)
                        }
                    }

                    override fun onError(e: Throwable) {
                        Log.d(TAG, "onError: " + e.toString())
                        mView.showMessage(e.message, LzyToast.TYPE_ERROR)

                    }

                    override fun onComplete() {
                        mView.showProgressBar(false)
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

        private val TAG = "ProjectPresenter"
    }


}
