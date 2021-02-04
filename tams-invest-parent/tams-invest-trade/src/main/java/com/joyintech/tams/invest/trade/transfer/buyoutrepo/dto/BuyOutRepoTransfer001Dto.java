package com.joyintech.tams.invest.trade.transfer.buyoutrepo.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 买断式回购转仓
 *
 * @author liuwei
 * @version 1.0 2019年12月31日
 * @see BuyOutRepoTransfer001Dto
 * @since 1.0
 */
public class BuyOutRepoTransfer001Dto extends BaseEntity implements ProcessEntity {
    /**
     * 实际组合代码
     */
    private String actPortfolioId;
    /**
     * 待转出组合
     */
    private String portfolioIdOut;
    /**
     * 待转入组合
     */
    private String portfolioId;
    /**
     * 风险试算实体类
     */
    private List<RiskTrialRetDto> riskTrialRetDto;
    /**
     * 原始交易编号
     */
    private String tradeId;
    /**
     * 画面元素
     */
    private String elementId;
    /**
     * 转仓信息列表dtolist
     */
    private List<com.joyintech.tams.invest.trade.transfer.buyoutrepo.dto.BuyOutRepoTransfer002Dto> buyOutRepoTransfer002DtoList;
    /**
     * 操作方式
     */
    private String operateType;
    /**
     * 交易日期
     */
    private Date tradeDate;
    /**
     * 交易方向
     */
    private String ps;
    /**
     * 标的资产代码
     */
    private String finprodMarketId;
    /**
     * 标的金融产品代码
     */
    private String finprodId;
    /**
     * 标的资产简称
     */
    private String finprodAbbr;
    /**
     * 期限品种
     */
    private String termType;
    /**
     * 起息日
     */
    private Date vdate;
    /**
     * 到期日
     */
    private Date mdate;
    /**
     * 实际占款天数
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal actCapDays;
    /**
     * 首次净价
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal unitCprice;
    /**
     * 首次应计利息
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal unitInt;
    /**
     * 首次全价
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal unitFprice;
    /**
     * 到期净价
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal mUnitCprice;
    /**
     * 到期应计利息
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal mUnitInt;
    /**
     * 到期全价
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal mUnitFprice;
    /**
     * 首次结算金额（基本信息）
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal firSettleAmtS;
    /**
     * 到期结算金额（基本信息）
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal dueSettleAmtS;
    /**
     * 计息基础
     */
    private String basis;
    /**
     * 币种
     */
    private String ccy;
    /**
     * 回购利率%
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal intRate;
    /**
     * 证券托管户
     */
    private String secManageAcctId;
    /**
     * 证券托管户名称
     */
    private String secAcctName;
    /**
     * 通道
     */
    private String chlId;
    /**
     * 通道名称
     */
    private String chlName;
    /**
     * 券面总额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal prinAmt;
    /**
     * 剩余未拆分券面总额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal undistributedAmount;

    /**
     * 拆分信息—券面总额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal proPrinAmt;

    /**
     * 拆分信息—组合剩余现金
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal avalibleCash;

    /**
     * 拆分信息—资产可用持仓
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal assetPosition;

    /**
     * 拆分信息—首次结算金额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal firSettleAmt;

    /**
     * 交易日历
     */
    private String calendarId;
    /**
     * 清算速度
     */
    private String settleSpeed;
    /**
     * 回购期限
     */
    private Long repoterm;
    /**
     * 交易市场
     */
    private String tradeMarket;
    /**
     * 已转仓的到期结算金额值
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private String mAmt;

    /**
     * 质押率
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal pledgeRatio;

    /**
     * 获取 待转出组合
     *
     * @return portfolioIdOut 待转出组合
     */
    public String getPortfolioIdOut() {
        return this.portfolioIdOut;
    }

    /**
     * 设置 待转出组合
     *
     * @param portfolioIdOut 待转出组合
     */
    public void setPortfolioIdOut(String portfolioIdOut) {
        this.portfolioIdOut = portfolioIdOut;
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
     * 获取 原始交易编号
     *
     * @return tradeId 原始交易编号
     */
    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getTradeId() {
        return this.tradeId;
    }

    /**
     * 设置 原始交易编号
     *
     * @param tradeId 原始交易编号
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    /**
     * 获取 画面元素
     *
     * @return elementId 画面元素
     */
    public String getElementId() {
        return this.elementId;
    }

    /**
     * 设置 画面元素
     *
     * @param elementId 画面元素
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    /**
     * 获取 转仓信息列表dtolist
     *
     * @return buyOutRepoTransfer002DtoList 转仓信息列表dtolist
     */
    public List<com.joyintech.tams.invest.trade.transfer.buyoutrepo.dto.BuyOutRepoTransfer002Dto> getBuyOutRepoTransfer002DtoList() {
        return this.buyOutRepoTransfer002DtoList;
    }

    /**
     * 设置 转仓信息列表dtolist
     *
     * @param buyOutRepoTransfer002DtoList 转仓信息列表dtolist
     */
    public void setBuyOutRepoTransfer002DtoList(List<com.joyintech.tams.invest.trade.transfer.buyoutrepo.dto.BuyOutRepoTransfer002Dto> buyOutRepoTransfer002DtoList) {
        this.buyOutRepoTransfer002DtoList = buyOutRepoTransfer002DtoList;
    }

    /**
     * 获取 操作方式
     *
     * @return operateType 操作方式
     */
    public String getOperateType() {
        return this.operateType;
    }

    /**
     * 设置 操作方式
     *
     * @param operateType 操作方式
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
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
     * 获取 交易方向
     *
     * @return ps 交易方向
     */
    public String getPs() {
        return this.ps;
    }

    /**
     * 设置 交易方向
     *
     * @param ps 交易方向
     */
    public void setPs(String ps) {
        this.ps = ps;
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
     * 获取 标的金融产品代码
     *
     * @return finprodId 标的金融产品代码
     */
    public String getFinprodId() {
        return this.finprodId;
    }

    /**
     * 设置 标的金融产品代码
     *
     * @param finprodId 标的金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    /**
     * 获取 标的资产简称
     *
     * @return finprodAbbr 标的资产简称
     */
    public String getFinprodAbbr() {
        return this.finprodAbbr;
    }

    /**
     * 设置 标的资产简称
     *
     * @param finprodAbbr 标的资产简称
     */
    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    /**
     * 获取 期限品种
     *
     * @return termType 期限品种
     */
    public String getTermType() {
        return this.termType;
    }

    /**
     * 设置 期限品种
     *
     * @param termType 期限品种
     */
    public void setTermType(String termType) {
        this.termType = termType;
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
     * 获取 实际占款天数
     *
     * @return actCapDays 实际占款天数
     */
    public BigDecimal getActCapDays() {
        return this.actCapDays;
    }

    /**
     * 设置 实际占款天数
     *
     * @param actCapDays 实际占款天数
     */
    public void setActCapDays(BigDecimal actCapDays) {
        this.actCapDays = actCapDays;
    }

    /**
     * 获取 首次净价
     *
     * @return unitCprice 首次净价
     */
    public BigDecimal getUnitCprice() {
        return this.unitCprice;
    }

    /**
     * 设置 首次净价
     *
     * @param unitCprice 首次净价
     */
    public void setUnitCprice(BigDecimal unitCprice) {
        this.unitCprice = unitCprice;
    }

    /**
     * 获取 首次应计利息
     *
     * @return unitInt 首次应计利息
     */
    public BigDecimal getUnitInt() {
        return this.unitInt;
    }

    /**
     * 设置 首次应计利息
     *
     * @param unitInt 首次应计利息
     */
    public void setUnitInt(BigDecimal unitInt) {
        this.unitInt = unitInt;
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
     * 获取 到期净价
     *
     * @return mUnitCprice 到期净价
     */
    public BigDecimal getMUnitCprice() {
        return this.mUnitCprice;
    }

    /**
     * 设置 到期净价
     *
     * @param mUnitCprice 到期净价
     */
    public void setMUnitCprice(BigDecimal mUnitCprice) {
        this.mUnitCprice = mUnitCprice;
    }

    /**
     * 获取 到期应计利息
     *
     * @return mUnitInt 到期应计利息
     */
    public BigDecimal getMUnitInt() {
        return this.mUnitInt;
    }

    /**
     * 设置 到期应计利息
     *
     * @param mUnitInt 到期应计利息
     */
    public void setMUnitInt(BigDecimal mUnitInt) {
        this.mUnitInt = mUnitInt;
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
     * 获取 首次结算金额（基本信息）
     *
     * @return firSettleAmtS 首次结算金额（基本信息）
     */
    public BigDecimal getFirSettleAmtS() {
        return this.firSettleAmtS;
    }

    /**
     * 设置 首次结算金额（基本信息）
     *
     * @param firSettleAmtS 首次结算金额（基本信息）
     */
    public void setFirSettleAmtS(BigDecimal firSettleAmtS) {
        this.firSettleAmtS = firSettleAmtS;
    }

    /**
     * 获取 到期结算金额（基本信息）
     *
     * @return dueSettleAmtS 到期结算金额（基本信息）
     */
    public BigDecimal getDueSettleAmtS() {
        return this.dueSettleAmtS;
    }

    /**
     * 设置 到期结算金额（基本信息）
     *
     * @param dueSettleAmtS 到期结算金额（基本信息）
     */
    public void setDueSettleAmtS(BigDecimal dueSettleAmtS) {
        this.dueSettleAmtS = dueSettleAmtS;
    }

    /**
     * 获取 计息基础
     *
     * @return basis 计息基础
     */
    public String getBasis() {
        return this.basis;
    }

    /**
     * 设置 计息基础
     *
     * @param basis 计息基础
     */
    public void setBasis(String basis) {
        this.basis = basis;
    }

    /**
     * 获取 币种
     *
     * @return ccy 币种
     */
    public String getCcy() {
        return this.ccy;
    }

    /**
     * 设置 币种
     *
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
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
     * 获取 证券托管户
     *
     * @return secManageAcctId 证券托管户
     */
    public String getSecManageAcctId() {
        return this.secManageAcctId;
    }

    /**
     * 设置 证券托管户
     *
     * @param secManageAcctId 证券托管户
     */
    public void setSecManageAcctId(String secManageAcctId) {
        this.secManageAcctId = secManageAcctId;
    }

    /**
     * 获取 证券托管户名称
     *
     * @return secAcctName 证券托管户名称
     */
    public String getSecAcctName() {
        return this.secAcctName;
    }

    /**
     * 设置 证券托管户名称
     *
     * @param secAcctName 证券托管户名称
     */
    public void setSecAcctName(String secAcctName) {
        this.secAcctName = secAcctName;
    }

    /**
     * 获取 通道
     *
     * @return chlId 通道
     */
    public String getChlId() {
        return this.chlId;
    }

    /**
     * 设置 通道
     *
     * @param chlId 通道
     */
    public void setChlId(String chlId) {
        this.chlId = chlId;
    }

    /**
     * 获取 通道名称
     *
     * @return chlName 通道名称
     */
    public String getChlName() {
        return this.chlName;
    }

    /**
     * 设置 通道名称
     *
     * @param chlName 通道名称
     */
    public void setChlName(String chlName) {
        this.chlName = chlName;
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
     * 获取 剩余未拆分券面总额
     *
     * @return undistributedAmount 剩余未拆分券面总额
     */
    public BigDecimal getUndistributedAmount() {
        return this.undistributedAmount;
    }

    /**
     * 设置 剩余未拆分券面总额
     *
     * @param undistributedAmount 剩余未拆分券面总额
     */
    public void setUndistributedAmount(BigDecimal undistributedAmount) {
        this.undistributedAmount = undistributedAmount;
    }

    /**
     * 获取 拆分信息—券面总额
     *
     * @return proPrinAmt 拆分信息—券面总额
     */
    public BigDecimal getProPrinAmt() {
        return this.proPrinAmt;
    }

    /**
     * 设置 拆分信息—券面总额
     *
     * @param proPrinAmt 拆分信息—券面总额
     */
    public void setProPrinAmt(BigDecimal proPrinAmt) {
        this.proPrinAmt = proPrinAmt;
    }

    /**
     * 获取 拆分信息—组合剩余现金
     *
     * @return avalibleCash 拆分信息—组合剩余现金
     */
    public BigDecimal getAvalibleCash() {
        return this.avalibleCash;
    }

    /**
     * 设置 拆分信息—组合剩余现金
     *
     * @param avalibleCash 拆分信息—组合剩余现金
     */
    public void setAvalibleCash(BigDecimal avalibleCash) {
        this.avalibleCash = avalibleCash;
    }

    /**
     * 获取 拆分信息—资产可用持仓
     *
     * @return assetPosition 拆分信息—资产可用持仓
     */
    public BigDecimal getAssetPosition() {
        return this.assetPosition;
    }

    /**
     * 设置 拆分信息—资产可用持仓
     *
     * @param assetPosition 拆分信息—资产可用持仓
     */
    public void setAssetPosition(BigDecimal assetPosition) {
        this.assetPosition = assetPosition;
    }

    /**
     * 获取 拆分信息—首次结算金额
     *
     * @return firSettleAmt 拆分信息—首次结算金额
     */
    public BigDecimal getFirSettleAmt() {
        return this.firSettleAmt;
    }

    /**
     * 设置 拆分信息—首次结算金额
     *
     * @param firSettleAmt 拆分信息—首次结算金额
     */
    public void setFirSettleAmt(BigDecimal firSettleAmt) {
        this.firSettleAmt = firSettleAmt;
    }

    /**
     * 获取 交易日历
     *
     * @return calendarId 交易日历
     */
    public String getCalendarId() {
        return this.calendarId;
    }

    /**
     * 设置 交易日历
     *
     * @param calendarId 交易日历
     */
    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }

    /**
     * 获取 已转仓的到期结算金额值
     *
     * @return mAmt 已转仓的到期结算金额值
     */
    public String getMAmt() {
        return this.mAmt;
    }

    /**
     * 设置 已转仓的到期结算金额值
     *
     * @param mAmt 已转仓的到期结算金额值
     */
    public void setMAmt(String mAmt) {
        this.mAmt = mAmt;
    }

    /**
     * 获取 清算速度
     *
     * @return settleSpeed 清算速度
     */
    public String getSettleSpeed() {
        return this.settleSpeed;
    }

    /**
     * 设置 清算速度
     *
     * @param settleSpeed 清算速度
     */
    public void setSettleSpeed(String settleSpeed) {
        this.settleSpeed = settleSpeed;
    }

    /**
     * 获取 回购期限
     *
     * @return repoterm 回购期限
     */
    public Long getRepoterm() {
        return this.repoterm;
    }

    /**
     * 设置 回购期限
     *
     * @param repoterm 回购期限
     */
    public void setRepoterm(Long repoterm) {
        this.repoterm = repoterm;
    }

    /**
     * 获取 交易市场
     *
     * @return tradeMarket 交易市场
     */
    public String getTradeMarket() {
        return this.tradeMarket;
    }

    /**
     * 设置 交易市场
     *
     * @param tradeMarket 交易市场
     */
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }

    /**
     * 获取 待转入组合
     *
     * @return portfolioId 待转入组合
     */
    public String getPortfolioId() {
        return this.portfolioId;
    }

    /**
     * 设置 待转入组合
     *
     * @param portfolioId 待转入组合
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
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
     * 获取 质押率
     *
     * @return pledgeRatio 质押率
     */
    public BigDecimal getPledgeRatio() {
        return this.pledgeRatio;
    }

    /**
     * 设置 质押率
     *
     * @param pledgeRatio 质押率
     */
    public void setPledgeRatio(BigDecimal pledgeRatio) {
        this.pledgeRatio = pledgeRatio;
    }
}
