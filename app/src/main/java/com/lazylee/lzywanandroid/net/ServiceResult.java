package com.lazylee.lzywanandroid.net;

import com.google.gson.JsonObject;

import java.io.Serializable;

/**
 * 后台返回数据存放
 * Created by lazylee on 2018/3/21.
 */

public class ServiceResult<T> implements Serializable{


    private int errorCode;
    private String errorMsg;
    private T data;


    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
