package com.lazylee.lzywanandroid.net

import com.google.gson.JsonObject

import java.io.Serializable

/**
 * 后台返回数据存放
 * Created by lazylee on 2018/3/21.
 */

class ServiceResult<T> : Serializable {

    var errorCode: Int = 0
    var errorMsg: String = ""
    var data: T? = null
}
