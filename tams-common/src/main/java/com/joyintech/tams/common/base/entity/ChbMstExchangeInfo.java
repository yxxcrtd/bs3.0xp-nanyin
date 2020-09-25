package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class ChbMstExchangeInfo extends ChbMstExchangeInfoKey {
    /**
     * 汇率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal exchangePrice;

    /**
     * 经办人
     */
    private String agent;
    /**
     * 汇率(收盘价)
     */
    private BigDecimal closePrice;
    /**
     * 汇率(开盘价)
     */
    private BigDecimal openPrice;
    /**
     * 汇率(最高价)
     */
    private BigDecimal highPrice;
    /**
     * 汇率(最低价)
     */
    private BigDecimal lowPrice;
    /**
     * 汇率(中间价)
     */
    private BigDecimal midPrice;
    /**
     * 报价单位
     */
    private BigDecimal quotUnit;
    
   

	public BigDecimal getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(BigDecimal closePrice) {
		this.closePrice = closePrice;
	}

	public BigDecimal getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(BigDecimal openPrice) {
		this.openPrice = openPrice;
	}

	public BigDecimal getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(BigDecimal highPrice) {
		this.highPrice = highPrice;
	}

	public BigDecimal getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(BigDecimal lowPrice) {
		this.lowPrice = lowPrice;
	}

	public BigDecimal getMidPrice() {
		return midPrice;
	}

	public void setMidPrice(BigDecimal midPrice) {
		this.midPrice = midPrice;
	}

	public BigDecimal getQuotUnit() {
		return quotUnit;
	}

	public void setQuotUnit(BigDecimal quotUnit) {
		this.quotUnit = quotUnit;
	}

	/**
     * 取得 汇率
     * @return 汇率
     */
    public BigDecimal getExchangePrice() {
        return exchangePrice;
    }

    /**
     * 设置 汇率
     * @param exchangePrice 汇率
     */
    public void setExchangePrice(BigDecimal exchangePrice) {
        this.exchangePrice = exchangePrice;
    }

    /**
     * 取得 经办人
     * @return 经办人
     */
    public String getAgent() {
        return agent;
    }

    /**
     * 设置 经办人
     * @param agent 经办人
     */
    public void setAgent(String agent) {
        this.agent = agent == null ? null : agent.trim();
    }
}