/*
 * 文件名：BookOrderDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年8月2日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.vatdraw.dto;

import java.math.BigDecimal;

/**
 * 增值税账务明细最大的处理序列Dto
 * @author weipeng
 * @version 1.0 2018年8月2日
 * @see TaxBookOrderDto
 * @since 1.0
 */
public class TaxBookOrderDto {
    
    /**
     * max(增值税账务明细余额.处理序列)
     */
    private BigDecimal maxBookOrder;
    
    /**
     * max(增值税汇总.处理序列) 
     */
    private BigDecimal maxTdyBookOrder;

    
    /**
     * 获取 max(增值税账务明细余额.处理序列)
     * @return max(增值税账务明细余额.处理序列)
     */
    public BigDecimal getMaxBookOrder() {
        //若为空，初始化为1
        if(maxBookOrder == null){
            return new BigDecimal(1);
        }
        return maxBookOrder;
    }

    
    /**
     * 设置 max(增值税账务明细余额.处理序列)
     * @param maxBookOrder max(增值税账务明细余额.处理序列)
     */
    public void setMaxBookOrder(BigDecimal maxBookOrder) {
        this.maxBookOrder = maxBookOrder;
    }

    
    /**
     * 获取 max(增值税汇总.处理序列) 
     * @return max(增值税汇总.处理序列) 
     */
    public BigDecimal getMaxTdyBookOrder() {
        // 若为空，初始化为0，因为process中需要+1
        if(maxTdyBookOrder == null){
            return new BigDecimal(0);
        }
        return maxTdyBookOrder;
    }

    
    /**
     * 设置 max(增值税汇总.处理序列) 
     * @param maxTdyBookOrder max(增值税汇总.处理序列) 
     */
    public void setMaxTdyBookOrder(BigDecimal maxTdyBookOrder) {
        this.maxTdyBookOrder = maxTdyBookOrder;
    }
    
    
}
