package com.lazylee.lzywanandroid.activity.main.project;

import android.util.Log;

import com.lazylee.lzywanandroid.activity.main.project.fragment.ProjectVPFragment;
import com.lazylee.lzywanandroid.data.entity.ProjectCategory;
import com.lazylee.lzywanandroid.net.Api;
import com.lazylee.lzywanandroid.net.ServiceResult;
import com.lazylee.lzywanandroid.net.WanAndroidService;
import com.lazylee.lzywanandroid.view.LzyToast;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProjectPresenter implements ProjectContract.Presenter {

    private static final String TAG = "ProjectPresenter";
    private ProjectContract.View mView;

    private WanAndroidService wanAndroidService;

    public ProjectPresenter(ProjectContract.View view) {
        this.mView = view;
        mView.setPresenter(this);
        initWanAndroidService();
    }


    @Override
    public void initProjectType(ArrayList<ProjectVPFragment> fragments, ArrayList<ProjectCategory> categories) {
        mView.showProgressBar(true);
        wanAndroidService.getProjectCategories()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ServiceResult<List<ProjectCategory>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ServiceResult<List<ProjectCategory>> listServiceResult) {
                        if (listServiceResult.getErrorCode() >= 0) {
                            categories.addAll(listServiceResult.getData());
                            for (ProjectCategory category : categories) {
                                fragments.add(ProjectVPFragment.newInstance(category));
                            }
                            mView.addTabs(categories);
                        } else {
                            mView.showMessage(listServiceResult.getErrorMsg(), LzyToast.TYPE_ERROR);
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.toString());
                        mView.showMessage(e.getMessage(), LzyToast.TYPE_ERROR);

                    }

                    @Override
                    public void onComplete() {
                        mView.showProgressBar(false);
                    }
                });
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
