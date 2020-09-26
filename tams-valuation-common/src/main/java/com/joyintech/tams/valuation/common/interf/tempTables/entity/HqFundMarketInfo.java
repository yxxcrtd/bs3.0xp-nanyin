package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

public class HqFundMarketInfo extends BaseEntity {
    /**
     * 证券代码
     */
    private String fundCode;
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
     * 获取 fundCode
     * @return fundCode.
     */
    public String getFundCode() {
        return fundCode;
    }
    
    /**
     * 设置 fundCode
     * @param fundCode fundCode
     */
    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
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

}