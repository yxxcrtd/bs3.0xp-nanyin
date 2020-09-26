package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

public class HqBondMarketInfo extends BaseEntity {
    /**
     * 证券代码
     */
    private String secId;
    /**
     * 估值日
     */
    private Date valueDate;
    /**
     * 估值来源
     */
    private String valueSource;
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
     * 证券类型
     */
    private String secType;
    
    /**
     * 获取 secId
     * @return secId.
     */
    public String getSecId() {
        return secId;
    }
    
    /**
     * 设置 secId
     * @param secId secId
     */
    public void setSecId(String secId) {
        this.secId = secId;
    }
    
    /**
     * 获取 valueDate
     * @return valueDate.
     */
    public Date getValueDate() {
        return valueDate;
    }
    
    /**
     * 设置 valueDate
     * @param valueDate valueDate
     */
    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }
    
    /**
     * 获取 valueSource
     * @return valueSource.
     */
    public String getValueSource() {
        return valueSource;
    }

    
    /**
     * 设置 valueSource
     * @param valueSource valueSource
     */
    public void setValueSource(String valueSource) {
        this.valueSource = valueSource;
    }

    /**
     * 获取 closePrice
     * @return closePrice.
     */
    public BigDecimal getClosePrice() {
        return closePrice;
    }
    
    /**
     * 设置 closePrice
     * @param closePrice closePrice
     */
    public void setClosePrice(BigDecimal closePrice) {
        this.closePrice = closePrice;
    }
    
    /**
     * 获取 maxPrice
     * @return maxPrice.
     */
    public BigDecimal getMaxPrice() {
        return maxPrice;
    }
    
    /**
     * 设置 maxPrice
     * @param maxPrice maxPrice
     */
    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }
    
    /**
     * 获取 minPrice
     * @return minPrice.
     */
    public BigDecimal getMinPrice() {
        return minPrice;
    }
    
    /**
     * 设置 minPrice
     * @param minPrice minPrice
     */
    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }
    
    /**
     * 获取 buyOnePrice
     * @return buyOnePrice.
     */
    public BigDecimal getBuyOnePrice() {
        return buyOnePrice;
    }
    
    /**
     * 设置 buyOnePrice
     * @param buyOnePrice buyOnePrice
     */
    public void setBuyOnePrice(BigDecimal buyOnePrice) {
        this.buyOnePrice = buyOnePrice;
    }
    
    /**
     * 获取 sellOnePrice
     * @return sellOnePrice.
     */
    public BigDecimal getSellOnePrice() {
        return sellOnePrice;
    }
    
    /**
     * 设置 sellOnePrice
     * @param sellOnePrice sellOnePrice
     */
    public void setSellOnePrice(BigDecimal sellOnePrice) {
        this.sellOnePrice = sellOnePrice;
    }

    /**
     * 获取 secType
     * @return secType.
     */
    public String getSecType() {
        return secType;
    }

    /**
     * 设置 secType
     * @param secType secType
     */
    public void setSecType(String secType) {
        this.secType = secType;
    }

    /**
     * 获取 averagePrice
     * @return averagePrice.
     */
    public BigDecimal getAveragePrice() {
        return averagePrice;
    }

    /**
     * 设置 averagePrice
     * @param averagePrice averagePrice
     */
    public void setAveragePrice(BigDecimal averagePrice) {
        this.averagePrice = averagePrice;
    }


}