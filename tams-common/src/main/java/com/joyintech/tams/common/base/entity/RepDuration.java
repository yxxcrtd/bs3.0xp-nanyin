package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class RepDuration extends RepDurationKey {
    /**
     * 发行机构名称
     */
    private String repIssuePartyName;

    /**
     * 发行机构代码（中债那边每家银行固定一个代码，联社是以分设为单位的）
     */
    private String repIssuePartyId;

    /**
     * 产品登记编码
     */
    private String repFinprodRegId;

    /**
     * 行内标识码/理财产品代码
     */
    private String repBankProdCode;

    /**
     * 产品起始日期
     */
    private Date repBusVdate;

    /**
     * 产品终止日期
     */
    private Date repBusMdate;

    /**
     * 币种
     */
    private String repCcy;

    /**
     * 该币种累计申购金额（元）

     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repApplyCny;

    /**
     * 该币种累计兑付金额（元）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repCashCny;

    /**
     * 该币种累计兑付收益金额（元）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repCashProfitCny;

    /**
     * 累计申购份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repSumApplyShare;

    /**
     * 累计赎回份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repSumCashShare;

    /**
     * 产品余额（元）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repProdBalance;

    /**
     * 产品份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repProdShare;

    /**
     * 初始净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repInitNetval;

    /**
     * 产品净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repProdNetval;

    /**
     * 累计净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repSumNetval;

    /**
     * 净值币种
     */
    private String repNetvalCcy;

    /**
     * 折算人民币初始净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repInitNetvalCny;

    /**
     * 折算人民币净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repNetvalCny;

    /**
     * 折算人民币累计净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repSumNetvalCny;

    /**
     * 实现收益率%

     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repAchiYield;

    /**
     * 最新预期收益率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repNewExpectedYield;

    /**
     * 银行实现收益（元）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repAchiProfitCny;

    /**
     * 备注
     */
    private String repRemark;

    /**
     * 报送状态
     */
    private String repSendStatus;

    /**
     * 数据日期
     */
    private Date repStatusDate;

    /**
     * 取得 发行机构名称
     * @return 发行机构名称
     */
    public String getRepIssuePartyName() {
        return repIssuePartyName;
    }

    /**
     * 设置 发行机构名称
     * @param repIssuePartyName 发行机构名称
     */
    public void setRepIssuePartyName(String repIssuePartyName) {
        this.repIssuePartyName = repIssuePartyName == null ? null : repIssuePartyName.trim();
    }

    /**
     * 取得 发行机构代码（中债那边每家银行固定一个代码，联社是以分设为单位的）
     * @return 发行机构代码（中债那边每家银行固定一个代码，联社是以分设为单位的）
     */
    public String getRepIssuePartyId() {
        return repIssuePartyId;
    }

    /**
     * 设置 发行机构代码（中债那边每家银行固定一个代码，联社是以分设为单位的）
     * @param repIssuePartyId 发行机构代码（中债那边每家银行固定一个代码，联社是以分设为单位的）
     */
    public void setRepIssuePartyId(String repIssuePartyId) {
        this.repIssuePartyId = repIssuePartyId == null ? null : repIssuePartyId.trim();
    }

    /**
     * 取得 产品登记编码
     * @return 产品登记编码
     */
    public String getRepFinprodRegId() {
        return repFinprodRegId;
    }

    /**
     * 设置 产品登记编码
     * @param repFinprodRegId 产品登记编码
     */
    public void setRepFinprodRegId(String repFinprodRegId) {
        this.repFinprodRegId = repFinprodRegId == null ? null : repFinprodRegId.trim();
    }

    /**
     * 取得 行内标识码/理财产品代码
     * @return 行内标识码/理财产品代码
     */
    public String getRepBankProdCode() {
        return repBankProdCode;
    }

    /**
     * 设置 行内标识码/理财产品代码
     * @param repBankProdCode 行内标识码/理财产品代码
     */
    public void setRepBankProdCode(String repBankProdCode) {
        this.repBankProdCode = repBankProdCode == null ? null : repBankProdCode.trim();
    }

    /**
     * 取得 产品起始日期
     * @return 产品起始日期
     */
    public Date getRepBusVdate() {
        return repBusVdate;
    }

    /**
     * 设置 产品起始日期
     * @param repBusVdate 产品起始日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRepBusVdate(Date repBusVdate) {
        this.repBusVdate = repBusVdate;
    }

    /**
     * 取得 产品终止日期
     * @return 产品终止日期
     */
    public Date getRepBusMdate() {
        return repBusMdate;
    }

    /**
     * 设置 产品终止日期
     * @param repBusMdate 产品终止日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRepBusMdate(Date repBusMdate) {
        this.repBusMdate = repBusMdate;
    }

    /**
     * 取得 币种
     * @return 币种
     */
    public String getRepCcy() {
        return repCcy;
    }

    /**
     * 设置 币种
     * @param repCcy 币种
     */
    public void setRepCcy(String repCcy) {
        this.repCcy = repCcy == null ? null : repCcy.trim();
    }

    /**
     * 取得 该币种累计申购金额（元）

     * @return 该币种累计申购金额（元）

     */
    public BigDecimal getRepApplyCny() {
        return repApplyCny;
    }

    /**
     * 设置 该币种累计申购金额（元）

     * @param repApplyCny 该币种累计申购金额（元）

     */
    public void setRepApplyCny(BigDecimal repApplyCny) {
        this.repApplyCny = repApplyCny;
    }

    /**
     * 取得 该币种累计兑付金额（元）
     * @return 该币种累计兑付金额（元）
     */
    public BigDecimal getRepCashCny() {
        return repCashCny;
    }

    /**
     * 设置 该币种累计兑付金额（元）
     * @param repCashCny 该币种累计兑付金额（元）
     */
    public void setRepCashCny(BigDecimal repCashCny) {
        this.repCashCny = repCashCny;
    }

    /**
     * 取得 该币种累计兑付收益金额（元）
     * @return 该币种累计兑付收益金额（元）
     */
    public BigDecimal getRepCashProfitCny() {
        return repCashProfitCny;
    }

    /**
     * 设置 该币种累计兑付收益金额（元）
     * @param repCashProfitCny 该币种累计兑付收益金额（元）
     */
    public void setRepCashProfitCny(BigDecimal repCashProfitCny) {
        this.repCashProfitCny = repCashProfitCny;
    }

    /**
     * 取得 累计申购份额
     * @return 累计申购份额
     */
    public BigDecimal getRepSumApplyShare() {
        return repSumApplyShare;
    }

    /**
     * 设置 累计申购份额
     * @param repSumApplyShare 累计申购份额
     */
    public void setRepSumApplyShare(BigDecimal repSumApplyShare) {
        this.repSumApplyShare = repSumApplyShare;
    }

    /**
     * 取得 累计赎回份额
     * @return 累计赎回份额
     */
    public BigDecimal getRepSumCashShare() {
        return repSumCashShare;
    }

    /**
     * 设置 累计赎回份额
     * @param repSumCashShare 累计赎回份额
     */
    public void setRepSumCashShare(BigDecimal repSumCashShare) {
        this.repSumCashShare = repSumCashShare;
    }

    /**
     * 取得 产品余额（元）
     * @return 产品余额（元）
     */
    public BigDecimal getRepProdBalance() {
        return repProdBalance;
    }

    /**
     * 设置 产品余额（元）
     * @param repProdBalance 产品余额（元）
     */
    public void setRepProdBalance(BigDecimal repProdBalance) {
        this.repProdBalance = repProdBalance;
    }

    /**
     * 取得 产品份额
     * @return 产品份额
     */
    public BigDecimal getRepProdShare() {
        return repProdShare;
    }

    /**
     * 设置 产品份额
     * @param repProdShare 产品份额
     */
    public void setRepProdShare(BigDecimal repProdShare) {
        this.repProdShare = repProdShare;
    }

    /**
     * 取得 初始净值
     * @return 初始净值
     */
    public BigDecimal getRepInitNetval() {
        return repInitNetval;
    }

    /**
     * 设置 初始净值
     * @param repInitNetval 初始净值
     */
    public void setRepInitNetval(BigDecimal repInitNetval) {
        this.repInitNetval = repInitNetval;
    }

    /**
     * 取得 产品净值
     * @return 产品净值
     */
    public BigDecimal getRepProdNetval() {
        return repProdNetval;
    }

    /**
     * 设置 产品净值
     * @param repProdNetval 产品净值
     */
    public void setRepProdNetval(BigDecimal repProdNetval) {
        this.repProdNetval = repProdNetval;
    }

    /**
     * 取得 累计净值
     * @return 累计净值
     */
    public BigDecimal getRepSumNetval() {
        return repSumNetval;
    }

    /**
     * 设置 累计净值
     * @param repSumNetval 累计净值
     */
    public void setRepSumNetval(BigDecimal repSumNetval) {
        this.repSumNetval = repSumNetval;
    }

    /**
     * 取得 净值币种
     * @return 净值币种
     */
    public String getRepNetvalCcy() {
        return repNetvalCcy;
    }

    /**
     * 设置 净值币种
     * @param repNetvalCcy 净值币种
     */
    public void setRepNetvalCcy(String repNetvalCcy) {
        this.repNetvalCcy = repNetvalCcy == null ? null : repNetvalCcy.trim();
    }

    /**
     * 取得 折算人民币初始净值
     * @return 折算人民币初始净值
     */
    public BigDecimal getRepInitNetvalCny() {
        return repInitNetvalCny;
    }

    /**
     * 设置 折算人民币初始净值
     * @param repInitNetvalCny 折算人民币初始净值
     */
    public void setRepInitNetvalCny(BigDecimal repInitNetvalCny) {
        this.repInitNetvalCny = repInitNetvalCny;
    }

    /**
     * 取得 折算人民币净值
     * @return 折算人民币净值
     */
    public BigDecimal getRepNetvalCny() {
        return repNetvalCny;
    }

    /**
     * 设置 折算人民币净值
     * @param repNetvalCny 折算人民币净值
     */
    public void setRepNetvalCny(BigDecimal repNetvalCny) {
        this.repNetvalCny = repNetvalCny;
    }

    /**
     * 取得 折算人民币累计净值
     * @return 折算人民币累计净值
     */
    public BigDecimal getRepSumNetvalCny() {
        return repSumNetvalCny;
    }

    /**
     * 设置 折算人民币累计净值
     * @param repSumNetvalCny 折算人民币累计净值
     */
    public void setRepSumNetvalCny(BigDecimal repSumNetvalCny) {
        this.repSumNetvalCny = repSumNetvalCny;
    }

    /**
     * 取得 实现收益率%

     * @return 实现收益率%

     */
    public BigDecimal getRepAchiYield() {
        return repAchiYield;
    }

    /**
     * 设置 实现收益率%

     * @param repAchiYield 实现收益率%

     */
    public void setRepAchiYield(BigDecimal repAchiYield) {
        this.repAchiYield = repAchiYield;
    }

    /**
     * 取得 最新预期收益率%
     * @return 最新预期收益率%
     */
    public BigDecimal getRepNewExpectedYield() {
        return repNewExpectedYield;
    }

    /**
     * 设置 最新预期收益率%
     * @param repNewExpectedYield 最新预期收益率%
     */
    public void setRepNewExpectedYield(BigDecimal repNewExpectedYield) {
        this.repNewExpectedYield = repNewExpectedYield;
    }

    /**
     * 取得 银行实现收益（元）
     * @return 银行实现收益（元）
     */
    public BigDecimal getRepAchiProfitCny() {
        return repAchiProfitCny;
    }

    /**
     * 设置 银行实现收益（元）
     * @param repAchiProfitCny 银行实现收益（元）
     */
    public void setRepAchiProfitCny(BigDecimal repAchiProfitCny) {
        this.repAchiProfitCny = repAchiProfitCny;
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRepRemark() {
        return repRemark;
    }

    /**
     * 设置 备注
     * @param repRemark 备注
     */
    public void setRepRemark(String repRemark) {
        this.repRemark = repRemark == null ? null : repRemark.trim();
    }

    /**
     * 取得 报送状态
     * @return 报送状态
     */
    public String getRepSendStatus() {
        return repSendStatus;
    }

    /**
     * 设置 报送状态
     * @param repSendStatus 报送状态
     */
    public void setRepSendStatus(String repSendStatus) {
        this.repSendStatus = repSendStatus == null ? null : repSendStatus.trim();
    }

    /**
     * 取得 数据日期
     * @return 数据日期
     */
    public Date getRepStatusDate() {
        return repStatusDate;
    }

    /**
     * 设置 数据日期
     * @param repStatusDate 数据日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRepStatusDate(Date repStatusDate) {
        this.repStatusDate = repStatusDate;
    }
}