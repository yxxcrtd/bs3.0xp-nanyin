/*
 * 文件名：CarryAmtSumDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年8月6日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.vatdraw.dto;

import java.math.BigDecimal;

/**
 * 增值税账务明细余额各金额汇总 Dto
 * 
 * @author weipeng
 * @version 1.0 2018年8月6日
 * @see TaxBalanceSumDto
 * @since 1.0
 */
public class TaxBalanceSumDto {

    /**
     * 确认标识
     */
    private String taxPType;

    /**
     * 币种
     */
    private String oCcy;

    /**
     * sum(增值税账务明细余额.当日结转累计发生额) 结转金额
     */
    private BigDecimal carryAmt;

    /**
     * sum(增值税账务明细余额.当日计税收入发生额) 收入发生额
     */
    private BigDecimal incomeHappenAmt;

    /**
     * sum(增值税账务明细余额.当日发生额) 发生额
     */
    private BigDecimal happenAmt;
    
    /**
     * sum(当日发生额(小于0设值0)) 发生额2
     */
    private BigDecimal happenAmt2;

    /**
     * 获取 确认标识
     * 
     * @return 确认标识
     */
    public String getTaxPType() {
        return taxPType;
    }

    /**
     * 设置 确认标识
     * 
     * @param taxPType 确认标识
     */
    public void setTaxPType(String taxPType) {
        this.taxPType = taxPType;
    }

    /**
     * 获取 币种
     * 
     * @return 币种
     */
    public String getoCcy() {
        return oCcy;
    }

    /**
     * 设置 币种
     * 
     * @param oCcy 币种
     */
    public void setoCcy(String oCcy) {
        this.oCcy = oCcy;
    }

    /**
     * 获取 结转金额
     * 
     * @return 结转金额
     */
    public BigDecimal getCarryAmt() {
        return carryAmt;
    }

    /**
     * 设置 结转金额
     * 
     * @param carryAmt 结转金额
     */
    public void setCarryAmt(BigDecimal carryAmt) {
        this.carryAmt = carryAmt;
    }

    /**
     * 取得 收入发生额
     * 
     * @return 收入发生额
     */
    public BigDecimal getIncomeHappenAmt() {
        return incomeHappenAmt;
    }

    /**
     * 设置 收入发生额
     * 
     * @param incomeHappenAmt 收入发生额
     */
    public void setIncomeHappenAmt(BigDecimal incomeHappenAmt) {
        this.incomeHappenAmt = incomeHappenAmt;
    }

    /**
     * 取得 发生额
     * 
     * @return 发生额
     */
    public BigDecimal getHappenAmt() {
        return happenAmt;
    }

    /**
     * 设置 发生额
     * 
     * @param happenAmt 发生额
     */
    public void setHappenAmt(BigDecimal happenAmt) {
        this.happenAmt = happenAmt;
    }

    
    /**
     * 获取 sum(当日发生额(小于0设值0))
     * @return sum(当日发生额(小于0设值0))
     */
    public BigDecimal getHappenAmt2() {
        return happenAmt2;
    }

    
    /**
     * 设置 sum(当日发生额(小于0设值0))
     * @param happenAmt2 sum(当日发生额(小于0设值0))
     */
    public void setHappenAmt2(BigDecimal happenAmt2) {
        this.happenAmt2 = happenAmt2;
    }
    
    
}
