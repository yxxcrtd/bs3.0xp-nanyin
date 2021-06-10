/*
 * 文件名：AssetCommonMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.basicdata.asset.common.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.common.base.entity.FinProductClass;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 *  资产端共通
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月25日
 * @see AssetCommonMapper
 * @since 1.0
 */
@Repository
public interface ValAssetCommonMapper {

    /**
     * 获取资产id联想文本框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SuggestDto> getAssetId(Map<String, Object> param);

    /**
     * 获取资产联想下拉框
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getAssetIdOption(Map<String, Object> map);

    List<SelectOptionDto> getCustomer(Map<String, Object> param);

    List<SelectOptionDto> selectCalendarId(Map<String, Object> param);


    /**
     * 查询核算分类
     * <功能详细描述>
     * @param string
     * @see
     * @since 1.0
     */
    public String getFinanceProductClass(FinProductClass finProductClass);

    /**
     * 查询税务分类
     * <功能详细描述>
     * @param string
     * @see
     * @since 1.0
     */
    public String getFinanceProductTaxClass(FinProductClass finProductClass);
}
