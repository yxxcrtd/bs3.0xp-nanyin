/*
 * 文件名：TrdCommandDispatcher001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2018年9月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.trade.investtrade.investdealquery.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.trade.investtrade.investdealquery.dto.AssetTransferExport001Dto;
import com.joyintech.service.trade.investtrade.investdealquery.dto.TrdInvestDealQuery001Dto;


public interface TrdInvestDealQuery001Mapper {
	/**
	 * 流程中的搜索
	 * @param map
	 * @return
	 */
	List<TrdInvestDealQuery001Dto> selectForPage(Map<String, Object> map);
	/**
	 * 有效时的搜索
	 * @param map
	 * @return
	 */
	List<TrdInvestDealQuery001Dto> validSelectForPage(Map<String, Object> map);
	/**
	 * 撤销时的搜索
	 * @param map
	 * @return
	 */
	List<TrdInvestDealQuery001Dto> cancelSelectForPage(Map<String, Object> map);
	/**
	 * 有效状态为空的查询
	 * @param map
	 * @return
	 */
	List<TrdInvestDealQuery001Dto>  allSelectForPage(Map<String, Object> map);
	/**
	 * 委托代码联想框
	 * @param entrustId
	 * @return
	 */
	List<SuggestDto> getEntrustId(@Param("entrustId")String entrustId);
	/**
	 * 组合代码联想框
	 * @param
	 * @return
	 */
	List<SuggestDto> getPortfolioIds(Map<String,Object> map);
	/**
	 * 资产代码联想框
	 * @param finprodMarketId
	 * @return
	 */
	List<SuggestDto> getFinprodMarketIds(@Param("finprodMarketId")String finprodMarketId);
	
	
	/**
     * check是否为转仓交易
     * 
     * @param teadIds
     * @return
     * @see
     * @since 1.0
     */
    int selectCheck(@Param("teadIds") List<String> teadIds);
    
    /**
     * 债券类交易
     * 
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<AssetTransferExport001Dto> selectDataByBond(@Param("teadIds") List<String> teadIds);
     
    /**
     * 存款类交易
     * 
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<AssetTransferExport001Dto> selectDataByDeposit(@Param("teadIds") List<String> teadIds);
    
    /**
     * 计划类利率型交易
     * 
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<AssetTransferExport001Dto> selectDataByPlanLl(@Param("teadIds") List<String> teadIds);
     
    /**
     * 计划类净值型交易
     * 
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<AssetTransferExport001Dto> selectDataByPlanJz(@Param("teadIds") List<String> teadIds);

    /**
     * 计划类货币型交易
     * 
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<AssetTransferExport001Dto> selectDataByPlanHb(@Param("teadIds") List<String> teadIds);

    /**
     * 基金类交易
     * 
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<AssetTransferExport001Dto> selectDataByFund(@Param("teadIds") List<String> teadIds);
    
    /**
     * 项目类交易
     * 
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<AssetTransferExport001Dto> selectDataByProject(@Param("teadIds") List<String> teadIds);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    BigDecimal selectInvestRateByPlan(AssetTransferExport001Dto dto);
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    BigDecimal selectInvestRateByProject(AssetTransferExport001Dto dto);
}
