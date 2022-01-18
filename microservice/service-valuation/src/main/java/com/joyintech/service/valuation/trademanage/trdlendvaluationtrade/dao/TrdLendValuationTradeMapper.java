/*
 * 文件名：TaxBalanceQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：leipeng
 * 修改时间：2018年10月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.trademanage.trdlendvaluationtrade.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.trademanage.trdlendvaluationtrade.dto.TrdLendValuationTradeDto;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * 信用拆借交易
 * @see TrdLendValuationTradeMapper
 * @since 1.0
 */
@Repository(value="serviceTrdLendValuationTradeMapper")
public interface TrdLendValuationTradeMapper {

	/**
	 * 产品代码下拉框
	 */
	List<SelectOptionDto> findPortfolioId(Map<String, Object> params);


	/**
	 * 资产代码下拉框
	 */
//	List<SelectOptionDto> findFinprodId(Map<String, Object> params);


//    List<TrdLendValuationTradeDto> select(Map<String, Object> key);

    Integer  selectCountByTradeId(@Param("tradeId") String tradeId);


    /**
     * 获取自增序列值
     * @param sequenceName 序列名
     * @return
     */
    String getNextId(@Param("sequenceName") String sequenceName);



	/**
	 * 检索交易列表
	 */
	List<TrdLendValuationTradeDto> selectDeal(Map<String, Object> paraMap);


	//根据交易编号查询数据
    TrdLendValuationTradeDto queryTradeInfoByTradeId(String tradeId);
}
