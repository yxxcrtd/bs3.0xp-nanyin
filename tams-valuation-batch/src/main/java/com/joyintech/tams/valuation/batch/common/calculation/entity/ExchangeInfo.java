/*
 * 文件名：ExchangeInfo.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：杨智
 * 修改时间：2018年6月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.common.calculation.entity;

import java.math.BigDecimal;

public class ExchangeInfo {
    private String baseCcy;
    private String quoCcy;
    private BigDecimal exRate;
    
    public String getBaseCcy() {
        return baseCcy;
    }
    
    public void setBaseCcy(String baseCcy) {
        this.baseCcy = baseCcy;
    }
    
    public String getQuoCcy() {
        return quoCcy;
    }
    
    public void setQuoCcy(String quoCcy) {
        this.quoCcy = quoCcy;
    }
    
    public BigDecimal getExRate() {
        return exRate;
    }
    
    public void setExRate(BigDecimal exRate) {
        this.exRate = exRate;
    }
    
}
