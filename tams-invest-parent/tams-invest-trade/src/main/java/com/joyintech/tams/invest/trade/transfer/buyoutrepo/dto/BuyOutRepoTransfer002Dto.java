package com.joyintech.tams.invest.trade.transfer.buyoutrepo.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 买断式回购转仓列表dto
 *
 * @author liuwei
 * @version 1.0 2019年12月31日
 * @see BuyOutRepoTransfer002Dto
 * @since 1.0
 */
public class BuyOutRepoTransfer002Dto extends BaseEntity {
    /**
     * 实际组合代码
     */
    private String actPortfolioId;
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
    private String finprodMarketId;
    /**
     * 交易日期
     */
    private Date tradeDate;
    /**
     * 券面总额（万元）
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal tradeAmt;
    /**
     * 回购利率%
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal intRate;
    /**
     * 起息日
     */
    private Date vdate;
    /**
     * 到期日
     */
    private Date mdate;
    /**
     * 首次结算金额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal firSettleAmt;
    /**
     * 到期净价
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal mUnitCprice;
    /**
     * 到期应计利息
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal mInt;
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
     * 风险试算实体类
     */
    private List<RiskTrialRetDto> riskTrialRetDto;

    /**
     * 各交易实体类
     */
    private FinProductTradeDto finProductTradeDto;
    /**
     * 通道费
     */
    private BigDecimal channelFee;

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
     * @return finprodMarketId 标的资产代码
     */
    public String getFinprodMarketId() {
        return this.finprodMarketId;
    }

    /**
     * 设置 标的资产代码
     *
     * @param finprodMarketId 标的资产代码
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
     * 获取 券面总额（万元）
     *
     * @return tradeAmt 券面总额（万元）
     */
    public BigDecimal getTradeAmt() {
        return this.tradeAmt;
    }

    /**
     * 设置 券面总额（万元）
     *
     * @param tradeAmt 券面总额（万元）
     */
    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
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
     * 获取 到期净价
     *
     * @return mUnitCprice 到期净价
     */
//    public BigDecimal getMUnitCprice() {
//        return this.mUnitCprice;
//    }
//
//    /**
//     * 设置 到期净价
//     *
//     * @param mUnitCprice 到期净价
//     */
//    public void setMUnitCprice(BigDecimal mUnitCprice) {
//        this.mUnitCprice = mUnitCprice;
//    }

    public BigDecimal getmUnitFprice() {
        return mUnitFprice;
    }

    public void setmUnitFprice(BigDecimal mUnitFprice) {
        this.mUnitFprice = mUnitFprice;
    }

    /**
     * 获取 到期应计利息
     *
     * @return mInt 到期应计利息
     */
//    public BigDecimal getMInt() {
//        return this.mInt;
//    }
//
//    /**
//     * 设置 到期应计利息
//     *
//     * @param mInt 到期应计利息
//     */
//    public void setMInt(BigDecimal mInt) {
//        this.mInt = mInt;
//    }


    public BigDecimal getmInt() {
        return mInt;
    }

    public void setmInt(BigDecimal mInt) {
        this.mInt = mInt;
    }

    /**
     * 获取 到期全价
     *
     * @return mUnitFprice 到期全价
     */
//    public BigDecimal getMUnitFprice() {
//        return this.mUnitFprice;
//    }
//
//    /**
//     * 设置 到期全价
//     *
//     * @param mUnitFprice 到期全价
//     */
//    public void setMUnitFprice(BigDecimal mUnitFprice) {
//        this.mUnitFprice = mUnitFprice;
//    }


    public BigDecimal getmUnitCprice() {
        return mUnitCprice;
    }

    public void setmUnitCprice(BigDecimal mUnitCprice) {
        this.mUnitCprice = mUnitCprice;
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
     * 获取 风险试算实体类
     *
     * @return riskTrialRetDto 风险试算实体类
     */
    public List<RiskTrialRetDto> getRiskTrialRetDto() {
        return this.riskTrialRetDto;
    }

    /**
     * 设置 风险试算实体类
     *
     * @param riskTrialRetDto 风险试算实体类
     */
    public void setRiskTrialRetDto(List<RiskTrialRetDto> riskTrialRetDto) {
        this.riskTrialRetDto = riskTrialRetDto;
    }

    /**
     * 获取 实际组合代码
     *
     * @return actPortfolioId 实际组合代码
     */
    public String getActPortfolioId() {
        return this.actPortfolioId;
    }

    /**
     * 设置 实际组合代码
     *
     * @param actPortfolioId 实际组合代码
     */
    public void setActPortfolioId(String actPortfolioId) {
        this.actPortfolioId = actPortfolioId;
    }

    /**
     * 获取 各交易实体类
     *
     * @return finProductTradeDto 各交易实体类
     */
    public FinProductTradeDto getFinProductTradeDto() {
        return this.finProductTradeDto;
    }

    /**
     * 设置 各交易实体类
     *
     * @param finProductTradeDto 各交易实体类
     */
    public void setFinProductTradeDto(FinProductTradeDto finProductTradeDto) {
        this.finProductTradeDto = finProductTradeDto;
    }

    /**
     * 获取 通道费
     *
     * @return channelFee 通道费
     */
    public BigDecimal getChannelFee() {
        return this.channelFee;
    }

    /**
     * 设置 通道费
     *
     * @param channelFee 通道费
     */
    public void setChannelFee(BigDecimal channelFee) {
        this.channelFee = channelFee;
    }
}
