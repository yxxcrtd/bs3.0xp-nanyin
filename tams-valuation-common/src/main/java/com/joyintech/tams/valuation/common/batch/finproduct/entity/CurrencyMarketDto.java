package com.joyintech.tams.valuation.common.batch.finproduct.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

/**
 * 汇率行情 - 实体类
 * 
 * @author chufengjie
 * @version 1.0 2019年5月17日
 * @see CurrencyMarketDto
 * @since 1.0
 */
public class CurrencyMarketDto extends BaseEntity{
	
    /**
     * 汇率
     */
    private BigDecimal closePrice;
    
    /**
     * 汇率日期
     */
    private Date currencyDate;
    
    /**
     * 行情代码，一般为货币对
     */
    private String exchargeCode;

    /**
     * 交易日
     */
    private Date tradeDate;
    
    /**
     * 数据来源
     */
    private String datasourceType;

    /**
     * 开盘价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal openPrice;

    /**
     * 收盘价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal closePrice1;

    /**
     * 最高价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal highPrice;

    /**
     * 最低价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal lowPrice;

    /**
     * 中间价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal midPrice;

    /**
     * 基础币种，比如USD/CNY中的USD
     */
    private String basisCyy;

    /**
     * 报价币种，比如USD/CNY中的CNY
     */
    private String quotCyy;

    /**
     * 报价单位
     */
    private Long quotUnit;

    /**
     * 录入方式，接口、手工等
     */
    private String inputType;

    /**
     * 取得 行情代码，一般为货币对
     * @return 行情代码，一般为货币对
     */
    public String getExchargeCode() {
        return exchargeCode;
    }

	public BigDecimal getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(BigDecimal closePrice) {
		this.closePrice = closePrice;
	}

	public Date getCurrencyDate() {
		return currencyDate;
	}

	public void setCurrencyDate(Date currencyDate) {
		this.currencyDate = currencyDate;
	}
	
    /**
     * 设置 行情代码，一般为货币对
     * @param exchargeCode 行情代码，一般为货币对
     */
    public void setExchargeCode(String exchargeCode) {
        this.exchargeCode = exchargeCode == null ? null : exchargeCode.trim();
    }

    /**
     * 取得 交易日
     * @return 交易日
     */
    public Date getTradeDate() {
        return tradeDate;
    }

    /**
     * 设置 交易日
     * @param tradeDate 交易日
     */
    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    /**
     * 取得 数据来源
     * @return 数据来源
     */
    public String getDatasourceType() {
        return datasourceType;
    }

    /**
     * 设置 数据来源
     * @param datasourceType 数据来源
     */
    public void setDatasourceType(String datasourceType) {
        this.datasourceType = datasourceType == null ? null : datasourceType.trim();
    }

    /**
     * 取得 开盘价
     * @return 开盘价
     */
    public BigDecimal getOpenPrice() {
        return openPrice;
    }

    /**
     * 设置 开盘价
     * @param openPrice 开盘价
     */
    public void setOpenPrice(BigDecimal openPrice) {
        this.openPrice = openPrice;
    }

    /**
     * 取得 收盘价
     * @return 收盘价
     */
    public BigDecimal getClosePrice1() {
        return closePrice1;
    }

    /**
     * 设置 收盘价
     * @param closePrice 收盘价
     */
    public void setClosePrice1(BigDecimal closePrice1) {
        this.closePrice1 = closePrice1;
    }

    /**
     * 取得 最高价
     * @return 最高价
     */
    public BigDecimal getHighPrice() {
        return highPrice;
    }

    /**
     * 设置 最高价
     * @param highPrice 最高价
     */
    public void setHighPrice(BigDecimal highPrice) {
        this.highPrice = highPrice;
    }

    /**
     * 取得 最低价
     * @return 最低价
     */
    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    /**
     * 设置 最低价
     * @param lowPrice 最低价
     */
    public void setLowPrice(BigDecimal lowPrice) {
        this.lowPrice = lowPrice;
    }

    /**
     * 取得 中间价
     * @return 中间价
     */
    public BigDecimal getMidPrice() {
        return midPrice;
    }

    /**
     * 设置 中间价
     * @param midPrice 中间价
     */
    public void setMidPrice(BigDecimal midPrice) {
        this.midPrice = midPrice;
    }

    /**
     * 取得 基础币种，比如USD/CNY中的USD
     * @return 基础币种，比如USD/CNY中的USD
     */
    public String getBasisCyy() {
        return basisCyy;
    }

    /**
     * 设置 基础币种，比如USD/CNY中的USD
     * @param basisCyy 基础币种，比如USD/CNY中的USD
     */
    public void setBasisCyy(String basisCyy) {
        this.basisCyy = basisCyy == null ? null : basisCyy.trim();
    }

    /**
     * 取得 报价币种，比如USD/CNY中的CNY
     * @return 报价币种，比如USD/CNY中的CNY
     */
    public String getQuotCyy() {
        return quotCyy;
    }

    /**
     * 设置 报价币种，比如USD/CNY中的CNY
     * @param quotCyy 报价币种，比如USD/CNY中的CNY
     */
    public void setQuotCyy(String quotCyy) {
        this.quotCyy = quotCyy == null ? null : quotCyy.trim();
    }

    /**
     * 取得 报价单位
     * @return 报价单位
     */
    public Long getQuotUnit() {
        return quotUnit;
    }

    /**
     * 设置 报价单位
     * @param quotUnit 报价单位
     */
    public void setQuotUnit(Long quotUnit) {
        this.quotUnit = quotUnit;
    }

    /**
     * 取得 录入方式，接口、手工等
     * @return 录入方式，接口、手工等
     */
    public String getInputType() {
        return inputType;
    }

    /**
     * 设置 录入方式，接口、手工等
     * @param inputType 录入方式，接口、手工等
     */
    public void setInputType(String inputType) {
        this.inputType = inputType == null ? null : inputType.trim();
    }
}
