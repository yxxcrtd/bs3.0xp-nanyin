/*
 * 文件名：BenchmarkRate.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.autogeneration.proledger.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

/**
 * 基准利率
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年10月11日
 * @see BenchmarkRate
 * @since 1.0
 */
public class BenchmarkRate {
    /**
     * 利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal intRate;
    /**
     * 权重
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal weight;
    
    public BigDecimal getIntRate() {
        return intRate;
    }
    
    /**
     * 
     */
    public BenchmarkRate() {
        super();
    }
    /**
     * @param intRate
     * @param weight
     */
    public BenchmarkRate(BigDecimal intRate, BigDecimal weight) {
        this.intRate = intRate;
        this.weight = weight;
    }

    public void setIntRate(BigDecimal intRate) {
        this.intRate = intRate;
    }
    
    public BigDecimal getWeight() {
        return weight;
    }
    
    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }
    
}
