package com.joyintech.interf.bankname.cfetsup.secrepoquote.dto;

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 选择委托
 *
 * @author zcf
 * @version 1.0
 * @date 2020-01-18
 */
public class FxSecrepoQuote004Dto extends BaseEntity {

    /**
     * 交易员
     */
    private String trader;

    /**
     * 委托编号
     */
    private String entrustId;

    /**
     * 接收状态
     */
    private String recStatus;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 金融产品代码
     */
    private String  finprodId;

    /**
     * 组合名称
     */
    private String portfolioName;

    /**
     * 金融产品名称
     */
    private String finprodName;

    /**
     * 市场代码
     */
    private String finprodMarketId;

    /**
     * 交易方向
     */
    private String ps;

    /**
     * 委托金额
     */
    private BigDecimal entrustAmt;

    /**
     * 委托状态
     */
    private String entrustStatus;

    /**
     * 资产类型
     */
    private String assetType;

    /**
     * 回购/拆借方向，正回购、逆回购、拆入、拆出
     */
    private String busiPs;

    /**
     * 委托开始日
     */
    private Date vdate;

    /**
     * 委托到期日
     */
    private Date mdate;

    /**
     * 委托下发日
     */
    private Date entrustDate;

    public String getTrader() {
        return trader;
    }

    public void setTrader(String trader) {
        this.trader = trader;
    }

    public String getEntrustId() {
        return entrustId;
    }

    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }

    public String getRecStatus() {
        return recStatus;
    }

    public void setRecStatus(String recStatus) {
        this.recStatus = recStatus;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public String getFinprodName() {
        return finprodName;
    }

    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
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

    public BigDecimal getEntrustAmt() {
        return entrustAmt;
    }

    public void setEntrustAmt(BigDecimal entrustAmt) {
        this.entrustAmt = entrustAmt;
    }

    public String getEntrustStatus() {
        return entrustStatus;
    }

    public void setEntrustStatus(String entrustStatus) {
        this.entrustStatus = entrustStatus;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getBusiPs() {
        return busiPs;
    }

    public void setBusiPs(String busiPs) {
        this.busiPs = busiPs;
    }

    public Date getVdate() {
        return vdate;
    }

    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    public Date getMdate() {
        return mdate;
    }

    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    public Date getEntrustDate() {
        return entrustDate;
    }

    public void setEntrustDate(Date entrustDate) {
        this.entrustDate = entrustDate;
    }
}