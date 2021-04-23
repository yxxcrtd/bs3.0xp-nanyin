package com.joyintech.background.component.common.pack.common.util;

/**
 * @author 何阳阳
 */
public class PageUtil {

    private static ThreadLocal<String> sortFieldThreadLocal = new ThreadLocal<>();

    private static ThreadLocal<String> sortDespThreadLocal = new ThreadLocal<>();

    public static void setOrderField(String field) {
        sortFieldThreadLocal.set(field);
    }

    public static void setOrderDirection(String direction) {
        sortDespThreadLocal.set(direction);
    }

    public static String getOrderField() {
        String field = sortFieldThreadLocal.get();
        sortFieldThreadLocal.remove();
        return field;
    }

    public static String getOrderDirection() {
        String direction = sortDespThreadLocal.get();
        sortDespThreadLocal.remove();
        return direction;
    }
}
