package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class InterfBondMarketInfo extends InterfBondMarketInfoKey {
    /**
     * 收盘价 
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal closePrice;

    /**
     * 平均价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal averagePrice;

    /**
     * 最高价 
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal maxPrice;

    /**
     * 最低价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal minPrice;

    /**
     * 买一价 
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyOnePrice;

    /**
     * 卖一价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sellOnePrice;

    /**
     * 取得 收盘价 
     * @return 收盘价 
     */
    public BigDecimal getClosePrice() {
        return closePrice;
    }

    /**
     * 设置 收盘价 
     * @param closePrice 收盘价 
     */
    public void setClosePrice(BigDecimal closePrice) {
        this.closePrice = closePrice;
    }

    /**
     * 取得 平均价
     * @return 平均价
     */
    public BigDecimal getAveragePrice() {
        return averagePrice;
    }

    /**
     * 设置 平均价
     * @param averagePrice 平均价
     */
    public void setAveragePrice(BigDecimal averagePrice) {
        this.averagePrice = averagePrice;
    }

    /**
     * 取得 最高价 
     * @return 最高价 
     */
    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    /**
     * 设置 最高价 
     * @param maxPrice 最高价 
     */
    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    /**
     * 取得 最低价
     * @return 最低价
     */
    public BigDecimal getMinPrice() {
        return minPrice;
    }

    /**
     * 设置 最低价
     * @param minPrice 最低价
     */
    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    /**
     * 取得 买一价 
     * @return 买一价 
     */
    public BigDecimal getBuyOnePrice() {
        return buyOnePrice;
    }

    /**
     * 设置 买一价 
     * @param buyOnePrice 买一价 
     */
    public void setBuyOnePrice(BigDecimal buyOnePrice) {
        this.buyOnePrice = buyOnePrice;
    }

    /**
     * 取得 卖一价
     * @return 卖一价
     */
    public BigDecimal getSellOnePrice() {
        return sellOnePrice;
    }

    /**
     * 设置 卖一价
     * @param sellOnePrice 卖一价
     */
    public void setSellOnePrice(BigDecimal sellOnePrice) {
        this.sellOnePrice = sellOnePrice;
    }
}