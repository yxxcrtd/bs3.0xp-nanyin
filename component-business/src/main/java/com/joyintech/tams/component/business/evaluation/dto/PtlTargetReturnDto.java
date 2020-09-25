/*
 * 文件名：PtlTargetReturnDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangJin
 * 修改时间：2019年10月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.evaluation.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 组合收益目标实体类
 * 
 * @author zhangJin
 * @version 1.0 2019年10月8日
 * @see PtlTargetReturnDto
 * @since 1.0
 */
public class PtlTargetReturnDto {

    /**
     * 收益目标类型
     */
    private String incomeType;

    /**
     * 日期
     */
    private Date effDate;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 指标代码
     */
    private String indexId;

    /**
     * 收益目标
     */
    private BigDecimal incomeTarget;

    /**
     * 利率
     */
    private BigDecimal intRate;

    /**
     * 利差
     */
    private BigDecimal spreadRate;

    /**
     * 系数
     */
    private BigDecimal coefficient;

    /**
     * 获取 incomeType
     * 
     * @return incomeType.
     */
    public String getIncomeType() {
        return incomeType;
    }

    /**
     * 设置 incomeType
     * 
     * @param incomeType incomeType
     */
    public void setIncomeType(String incomeType) {
        this.incomeType = incomeType;
    }

    /**
     * 获取 effDate
     * 
     * @return effDate.
     */
    public Date getEffDate() {
        return effDate;
    }

    /**
     * 设置 effDate
     * 
     * @param effDate effDate
     */
    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

    /**
     * 获取 portfolioId
     * 
     * @return portfolioId.
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 portfolioId
     * 
     * @param portfolioId portfolioId
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    /**
     * 获取 indexId
     * 
     * @return indexId.
     */
    public String getIndexId() {
        return indexId;
    }

    /**
     * 设置 indexId
     * 
     * @param indexId indexId
     */
    public void setIndexId(String indexId) {
        this.indexId = indexId;
    }

    /**
     * 获取 incomeTarget
     * 
     * @return incomeTarget.
     */
    public BigDecimal getIncomeTarget() {
        return incomeTarget;
    }

    /**
     * 设置 incomeTarget
     * 
     * @param incomeTarget incomeTarget
     */
    public void setIncomeTarget(BigDecimal incomeTarget) {
        this.incomeTarget = incomeTarget;
    }

    /**
     * 获取 intRate
     * 
     * @return intRate.
     */
    public BigDecimal getIntRate() {
        return intRate;
    }

    /**
     * 设置 intRate
     * 
     * @param intRate intRate
     */
    public void setIntRate(BigDecimal intRate) {
        this.intRate = intRate;
    }

    /**
     * 获取 spreadRate
     * 
     * @return spreadRate.
     */
    public BigDecimal getSpreadRate() {
        return spreadRate;
    }

    /**
     * 设置 spreadRate
     * 
     * @param spreadRate spreadRate
     */
    public void setSpreadRate(BigDecimal spreadRate) {
        this.spreadRate = spreadRate;
    }

    /**
     * 获取 coefficient
     * 
     * @return coefficient.
     */
    public BigDecimal getCoefficient() {
        return coefficient;
    }

    /**
     * 设置 coefficient
     * 
     * @param coefficient coefficient
     */
    public void setCoefficient(BigDecimal coefficient) {
        this.coefficient = coefficient;
    }

}
