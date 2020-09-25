package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class RepTerminateReg extends BaseEntity {
    /**
     * 金融产品代码
     */
    private String repFinprodId;

    /**
     * 产品登记编码
     */
    private String repFinprodRegId;

    /**
     * 发行机构代码（中债那边每家银行固定一个代码，联社是以分设为单位的）
     */
    private String repIssuePartyId;

    /**
     * 理财产品实际终止日期
     */
    private Date repProdActendDate;

    /**
     * 银行实际实现收入（元）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repActAchiProfit;

    /**
     * 兑付客户收益（元）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repCashCusProfit;

    /**
     * 兑付客户总金额（元）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repCashCusSum;

    /**
     * 兑付总份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repCashShare;

    /**
     * 本机构托管费（元）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repThisTrusteeFee;

    /**
     * 本机构管理费（元）

     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repThisMngFee;

    /**
     * 本机构销售手续费（元）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repThisSaleFee;

    /**
     * 本机构其他产品费用（元）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repThisOtherFee;

    /**
     * 其他机构托管费（元）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repOtherTrusteeFee;

    /**
     * 其他机构管理费（元）

     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repOtherMngFee;

    /**
     * 其他机构销售手续费（元）

     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repOtherSaleFee;

    /**
     * 投资顾问费用（元）

     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repInvestConvinceFee;

    /**
     * 其他机构其他产品费用（元）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repOtherOtherFee;

    /**
     * 客户实际年化收益率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repCusActYearYield;

    /**
     * 产品实际年化收益率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repProdActYearYield;

    /**
     * 终止登记日

     */
    private Date repTerminRegDate;

    /**
     * 报送状态
     */
    private String repSendStatus;

    /**
     * 数据日期
     */
    private Date repStatusDate;

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getRepFinprodId() {
        return repFinprodId;
    }

    /**
     * 设置 金融产品代码
     * @param repFinprodId 金融产品代码
     */
    public void setRepFinprodId(String repFinprodId) {
        this.repFinprodId = repFinprodId == null ? null : repFinprodId.trim();
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
     * 取得 理财产品实际终止日期
     * @return 理财产品实际终止日期
     */
    public Date getRepProdActendDate() {
        return repProdActendDate;
    }

    /**
     * 设置 理财产品实际终止日期
     * @param repProdActendDate 理财产品实际终止日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRepProdActendDate(Date repProdActendDate) {
        this.repProdActendDate = repProdActendDate;
    }

    /**
     * 取得 银行实际实现收入（元）
     * @return 银行实际实现收入（元）
     */
    public BigDecimal getRepActAchiProfit() {
        return repActAchiProfit;
    }

    /**
     * 设置 银行实际实现收入（元）
     * @param repActAchiProfit 银行实际实现收入（元）
     */
    public void setRepActAchiProfit(BigDecimal repActAchiProfit) {
        this.repActAchiProfit = repActAchiProfit;
    }

    /**
     * 取得 兑付客户收益（元）
     * @return 兑付客户收益（元）
     */
    public BigDecimal getRepCashCusProfit() {
        return repCashCusProfit;
    }

    /**
     * 设置 兑付客户收益（元）
     * @param repCashCusProfit 兑付客户收益（元）
     */
    public void setRepCashCusProfit(BigDecimal repCashCusProfit) {
        this.repCashCusProfit = repCashCusProfit;
    }

    /**
     * 取得 兑付客户总金额（元）
     * @return 兑付客户总金额（元）
     */
    public BigDecimal getRepCashCusSum() {
        return repCashCusSum;
    }

    /**
     * 设置 兑付客户总金额（元）
     * @param repCashCusSum 兑付客户总金额（元）
     */
    public void setRepCashCusSum(BigDecimal repCashCusSum) {
        this.repCashCusSum = repCashCusSum;
    }

    /**
     * 取得 兑付总份额
     * @return 兑付总份额
     */
    public BigDecimal getRepCashShare() {
        return repCashShare;
    }

    /**
     * 设置 兑付总份额
     * @param repCashShare 兑付总份额
     */
    public void setRepCashShare(BigDecimal repCashShare) {
        this.repCashShare = repCashShare;
    }

    /**
     * 取得 本机构托管费（元）
     * @return 本机构托管费（元）
     */
    public BigDecimal getRepThisTrusteeFee() {
        return repThisTrusteeFee;
    }

    /**
     * 设置 本机构托管费（元）
     * @param repThisTrusteeFee 本机构托管费（元）
     */
    public void setRepThisTrusteeFee(BigDecimal repThisTrusteeFee) {
        this.repThisTrusteeFee = repThisTrusteeFee;
    }

    /**
     * 取得 本机构管理费（元）

     * @return 本机构管理费（元）

     */
    public BigDecimal getRepThisMngFee() {
        return repThisMngFee;
    }

    /**
     * 设置 本机构管理费（元）

     * @param repThisMngFee 本机构管理费（元）

     */
    public void setRepThisMngFee(BigDecimal repThisMngFee) {
        this.repThisMngFee = repThisMngFee;
    }

    /**
     * 取得 本机构销售手续费（元）
     * @return 本机构销售手续费（元）
     */
    public BigDecimal getRepThisSaleFee() {
        return repThisSaleFee;
    }

    /**
     * 设置 本机构销售手续费（元）
     * @param repThisSaleFee 本机构销售手续费（元）
     */
    public void setRepThisSaleFee(BigDecimal repThisSaleFee) {
        this.repThisSaleFee = repThisSaleFee;
    }

    /**
     * 取得 本机构其他产品费用（元）
     * @return 本机构其他产品费用（元）
     */
    public BigDecimal getRepThisOtherFee() {
        return repThisOtherFee;
    }

    /**
     * 设置 本机构其他产品费用（元）
     * @param repThisOtherFee 本机构其他产品费用（元）
     */
    public void setRepThisOtherFee(BigDecimal repThisOtherFee) {
        this.repThisOtherFee = repThisOtherFee;
    }

    /**
     * 取得 其他机构托管费（元）
     * @return 其他机构托管费（元）
     */
    public BigDecimal getRepOtherTrusteeFee() {
        return repOtherTrusteeFee;
    }

    /**
     * 设置 其他机构托管费（元）
     * @param repOtherTrusteeFee 其他机构托管费（元）
     */
    public void setRepOtherTrusteeFee(BigDecimal repOtherTrusteeFee) {
        this.repOtherTrusteeFee = repOtherTrusteeFee;
    }

    /**
     * 取得 其他机构管理费（元）

     * @return 其他机构管理费（元）

     */
    public BigDecimal getRepOtherMngFee() {
        return repOtherMngFee;
    }

    /**
     * 设置 其他机构管理费（元）

     * @param repOtherMngFee 其他机构管理费（元）

     */
    public void setRepOtherMngFee(BigDecimal repOtherMngFee) {
        this.repOtherMngFee = repOtherMngFee;
    }

    /**
     * 取得 其他机构销售手续费（元）

     * @return 其他机构销售手续费（元）

     */
    public BigDecimal getRepOtherSaleFee() {
        return repOtherSaleFee;
    }

    /**
     * 设置 其他机构销售手续费（元）

     * @param repOtherSaleFee 其他机构销售手续费（元）

     */
    public void setRepOtherSaleFee(BigDecimal repOtherSaleFee) {
        this.repOtherSaleFee = repOtherSaleFee;
    }

    /**
     * 取得 投资顾问费用（元）

     * @return 投资顾问费用（元）

     */
    public BigDecimal getRepInvestConvinceFee() {
        return repInvestConvinceFee;
    }

    /**
     * 设置 投资顾问费用（元）

     * @param repInvestConvinceFee 投资顾问费用（元）

     */
    public void setRepInvestConvinceFee(BigDecimal repInvestConvinceFee) {
        this.repInvestConvinceFee = repInvestConvinceFee;
    }

    /**
     * 取得 其他机构其他产品费用（元）
     * @return 其他机构其他产品费用（元）
     */
    public BigDecimal getRepOtherOtherFee() {
        return repOtherOtherFee;
    }

    /**
     * 设置 其他机构其他产品费用（元）
     * @param repOtherOtherFee 其他机构其他产品费用（元）
     */
    public void setRepOtherOtherFee(BigDecimal repOtherOtherFee) {
        this.repOtherOtherFee = repOtherOtherFee;
    }

    /**
     * 取得 客户实际年化收益率%
     * @return 客户实际年化收益率%
     */
    public BigDecimal getRepCusActYearYield() {
        return repCusActYearYield;
    }

    /**
     * 设置 客户实际年化收益率%
     * @param repCusActYearYield 客户实际年化收益率%
     */
    public void setRepCusActYearYield(BigDecimal repCusActYearYield) {
        this.repCusActYearYield = repCusActYearYield;
    }

    /**
     * 取得 产品实际年化收益率%
     * @return 产品实际年化收益率%
     */
    public BigDecimal getRepProdActYearYield() {
        return repProdActYearYield;
    }

    /**
     * 设置 产品实际年化收益率%
     * @param repProdActYearYield 产品实际年化收益率%
     */
    public void setRepProdActYearYield(BigDecimal repProdActYearYield) {
        this.repProdActYearYield = repProdActYearYield;
    }

    /**
     * 取得 终止登记日

     * @return 终止登记日

     */
    public Date getRepTerminRegDate() {
        return repTerminRegDate;
    }

    /**
     * 设置 终止登记日

     * @param repTerminRegDate 终止登记日

     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRepTerminRegDate(Date repTerminRegDate) {
        this.repTerminRegDate = repTerminRegDate;
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