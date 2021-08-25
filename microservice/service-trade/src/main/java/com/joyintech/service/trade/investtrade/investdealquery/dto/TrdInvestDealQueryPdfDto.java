package com.joyintech.service.trade.investtrade.investdealquery.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 交易查询导出pdf
 *
 * @author zcf
 * @version 1.0
 * @date 2021-08-03
 */
public class TrdInvestDealQueryPdfDto {

    /**
     * 交易编号
     */
    private String tradeId;
    /**
     * 组合代码
     */
    private String portfolioId;
    /**
     * 组合名称
     */
    private String portfolioName;
    /**
     * 资产类型
     */
    private String finprodType2;
    /**
     * 资产类型
     */
    private String finprodTypeStr;
    /**
     * 资产代码
     */
    private String finprodMarketId;
    /**
     * 资产简称
     */
    private String finprodAbbr;
    /**
     * 交易方向
     */
    private String ps;
    /**
     * 交易方向
     */
    private String psStr;
    /**
     * 交易方式
     */
    private String tradeMarketMode;
    /**
     * 交易方式
     */
    private String tradeMarketModeStr;
    /**
     * 交易金额
     */
    private BigDecimal tradeAmt;
    /**
     * 交易价格
     */
    private BigDecimal unitCprice;
    /**
     * 交易日期
     */
    private Date tradeDate;
    /**
     * 交易日期
     */
    private String tradeDateStr;
    /**
     * 交易对手
     */
    private String counterName;

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public String getFinprodType2() {
        return finprodType2;
    }

    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2;
    }

    public String getFinprodTypeStr() {
        return finprodTypeStr;
    }

    public void setFinprodTypeStr(String finprodTypeStr) {
        this.finprodTypeStr = finprodTypeStr;
    }

    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public String getPsStr() {
        return psStr;
    }

    public void setPsStr(String psStr) {
        this.psStr = psStr;
    }

    public String getTradeMarketMode() {
        return tradeMarketMode;
    }

    public void setTradeMarketMode(String tradeMarketMode) {
        this.tradeMarketMode = tradeMarketMode;
    }

    public String getTradeMarketModeStr() {
        return tradeMarketModeStr;
    }

    public void setTradeMarketModeStr(String tradeMarketModeStr) {
        this.tradeMarketModeStr = tradeMarketModeStr;
    }

    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    public BigDecimal getUnitCprice() {
        return unitCprice;
    }

    public void setUnitCprice(BigDecimal unitCprice) {
        this.unitCprice = unitCprice;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getTradeDateStr() {
        return tradeDateStr;
    }

    public void setTradeDateStr(String tradeDateStr) {
        this.tradeDateStr = tradeDateStr;
    }

    public String getCounterName() {
        return counterName;
    }

    public void setCounterName(String counterName) {
        this.counterName = counterName;
    }
}
