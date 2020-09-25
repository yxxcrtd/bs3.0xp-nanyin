package com.joyintech.invest.interf.common.core.cache;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.joyintech.invest.interf.common.core.service.InvestInterfDataDictService;
import com.joyintech.invest.interf.common.core.service.SysParamInvestInfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.joyintech.framework.listener.AppStartupListener;

/**
 * @program: tams-base
 * @description: 接口应用监听
 * @author: 王波
 * @create: 2020-02-25 15:57
 */
@Component
public class InvestInterfAppListener extends TimerTask implements AppStartupListener {
    /**
     * 缓存刷新时间，默认为20分钟
     */
    private long cacheRefreshTime = 20 * 60 * 1000L;

    /**
     * 系统参数Service
     */
    @Autowired(required = false)
    private SysParamInvestInfService paraService;

    /**
     * 接口数据字典处理Service
     */
    @Autowired(required = false)
    private InvestInterfDataDictService dictService;


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

    @Override
    public void afterStartup(ApplicationContext applicationContext) {

    }

    @Override
    public void run() {
        // 系统参数缓存
        InvestInterfCacheUtil.putInterfCache(interfCache());
        // 数据字典对应表缓存
        InvestInterfCacheUtil.putDictListCache(dictService.selectAllInfDictList());
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
     * 取得系统参数缓存内容
     *
     * @return 系统参数缓存内容
     * @see
     * @since 1.0
     */
    public Map<String, Object> interfCache() {
        Map<String, Object> resultMap = paraService.interfCache();
        String sysDate = paraService.getSysDate();
        resultMap.put(InvestInterfCacheUtil.SYS_DATE, sysDate);
        return resultMap;
    }

    /**
     * 设置缓存刷新时间
     *
     * @param cacheRefreshTime 毫秒数
     */
    public void setCacheRefreshTime(long cacheRefreshTime) {
        this.cacheRefreshTime = cacheRefreshTime;
    }
}
