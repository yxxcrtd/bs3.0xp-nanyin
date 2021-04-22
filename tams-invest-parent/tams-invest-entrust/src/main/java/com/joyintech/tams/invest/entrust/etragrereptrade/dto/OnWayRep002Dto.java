/*
 * 文件名：EtrExchangeTradeDto002.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chendijun
 * 修改时间：2020年7月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.entrust.etragrereptrade.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.systemtools.util.SysDatadictUtil;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author chendijun
 * @version 1.0 2020年7月2日
 * @see OnWayRep002Dto
 * @since 1.0
 */
@PageId("OnWayRep002Dto")
public class OnWayRep002Dto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -5799209247492596989L;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 组合名称
     */
    private String portfolioName;

    /**
     * 交易方向
     */
    private String ps;
    /**
     * 交易方向
     */
    private String psStr;
    /**
     * 购回状态
     */
    private String purStatus;
    /**
     * 交收状态
     */
    private String settleStatus;
    /**
     * 回购天数
     */
    private Integer repDays;
    /**
     * 回购到期日
     */
    private Date backDate;
    /**
     * 市场
     */
    private String market;
    private String marketStr;
    /**
     * 质押券代码
     */
    private String stockCode;
    /**
     * 折算率
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal discountRate;
    /**
     * 质押券数量
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal repSecNum;
    /**
     * 质押券面总额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal pledStrdPrinAmt;
    /**
     * 回购金额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal tradeAmt;
    /**
     * 回购利率
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal yield;
    /**
     * 首次结算日
     */
    private Date fristSettleDate;
    /**
     * 到期结算日
     */
    private Date expireSettleDate;
    /**
     * 实际占款天数
     */
    private Integer occupyDays;
    /**
     * 到期利息
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal mIntAmt;
    /**
     * 到期结算金额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal expireSettleAmt;
    /**
     * 成交编号
     */
    private String tradeId;
    /**
     * 初始交易编号
     */
    private String intiTradeId;

    /**
     * 质押券名称
     */
    private String stockName;

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

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public String getPurStatus() {
        return purStatus;
    }

    public void setPurStatus(String purStatus) {
        this.purStatus = purStatus;
    }

    public String getSettleStatus() {
        return settleStatus;
    }

    public void setSettleStatus(String settleStatus) {
        this.settleStatus = settleStatus;
    }

    public Integer getRepDays() {
        return repDays;
    }

    public void setRepDays(Integer repDays) {
        this.repDays = repDays;
    }

    public Date getBackDate() {
        return backDate;
    }

    public void setBackDate(Date backDate) {
        this.backDate = backDate;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    public BigDecimal getRepSecNum() {
        return repSecNum;
    }

    public void setRepSecNum(BigDecimal repSecNum) {
        this.repSecNum = repSecNum;
    }

    public BigDecimal getPledStrdPrinAmt() {
        return pledStrdPrinAmt;
    }

    public void setPledStrdPrinAmt(BigDecimal pledStrdPrinAmt) {
        this.pledStrdPrinAmt = pledStrdPrinAmt;
    }

    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    public BigDecimal getYield() {
        return yield;
    }

    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }

    public Date getFristSettleDate() {
        return fristSettleDate;
    }

    public void setFristSettleDate(Date fristSettleDate) {
        this.fristSettleDate = fristSettleDate;
    }

    public Date getExpireSettleDate() {
        return expireSettleDate;
    }

    public void setExpireSettleDate(Date expireSettleDate) {
        this.expireSettleDate = expireSettleDate;
    }

    public Integer getOccupyDays() {
        return occupyDays;
    }

    public void setOccupyDays(Integer occupyDays) {
        this.occupyDays = occupyDays;
    }

    public BigDecimal getmIntAmt() {
        return mIntAmt;
    }

    public void setmIntAmt(BigDecimal mIntAmt) {
        this.mIntAmt = mIntAmt;
    }

    public BigDecimal getExpireSettleAmt() {
        return expireSettleAmt;
    }

    public void setExpireSettleAmt(BigDecimal expireSettleAmt) {
        this.expireSettleAmt = expireSettleAmt;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getIntiTradeId() {
        return intiTradeId;
    }

    public void setIntiTradeId(String intiTradeId) {
        this.intiTradeId = intiTradeId;
    }

    public String getPsStr() {
        return SysDatadictUtil.getNameFromDataDict(DictCode.交易方向,this.ps);
    }

    public String getMarketStr() {
        return SysDatadictUtil.getNameFromDataDict(DictCode.交易流通场所,this.market);
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }
}
