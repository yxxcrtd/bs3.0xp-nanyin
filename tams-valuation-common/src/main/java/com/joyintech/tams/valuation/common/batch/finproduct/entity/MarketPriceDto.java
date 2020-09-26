/*
 * 文件名：MarketPriceDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年8月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.common.batch.finproduct.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 市价Dto
 * @author weipeng
 * @version 1.0 2018年8月20日
 * @see MarketPriceDto
 * @since 1.0
 */
public class MarketPriceDto {
    /**
     * 估值日期
     */
    private Date valDate;
    
    /**
     * 市价净价
     */
    private BigDecimal price;
    
    /**
     * 市价全价
     */
    private BigDecimal allPrice;

    /**
     * 估值来源
     */
    private String valueSource;
    
    /**
     * 估值方案估值日期
     */
    private Date schemeValDate;
    
    public String getValueSource() {
		return valueSource;
	}


	public void setValueSource(String valueSource) {
		this.valueSource = valueSource;
	}


	/**
     * 获取 估值日期
     * @return 估值日期
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
     * 获取 市价净价
     * @return 市价净价
     */
    public BigDecimal getPrice() {
        return price;
    }

    
    /**
     * 设置 市价净价
     * @param price 市价净价
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    /**
     * 获取 市价全价
     * @return 市价全价
     */
    public BigDecimal getAllPrice() {
        return allPrice;
    }
    
    /**
     * 设置 市价全价
     * @param allPrice 市价全价
     */
    public void setAllPrice(BigDecimal allPrice) {
        this.allPrice = allPrice;
    }


	public Date getSchemeValDate() {
		return schemeValDate;
	}


	public void setSchemeValDate(Date queryDate) {
		this.schemeValDate = queryDate;
	}
    
    
}
