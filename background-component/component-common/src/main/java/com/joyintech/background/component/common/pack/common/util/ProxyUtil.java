/*
 * 文件名：ProxyUtil.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2019年3月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.background.component.common.pack.common.util;

import java.lang.reflect.Proxy;

import org.springframework.util.ClassUtils;

/**
 * 
 * @author 何阳阳
 * @version 1.0 2019年3月26日
 * @see ProxyUtil
 * @since 1.0
 */
public final class ProxyUtil {

    public static Class<?> getTargetClass(Class<?> proxyClass) {
        if (Proxy.isProxyClass(proxyClass) || ClassUtils.isCglibProxyClass(proxyClass)) {
            return proxyClass.getSuperclass();
        }
        return proxyClass;
    }
}
