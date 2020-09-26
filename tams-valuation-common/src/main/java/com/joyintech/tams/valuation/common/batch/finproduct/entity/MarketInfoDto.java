/*
 * 文件名：MarketInfoDto.java
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
 * 基金估值行情/债券行情（第三方） 共通元素行情Dto
 * 
 * @author weipeng
 * @version 1.0 2018年8月20日
 * @see MarketInfoDto
 * @since 1.0
 */
public class MarketInfoDto {

    /**
     * 估值日
     */
    private Date valueDate;

    /**
     * 收盘价
     */
    private BigDecimal closePrice;

    /**
     * 平均价
     */
    private BigDecimal averagePrice;

    /**
     * 最高价
     */
    private BigDecimal maxPrice;

    /**
     * 最低价
     */
    private BigDecimal minPrice;

    /**
     * 买一价
     */
    private BigDecimal buyOnePrice;

    /**
     * 卖一价
     */
    private BigDecimal sellOnePrice;

    /**
     * 收盘价全价
     */
    private BigDecimal closePriceFull;

    /**
     * 平均价全价
     */
    private BigDecimal averagePriceFull;

    /**
     * 最高价全价
     */
    private BigDecimal maxPriceFull;

    /**
     * 最低价全价
     */
    private BigDecimal minPriceFull;

    /**
     * 买一价全价
     */
    private BigDecimal buyOnePriceFull;

    /**
     * 卖一价全价
     */
    private BigDecimal sellOnePriceFull;

    /**
     * 估值来源
     */
    private String valueSource;

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
     * @param valueDate 估值日
     */
    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

    /**
     * 取得 收盘价
     * 
     * @return 收盘价
     */
    public BigDecimal getClosePrice() {
        return closePrice;
    }

    /**
     * 设置 收盘价
     * 
     * @param closePrice 收盘价
     */
    public void setClosePrice(BigDecimal closePrice) {
        this.closePrice = closePrice;
    }

    /**
     * 取得 平均价
     * 
     * @return 平均价
     */
    public BigDecimal getAveragePrice() {
        return averagePrice;
    }

    /**
     * 设置 平均价
     * 
     * @param averagePrice 平均价
     */
    public void setAveragePrice(BigDecimal averagePrice) {
        this.averagePrice = averagePrice;
    }

    /**
     * 取得 最高价
     * 
     * @return 最高价
     */
    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    /**
     * 设置 最高价
     * 
     * @param maxPrice 最高价
     */
    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    /**
     * 取得 最低价
     * 
     * @return 最低价
     */
    public BigDecimal getMinPrice() {
        return minPrice;
    }

    /**
     * 设置 最低价
     * 
     * @param minPrice 最低价
     */
    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    /**
     * 取得 买一价
     * 
     * @return 买一价
     */
    public BigDecimal getBuyOnePrice() {
        return buyOnePrice;
    }

    /**
     * 设置 买一价
     * 
     * @param buyOnePrice 买一价
     */
    public void setBuyOnePrice(BigDecimal buyOnePrice) {
        this.buyOnePrice = buyOnePrice;
    }

    /**
     * 取得 卖一价
     * 
     * @return 卖一价
     */
    public BigDecimal getSellOnePrice() {
        return sellOnePrice;
    }

    /**
     * 设置 卖一价
     * 
     * @param sellOnePrice 卖一价
     */
    public void setSellOnePrice(BigDecimal sellOnePrice) {
        this.sellOnePrice = sellOnePrice;
    }

    public String getValueSource() {
        return valueSource;
    }

    public void setValueSource(String valueSource) {
        this.valueSource = valueSource;
    }

    /**
     * 获取 收盘价全价
     * 
     * @return 收盘价全价
     */
    public BigDecimal getClosePriceFull() {
        return closePriceFull;
    }

    /**
     * 设置 收盘价全价
     * 
     * @param closePriceFull 收盘价全价
     */
    public void setClosePriceFull(BigDecimal closePriceFull) {
        this.closePriceFull = closePriceFull;
    }

    /**
     * 获取 平均价全价
     * 
     * @return 平均价全价
     */
    public BigDecimal getAveragePriceFull() {
        return averagePriceFull;
    }

    /**
     * 设置 平均价全价
     * 
     * @param averagePriceFull 平均价全价
     */
    public void setAveragePriceFull(BigDecimal averagePriceFull) {
        this.averagePriceFull = averagePriceFull;
    }

    /**
     * 获取 最高价全价
     * 
     * @return 最高价全价
     */
    public BigDecimal getMaxPriceFull() {
        return maxPriceFull;
    }

    /**
     * 设置 最高价全价
     * 
     * @param maxPriceFull 最高价全价
     */
    public void setMaxPriceFull(BigDecimal maxPriceFull) {
        this.maxPriceFull = maxPriceFull;
    }

    /**
     * 获取 最低价全价
     * 
     * @return 最低价全价
     */
    public BigDecimal getMinPriceFull() {
        return minPriceFull;
    }

    /**
     * 设置 最低价全价
     * 
     * @param minPriceFull 最低价全价
     */
    public void setMinPriceFull(BigDecimal minPriceFull) {
        this.minPriceFull = minPriceFull;
    }

    /**
     * 获取 买一价全价
     * 
     * @return 买一价全价
     */
    public BigDecimal getBuyOnePriceFull() {
        return buyOnePriceFull;
    }

    /**
     * 设置 买一价全价
     * 
     * @param buyOnePriceFull 买一价全价
     */
    public void setBuyOnePriceFull(BigDecimal buyOnePriceFull) {
        this.buyOnePriceFull = buyOnePriceFull;
    }

    /**
     * 获取 卖一价全价
     * 
     * @return 卖一价全价
     */
    public BigDecimal getSellOnePriceFull() {
        return sellOnePriceFull;
    }

    /**
     * 设置 卖一价全价
     * 
     * @param sellOnePriceFull 卖一价全价
     */
    public void setSellOnePriceFull(BigDecimal sellOnePriceFull) {
        this.sellOnePriceFull = sellOnePriceFull;
    }

}
