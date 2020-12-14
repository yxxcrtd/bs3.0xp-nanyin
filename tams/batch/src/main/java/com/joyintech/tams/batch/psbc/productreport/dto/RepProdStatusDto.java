/*
 * 文件名：ProductStatusDto.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：qiangchengxi
 * 修改时间：2019年8月28日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.batch.psbc.productreport.dto;

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 理财产品状态表dto
 *
 * @author jiangpeng
 * @version 1.0 2020/12/04
 * @see RepProdStatusDto
 * @since 1.0
 */
public class RepProdStatusDto extends BaseEntity {
    /**
     * 产品ID
     */
    private String finprodId;
    /**
     * 产品登记编码
     */
    private String prodRegistCode;
    /**
     * 产品名称
     */
    private String finprodAbbr;
    /**
     * 发行机构代码
     */
    private String issuePartyId;
    /**
     * 发行机构名称
     */
    private String issuePartyName;
    /**
     * 累计申购金额
     */
    private BigDecimal repApplyCny;
    /**
     * 累计申购份额
     */
    private BigDecimal repSumApplyShare;
    /**
     * 累计兑付金额
     */
    private BigDecimal repCashCny;
    /**
     * 累计兑付收益金额
     */
    private BigDecimal repCashProfitCny;
    /**
     * 累计赎回份额
     */
    private BigDecimal repSumCashShare;
    /**
     * 产品统计日状态 存续/终止(1/0)
     */
    private String prodStatus;
    /**
     * 产品存续余额
     */
    private BigDecimal prodPrdAmt;
    /**
     * 初始净值
     */
    private BigDecimal originalValue;
    /**
     * 产品净值
     */
    private BigDecimal netValue;
    /**
     * 累计净值
     */
    private BigDecimal totalNetNalue;
    /**
     * 净值币种
     */
    private String valueCcy;
    /**
     * 折算人民币初始净值
     */
    private BigDecimal repInitNetvalCny;
    /**
     * 折算人民币净值
     */
    private BigDecimal repNetvalCny;
    /**
     * 折算人民币累计净值
     */
    private BigDecimal repSumNetvalCny;
    /**
     * 实现收益率
     */
    private BigDecimal reaYield;
    /**
     * 最新预期收益率
     */
    private BigDecimal repNewExpectedYield;
    /**
     * 银行实现收益
     */
    private BigDecimal bankImpInt;
    /**
     * 产品统计日
     */
    private Date cDate;
    /**
     * 开放式产品易变现资产期末持仓占比
     */
    private BigDecimal openProddEndScale;
    /**
     * 封闭式产品债权类资产平均期限错配比
     */
    private BigDecimal closeProddEndScale;
    /**
     * 非标债权资产持仓比例
     */
    private BigDecimal nonStandBondRatio;

    /**
     * 币种及申购兑付金额
     */
    private String ccyPurchase;

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getProdRegistCode() {
        return prodRegistCode;
    }

    public void setProdRegistCode(String prodRegistCode) {
        this.prodRegistCode = prodRegistCode;
    }

    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    public String getIssuePartyId() {
        return issuePartyId;
    }

    public void setIssuePartyId(String issuePartyId) {
        this.issuePartyId = issuePartyId;
    }

    public String getIssuePartyName() {
        return issuePartyName;
    }

    public void setIssuePartyName(String issuePartyName) {
        this.issuePartyName = issuePartyName;
    }

    public BigDecimal getRepApplyCny() {
        return repApplyCny;
    }

    public void setRepApplyCny(BigDecimal repApplyCny) {
        this.repApplyCny = repApplyCny;
    }

    public BigDecimal getRepSumApplyShare() {
        return repSumApplyShare;
    }

    public void setRepSumApplyShare(BigDecimal repSumApplyShare) {
        this.repSumApplyShare = repSumApplyShare;
    }

    public BigDecimal getRepCashCny() {
        return repCashCny;
    }

    public void setRepCashCny(BigDecimal repCashCny) {
        this.repCashCny = repCashCny;
    }

    public BigDecimal getRepCashProfitCny() {
        return repCashProfitCny;
    }

    public void setRepCashProfitCny(BigDecimal repCashProfitCny) {
        this.repCashProfitCny = repCashProfitCny;
    }

    public BigDecimal getRepSumCashShare() {
        return repSumCashShare;
    }

    public void setRepSumCashShare(BigDecimal repSumCashShare) {
        this.repSumCashShare = repSumCashShare;
    }

    public String getProdStatus() {
        return prodStatus;
    }

    public void setProdStatus(String prodStatus) {
        this.prodStatus = prodStatus;
    }

    public BigDecimal getProdPrdAmt() {
        return prodPrdAmt;
    }

    public void setProdPrdAmt(BigDecimal prodPrdAmt) {
        this.prodPrdAmt = prodPrdAmt;
    }

    public BigDecimal getOriginalValue() {
        return originalValue;
    }

    public void setOriginalValue(BigDecimal originalValue) {
        this.originalValue = originalValue;
    }

    public BigDecimal getNetValue() {
        return netValue;
    }

    public void setNetValue(BigDecimal netValue) {
        this.netValue = netValue;
    }

    public BigDecimal getTotalNetNalue() {
        return totalNetNalue;
    }

    public void setTotalNetNalue(BigDecimal totalNetNalue) {
        this.totalNetNalue = totalNetNalue;
    }

    public String getValueCcy() {
        return valueCcy;
    }

    public void setValueCcy(String valueCcy) {
        this.valueCcy = valueCcy;
    }

    public BigDecimal getRepInitNetvalCny() {
        return repInitNetvalCny;
    }

    public void setRepInitNetvalCny(BigDecimal repInitNetvalCny) {
        this.repInitNetvalCny = repInitNetvalCny;
    }

    public BigDecimal getRepNetvalCny() {
        return repNetvalCny;
    }

    public void setRepNetvalCny(BigDecimal repNetvalCny) {
        this.repNetvalCny = repNetvalCny;
    }

    public BigDecimal getRepSumNetvalCny() {
        return repSumNetvalCny;
    }

    public void setRepSumNetvalCny(BigDecimal repSumNetvalCny) {
        this.repSumNetvalCny = repSumNetvalCny;
    }

    public BigDecimal getReaYield() {
        return reaYield;
    }

    public void setReaYield(BigDecimal reaYield) {
        this.reaYield = reaYield;
    }

    public BigDecimal getRepNewExpectedYield() {
        return repNewExpectedYield;
    }

    public void setRepNewExpectedYield(BigDecimal repNewExpectedYield) {
        this.repNewExpectedYield = repNewExpectedYield;
    }

    public BigDecimal getBankImpInt() {
        return bankImpInt;
    }

    public void setBankImpInt(BigDecimal bankImpInt) {
        this.bankImpInt = bankImpInt;
    }

    public Date getcDate() {
        return cDate;
    }

    public void setcDate(Date cDate) {
        this.cDate = cDate;
    }

    public BigDecimal getOpenProddEndScale() {
        return openProddEndScale;
    }

    public void setOpenProddEndScale(BigDecimal openProddEndScale) {
        this.openProddEndScale = openProddEndScale;
    }

    public BigDecimal getCloseProddEndScale() {
        return closeProddEndScale;
    }

    public void setCloseProddEndScale(BigDecimal closeProddEndScale) {
        this.closeProddEndScale = closeProddEndScale;
    }

    public BigDecimal getNonStandBondRatio() {
        return nonStandBondRatio;
    }

    public void setNonStandBondRatio(BigDecimal nonStandBondRatio) {
        this.nonStandBondRatio = nonStandBondRatio;
    }

    public String getCcyPurchase() {
        return ccyPurchase;
    }

    public void setCcyPurchase(String ccyPurchase) {
        this.ccyPurchase = ccyPurchase;
    }
}
