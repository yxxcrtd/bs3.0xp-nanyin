package com.joyintech.tams.model.impairment.batch.pdcalculate.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;

/**
 * @Description:评级PD配置Dto
 * @auther: yuzj
 * @date: 2021/7/16
 * @param: 
 * @return: 
 */
public class ModGradePdConfigDto {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;


    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 配置类型
     */
    private String  configType;

    /**
     * 乐观权重
     */
    private BigDecimal optimisticWeight;

    /**
     * 年份num
     */
    private BigDecimal yearNum;
    /**
     * 前瞻前年度边际PD
     */
    private BigDecimal forwardYearPd;

    /**
     * 年份Str
     */
    private String yearStr;

    /**
     * 金融机构风险暴露系数
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal R;


    /**
     * 调整后乐观因子-一季度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal optimisticFactorOne;
    /**
     * 乐观因子季度年化边际PD-一季度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal optimisticFactorPdOne;
    /**
     * 调整后基础因子-一季度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal basicFactorOne;
    /**
     * 基础因子季度年化边际PD-一季度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal basicFactorPdOne;
    /**
     * 调整后悲观因子-一季度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pessimismFactorOne;

    /**
     * 悲观因子季度年化边际PD-一季度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pessimismFactorPdOne;

    /**
     * 调整后乐观因子-二季度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal optimisticFactorTwo;
    /**
     * 乐观因子季度年化边际PD-二季度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal optimisticFactorPdTwo;
    /**
     * 调整后基础因子-二季度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal basicFactorTwo;
    /**
     * 基础因子季度年化边际PD-二季度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal basicFactorPdTwo;
    /**
     * 调整后悲观因子-二季度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pessimismFactorTwo;

    /**
     * 悲观因子季度年化边际PD-二季度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pessimismFactorPdTwo;

    /**
     * 调整后乐观因子-三季度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal optimisticFactorThree;
    /**
     * 乐观因子季度年化边际PD-三季度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal optimisticFactorPdThree;
    /**
     * 调整后基础因子-三季度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal basicFactorThree;
    /**
     * 基础因子季度年化边际PD-三季度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal basicFactorPdThree;
    /**
     * 调整后悲观因子-三季度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pessimismFactorThree;

    /**
     * 悲观因子季度年化边际PD-三季度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pessimismFactorPdThree;

    /**
     * 调整后乐观因子-四季度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal optimisticFactorFour;
    /**
     * 乐观因子季度年化边际PD-四季度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal optimisticFactorPdFour;
    /**
     * 调整后基础因子-四季度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal basicFactorFour;
    /**
     * 基础因子季度年化边际PD-四季度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal basicFactorPdFour;
    /**
     * 调整后悲观因子-四季度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pessimismFactorFour;

    /**
     * 悲观因子季度年化边际PD-四季度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pessimismFactorPdFour;







    public BigDecimal getR() {
        return R;
    }

    public void setR(BigDecimal r) {
        R = r;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public String getConfigType() {
        return configType;
    }

    public void setConfigType(String configType) {
        this.configType = configType;
    }

    public BigDecimal getOptimisticWeight() {
        return optimisticWeight;
    }

    public void setOptimisticWeight(BigDecimal optimisticWeight) {
        this.optimisticWeight = optimisticWeight;
    }

    public BigDecimal getYearNum() {
        return yearNum;
    }

    public void setYearNum(BigDecimal yearNum) {
        this.yearNum = yearNum;
    }

    public BigDecimal getForwardYearPd() {
        return forwardYearPd;
    }

    public void setForwardYearPd(BigDecimal forwardYearPd) {
        this.forwardYearPd = forwardYearPd;
    }

    public String getYearStr() {
        return yearStr;
    }

    public void setYearStr(String yearStr) {
        this.yearStr = yearStr;
    }

    public BigDecimal getOptimisticFactorOne() {
        return optimisticFactorOne;
    }

    public void setOptimisticFactorOne(BigDecimal optimisticFactorOne) {
        this.optimisticFactorOne = optimisticFactorOne;
    }

    public BigDecimal getOptimisticFactorPdOne() {
        return optimisticFactorPdOne;
    }

    public void setOptimisticFactorPdOne(BigDecimal optimisticFactorPdOne) {
        this.optimisticFactorPdOne = optimisticFactorPdOne;
    }

    public BigDecimal getBasicFactorOne() {
        return basicFactorOne;
    }

    public void setBasicFactorOne(BigDecimal basicFactorOne) {
        this.basicFactorOne = basicFactorOne;
    }

    public BigDecimal getBasicFactorPdOne() {
        return basicFactorPdOne;
    }

    public void setBasicFactorPdOne(BigDecimal basicFactorPdOne) {
        this.basicFactorPdOne = basicFactorPdOne;
    }

    public BigDecimal getPessimismFactorOne() {
        return pessimismFactorOne;
    }

    public void setPessimismFactorOne(BigDecimal pessimismFactorOne) {
        this.pessimismFactorOne = pessimismFactorOne;
    }

    public BigDecimal getPessimismFactorPdOne() {
        return pessimismFactorPdOne;
    }

    public void setPessimismFactorPdOne(BigDecimal pessimismFactorPdOne) {
        this.pessimismFactorPdOne = pessimismFactorPdOne;
    }

    public BigDecimal getOptimisticFactorTwo() {
        return optimisticFactorTwo;
    }

    public void setOptimisticFactorTwo(BigDecimal optimisticFactorTwo) {
        this.optimisticFactorTwo = optimisticFactorTwo;
    }

    public BigDecimal getOptimisticFactorPdTwo() {
        return optimisticFactorPdTwo;
    }

    public void setOptimisticFactorPdTwo(BigDecimal optimisticFactorPdTwo) {
        this.optimisticFactorPdTwo = optimisticFactorPdTwo;
    }

    public BigDecimal getBasicFactorTwo() {
        return basicFactorTwo;
    }

    public void setBasicFactorTwo(BigDecimal basicFactorTwo) {
        this.basicFactorTwo = basicFactorTwo;
    }

    public BigDecimal getBasicFactorPdTwo() {
        return basicFactorPdTwo;
    }

    public void setBasicFactorPdTwo(BigDecimal basicFactorPdTwo) {
        this.basicFactorPdTwo = basicFactorPdTwo;
    }

    public BigDecimal getPessimismFactorTwo() {
        return pessimismFactorTwo;
    }

    public void setPessimismFactorTwo(BigDecimal pessimismFactorTwo) {
        this.pessimismFactorTwo = pessimismFactorTwo;
    }

    public BigDecimal getPessimismFactorPdTwo() {
        return pessimismFactorPdTwo;
    }

    public void setPessimismFactorPdTwo(BigDecimal pessimismFactorPdTwo) {
        this.pessimismFactorPdTwo = pessimismFactorPdTwo;
    }

    public BigDecimal getOptimisticFactorThree() {
        return optimisticFactorThree;
    }

    public void setOptimisticFactorThree(BigDecimal optimisticFactorThree) {
        this.optimisticFactorThree = optimisticFactorThree;
    }

    public BigDecimal getOptimisticFactorPdThree() {
        return optimisticFactorPdThree;
    }

    public void setOptimisticFactorPdThree(BigDecimal optimisticFactorPdThree) {
        this.optimisticFactorPdThree = optimisticFactorPdThree;
    }

    public BigDecimal getBasicFactorThree() {
        return basicFactorThree;
    }

    public void setBasicFactorThree(BigDecimal basicFactorThree) {
        this.basicFactorThree = basicFactorThree;
    }

    public BigDecimal getBasicFactorPdThree() {
        return basicFactorPdThree;
    }

    public void setBasicFactorPdThree(BigDecimal basicFactorPdThree) {
        this.basicFactorPdThree = basicFactorPdThree;
    }

    public BigDecimal getPessimismFactorThree() {
        return pessimismFactorThree;
    }

    public void setPessimismFactorThree(BigDecimal pessimismFactorThree) {
        this.pessimismFactorThree = pessimismFactorThree;
    }

    public BigDecimal getPessimismFactorPdThree() {
        return pessimismFactorPdThree;
    }

    public void setPessimismFactorPdThree(BigDecimal pessimismFactorPdThree) {
        this.pessimismFactorPdThree = pessimismFactorPdThree;
    }

    public BigDecimal getOptimisticFactorFour() {
        return optimisticFactorFour;
    }

    public void setOptimisticFactorFour(BigDecimal optimisticFactorFour) {
        this.optimisticFactorFour = optimisticFactorFour;
    }

    public BigDecimal getOptimisticFactorPdFour() {
        return optimisticFactorPdFour;
    }

    public void setOptimisticFactorPdFour(BigDecimal optimisticFactorPdFour) {
        this.optimisticFactorPdFour = optimisticFactorPdFour;
    }

    public BigDecimal getBasicFactorFour() {
        return basicFactorFour;
    }

    public void setBasicFactorFour(BigDecimal basicFactorFour) {
        this.basicFactorFour = basicFactorFour;
    }

    public BigDecimal getBasicFactorPdFour() {
        return basicFactorPdFour;
    }

    public void setBasicFactorPdFour(BigDecimal basicFactorPdFour) {
        this.basicFactorPdFour = basicFactorPdFour;
    }

    public BigDecimal getPessimismFactorFour() {
        return pessimismFactorFour;
    }

    public void setPessimismFactorFour(BigDecimal pessimismFactorFour) {
        this.pessimismFactorFour = pessimismFactorFour;
    }

    public BigDecimal getPessimismFactorPdFour() {
        return pessimismFactorPdFour;
    }

    public void setPessimismFactorPdFour(BigDecimal pessimismFactorPdFour) {
        this.pessimismFactorPdFour = pessimismFactorPdFour;
    }
}
