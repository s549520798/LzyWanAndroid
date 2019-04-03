package com.lazylee.lzywanandroid.net

import com.lazylee.lzywanandroid.data.entity.Banner
import com.lazylee.lzywanandroid.data.entity.CommonWebsite
import com.lazylee.lzywanandroid.data.entity.HotKey
import com.lazylee.lzywanandroid.data.entity.Page
import com.lazylee.lzywanandroid.data.entity.ProjectCategory
import com.lazylee.lzywanandroid.data.entity.User

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * retrofit service
 * Created by lazylee on 2018/3/20.
 */

interface WanAndroidService {

    @get:GET("/banner/json")
    val banner: Observable<ServiceResult<List<Banner>>>

    @get:GET("/friend/json")
    val commonWebsites: Observable<List<CommonWebsite>>

    @get:GET("/hotkey/json")
    val hotKeys: Observable<ServiceResult<List<HotKey>>>

    @get:GET("/project/tree/json")
    val projectCategories: Observable<ServiceResult<List<ProjectCategory>>>

    @POST("user/login/")
    fun login(@Query("username") username: String, @Query("password") password: String): Observable<ServiceResult<User>>

    @POST("user/register/")
    fun register(@Query("username") username: String, @Query("password") password: String,
                 @Query("repassword") repasssword: String): Observable<ServiceResult<User>>

    @GET("/article/list/{page}/json")
    fun getArticles(@Path("page") page: Int): Observable<ServiceResult<Page>>

    @POST("/article/query/{page}/json")
    fun search(@Path("page") page: Int, @Query("k") k: String): Observable<ServiceResult<Page>>

    @GET("/project/list/{page}/json")
    fun getProjects(@Path("page") page: Int, @Query("cid") cid: Int): Observable<ServiceResult<Page>>
}
