/*
 * 文件名：ValBondMarketInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年5月31日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.batch.finproduct.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 债券行情Dto （债券估值行情表）
 * 
 * @author weipeng
 * @version 1.0 2018年5月31日
 * @see BondValuationInfoDto
 * @since 1.0
 */
public class BondValuationInfoDto {
    /**
     * 估值日
     */
    private Date valueDate;
    /**
     * 市价净价
     */
    private BigDecimal price;
    /**
     * 估值来源
     */
    private String valueSource;
    
    /**
     * 市价全价
     */
    private BigDecimal allPrice;

    /**
     * 取得 估值日
     * 
     * @return 估值日
     */
    public Date getValueDate() {
        return valueDate;
    }

    /**
     * 设置 估值日
     * 
     * @param valueDate
     *            估值日
     */
    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

    /**
     * 取得 市价净价
     * 
     * @return 市价净价
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置 市价净价
     * 
     * @param price
     *            市价净价
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 取得 估值来源
     * 
     * @return 估值来源
     */
    public String getValueSource() {
        return valueSource;
    }

    /**
     * 设置 估值来源
     * 
     * @param valueSource
     *            估值来源
     */
    public void setValueSource(String valueSource) {
        this.valueSource = valueSource;
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
    

}
