/*
 * 文件名：ValueAddedTaxMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年11月13日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.datatransplant.dao;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * 【增值税模版】对应Mapper
 * 
 * @author yangjian
 * @version 1.0 2018年11月13日
 * @see ValueAddedTaxMapper
 * @since 1.0
 */
@Repository
public interface ValueAddedTaxMapper {
    
    /**
     * 【增值税汇总余额(TAX_BOK_BALANCE_SUM)】中【计税收入余额】汇总
     * @return
     * @see
     * @since 1.0
     */
    BigDecimal SumIncomeAmtAllForTaxBokBlanceSum(Map<String,Object> map);
    
    /**
     * 【增值税汇总余额(TAX_BOK_BALANCE_SUM)】中【当日发生额】汇总
     * @return
     * @see
     * @since 1.0
     */
    BigDecimal SumHappenAmtForTaxBokBlanceSum(Map<String,Object> map);
    
    /**
     * 【增值税汇总余额(TAX_BOK_BALANCE_SUM)】中【计税发生额汇总】汇总
     * @return
     * @see
     * @since 1.0
     */
    BigDecimal SumTaxAmtSumForTaxBokBlanceSum(Map<String,Object> map);

}
