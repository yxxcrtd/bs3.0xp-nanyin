/*
 * 文件名：ValRepurchaseAssetQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangqidong
 * 修改时间：2018年10月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.assets.repurchase.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.assets.repurchase.dto.ValRepurchaseAssetQuery001Dto;

/**
 * 回购类资产查询mapper
 * 
 * @author wangqidong
 * @version 1.0 2018年10月19日
 * @see ValRepurchaseAssetQuery002Mapper
 * @since 1.0
 */
@Repository(value="serviceValRepurchaseAssetQuery002Mapper")
public interface ValRepurchaseAssetQuery002Mapper {

    /**
     * 抵押品信息查询
     * 
     * @param map map
     * @return List<ValRepurchaseAssetQuery001Dto>
     * @see
     * @since 1.0
     */
    List<ValRepurchaseAssetQuery001Dto> queryCollateralinfo(Map<String, Object> map);

    /**
     * 根据产品findprodid获取回购类资产详细页面
     * 
     * @param map map
     * @return ValRepurchaseAssetQuery001Dto
     * @see
     * @since 1.0
     */
    ValRepurchaseAssetQuery001Dto queryRepassetByfinprodId(Map<String, Object> map);
    
    /**
     * 检索交易日历下拉框
     * 
     * @return 交易日历信息List
     * @since 1.0
     */
    List<SelectOptionDto> getCalendarInfoList();
}
