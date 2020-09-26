/*
 * 文件名：FundValuationOutsideDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年6月1日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.common.batch.finproduct.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 场外金融产品行情
 * @author weipeng
 * @version 1.0 2018年6月1日
 * @see FinProductValuationDto
 * @since 1.0
 */
public class FinProductValuationDto {
    
    /**
     * 估值日期
     */
    private Date valDate;
    /**
     * 估值
     */
    private BigDecimal valuation;
    
    /**
     * 获取 估值日期
     * @return 估值日期.
     */
    public Date getValDate() {
        return valDate;
    }
    /**
     * 设置 估值日期
     * @param valDate 估值日期
     */
    public void setValDate(Date valDate) {
        this.valDate = valDate;
    }
    /**
     * 获取 估值
     * @return 估值.
     */
    public BigDecimal getValuation() {
        return valuation;
    }
    /**
     * 设置 估值
     * @param valuation 估值
     */
    public void setValuation(BigDecimal valuation) {
        this.valuation = valuation;
    }
    
}
