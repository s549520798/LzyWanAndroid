package com.lazylee.lzywanandroid.net;

/**
 * wan android api
 * Created by lazylee on 2018/3/16.
 */

public class Api {
    /**
     * base url
     */
    public static final String API_BASE_URL = "http://www.wanandroid.com";

    /**
     * method : post;     param : username , password;
     */
    public static final String API_LOGIN = "http://www.wanandroid.com/user/login";
    /**
     * method : post;     param : username , password , repassword;
     */
    public static final String API_REGISTER = "http://www.wanandroid.com/user/register";
    /**
     * method : get;     param : 页码，拼接在连接中，从0开始;
     */
    public static final String API_ARTICLE = "http://www.wanandroid.com/article/list/0/json";
}

