package com.lazylee.lzywanandroid.tools.log;

/**
 * Logger utils
 */
class Utils {

    /**
     * get current method Name
     * @return method name
     */
    static String getCurrentMethodName() {
        try {
            return Thread.currentThread().getStackTrace()[4].getMethodName() + "()";
        } catch (Exception ignored) {
        }
        return "CannotFindMethod";
    }

    /**
     * get current class name
     *
     * @return class name
     */
    static String getCurrentClassName() {
        try {
            String className = Thread.currentThread().getStackTrace()[4].getClassName();
            String[] temp = className.split("[.]");
            className = temp[temp.length - 1];
            return className;
        } catch (Exception ignored) {
        }
        return "CannotFindClass";
    }
}
