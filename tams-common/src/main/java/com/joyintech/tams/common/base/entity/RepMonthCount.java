package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class RepMonthCount extends RepMonthCountKey {
    /**
     * 行内标识码/理财产品代码
     */
    private String repBankProdCode;

    /**
     * 发行机构代码（中债那边每家银行固定一个代码，联社是以分设为单位的）
     */
    private String repIssuePartyId;

    /**
     * 本期发行产品数（只）

     */
    private Long repProdCount;

    /**
     * 本期总募集金额（元）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repRaiseMoneySum;

    /**
     * 本期净募集金额（元）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repRaiseMoneyNet;

    /**
     * 期末存续产品数(只)
     */
    private Long repProdAliveCount;

    /**
     * 期末余额（元）

     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repBalance;

    /**
     * 本期客户实现收益总额（元）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repCusAchiProfitSum;

    /**
     * 本期银行实现收益总额（元）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repBankAchiProfit;

    /**
     * 登记日期
     */
    private Date repRegDate;

    /**
     * 报送状态
     */
    private String repSendStatus;

    /**
     * 数据日期
     */
    private Date repStatusDate;

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
     * 取得 本期发行产品数（只）

     * @return 本期发行产品数（只）

     */
    public Long getRepProdCount() {
        return repProdCount;
    }

    /**
     * 设置 本期发行产品数（只）

     * @param repProdCount 本期发行产品数（只）

     */
    public void setRepProdCount(Long repProdCount) {
        this.repProdCount = repProdCount;
    }

    /**
     * 取得 本期总募集金额（元）
     * @return 本期总募集金额（元）
     */
    public BigDecimal getRepRaiseMoneySum() {
        return repRaiseMoneySum;
    }

    /**
     * 设置 本期总募集金额（元）
     * @param repRaiseMoneySum 本期总募集金额（元）
     */
    public void setRepRaiseMoneySum(BigDecimal repRaiseMoneySum) {
        this.repRaiseMoneySum = repRaiseMoneySum;
    }

    /**
     * 取得 本期净募集金额（元）
     * @return 本期净募集金额（元）
     */
    public BigDecimal getRepRaiseMoneyNet() {
        return repRaiseMoneyNet;
    }

    /**
     * 设置 本期净募集金额（元）
     * @param repRaiseMoneyNet 本期净募集金额（元）
     */
    public void setRepRaiseMoneyNet(BigDecimal repRaiseMoneyNet) {
        this.repRaiseMoneyNet = repRaiseMoneyNet;
    }

    /**
     * 取得 期末存续产品数(只)
     * @return 期末存续产品数(只)
     */
    public Long getRepProdAliveCount() {
        return repProdAliveCount;
    }

    /**
     * 设置 期末存续产品数(只)
     * @param repProdAliveCount 期末存续产品数(只)
     */
    public void setRepProdAliveCount(Long repProdAliveCount) {
        this.repProdAliveCount = repProdAliveCount;
    }

    /**
     * 取得 期末余额（元）

     * @return 期末余额（元）

     */
    public BigDecimal getRepBalance() {
        return repBalance;
    }

    /**
     * 设置 期末余额（元）

     * @param repBalance 期末余额（元）

     */
    public void setRepBalance(BigDecimal repBalance) {
        this.repBalance = repBalance;
    }

    /**
     * 取得 本期客户实现收益总额（元）
     * @return 本期客户实现收益总额（元）
     */
    public BigDecimal getRepCusAchiProfitSum() {
        return repCusAchiProfitSum;
    }

    /**
     * 设置 本期客户实现收益总额（元）
     * @param repCusAchiProfitSum 本期客户实现收益总额（元）
     */
    public void setRepCusAchiProfitSum(BigDecimal repCusAchiProfitSum) {
        this.repCusAchiProfitSum = repCusAchiProfitSum;
    }

    /**
     * 取得 本期银行实现收益总额（元）
     * @return 本期银行实现收益总额（元）
     */
    public BigDecimal getRepBankAchiProfit() {
        return repBankAchiProfit;
    }

    /**
     * 设置 本期银行实现收益总额（元）
     * @param repBankAchiProfit 本期银行实现收益总额（元）
     */
    public void setRepBankAchiProfit(BigDecimal repBankAchiProfit) {
        this.repBankAchiProfit = repBankAchiProfit;
    }

    /**
     * 取得 登记日期
     * @return 登记日期
     */
    public Date getRepRegDate() {
        return repRegDate;
    }

    /**
     * 设置 登记日期
     * @param repRegDate 登记日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRepRegDate(Date repRegDate) {
        this.repRegDate = repRegDate;
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