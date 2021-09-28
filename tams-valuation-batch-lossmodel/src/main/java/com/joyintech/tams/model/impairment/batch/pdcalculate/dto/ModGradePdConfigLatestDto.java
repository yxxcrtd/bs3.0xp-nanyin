package com.joyintech.tams.model.impairment.batch.pdcalculate.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;

/**
 * @Description:最新评级PD配置Dto
 * @auther: weipeng
 * @date: 2021/9/28
 * @param: 
 * @return: 
 */
public class ModGradePdConfigLatestDto {

    /**
     * 前瞻前年度边际PD
     */
    private BigDecimal forwardYearPd;

    /**
     * 乐观因子季度年化边际PD
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal optimisticFactorPd;

    /**
     * 基础因子季度年化边际PD
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal basicFactorPd;

    /**
     * 悲观因子季度年化边际PD
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pessimismFactorPd;

    /**
     * @return 前瞻前年度边际PD
     */
    public BigDecimal getForwardYearPd() {
        return forwardYearPd;
    }

    /**
     * 设置 前瞻前年度边际PD
     *
     * @param forwardYearPd 前瞻前年度边际PD
     */
    public void setForwardYearPd(BigDecimal forwardYearPd) {
        this.forwardYearPd = forwardYearPd;
    }

    /**
     * @return 乐观因子季度年化边际PD
     */
    public BigDecimal getOptimisticFactorPd() {
        return optimisticFactorPd;
    }

    /**
     * 设置 乐观因子季度年化边际PD
     *
     * @param optimisticFactorPd 乐观因子季度年化边际PD
     */
    public void setOptimisticFactorPd(BigDecimal optimisticFactorPd) {
        this.optimisticFactorPd = optimisticFactorPd;
    }

    /**
     * @return 基础因子季度年化边际PD
     */
    public BigDecimal getBasicFactorPd() {
        return basicFactorPd;
    }

    /**
     * 设置 基础因子季度年化边际PD
     *
     * @param basicFactorPd 基础因子季度年化边际PD
     */
    public void setBasicFactorPd(BigDecimal basicFactorPd) {
        this.basicFactorPd = basicFactorPd;
    }

    /**
     * @return 悲观因子季度年化边际PD
     */
    public BigDecimal getPessimismFactorPd() {
        return pessimismFactorPd;
    }

    /**
     * 设置 悲观因子季度年化边际PD
     *
     * @param pessimismFactorPd 悲观因子季度年化边际PD
     */
    public void setPessimismFactorPd(BigDecimal pessimismFactorPd) {
        this.pessimismFactorPd = pessimismFactorPd;
    }
}
