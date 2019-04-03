package com.lazylee.lzywanandroid.net

/**
 * wan android api
 * Created by lazylee on 2018/3/16.
 */

object Api {
    /**
     * base url
     */
    val API_BASE_URL = "http://www.wanandroid.com"

    /**
     * method : post;     param : username , password;
     */
    val API_LOGIN = "http://www.wanandroid.com/user/login"
    /**
     * method : post;     param : username , password , repassword;
     */
    val API_REGISTER = "http://www.wanandroid.com/user/register"
    /**
     * method : get;     param : 页码，拼接在连接中，从0开始;
     */
    val API_ARTICLE = "http://www.wanandroid.com/article/list/0/json"
    /**
     * method post; param: k 关键词，
     */
    val API_SEARCH = "http://www.wanandroid.com/article/query/0/json"
    /**
     * method get; 获取project 分类
     */
    val API_PROJECT_CATEGORY = "$API_BASE_URL/project/tree/json"
    /**
     * method get; 拼接cid (project category 的id)
     */
    val API_PROJECTS = "$API_BASE_URL/project/list/1/json?cid=294"
}

