package com.joyintech.service.riskrdsinf.tool.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.service.riskrdsinf.cache.InterfCacheUtil;
import com.joyintech.service.riskrdsinf.cache.service.InterfDataDictService;
import com.joyintech.service.riskrdsinf.cache.service.SysParamInfService;

/**
 * 刷新缓存
 * 
 * @author zy_zhangrui
 *
 */
@Service
public class RefreshCacheService  {

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

	public void execute() {
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
}
