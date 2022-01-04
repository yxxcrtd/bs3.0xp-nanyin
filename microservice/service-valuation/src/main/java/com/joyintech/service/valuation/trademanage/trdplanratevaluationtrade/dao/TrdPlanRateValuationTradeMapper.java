/*
 * 文件名：TaxBalanceQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：leipeng
 * 修改时间：2018年10月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.trademanage.trdplanratevaluationtrade.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.joyintech.service.valuation.trademanage.trdplanratevaluationtrade.dto.TrdPlanRateValuationTradeDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.valuation.trademanage.taxbalancequery.dto.TaxBalanceQuery001Dto;


/**
 * 计划类利率型交易
 * @author yuzj
 * @version 1.0 2021年12月29日
 * @see TrdPlanRateValuationTradeMapper
 * @since 1.0
 */
@Repository(value="serviceTrdPlanRateValuationTradeMapper")
public interface TrdPlanRateValuationTradeMapper {

	/**
	 * 产品代码下拉框
	 * @auther: yuzj
	 * @date: 2021/12/29
	 * @param:
	 * @return:
	 */
	List<SelectOptionDto> findPortfolioId(Map<String, Object> params);


	/**
	 * 资产代码下拉框
	 * @auther: yuzj
	 * @date: 2021/12/29
	 * @param:
	 * @return:
	 */
	List<SelectOptionDto> findFinprodId(Map<String, Object> params);


    List<TrdPlanRateValuationTradeDto> select(Map<String, Object> key);

    Integer  selectCountByTradeId(@Param("tradeId")String tradeId);


    /**
     * 获取自增序列值
     * @param sequenceName 序列名
     * @return
     */
    String getNextId(@Param("sequenceName")String sequenceName);



}
