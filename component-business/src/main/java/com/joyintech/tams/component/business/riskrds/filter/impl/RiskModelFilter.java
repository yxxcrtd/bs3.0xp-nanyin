/*
 * 文件名：RiskModelFilter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年9月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.riskrds.filter.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.joyintech.tams.component.business.riskrds.constant.ModelConstant;
import com.joyintech.tams.component.business.riskrds.dto.PageModel;
import com.joyintech.tams.component.business.riskrds.filter.IRiskModelFilter;

/**
 * 风控委托交易模块过滤器
 * 
 * @author zhangrui1
 * @version 1.0 2020年9月19日
 * @see RiskModelFilter
 * @since 1.0
 */
@Component
public class RiskModelFilter implements IRiskModelFilter {

    /**
     * 画面接口字段映射
     */
    private static Map<String, PageModel> modelMap = new HashMap<>();


    /**
     * {@inheritDoc}
     */
    @Override
    public <T> List<Map<String, Object>> filter(String pageId, T dto) {
        PageModel model = modelMap.get(pageId);
        if (null != model) {
            return model.filter(dto);
        }
        return new ArrayList<>();
    }

    @Autowired
    protected void initModelMap() {
        modelMap.clear();
        modelMap.putAll(getModelLocal());
        Map<String, PageModel> mapCache = getModelFromCache();
        if (CollectionUtils.isEmpty(mapCache)) {
            mapCache = getModelFromRedis();
        }
        if (CollectionUtils.isEmpty(mapCache)) {
            mapCache = getModelFromDb();
        }
        if (!CollectionUtils.isEmpty(mapCache)) {
            modelMap.putAll(mapCache);
        }
    }

    /**
     * 从缓存取配置
     * 
     * @return 画面对应接口配置
     */
    protected Map<String, PageModel> getModelFromCache() {
        return new HashMap<>();
    }

    /**
     * 从Redis取配置
     * 
     * @return 画面对应接口配置
     */
    protected Map<String, PageModel> getModelFromRedis() {
        return new HashMap<>();
    }

    /**
     * 从DB取配置
     * 
     * @return 画面对应接口配置
     */
    protected Map<String, PageModel> getModelFromDb() {
        return new HashMap<>();
    }

    /**
     * 本地取配置
     * 
     * @return 画面对应接口配置
     */
    protected Map<String, PageModel> getModelLocal() {
        return ModelConstant.modelMap();
    }
}
