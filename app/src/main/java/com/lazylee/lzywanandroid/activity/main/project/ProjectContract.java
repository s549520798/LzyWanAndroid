package com.lazylee.lzywanandroid.activity.main.project;

import com.lazylee.lzywanandroid.activity.main.project.fragment.ProjectVPFragment;
import com.lazylee.lzywanandroid.data.entity.ProjectCategory;
import com.lazylee.lzywanandroid.mvp.BasePresenter;
import com.lazylee.lzywanandroid.mvp.BaseView;

import java.util.ArrayList;

/**
 * project contract
 */
public interface ProjectContract {

    interface View extends BaseView<Presenter> {

        void showProgressBar(boolean show);

        void addTabs(ArrayList<ProjectCategory> categories);
    }

    interface Presenter extends BasePresenter {
        void initProjectType(ArrayList<ProjectVPFragment> fragments, ArrayList<ProjectCategory> categories);
    }
}
