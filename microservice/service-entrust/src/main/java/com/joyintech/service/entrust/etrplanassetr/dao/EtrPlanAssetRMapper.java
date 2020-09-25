/*
 * 文件名：EtrPlanAssetMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangjun
 * 修改时间：2018年9月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.entrust.etrplanassetr.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.entrust.etrplanassetr.dto.EtrPlanAssetRDto;

/**
 * 计划类利率委托Mapper
 * 〈功能详细描述〉
 * @author zhangjun
 * @version 1.0 2018年9月25日
 * @see EtrPlanAssetRMapper
 * @since 1.0
 */
public interface EtrPlanAssetRMapper {
    /**
     * 
     * 计划类利率委托查询
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<EtrPlanAssetRDto> selectForPage(Map<String, Object> param);
    /**
     * 
     * 获取组合代码联想文本框信息
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SuggestDto> getentrustId(Map<String, Object> map);
    /**
     * 
     * 获取资产代码联想文本框信息
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SuggestDto> getfinprodId(Map<String, Object> map);
    /**
     * 
     * 获取新建界面的资产代码联想下拉框框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getfinprodIdForAdd(Map<String, Object> param);
    /**
     * 
     * 获取新建界面的组合代码联想下拉框框
     * <功能详细描述>
     * @param param 
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getentrustIdAdd(Map<String, Object> map);
    /**
     * 
     * 通过主键查询修改信息
     * <功能详细描述>
     * @param entrustId
     * @param portfolioId 
     * @return
     * @see
     * @since 1.0
     */
    EtrPlanAssetRDto selectByKey(@Param("entrustId") String entrustId);
    /**
     * 通过资产代码获取资产类型
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    String searchType(String finprodId);
    /**
     * 
     * 判断是否内部交易对手
     * <功能详细描述>
     * @param counterId
     * @return
     * @see
     * @since 1.0
     */
    String judgeCounterType(@Param("counterId") String counterId);
}
