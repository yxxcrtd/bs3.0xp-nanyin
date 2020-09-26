/*
 * 文件名：SecIntRange.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年6月1日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.common.calculation.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

/**
 * 分段明细
 * 
 * @author 王波
 * @version 1.0 2018年6月1日
 * @see SecIntRange
 * @since 1.0
 */
public class SecIntRange {

    /**
     * 分段起始日
     */
    private Date startDate;

    /**
     * 分段结束日
     */
    private Date endDate;

    /**
     * 分段利率
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal intRate;

    /**
     * 利息金额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal intAmt;

    /**
     * 计息本金
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal prinInt;
    
    /**
     * 计算基础
     */
    private String accountBasis;
    
    
    /**
     * 获取 计算基础
     * @return 计算基础.
     */
    public String getAccountBasis() {
        return accountBasis;
    }

    
    /**
     * 设置 计算基础
     * @param accountBasis 计算基础
     */
    public void setAccountBasis(String accountBasis) {
        this.accountBasis = accountBasis;
    }

    /**
     * 获取 分段起始日
     * 
     * @return 分段起始日.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 设置 分段起始日
     * 
     * @param startDate 分段起始日
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 获取 分段结束日
     * 
     * @return 分段结束日.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置 分段结束日
     * 
     * @param endDate 分段结束日
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 获取 分段利率
     * 
     * @return 分段利率.
     */
    public BigDecimal getIntRate() {
        return intRate;
    }

    /**
     * 设置 分段利率
     * 
     * @param intRate 分段利率
     */
    public void setIntRate(BigDecimal intRate) {
        this.intRate = intRate;
    }

    /**
     * 获取 利息金额
     * 
     * @return 利息金额.
     */
    public BigDecimal getIntAmt() {
        return intAmt;
    }

    /**
     * 设置 利息金额
     * 
     * @param intAmt 利息金额
     */
    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
    }

    /**
     * 获取 计息本金
     * 
     * @return 计息本金.
     */
    public BigDecimal getPrinInt() {
        return prinInt;
    }

    /**
     * 设置 计息本金
     * 
     * @param prinInt 计息本金
     */
    public void setPrinInt(BigDecimal prinInt) {
        this.prinInt = prinInt;
    }
}
