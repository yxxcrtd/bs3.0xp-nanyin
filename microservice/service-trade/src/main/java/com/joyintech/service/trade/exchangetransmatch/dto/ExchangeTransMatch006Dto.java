package com.joyintech.service.trade.exchangetransmatch.dto;

import com.joyintech.background.component.common.pack.common.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 匹配明细查看页面-委托数据列表
 */
public class ExchangeTransMatch006Dto extends BaseEntity{
    //匹配方式
    private String matchClass;
    //业务编号
    private String entrustTradeId;
    //交易场所
    private String tradeMarket;
    //申请编号
    private String applyTradeId;
    //组合名称
    private String portfolioName;
    //组合代码
    private String portfolioId;
    //资产类型
    private String secType;
    //资产代码
    private String finprodMarketId;
    //交易方向
    private String ps;
    //交易金额
    private BigDecimal marchAmt;
    //交易数量
    private BigDecimal marchQty;
    //交易价格
    private BigDecimal tradeAmt;
    //交易日期
    private Date tradeDate;
    //币种
    private String ccy;

    public String getMatchClass() {
        return matchClass;
    }

    public void setMatchClass(String matchClass) {
        this.matchClass = matchClass;
    }

    public String getEntrustTradeId() {
        return entrustTradeId;
    }

    public void setEntrustTradeId(String entrustTradeId) {
        this.entrustTradeId = entrustTradeId;
    }

    public String getTradeMarket() {
        return tradeMarket;
    }

    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }

    public String getApplyTradeId() {
        return applyTradeId;
    }

    public void setApplyTradeId(String applyTradeId) {
        this.applyTradeId = applyTradeId;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getSecType() {
        return secType;
    }

    public void setSecType(String secType) {
        this.secType = secType;
    }

    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public BigDecimal getMarchAmt() {
        return marchAmt;
    }

    public void setMarchAmt(BigDecimal marchAmt) {
        this.marchAmt = marchAmt;
    }

    public BigDecimal getMarchQty() {
        return marchQty;
    }

    public void setMarchQty(BigDecimal marchQty) {
        this.marchQty = marchQty;
    }

    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }
}
