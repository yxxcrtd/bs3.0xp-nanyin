/*
 * 文件名：ValRepurchaseAssetQuery002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangqidong
 * 修改时间：2018年10月22日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.assets.repurchase.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.assets.repurchase.dao.ValRepurchaseAssetQuery002Mapper;
import com.joyintech.service.valuation.assets.repurchase.dto.ValRepurchaseAssetQuery001Dto;

/**
 * 回购类资产详细页面查看
 * @author wangqidong
 * @version 1.0 2018年10月22日
 * @see ValRepurchaseAssetQuery002Service
 * @since 1.0
 */
@Service(value="serviceValRepurchaseAssetQuery002Service")
@Transactional(rollbackFor = Exception.class)
public class ValRepurchaseAssetQuery002Service {
    /**
     * mapper
     */
    @Autowired
    private ValRepurchaseAssetQuery002Mapper mapper;

    /**
     * 抵押品信息查询
     * @param map map
     * @return List<ValRepurchaseAssetQuery001Dto>
     * @see
     * @since 1.0
     */
    public List<ValRepurchaseAssetQuery001Dto> queryCollateralinfo(Map<String, Object> map) {       
        return mapper.queryCollateralinfo(map);
    }

    /**
     * 根据产品findprodid获取回购类资产详细页面
     * @param map map
     * @return ValRepurchaseAssetQuery001Dto
     * @see
     * @since 1.0
     */
    public ValRepurchaseAssetQuery001Dto queryRepassetByfinprodId(Map<String, Object> map) {
        
        return mapper.queryRepassetByfinprodId(map);
    }
    
    /**
     * 获取交易日历下拉框
     * @param map map
     * @return ValRepurchaseAssetQuery001Dto
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getCalendarInfoList() {
        return mapper.getCalendarInfoList();
    }

}
