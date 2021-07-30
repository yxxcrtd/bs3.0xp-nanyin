/*
 * 文件名：InterfAPPListener.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年10月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.riskrdsinf.cache;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.joyintech.framework.listener.AppStartupListener;
import com.joyintech.service.riskrdsinf.cache.service.InterfDataDictService;
import com.joyintech.service.riskrdsinf.cache.service.SysParamInfService;

/**
 * 服务启动监听类
 * 
 * @author zhangrui1
 * @version 1.0 2018年10月26日
 * @see InterfAppListener
 * @since 1.0
 */
@Component
public class InterfAppListener extends TimerTask implements AppStartupListener {

    /**
     * 缓存刷新时间，默认为20分钟
     */
    private long cacheRefreshTime = 20 * 60 * 1000L;

    /**
     * 系统参数Service
     */
    @Autowired(required = false)
    private SysParamInfService paraService;

    /**
     * 接口数据字典处理Service
     */
    @Autowired(required = false)
    private InterfDataDictService dictService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void onApplicationStartup(ContextRefreshedEvent event) {
        long time = getCacheRefreshTime();
        if (time > 0) {
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(this, 1000, time);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        // 系统参数缓存
        InterfCacheUtil.putInterfCache(interfCache());
        // 数据字典对应表缓存
        InterfCacheUtil.putDictListCache(dictService.selectAllInfDictList());
    }

    /**
     * 取得系统参数缓存内容
     * 
     * @return 系统参数缓存内容
     * @see
     * @since 1.0
     */
    public Map<String, Object> interfCache() {
        Map<String, Object> resultMap = paraService.interfCache();
        String sysDate = paraService.getSysDate();
        resultMap.put(InterfCacheUtil.SYS_DATE, sysDate);
        return resultMap;
    }

    /**
     * 缓存刷新时间（单位：毫秒）
     * 
     * @return 毫秒
     */
    public long getCacheRefreshTime() {
        return cacheRefreshTime;
    }

    /**
     * 设置缓存刷新时间
     * 
     * @param cacheRefreshTime 毫秒数
     */
    public void setCacheRefreshTime(long cacheRefreshTime) {
        this.cacheRefreshTime = cacheRefreshTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void afterStartup(ApplicationContext arg0) {
        // Nothing to do
    }
}
