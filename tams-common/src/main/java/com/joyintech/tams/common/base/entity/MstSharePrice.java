package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class MstSharePrice extends MstSharePriceKey {
    /**
     * 资产简称
     */
	private String finprodAbbr;
	
	/**
     * 币种
     */
    private String ccy;

    /**
     * 前收盘价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal preClosePrice;

    /**
     * 开盘价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal openPrice;

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
     * 收盘价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal closePrice;

    /**
     * 当日总股本
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sShares;

    /**
     * 当日流通股本
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cShares;

    /**
     * 涨跌
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal change;

    /**
     * 涨跌幅
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal changePercent;

    /**
     * 成交量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dealValume;

    /**
     * 成交金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dealAmount;

    /**
     * 均价，成交金额/成交量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal avgPrice;

    /**
     * 前收盘价(复权)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal preClosePriceAdj;

    /**
     * 开盘价(复权)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal openPriceAdj;

    /**
     * 最高价(复权)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal highPriceAdj;

    /**
     * 最低价(复权)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal lowPriceAdj;

    /**
     * 收盘价(复权)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal closePriceAdj;

    /**
     * 复权因子，初始值为 1；当日复权因子=
前一交易日收盘价/当日昨
收盘价*前一交易日复权因
子
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal factorAdj;

    /**
     * 交易状态
     */
    private String tradeStatus;

    /**
     * 录入方式
     */
    private String inputType;

    /**
     * 取得 币种
     * @return 币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 前收盘价
     * @return 前收盘价
     */
    public BigDecimal getPreClosePrice() {
        return preClosePrice;
    }

    /**
     * 设置 前收盘价
     * @param preClosePrice 前收盘价
     */
    public void setPreClosePrice(BigDecimal preClosePrice) {
        this.preClosePrice = preClosePrice;
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
     * 取得 当日总股本
     * @return 当日总股本
     */
    public BigDecimal getsShares() {
        return sShares;
    }

    /**
     * 设置 当日总股本
     * @param sShares 当日总股本
     */
    public void setsShares(BigDecimal sShares) {
        this.sShares = sShares;
    }

    /**
     * 取得 当日流通股本
     * @return 当日流通股本
     */
    public BigDecimal getcShares() {
        return cShares;
    }

    /**
     * 设置 当日流通股本
     * @param cShares 当日流通股本
     */
    public void setcShares(BigDecimal cShares) {
        this.cShares = cShares;
    }

    /**
     * 取得 涨跌
     * @return 涨跌
     */
    public BigDecimal getChange() {
        return change;
    }

    /**
     * 设置 涨跌
     * @param change 涨跌
     */
    public void setChange(BigDecimal change) {
        this.change = change;
    }

    /**
     * 取得 涨跌幅
     * @return 涨跌幅
     */
    public BigDecimal getChangePercent() {
        return changePercent;
    }

    /**
     * 设置 涨跌幅
     * @param changePercent 涨跌幅
     */
    public void setChangePercent(BigDecimal changePercent) {
        this.changePercent = changePercent;
    }

    /**
     * 取得 成交量
     * @return 成交量
     */
    public BigDecimal getDealValume() {
        return dealValume;
    }

    /**
     * 设置 成交量
     * @param dealValume 成交量
     */
    public void setDealValume(BigDecimal dealValume) {
        this.dealValume = dealValume;
    }

    /**
     * 取得 成交金额
     * @return 成交金额
     */
    public BigDecimal getDealAmount() {
        return dealAmount;
    }

    /**
     * 设置 成交金额
     * @param dealAmount 成交金额
     */
    public void setDealAmount(BigDecimal dealAmount) {
        this.dealAmount = dealAmount;
    }

    /**
     * 取得 均价，成交金额/成交量
     * @return 均价，成交金额/成交量
     */
    public BigDecimal getAvgPrice() {
        return avgPrice;
    }

    /**
     * 设置 均价，成交金额/成交量
     * @param avgPrice 均价，成交金额/成交量
     */
    public void setAvgPrice(BigDecimal avgPrice) {
        this.avgPrice = avgPrice;
    }

    /**
     * 取得 前收盘价(复权)
     * @return 前收盘价(复权)
     */
    public BigDecimal getPreClosePriceAdj() {
        return preClosePriceAdj;
    }

    /**
     * 设置 前收盘价(复权)
     * @param preClosePriceAdj 前收盘价(复权)
     */
    public void setPreClosePriceAdj(BigDecimal preClosePriceAdj) {
        this.preClosePriceAdj = preClosePriceAdj;
    }

    /**
     * 取得 开盘价(复权)
     * @return 开盘价(复权)
     */
    public BigDecimal getOpenPriceAdj() {
        return openPriceAdj;
    }

    /**
     * 设置 开盘价(复权)
     * @param openPriceAdj 开盘价(复权)
     */
    public void setOpenPriceAdj(BigDecimal openPriceAdj) {
        this.openPriceAdj = openPriceAdj;
    }

    /**
     * 取得 最高价(复权)
     * @return 最高价(复权)
     */
    public BigDecimal getHighPriceAdj() {
        return highPriceAdj;
    }

    /**
     * 设置 最高价(复权)
     * @param highPriceAdj 最高价(复权)
     */
    public void setHighPriceAdj(BigDecimal highPriceAdj) {
        this.highPriceAdj = highPriceAdj;
    }

    /**
     * 取得 最低价(复权)
     * @return 最低价(复权)
     */
    public BigDecimal getLowPriceAdj() {
        return lowPriceAdj;
    }

    /**
     * 设置 最低价(复权)
     * @param lowPriceAdj 最低价(复权)
     */
    public void setLowPriceAdj(BigDecimal lowPriceAdj) {
        this.lowPriceAdj = lowPriceAdj;
    }

    /**
     * 取得 收盘价(复权)
     * @return 收盘价(复权)
     */
    public BigDecimal getClosePriceAdj() {
        return closePriceAdj;
    }

    /**
     * 设置 收盘价(复权)
     * @param closePriceAdj 收盘价(复权)
     */
    public void setClosePriceAdj(BigDecimal closePriceAdj) {
        this.closePriceAdj = closePriceAdj;
    }

    /**
     * 取得 复权因子，初始值为 1；当日复权因子=
前一交易日收盘价/当日昨
收盘价*前一交易日复权因
子
     * @return 复权因子，初始值为 1；当日复权因子=
前一交易日收盘价/当日昨
收盘价*前一交易日复权因
子
     */
    public BigDecimal getFactorAdj() {
        return factorAdj;
    }

    /**
     * 设置 复权因子，初始值为 1；当日复权因子=
前一交易日收盘价/当日昨
收盘价*前一交易日复权因
子
     * @param factorAdj 复权因子，初始值为 1；当日复权因子=
前一交易日收盘价/当日昨
收盘价*前一交易日复权因
子
     */
    public void setFactorAdj(BigDecimal factorAdj) {
        this.factorAdj = factorAdj;
    }

    /**
     * 取得 交易状态
     * @return 交易状态
     */
    public String getTradeStatus() {
        return tradeStatus;
    }

    /**
     * 设置 交易状态
     * @param tradeStatus 交易状态
     */
    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus == null ? null : tradeStatus.trim();
    }

    /**
     * 取得 录入方式
     * @return 录入方式
     */
    public String getInputType() {
        return inputType;
    }

    /**
     * 设置 录入方式
     * @param inputType 录入方式
     */
    public void setInputType(String inputType) {
        this.inputType = inputType == null ? null : inputType.trim();
    }

	public String getFinprodAbbr() {
		return finprodAbbr;
	}

	public void setFinprodAbbr(String finprodAbbr) {
		this.finprodAbbr = finprodAbbr;
	}
}