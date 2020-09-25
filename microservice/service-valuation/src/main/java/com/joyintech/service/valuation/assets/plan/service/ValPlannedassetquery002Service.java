/*
 * 文件名：ValBondassetquery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年7月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.assets.plan.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.service.valuation.assets.plan.dao.ValPlannedassetquery002Mapper;
import com.joyintech.service.valuation.assets.plan.dto.ValPlannedassetquery001Dto;

/**
 * 
 * 债券类资产查询
 * @author zhangzhao
 * @version 1.0 2018年7月20日
 * @see ValPlannedassetquery002Service
 * @since 1.0
 */
@Service(value="serviceValPlannedassetquery002Service")
@Transactional(rollbackFor = Exception.class)
public class ValPlannedassetquery002Service extends CommonExportService<ValPlannedassetquery001Dto> {
    
    /**
     * ValPlannedassetquery002Mapper
     */
    @Autowired
    private ValPlannedassetquery002Mapper mapper;
    
    /**
     * 根据产品findprodid获取债券详细信息
     * <功能详细描述>
     * @param map
     * @see
     * @since 1.0
     */
    public ValPlannedassetquery001Dto queryBondasetByfinprodId(Map<String, Object> map) {
        return mapper.queryBondasetByfinprodId(map);
    }

    /**
     * 详情页表单数据的获取-利率报价信息
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public List<ValPlannedassetquery001Dto> queryintrate(Map<String, Object> map) {
        return mapper.queryintrate(map);
    }

    /**
     *  详情页表单数据的获取-付息计划列表
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public List<ValPlannedassetquery001Dto> queryintpay(Map<String, Object> map) {
        return mapper.queryintpay(map);
    }

    /**
     * 详情页表单数据的获取-还本计划列表
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public List<ValPlannedassetquery001Dto> queryrepay(Map<String, Object> map) {
        return mapper.queryrepay(map);
    }

    /**
     * 详情页表单数据的获取-分红计划信息
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public List<ValPlannedassetquery001Dto> querybonus(Map<String, Object> map) {
        return mapper.querybonus(map);
    }

    /**
     * 付息信息
     * 
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public ValPlannedassetquery001Dto queryIntInfo(Map<String, Object> map) {
        return mapper.queryIntInfo(map);
    }
 
}

