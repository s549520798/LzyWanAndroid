package com.lazylee.lzywanandroid.net;

import com.lazylee.lzywanandroid.data.entity.Banner;
import com.lazylee.lzywanandroid.data.entity.CommonWebsite;
import com.lazylee.lzywanandroid.data.entity.HotKey;
import com.lazylee.lzywanandroid.data.entity.Page;
import com.lazylee.lzywanandroid.data.entity.ProjectCategory;
import com.lazylee.lzywanandroid.data.entity.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * retrofit service
 * Created by lazylee on 2018/3/20.
 */

public interface WanAndroidService {

    @POST("user/login/")
    Observable<ServiceResult<User>> login(@Query("username") String username, @Query("password") String password);

    @POST("user/register/")
    Observable<ServiceResult<User>> register(@Query("username") String username, @Query("password") String password,
                                             @Query("repassword") String repasssword);

    @GET("/banner/json")
    Observable<ServiceResult<List<Banner>>> getBanner();

    @GET("/article/list/{page}/json")
    Observable<ServiceResult<Page>> getArticles(@Path("page") int page);

    @GET("/friend/json")
    Observable<List<CommonWebsite>> getCommonWebsites();

    @GET("/hotkey/json")
    Observable<ServiceResult<List<HotKey>>> getHotKeys();

    @POST("/article/query/{page}/json")
    Observable<ServiceResult<Page>> search(@Path("page") int page, @Query("k") String k);

    @GET("/project/tree/json")
    Observable<ServiceResult<List<ProjectCategory>>> getProjectCategories();

    @GET("/project/list/{page}/json")
    Observable<ServiceResult<Page>> getProjects(@Path("page") int page, @Query("cid") int cid);
}
