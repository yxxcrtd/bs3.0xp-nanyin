/*
 * 文件名：ValExchangeRateInfo001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangqidong
 * 修改时间：2018年8月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.basedata.marketdata.exchangerateinfo.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.DateUtil;

/**
 * 实体类
 * 〈功能详细描述〉
 * @author wangqidong
 * @version 1.0 2018年8月7日
 * @see ValExchangeRateInfo001Dto
 * @since 1.0
 */
public class ValExchangeRateInfo001Dto extends BaseEntity{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 货币对
     */
    private String currencyPair;
    
    /**
     * 外汇类型
     */
    private String foreignExchangeType;
    
    /**
     * 交易日期
     */
    private String tradeDate;
    
    /**
     * 交易开始日期
     */
    private Date startDate;
    
    /**
     * 交易结束日期
     */
    private Date endDate;
    
    /**
     * 期限
     */
    private String term;
    
    /**
     * 到期日
     */
    private Date expiryDate;

    /**
     * 开盘价
     */
    private BigDecimal openPrice;
    
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
     * 数据来源
     */
    private String dataSources;

    
    /**
     * 获取 currencyPair
     * @return currencyPair.
     */
    public String getCurrencyPair() {
        return currencyPair;
    }

    
    /**
     * 设置 currencyPair
     * @param currencyPair currencyPair
     */
    public void setCurrencyPair(String currencyPair) {
        this.currencyPair = currencyPair;
    }

    
    /**
     * 获取 foreignExchangeType
     * @return foreignExchangeType.
     */
    public String getForeignExchangeType() {
        return foreignExchangeType;
    }

    
    /**
     * 设置 foreignExchangeType
     * @param foreignExchangeType foreignExchangeType
     */
    public void setForeignExchangeType(String foreignExchangeType) {
        this.foreignExchangeType = foreignExchangeType;
    }

    
    /**
     * 获取 tradeDate
     * @return tradeDate.
     */
    public String getTradeDate() {
        return DateUtil.formatDateAddSplit(tradeDate);
    }

    
    /**
     * 设置 tradeDate
     * @param tradeDate tradeDate
     */
    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    
    /**
     * 获取 startDate
     * @return startDate.
     */
    public Date getStartDate() {
        return startDate;
    }

    
    /**
     * 设置 startDate
     * @param startDate startDate
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    
    /**
     * 获取 endDate
     * @return endDate.
     */
    public Date getEndDate() {
        return endDate;
    }

    
    /**
     * 设置 endDate
     * @param endDate endDate
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    
    /**
     * 获取 term
     * @return term.
     */
    public String getTerm() {
        return term;
    }

    
    /**
     * 设置 term
     * @param term term
     */
    public void setTerm(String term) {
        this.term = term;
    }

    
    /**
     * 获取 expiryDate
     * @return expiryDate.
     */
    public Date getExpiryDate() {
        return expiryDate;
    }

    
    /**
     * 设置 expiryDate
     * @param expiryDate expiryDate
     */
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    
    /**
     * 获取 openPrice
     * @return openPrice.
     */
    public BigDecimal getOpenPrice() {
        return openPrice;
    }

    
    /**
     * 设置 openPrice
     * @param openPrice openPrice
     */
    public void setOpenPrice(BigDecimal openPrice) {
        this.openPrice = openPrice;
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
     * 获取 dataSources
     * @return dataSources.
     */
    public String getDataSources() {
        return dataSources;
    }

    
    /**
     * 设置 dataSources
     * @param dataSources dataSources
     */
    public void setDataSources(String dataSources) {
        this.dataSources = dataSources;
    }
        
}




