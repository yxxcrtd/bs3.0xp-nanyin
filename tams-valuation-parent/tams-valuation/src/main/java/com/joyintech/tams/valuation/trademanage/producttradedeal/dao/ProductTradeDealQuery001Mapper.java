/*
 * 文件名：ProductTradeDealQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年8月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.trademanage.producttradedeal.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.valuation.trademanage.producttradedeal.dto.ProductTradeDealQuery001Dto;


/**
 * TA交易 Mapper
 * @author zhangwei
 * @version 1.0 2018年8月7日
 * @see ProductTradeDealQuery001Mapper
 * @since 1.0
 */
public interface ProductTradeDealQuery001Mapper {

	/**
     * 根据条件查询产品 ：TA交易树查询
     * @param params
     * @return
     */
    List<ProductTradeDealQuery001Dto> selectList(Map<String, Object> params);
    
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
}
