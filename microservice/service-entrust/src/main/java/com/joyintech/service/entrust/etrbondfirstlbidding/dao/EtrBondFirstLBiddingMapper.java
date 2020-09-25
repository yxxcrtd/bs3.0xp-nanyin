/*
 * 文件名：EtrBondFirstBiddingMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yangjm
 * 修改时间：2019年5月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.entrust.etrbondfirstlbidding.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.EtrEntrustBidPrice;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.entrust.etrbondfirstlbidding.dto.EtrBondFirstLBiddingDto;

/**
 * 债券一级投标委托 查询mapper
 * 〈功能详细描述〉
 * @author yangjm
 * @version 1.0 2019年5月6日
 * @see EtrBondFirstLBiddingMapper
 * @since 1.0
 */
@Component
public interface EtrBondFirstLBiddingMapper {

    /**
     * 债券一级投标委托  分页查询
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<EtrBondFirstLBiddingDto> selectForPage(Map<String, Object> pageParam);

    /**
     * 
     * 获取组合代码联想文本框信息
     * <功能详细描述>
     * @param
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
    List<SuggestDto> getfinprodId(Map<String, Object> param);
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
    List<SelectOptionDto> getentrustIdAdd(Map<String, Object> param);
    
    /**
     * 
     * 通过主键查询修改信息
     * <功能详细描述>
     * @param
     * @return
     * @see
     * @since 1.0
     */ 
    EtrBondFirstLBiddingDto selectByKey(Map<String, Object> map);

    /**
     * 查询投标标位
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<EtrEntrustBidPrice> selectBidPriceByKey(Map<String, Object> map);
    /**
     *
     * 获取新建界面的主承销商下拉框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getMainConsignee(Map<String, Object> param);

}
