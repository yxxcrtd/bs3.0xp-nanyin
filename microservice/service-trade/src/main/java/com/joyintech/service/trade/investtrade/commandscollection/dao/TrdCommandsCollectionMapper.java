/*
 * 文件名：TrdCommandsCollectionMapper.java
 * 版权：Copyright 2018-2019 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.1.2
 * 修改人：王贵奇
 * 修改时间：2019年12月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.trade.investtrade.commandscollection.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.trade.investtrade.commandscollection.dto.TrdCommandsCollection001Dto;
import com.joyintech.service.trade.investtrade.commandscollection.dto.TrdCommandsCollection002Dto;
import com.joyintech.service.trade.investtrade.commandscollection.dto.TrdCommandsCollection003Dto;

/**
 *  指令汇总Mapper
 * @author 王贵奇
 * @version 1.0 2019年12月31日
 * @see TrdCommandsCollectionMapper
 * @since 1.0
 */
public interface TrdCommandsCollectionMapper {

    /**
     * 查询汇总指令： 债券，基金，股票
     * @param map 包括资产大类和输入的查询条件
     * @return List<TrdCommandsCollection001Dto> 汇总指令列表
     * @see
     * @since 1.0
     */
    List<TrdCommandsCollection001Dto> selectForCommonPage(Map<String, Object> map);

    /**
     * 查询汇总指令：除 债券，基金，股票
     * @param map 包括资产大类和输入的查询条件
     * @return List<TrdCommandsCollection001Dto> 汇总指令列表
     * @see
     * @since 1.0
     */
    List<TrdCommandsCollection001Dto> selectForPage(Map<String, Object> map);
    
    /**
     * 查询委托详情列表: 债券，基金，股票
     * @param param 查询参数
     * @return List<TrdCommandsCollection002Dto> 委托详情列表
     * @see
     * @since 1.0
     */
    List<TrdCommandsCollection002Dto> selectEntrustCommonList(Map<String, Object> param);
    
    /**
     * 查询委托详情列表: 除 债券，基金，股票
     * @param param 查询参数
     * @return List<TrdCommandsCollection002Dto> 委托详情列表
     * @see
     * @since 1.0
     */
    List<TrdCommandsCollection002Dto> selectEntrustList(Map<String, Object> param);

    /**
     * 资产代码联想查询各类资产对应的资产代码 edit by songyawei  共有类资产：债券，基金，股票
     * @param param 包括资产大类和输入的资产名称
     * @return List<SuggestDto> 联想框下拉选项
     * @see
     * @since 1.0
     */
    List<SuggestDto> getCommomFinprodMarketIds(Map<String, Object> param);
    
    /**
     * 资产代码联想查询各类资产对应的资产代码   edit by songyawei  非共有类资产：除债券，基金，股票 
     * @param param 包括资产大类和输入的资产名称
     * @return List<SuggestDto> 联想框下拉选项
     * @see
     * @since 1.0
     */
    List<SuggestDto> getFinprodMarketIds(Map<String, Object> param);

    /**
     * 委托质押券详情查询
     * @param param 包括委托代码
     * @return List<TrdCommandsCollection003Dto> 委托质押券详情
     * @see
     * @since 1.0
     */
    List<TrdCommandsCollection003Dto> selectPledgeList(Map<String, Object> param);
}
