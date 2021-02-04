package com.joyintech.service.trade.transfer.netplantransfer.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: HXB2.1.0
 * @description:
 * @author: liuwei
 * @create: 2019-11-14 17:18
 **/
public class NetPlanTransferDto002 extends BaseEntity {
    /**
     * 组合代码
     */
    private String portfolioId;
    /**
     * 组合名称
     */
    private String portfolioName;
    /**
     * 资产代码
     */
    private String finprodMarketId;
    /**
     * 交易日期
     */
    private Date tradeDate;
    /**
     * 交易份额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal tradeAmt;
    /**
     * 单位净值
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal unitValue;
    /**
     * 交易金额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal tradeMoney;

    /**
     * 获取 组合代码
     *
     * @return portfolioId 组合代码
     */
    public String getPortfolioId() {
        return this.portfolioId;
    }

    /**
     * 设置 组合代码
     *
     * @param portfolioId 组合代码
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    /**
     * 获取 组合名称
     *
     * @return portfolioName 组合名称
     */
    public String getPortfolioName() {
        return this.portfolioName;
    }

    /**
     * 设置 组合名称
     *
     * @param portfolioName 组合名称
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    /**
     * 获取 资产代码
     *
     * @return finprodMarketId 资产代码
     */
    public String getFinprodMarketId() {
        return this.finprodMarketId;
    }

    /**
     * 设置 资产代码
     *
     * @param finprodMarketId 资产代码
     */
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    /**
     * 获取 交易日期
     *
     * @return tradeDate 交易日期
     */
    public Date getTradeDate() {
        return this.tradeDate;
    }

    /**
     * 设置 交易日期
     *
     * @param tradeDate 交易日期
     */
    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    /**
     * 获取 交易份额
     *
     * @return tradeAmt 交易份额
     */
    public BigDecimal getTradeAmt() {
        return this.tradeAmt;
    }

    /**
     * 设置 交易份额
     *
     * @param tradeAmt 交易份额
     */
    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    /**
     * 获取 单位净值
     *
     * @return unitValue 单位净值
     */
    public BigDecimal getUnitValue() {
        return this.unitValue;
    }

    /**
     * 设置 单位净值
     *
     * @param unitValue 单位净值
     */
    public void setUnitValue(BigDecimal unitValue) {
        this.unitValue = unitValue;
    }

    /**
     * 获取 单位净值
     *
     * @return tradeMoney 单位净值
     */
    public BigDecimal getTradeMoney() {
        return this.tradeMoney;
    }

    /**
     * 设置 单位净值
     *
     * @param tradeMoney 单位净值
     */
    public void setTradeMoney(BigDecimal tradeMoney) {
        this.tradeMoney = tradeMoney;
    }
}
