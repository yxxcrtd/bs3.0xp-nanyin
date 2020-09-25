package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class RepTrade extends RepTradeKey {
    /**
     * 产品登记编码
     */
    private String repFinprodRegId;

    /**
     * 发行机构代码（中债那边每家银行固定一个代码，联社是以分设为单位的）
     */
    private String repIssuePartyId;

    /**
     * 行内资产/负债编码

     */
    private String repBankAssetCode;

    /**
     * 币种
     */
    private String repCcy;

    /**
     * 发生金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repOccurMoney;

    /**
     * 折算人民币金额（元）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repMoneyCny;

    /**
     * 数量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repCount;

    /**
     * 资金流动类型说明
     */
    private String repMoneyFlowTypeExp;

    /**
     * 交易日
     */
    private Date repTradeDate;

    /**
     * 交易对手方
     */
    private String repTradeComponent;

    /**
     * 单位成交价格（全价）

     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repUnitPriceFull;

    /**
     * 单位成交价格（净价）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repUnitPriceNet;

    /**
     * 到期收益率%

     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repExpireYield;

    /**
     * 成交编号/合同号
     */
    private String repDelCode;

    /**
     * 备注
     */
    private String repRemark;

    /**
     * 交易登记日
     */
    private Date repTradeRegDate;

    /**
     * 报送状态
     */
    private String repSendStatus;

    /**
     * 数据日期
     */
    private Date repStatusDate;

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
     * 取得 行内资产/负债编码

     * @return 行内资产/负债编码

     */
    public String getRepBankAssetCode() {
        return repBankAssetCode;
    }

    /**
     * 设置 行内资产/负债编码

     * @param repBankAssetCode 行内资产/负债编码

     */
    public void setRepBankAssetCode(String repBankAssetCode) {
        this.repBankAssetCode = repBankAssetCode == null ? null : repBankAssetCode.trim();
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
     * 取得 发生金额
     * @return 发生金额
     */
    public BigDecimal getRepOccurMoney() {
        return repOccurMoney;
    }

    /**
     * 设置 发生金额
     * @param repOccurMoney 发生金额
     */
    public void setRepOccurMoney(BigDecimal repOccurMoney) {
        this.repOccurMoney = repOccurMoney;
    }

    /**
     * 取得 折算人民币金额（元）
     * @return 折算人民币金额（元）
     */
    public BigDecimal getRepMoneyCny() {
        return repMoneyCny;
    }

    /**
     * 设置 折算人民币金额（元）
     * @param repMoneyCny 折算人民币金额（元）
     */
    public void setRepMoneyCny(BigDecimal repMoneyCny) {
        this.repMoneyCny = repMoneyCny;
    }

    /**
     * 取得 数量
     * @return 数量
     */
    public BigDecimal getRepCount() {
        return repCount;
    }

    /**
     * 设置 数量
     * @param repCount 数量
     */
    public void setRepCount(BigDecimal repCount) {
        this.repCount = repCount;
    }

    /**
     * 取得 资金流动类型说明
     * @return 资金流动类型说明
     */
    public String getRepMoneyFlowTypeExp() {
        return repMoneyFlowTypeExp;
    }

    /**
     * 设置 资金流动类型说明
     * @param repMoneyFlowTypeExp 资金流动类型说明
     */
    public void setRepMoneyFlowTypeExp(String repMoneyFlowTypeExp) {
        this.repMoneyFlowTypeExp = repMoneyFlowTypeExp == null ? null : repMoneyFlowTypeExp.trim();
    }

    /**
     * 取得 交易日
     * @return 交易日
     */
    public Date getRepTradeDate() {
        return repTradeDate;
    }

    /**
     * 设置 交易日
     * @param repTradeDate 交易日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRepTradeDate(Date repTradeDate) {
        this.repTradeDate = repTradeDate;
    }

    /**
     * 取得 交易对手方
     * @return 交易对手方
     */
    public String getRepTradeComponent() {
        return repTradeComponent;
    }

    /**
     * 设置 交易对手方
     * @param repTradeComponent 交易对手方
     */
    public void setRepTradeComponent(String repTradeComponent) {
        this.repTradeComponent = repTradeComponent == null ? null : repTradeComponent.trim();
    }

    /**
     * 取得 单位成交价格（全价）

     * @return 单位成交价格（全价）

     */
    public BigDecimal getRepUnitPriceFull() {
        return repUnitPriceFull;
    }

    /**
     * 设置 单位成交价格（全价）

     * @param repUnitPriceFull 单位成交价格（全价）

     */
    public void setRepUnitPriceFull(BigDecimal repUnitPriceFull) {
        this.repUnitPriceFull = repUnitPriceFull;
    }

    /**
     * 取得 单位成交价格（净价）
     * @return 单位成交价格（净价）
     */
    public BigDecimal getRepUnitPriceNet() {
        return repUnitPriceNet;
    }

    /**
     * 设置 单位成交价格（净价）
     * @param repUnitPriceNet 单位成交价格（净价）
     */
    public void setRepUnitPriceNet(BigDecimal repUnitPriceNet) {
        this.repUnitPriceNet = repUnitPriceNet;
    }

    /**
     * 取得 到期收益率%

     * @return 到期收益率%

     */
    public BigDecimal getRepExpireYield() {
        return repExpireYield;
    }

    /**
     * 设置 到期收益率%

     * @param repExpireYield 到期收益率%

     */
    public void setRepExpireYield(BigDecimal repExpireYield) {
        this.repExpireYield = repExpireYield;
    }

    /**
     * 取得 成交编号/合同号
     * @return 成交编号/合同号
     */
    public String getRepDelCode() {
        return repDelCode;
    }

    /**
     * 设置 成交编号/合同号
     * @param repDelCode 成交编号/合同号
     */
    public void setRepDelCode(String repDelCode) {
        this.repDelCode = repDelCode == null ? null : repDelCode.trim();
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
     * 取得 交易登记日
     * @return 交易登记日
     */
    public Date getRepTradeRegDate() {
        return repTradeRegDate;
    }

    /**
     * 设置 交易登记日
     * @param repTradeRegDate 交易登记日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRepTradeRegDate(Date repTradeRegDate) {
        this.repTradeRegDate = repTradeRegDate;
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