package com.joyintech.service.trade.transfer.buyoutrepo.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * 买断式回购交易拆分列表Dto
 * @author liuwei
 * @version 1.0 2019年11月20日
 * @see BuyoutRepoResoluteDto
 * @since 1.0
 */
public class BuyoutRepoResoluteDto extends BaseEntity {
    /**
     * 实体组合
     */
    private String actPortfolioId;

    /**
     * 组合名称
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
     * 标的资产代码
     */
    private String pledgeFinprodId;
    /**
     * 交易日期
     */
    private Date tradeDate;
    /**
     * 券面总额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal prinAmt;
    /**
     * 回购利率%
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal intRate;
    /**
     * 首次全价
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal unitFprice;
    /**
     * 首次结算金额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal firSettleAmt;
    /**
     * 到期全价
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal mUnitFprice;
    /**
     * 到期结算金额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal dueSettleAmt;
    /**
     * 起息日
     */
    private Date vdate;
    /**
     * 到期日
     */
    private Date mdate;
    /**
     * 回购期限（天）
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal term;
    /**
     * 实际占款天数（天）
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal actCapDays;
    /**
     * 交易手续费
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal tradeFee;
    /**
     * 结算费
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal clearFee;

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
     * 获取 标的资产代码
     *
     * @return pledgeFinprodId 标的资产代码
     */
    public String getPledgeFinprodId() {
        return this.pledgeFinprodId;
    }

    /**
     * 设置 标的资产代码
     *
     * @param pledgeFinprodId 标的资产代码
     */
    public void setPledgeFinprodId(String pledgeFinprodId) {
        this.pledgeFinprodId = pledgeFinprodId;
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
     * 获取 券面总额
     *
     * @return prinAmt 券面总额
     */
    public BigDecimal getPrinAmt() {
        return this.prinAmt;
    }

    /**
     * 设置 券面总额
     *
     * @param prinAmt 券面总额
     */
    public void setPrinAmt(BigDecimal prinAmt) {
        this.prinAmt = prinAmt;
    }

    /**
     * 获取 回购利率%
     *
     * @return intRate 回购利率%
     */
    public BigDecimal getIntRate() {
        return this.intRate;
    }

    /**
     * 设置 回购利率%
     *
     * @param intRate 回购利率%
     */
    public void setIntRate(BigDecimal intRate) {
        this.intRate = intRate;
    }

    /**
     * 获取 首次全价
     *
     * @return unitFprice 首次全价
     */
    public BigDecimal getUnitFprice() {
        return this.unitFprice;
    }

    /**
     * 设置 首次全价
     *
     * @param unitFprice 首次全价
     */
    public void setUnitFprice(BigDecimal unitFprice) {
        this.unitFprice = unitFprice;
    }

    /**
     * 获取 首次结算金额
     *
     * @return firSettleAmt 首次结算金额
     */
    public BigDecimal getFirSettleAmt() {
        return this.firSettleAmt;
    }

    /**
     * 设置 首次结算金额
     *
     * @param firSettleAmt 首次结算金额
     */
    public void setFirSettleAmt(BigDecimal firSettleAmt) {
        this.firSettleAmt = firSettleAmt;
    }

    /**
     * 获取 到期全价
     *
     * @return mUnitFprice 到期全价
     */
    public BigDecimal getMUnitFprice() {
        return this.mUnitFprice;
    }

    /**
     * 设置 到期全价
     *
     * @param mUnitFprice 到期全价
     */
    public void setMUnitFprice(BigDecimal mUnitFprice) {
        this.mUnitFprice = mUnitFprice;
    }

    /**
     * 获取 到期结算金额
     *
     * @return dueSettleAmt 到期结算金额
     */
    public BigDecimal getDueSettleAmt() {
        return this.dueSettleAmt;
    }

    /**
     * 设置 到期结算金额
     *
     * @param dueSettleAmt 到期结算金额
     */
    public void setDueSettleAmt(BigDecimal dueSettleAmt) {
        this.dueSettleAmt = dueSettleAmt;
    }

    /**
     * 获取 起息日
     *
     * @return vdate 起息日
     */
    public Date getVdate() {
        return this.vdate;
    }

    /**
     * 设置 起息日
     *
     * @param vdate 起息日
     */
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 获取 到期日
     *
     * @return mdate 到期日
     */
    public Date getMdate() {
        return this.mdate;
    }

    /**
     * 设置 到期日
     *
     * @param mdate 到期日
     */
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 获取 回购期限（天）
     *
     * @return term 回购期限（天）
     */
    public BigDecimal getTerm() {
        return this.term;
    }

    /**
     * 设置 回购期限（天）
     *
     * @param term 回购期限（天）
     */
    public void setTerm(BigDecimal term) {
        this.term = term;
    }

    /**
     * 获取 实际占款天数（天）
     *
     * @return actCapDays 实际占款天数（天）
     */
    public BigDecimal getActCapDays() {
        return this.actCapDays;
    }

    /**
     * 设置 实际占款天数（天）
     *
     * @param actCapDays 实际占款天数（天）
     */
    public void setActCapDays(BigDecimal actCapDays) {
        this.actCapDays = actCapDays;
    }

    /**
     * 获取 交易手续费
     *
     * @return tradeFee 交易手续费
     */
    public BigDecimal getTradeFee() {
        return this.tradeFee;
    }

    /**
     * 设置 交易手续费
     *
     * @param tradeFee 交易手续费
     */
    public void setTradeFee(BigDecimal tradeFee) {
        this.tradeFee = tradeFee;
    }

    /**
     * 获取 结算费
     *
     * @return clearFee 结算费
     */
    public BigDecimal getClearFee() {
        return this.clearFee;
    }

    /**
     * 设置 结算费
     *
     * @param clearFee 结算费
     */
    public void setClearFee(BigDecimal clearFee) {
        this.clearFee = clearFee;
    }

    /**
     * 获取 组合名称
     *
     * @return tradeId 组合名称
     */
    public String getTradeId() {
        return this.tradeId;
    }

    /**
     * 设置 组合名称
     *
     * @param tradeId 组合名称
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    /**
     * 获取 实体组合
     *
     * @return actPortfolioId 实体组合
     */
    public String getActPortfolioId() {
        return this.actPortfolioId;
    }

    /**
     * 设置 实体组合
     *
     * @param actPortfolioId 实体组合
     */
    public void setActPortfolioId(String actPortfolioId) {
        this.actPortfolioId = actPortfolioId;
    }

    public BigDecimal getmUnitFprice() {
        return mUnitFprice;
    }

    public void setmUnitFprice(BigDecimal mUnitFprice) {
        this.mUnitFprice = mUnitFprice;
    }
}
