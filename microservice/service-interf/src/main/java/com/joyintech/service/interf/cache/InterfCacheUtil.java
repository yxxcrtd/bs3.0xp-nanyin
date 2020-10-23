/*
 * 文件名：InterfCacheUtil.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年10月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.cache;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;

/**
 * 缓存工具类
 * 
 * @author zhangrui1
 * @version 1.0 2018年10月26日
 * @see InterfCacheUtil
 * @since 1.0
 */
@Component("backInterfCacheUtil")
public class InterfCacheUtil {

    /**
     * 接口系统参数缓存
     */
    private static Cache interfCache;

    /**
     * 接口缓存Key
     */
    private static final String INTERF_CACHE = "INTERF_CACHE";

    /**
     * 平台日期Key
     */
    public static final String SYS_DATE = "SYS_DATE";

    /**
     * 设置缓存管理者
     * 
     * @param cacheManager 缓存管理者
     * @see
     * @since 1.0
     */
    @Autowired
    protected void setCacheManager(CacheManager cacheManager) {
        if (null == cacheManager) {
            throw new RuntimeException("请配置CacheManager.");
        }
        else {
            interfCache = cacheManager.getCache("appCache");
        }
    }

    /**
     * 将系统参数存入缓存
     * 
     * @param value 参数值
     * @see
     * @since 1.0
     */
    public static void putInterfCache(Map<String, Object> value) {
        interfCache.put(INTERF_CACHE, value);
    }

    /**
     * 获取系统参数的值
     * 
     * @param key 系统参数Code
     * @return 系统参数的值
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    public static String getSysParam(String key) {
        Map<String, Object> map = interfCache.get(INTERF_CACHE, Map.class);
        return map == null ? StringUtil.EMPTY : (String)map.get(key);
    }

    /**
     * 获取平台日期
     * 
     * @return 平台日期 yyyyMMdd
     * @see
     * @since 1.0
     */
    public static String getSysDate() {
        return getSysParam(SYS_DATE);
    }
}
