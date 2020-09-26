/*
 * 文件名：TaxBalanceQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：leipeng
 * 修改时间：2018年10月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.trademanage.taxbalancequery.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.valuation.trademanage.taxbalancequery.dto.TaxBalanceQuery001Dto;


/**
 * 简易计税余额查询
 * @author leipeng
 * @version 1.0 2018年10月9日
 * @see TaxBalanceQuery001Mapper
 * @since 1.0
 */
public interface TaxBalanceQuery001Mapper {

	/**
     * 根据条件查询产品 ：TA交易树查询
     * @param params
     * @return
     */
    List<TaxBalanceQuery001Dto> selectList(Map<String, Object> params);
    
    /**
     * 根据 产品Id 和 确认日 单个查询 上一日份额 
     * @param params
     * @return
     */
    BigDecimal selectShareLastDay(@Param("finprodId")String finprodId, @Param("settleDate")String settleDate);
    
    /**
     * 根据 产品Id 和 确认日 单个查询 上一日份额 
     * @param params
     * @return
     */
    BigDecimal selectIntAmt(@Param("finprodId")String finprodId, @Param("settleDate")String settleDate);

    /**
     * 根据 产品Id 和 确认日 单个查询 当日剩余份额
     * @param params
     * @return
     */
    BigDecimal selectShareRemainderDay(@Param("finprodId")String finprodId, @Param("settleDate")String settleDate);
    
    
    /**
     * 检索产品代码和产品名称
     * 
     * @return 账套代码列表
     * @see
     * @since 1.0
     */
    List<SuggestDto> getFinprod(Map<String, Object> params);
    /**
     * 检索资产代码
     * 
     * @return 账套资产代码
     * @see
     * @since 1.0
     */
	 List<SelectOptionDto> getAssetId(Map<String, Object> params);
	  /**
	     *资产维度查询
	     * 
	     * @return 
	     * @see
	     * @since 1.0
	     */
	 List<TaxBalanceQuery001Dto> selectListAsset(Map<String, Object> params);
}
