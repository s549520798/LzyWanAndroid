package com.lazylee.lzywanandroid.tools.log;

import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;


/**
 * log tool
 * if in debug model, use android.util.Log to print logs
 * if in release model, keep logs in files and send them to service when zhe size is 5m
 *
 * @author zhiyuanli
 */
public class Logger {

    public static final int LOG_MODEL_UI = 1;    //"-UI"
    public static final int LOG_MODEL_DATA = 2;  //"-DATA"

    public static final int LOG_DETAIL_COMMON = 0; //"-COMMON"
    public static final int LOG_DETAIL_LOGIN = 1; //"-LOGIN"
    public static final int LOG_DETAIL_MAIN = 2; //"-MAIN"
    public static final int LOG_DETAIL_SEARCH = 3; //"-SEARCH"
    public static final int LOG_DETAIL_HISTORY = 4; //"-HISTORY"


    private static final String LOG_TAG_BASE = "WanAndroid";
    private static final String LOG_TAG_MODEL_UI = LOG_TAG_BASE + "-UI";
    private static final String LOG_TAG_MODEL_DATA = LOG_TAG_BASE + "-DATA";
    private static final String LOG_TAG_DETAIL_COMMON = "-COMMON-";
    private static final String LOG_TAG_DETAIL_LOGIN = "-LOGIN-";
    private static final String LOG_TAG_DETAIL_MAIN = "-MAIN-";
    private static final String LOG_TAG_DETAIL_SEARCH = "-SEARCH-";
    private static final String LOG_TAG_DETAIL_HISTORY = "-HISTORY-";


    private static boolean mIsDebuggable;

    public static void setDebuggable(boolean debuggable) {
        mIsDebuggable = debuggable;
    }

    public static void i(int model, int detail, @Nullable String tag, @Nullable Object o) {
        if (mIsDebuggable) {
            Log.i(getLogTag(model, detail, tag) + " || " + getCurrentMethodName(), o != null ? o.toString() : "LOG INFO Message is null");
        }
        //TODO write log to file
    }

    public static void d(int model, int detail, @Nullable String tag, @Nullable Object o) {
        if (mIsDebuggable) {
            Log.d(getLogTag(model, detail, tag) + " || " + getCurrentMethodName(), o != null ? o.toString() : "LOG DEBUG Message is null");
        }
        //TODO write log to file
    }

    public static void e(int model, int detail, @Nullable String tag, @Nullable Object o) {
        if (mIsDebuggable) {
            Log.e(getLogTag(model, detail, tag) + " || " + getCurrentMethodName(), o != null ? o.toString() : "LOG Error Message is null");
        }
        //TODO write log to file
    }

    private static String getLogTag(int model, int detail, @Nullable String tag) {
        String re = "";
        switch (model) {
            case LOG_MODEL_UI:
                re += LOG_TAG_MODEL_UI;
                break;
            case LOG_MODEL_DATA:
                re += LOG_TAG_MODEL_DATA;
                break;
            default:
                re += LOG_TAG_BASE;
                break;
        }

        switch (detail) {
            case LOG_DETAIL_COMMON:
                re += LOG_TAG_DETAIL_COMMON;
                break;
            case LOG_DETAIL_LOGIN:
                re += LOG_TAG_DETAIL_LOGIN;
                break;
            case LOG_DETAIL_MAIN:
                re += LOG_TAG_DETAIL_MAIN;
                break;
            case LOG_DETAIL_SEARCH:
                re += LOG_TAG_DETAIL_SEARCH;
                break;
            case LOG_DETAIL_HISTORY:
                re += LOG_TAG_DETAIL_HISTORY;
                break;
        }

        if (TextUtils.isEmpty(tag)) {
            re += getCurrentClassName();
        } else {
            re += tag;
        }
        return re;

    }

    private static String getCurrentMethodName() {
        try {
            return Thread.currentThread().getStackTrace()[4].getMethodName() + "()";
        } catch (Exception ignored) {
        }
        return "NoFindMethod";
    }

    private static String getCurrentClassName() {
        try {
            String className = Thread.currentThread().getStackTrace()[4].getClassName();
            String[] temp = className.split("[.]");
            className = temp[temp.length - 1];
            return className;
        } catch (Exception ignored) {
        }
        return "NoFindClass";
    }

}
