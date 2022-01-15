/*
 * 文件名：TaxBalanceQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：leipeng
 * 修改时间：2018年10月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.trademanage.trdfundvaluationtrade.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.service.valuation.trademanage.trdfundvaluationtrade.dto.TrdFundValuationTradeDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;


/**
 * 基金交易
 * @author yuzj
 * @version 1.0 2021年01月04日
 * @see TrdFundValuationTradeMapper
 * @since 1.0
 */
@Repository(value="serviceTrdFundValuationTradeMapper")
public interface TrdFundValuationTradeMapper {

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


    List<TrdFundValuationTradeDto> select(Map<String, Object> key);

    Integer  selectCountByTradeId(@Param("tradeId") String tradeId);


    /**
     * 获取自增序列值
     * @param sequenceName 序列名
     * @return
     */
    String getNextId(@Param("sequenceName") String sequenceName);


	TrdFundValuationTradeDto queryTradeInfoByTradeId(String tradeId);

	/**
	 * 检索债券交易列表
	 *
	 * @param paraMap 参数
	 * @return 债券交易列表
	 * @see
	 * @since 1.0
	 */
	List<TrdFundValuationTradeDto> selectDeal(Map<String, Object> paraMap);


}
