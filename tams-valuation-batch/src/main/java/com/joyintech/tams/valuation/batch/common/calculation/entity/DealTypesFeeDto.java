/*
 * 文件名：DealTypesFeeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：杨智
 * 修改时间：2018年6月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.common.calculation.entity;

import java.math.BigDecimal;

import com.joyintech.tams.common.base.entity.BokDetailId;

/**
 * 交易各类费用
 *
 * @author chengzhong
 * @version 1.0 2018年6月7日
 * @see DealTypesFeeDto
 * @since 1.0
 */
public class DealTypesFeeDto {

    /**
     * 交易费用
     */
    private BigDecimal feeAmt;

    /**
     * 印花税
     */
    private BigDecimal stampDuty;

    /**
     * 经手费
     */
    private BigDecimal brokerage;

    /**
     * 证管费
     */
    private BigDecimal certificateFee;

    /**
     * 结算费
     */
    private BigDecimal clearingFee;

    /**
     * 佣金
     */
    private BigDecimal commission;

    /**
     * 交易手续费
     */
    private BigDecimal transactionFee;

    /**
     * 结算手续费
     */
    private BigDecimal settlementFee;

    /**
     * 通道费
     */
    private BigDecimal chlFee;

    /**
     * 不随交易费用
     */
    private BigDecimal noFeeAmt;

    /**
     * 印花税账务明细代码
     */
    private BokDetailId stampDutyCode;

    /**
     * 经手费账务明细代码
     */
    private BokDetailId brokerageCode;

    /**
     * 证管费账务明细代码
     */
    private BokDetailId certificateFeeCode;

    /**
     * 结算费账务明细代码
     */
    private BokDetailId clearingFeeCode;

    /**
     * 佣金账务明细代码
     */
    private BokDetailId commissionCode;

    /**
     * 交易手续费账务明细代码
     */
    private BokDetailId transactionFeeCode;

    /**
     * 结算手续费账务明细代码
     */
    private BokDetailId settlementFeeCode;

    /**
     * 通道费账务明细代码
     */
    private BokDetailId chlFeeCode;

    /**
     * 通道管理费
     */
    private BigDecimal chlGlFee;

    /**
     * 通道销售费
     */
    private BigDecimal chlXsFee;

    /**
     * 通道托管费
     */
    private BigDecimal chlTgFee;

    /**
     * 通道管理费账务明细代码
     */
    private BokDetailId chlGlFeeCode;

    /**
     * 通道销售费账务明细代码
     */
    private BokDetailId chlXsFeeCode;

    /**
     * 通道托管费账务明细代码
     */
    private BokDetailId chlTgFeeCode;

    /**
     * 随交易支付_印花税
     */
    private BigDecimal stampDutyForDealPay;

    /**
     * 随交易支付_经手费
     */
    private BigDecimal brokerageForDealPay;

    /**
     * 随交易支付_证管费
     */
    private BigDecimal certificateFeeForDealPay;

    /**
     * 随交易支付_结算费
     */
    private BigDecimal clearingFeeForDealPay;

    /**
     * 随交易支付_佣金
     */
    private BigDecimal commissionForDealPay;

    /**
     * 随交易支付_交易手续费
     */
    private BigDecimal transactionFeeForDealPay;

    /**
     * 随交易支付_结算手续费
     */
    private BigDecimal settlementFeeForDealPay;

    /**
     * 随交易支付_印花税账务明细代码
     */
    private BokDetailId stampDutyCodeForDealPay;

    /**
     * 随交易支付_经手费账务明细代码
     */
    private BokDetailId brokerageCodeForDealPay;

    /**
     * 随交易支付_证管费账务明细代码
     */
    private BokDetailId certificateFeeCodeForDealPay;

    /**
     * 随交易支付_结算费账务明细代码
     */
    private BokDetailId clearingFeeCodeForDealPay;

    /**
     * 随交易支付_佣金账务明细代码
     */
    private BokDetailId commissionCodeForDealPay;

    /**
     * 随交易支付_交易手续费账务明细代码
     */
    private BokDetailId transactionFeeCodeForDealPay;

    /**
     * 随交易支付_结算手续费账务明细代码
     */
    private BokDetailId settlementFeeCodeForDealPay;

    /**
     * 其他费用
     */
    private BigDecimal otherFee;

    /**
     * 申购费
     */
    private BigDecimal subscriptionFee;

    /**
     * 赎回费
     */
    private BigDecimal redemptionFee;

    /**
     * 认购费
     */
    private BigDecimal offerFee;


    /**
     * 账户维护费
     * add jinmaodong 处理预提费用 20200317
     */
    private BigDecimal accountManageFee;

    /**
     * 账户维护费 账务明细代码
     * add jinmaodong 处理预提费用 20200317
     */
    private BokDetailId accountManageFeeCode;

    public BigDecimal getAccountManageFee() {
        return accountManageFee;
    }

    public void setAccountManageFee(BigDecimal accountManageFee) {
        this.accountManageFee = accountManageFee;
    }

    public BokDetailId getAccountManageFeeCode() {
        return accountManageFeeCode;
    }

    public void setAccountManageFeeCode(BokDetailId accountManageFeeCode) {
        this.accountManageFeeCode = accountManageFeeCode;
    }

    /**
     * 其他费用账务明细代码
     */
    private BokDetailId otherFeeCode;

    /**
     * 申购费账务明细代码
     */
    private BokDetailId subscriptionFeeCode;

    /**
     * 赎回费账务明细代码
     */
    private BokDetailId redemptionFeeCode;

    /**
     * 认购费账务明细代码
     */
    private BokDetailId offerFeeCode;

    /**
     * 随交易支付_其他费用
     */
    private BigDecimal otherFeeForDealPay;

    /**
     * 随交易支付_申购费
     */
    private BigDecimal subscriptionFeeForDealPay;

    /**
     * 随交易支付_赎回费
     */
    private BigDecimal redemptionFeeForDealPay;

    /**
     * 随交易支付_认购费
     */
    private BigDecimal offerFeeForDealPay;

    /**
     * 随交易支付_其他费用账务明细代码
     */
    private BokDetailId otherFeeCodeForDealPay;

    /**
     * 随交易支付_申购费账务明细代码
     */
    private BokDetailId subscriptionFeeCodeForDealPay;

    /**
     * 随交易支付_赎回费账务明细代码
     */
    private BokDetailId redemptionFeeCodeForDealPay;

    /**
     * 随交易支付_认购费账务明细代码
     */
    private BokDetailId offerFeeCodeForDealPay;


    /**
     * 获取 通道管理费
     *
     * @return chlGlFee.
     */
    public BigDecimal getChlGlFee() {
        return chlGlFee;
    }

    /**
     * 设置 通道管理费
     *
     * @param chlGlFee 通道管理费
     */
    public void setChlGlFee(BigDecimal chlGlFee) {
        this.chlGlFee = chlGlFee;
    }

    /**
     * 获取 通道销售费
     *
     * @return chlXsFee.
     */
    public BigDecimal getChlXsFee() {
        return chlXsFee;
    }

    /**
     * 设置 通道销售费
     *
     * @param chlXsFee 通道销售费
     */
    public void setChlXsFee(BigDecimal chlXsFee) {
        this.chlXsFee = chlXsFee;
    }

    /**
     * 获取 通道托管费
     *
     * @return chlTgFee.
     */
    public BigDecimal getChlTgFee() {
        return chlTgFee;
    }

    /**
     * 设置 通道托管费
     *
     * @param chlTgFee 通道托管费
     */
    public void setChlTgFee(BigDecimal chlTgFee) {
        this.chlTgFee = chlTgFee;
    }

    /**
     * 获取 通道管理费账务明细代码
     *
     * @return chlGlFeeCode.
     */
    public BokDetailId getChlGlFeeCode() {
        return chlGlFeeCode;
    }

    /**
     * 设置 通道管理费账务明细代码
     *
     * @param chlGlFeeCode 通道管理费账务明细代码
     */
    public void setChlGlFeeCode(BokDetailId chlGlFeeCode) {
        this.chlGlFeeCode = chlGlFeeCode;
    }

    /**
     * 获取 通道销售费账务明细代码
     *
     * @return chlXsFeeCode.
     */
    public BokDetailId getChlXsFeeCode() {
        return chlXsFeeCode;
    }

    /**
     * 设置 通道销售费账务明细代码
     *
     * @param chlXsFeeCode 通道销售费账务明细代码
     */
    public void setChlXsFeeCode(BokDetailId chlXsFeeCode) {
        this.chlXsFeeCode = chlXsFeeCode;
    }

    /**
     * 获取 通道托管费账务明细代码
     *
     * @return chlTgFeeCode.
     */
    public BokDetailId getChlTgFeeCode() {
        return chlTgFeeCode;
    }

    /**
     * 设置 通道托管费账务明细代码
     *
     * @param chlTgFeeCode 通道托管费账务明细代码
     */
    public void setChlTgFeeCode(BokDetailId chlTgFeeCode) {
        this.chlTgFeeCode = chlTgFeeCode;
    }

    /**
     * 获取 通道费
     *
     * @return chlFee
     */
    public BigDecimal getChlFee() {
        return chlFee;
    }

    /**
     * 设置 通道费
     *
     * @param chlFee 通道费
     */
    public void setChlFee(BigDecimal chlFee) {
        this.chlFee = chlFee;
    }

    /**
     * 获取 不随交易费用
     *
     * @return noFeeAmt.
     */
    public BigDecimal getNoFeeAmt() {
        return noFeeAmt;
    }

    /**
     * 设置 不随交易费用
     *
     * @param noFeeAmt 不随交易费用
     */
    public void setNoFeeAmt(BigDecimal noFeeAmt) {
        this.noFeeAmt = noFeeAmt;
    }

    /**
     * 获取 印花税账务明细代码
     *
     * @return stampDutyCode.
     */
    public BokDetailId getStampDutyCode() {
        return stampDutyCode;
    }

    /**
     * 设置 印花税账务明细代码
     *
     * @param stampDutyCode 印花税账务明细代码
     */
    public void setStampDutyCode(BokDetailId stampDutyCode) {
        this.stampDutyCode = stampDutyCode;
    }

    /**
     * 获取 经手费账务明细代码
     *
     * @return brokerageCode.
     */
    public BokDetailId getBrokerageCode() {
        return brokerageCode;
    }

    /**
     * 设置 经手费账务明细代码
     *
     * @param brokerageCode 经手费账务明细代码
     */
    public void setBrokerageCode(BokDetailId brokerageCode) {
        this.brokerageCode = brokerageCode;
    }

    /**
     * 获取 证管费账务明细代码
     *
     * @return certificateFeeCode.
     */
    public BokDetailId getCertificateFeeCode() {
        return certificateFeeCode;
    }

    /**
     * 设置 证管费账务明细代码
     *
     * @param certificateFeeCode 证管费账务明细代码
     */
    public void setCertificateFeeCode(BokDetailId certificateFeeCode) {
        this.certificateFeeCode = certificateFeeCode;
    }

    /**
     * 获取 结算费账务明细代码
     *
     * @return clearingFeeCode.
     */
    public BokDetailId getClearingFeeCode() {
        return clearingFeeCode;
    }

    /**
     * 设置 结算费账务明细代码
     *
     * @param clearingFeeCode 结算费账务明细代码
     */
    public void setClearingFeeCode(BokDetailId clearingFeeCode) {
        this.clearingFeeCode = clearingFeeCode;
    }

    /**
     * 获取 佣金账务明细代码
     *
     * @return commissionCode.
     */
    public BokDetailId getCommissionCode() {
        return commissionCode;
    }

    /**
     * 设置 佣金账务明细代码
     *
     * @param commissionCode 佣金账务明细代码
     */
    public void setCommissionCode(BokDetailId commissionCode) {
        this.commissionCode = commissionCode;
    }

    /**
     * 获取 交易手续费账务明细代码
     *
     * @return transactionFeeCode.
     */
    public BokDetailId getTransactionFeeCode() {
        return transactionFeeCode;
    }

    /**
     * 设置 交易手续费账务明细代码
     *
     * @param transactionFeeCode 交易手续费账务明细代码
     */
    public void setTransactionFeeCode(BokDetailId transactionFeeCode) {
        this.transactionFeeCode = transactionFeeCode;
    }

    /**
     * 获取 结算手续费账务明细代码
     *
     * @return settlementFeeCode.
     */
    public BokDetailId getSettlementFeeCode() {
        return settlementFeeCode;
    }

    /**
     * 设置 结算手续费账务明细代码
     *
     * @param settlementFeeCode 结算手续费账务明细代码
     */
    public void setSettlementFeeCode(BokDetailId settlementFeeCode) {
        this.settlementFeeCode = settlementFeeCode;
    }

    /**
     * 获取 通道费账务明细代码
     *
     * @return chlFeeCode.
     */
    public BokDetailId getChlFeeCode() {
        return chlFeeCode;
    }

    /**
     * 设置 通道费账务明细代码
     *
     * @param chlFeeCode 通道费账务明细代码
     */
    public void setChlFeeCode(BokDetailId chlFeeCode) {
        this.chlFeeCode = chlFeeCode;
    }

    /**
     * 取得 交易费用
     *
     * @return 交易费用
     */
    public BigDecimal getFeeAmt() {
        return feeAmt;
    }

    /**
     * 设置 交易费用
     *
     * @param feeAmt 交易费用
     */
    public void setFeeAmt(BigDecimal feeAmt) {
        this.feeAmt = feeAmt;
    }

    /**
     * 取得 印花税
     *
     * @return 印花税
     */
    public BigDecimal getStampDuty() {
        return stampDuty;
    }

    /**
     * 设置 印花税
     *
     * @param stampDuty 印花税
     */
    public void setStampDuty(BigDecimal stampDuty) {
        this.stampDuty = stampDuty;
    }

    /**
     * 取得 经手费
     *
     * @return 经手费
     */
    public BigDecimal getBrokerage() {
        return brokerage;
    }

    /**
     * 设置 经手费
     *
     * @param brokerage 经手费
     */
    public void setBrokerage(BigDecimal brokerage) {
        this.brokerage = brokerage;
    }

    /**
     * 取得 证管费
     *
     * @return 证管费
     */
    public BigDecimal getCertificateFee() {
        return certificateFee;
    }

    /**
     * 设置 证管费
     *
     * @param certificateFee 证管费
     */
    public void setCertificateFee(BigDecimal certificateFee) {
        this.certificateFee = certificateFee;
    }

    /**
     * 取得 结算费
     *
     * @return 结算费
     */
    public BigDecimal getClearingFee() {
        return clearingFee;
    }

    /**
     * 设置 结算费
     *
     * @param clearingFee 结算费
     */
    public void setClearingFee(BigDecimal clearingFee) {
        this.clearingFee = clearingFee;
    }

    /**
     * 取得 佣金
     *
     * @return 佣金
     */
    public BigDecimal getCommission() {
        return commission;
    }

    /**
     * 设置 佣金
     *
     * @param commission 佣金
     */
    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    /**
     * 取得 交易手续费
     *
     * @return 交易手续费
     */
    public BigDecimal getTransactionFee() {
        return transactionFee;
    }

    /**
     * 设置 交易手续费
     *
     * @param transactionFee 交易手续费
     */
    public void setTransactionFee(BigDecimal transactionFee) {
        this.transactionFee = transactionFee;
    }

    /**
     * 取得 结算手续费
     *
     * @return 结算手续费
     */
    public BigDecimal getSettlementFee() {
        return settlementFee;
    }

    /**
     * 设置 结算手续费
     *
     * @param settlementFee 结算手续费
     */
    public void setSettlementFee(BigDecimal settlementFee) {
        this.settlementFee = settlementFee;
    }

    /**
     * 获取 stampDutyForDealPay
     *
     * @return stampDutyForDealPay.
     */
    public BigDecimal getStampDutyForDealPay() {
        return stampDutyForDealPay;
    }

    /**
     * 设置 stampDutyForDealPay
     *
     * @param stampDutyForDealPay stampDutyForDealPay
     */
    public void setStampDutyForDealPay(BigDecimal stampDutyForDealPay) {
        this.stampDutyForDealPay = stampDutyForDealPay;
    }

    /**
     * 获取 brokerageForDealPay
     *
     * @return brokerageForDealPay.
     */
    public BigDecimal getBrokerageForDealPay() {
        return brokerageForDealPay;
    }

    /**
     * 设置 brokerageForDealPay
     *
     * @param brokerageForDealPay brokerageForDealPay
     */
    public void setBrokerageForDealPay(BigDecimal brokerageForDealPay) {
        this.brokerageForDealPay = brokerageForDealPay;
    }

    /**
     * 获取 certificateFeeForDealPay
     *
     * @return certificateFeeForDealPay.
     */
    public BigDecimal getCertificateFeeForDealPay() {
        return certificateFeeForDealPay;
    }

    /**
     * 设置 certificateFeeForDealPay
     *
     * @param certificateFeeForDealPay certificateFeeForDealPay
     */
    public void setCertificateFeeForDealPay(BigDecimal certificateFeeForDealPay) {
        this.certificateFeeForDealPay = certificateFeeForDealPay;
    }

    /**
     * 获取 clearingFeeForDealPay
     *
     * @return clearingFeeForDealPay.
     */
    public BigDecimal getClearingFeeForDealPay() {
        return clearingFeeForDealPay;
    }

    /**
     * 设置 clearingFeeForDealPay
     *
     * @param clearingFeeForDealPay clearingFeeForDealPay
     */
    public void setClearingFeeForDealPay(BigDecimal clearingFeeForDealPay) {
        this.clearingFeeForDealPay = clearingFeeForDealPay;
    }

    /**
     * 获取 commissionForDealPay
     *
     * @return commissionForDealPay.
     */
    public BigDecimal getCommissionForDealPay() {
        return commissionForDealPay;
    }

    /**
     * 设置 commissionForDealPay
     *
     * @param commissionForDealPay commissionForDealPay
     */
    public void setCommissionForDealPay(BigDecimal commissionForDealPay) {
        this.commissionForDealPay = commissionForDealPay;
    }

    /**
     * 获取 transactionFeeForDealPay
     *
     * @return transactionFeeForDealPay.
     */
    public BigDecimal getTransactionFeeForDealPay() {
        return transactionFeeForDealPay;
    }

    /**
     * 设置 transactionFeeForDealPay
     *
     * @param transactionFeeForDealPay transactionFeeForDealPay
     */
    public void setTransactionFeeForDealPay(BigDecimal transactionFeeForDealPay) {
        this.transactionFeeForDealPay = transactionFeeForDealPay;
    }

    /**
     * 获取 settlementFeeForDealPay
     *
     * @return settlementFeeForDealPay.
     */
    public BigDecimal getSettlementFeeForDealPay() {
        return settlementFeeForDealPay;
    }

    /**
     * 设置 settlementFeeForDealPay
     *
     * @param settlementFeeForDealPay settlementFeeForDealPay
     */
    public void setSettlementFeeForDealPay(BigDecimal settlementFeeForDealPay) {
        this.settlementFeeForDealPay = settlementFeeForDealPay;
    }

    /**
     * 获取 stampDutyCodeForDealPay
     *
     * @return stampDutyCodeForDealPay.
     */
    public BokDetailId getStampDutyCodeForDealPay() {
        return stampDutyCodeForDealPay;
    }

    /**
     * 设置 stampDutyCodeForDealPay
     *
     * @param stampDutyCodeForDealPay stampDutyCodeForDealPay
     */
    public void setStampDutyCodeForDealPay(BokDetailId stampDutyCodeForDealPay) {
        this.stampDutyCodeForDealPay = stampDutyCodeForDealPay;
    }

    /**
     * 获取 brokerageCodeForDealPay
     *
     * @return brokerageCodeForDealPay.
     */
    public BokDetailId getBrokerageCodeForDealPay() {
        return brokerageCodeForDealPay;
    }

    /**
     * 设置 brokerageCodeForDealPay
     *
     * @param brokerageCodeForDealPay brokerageCodeForDealPay
     */
    public void setBrokerageCodeForDealPay(BokDetailId brokerageCodeForDealPay) {
        this.brokerageCodeForDealPay = brokerageCodeForDealPay;
    }

    /**
     * 获取 certificateFeeCodeForDealPay
     *
     * @return certificateFeeCodeForDealPay.
     */
    public BokDetailId getCertificateFeeCodeForDealPay() {
        return certificateFeeCodeForDealPay;
    }

    /**
     * 设置 certificateFeeCodeForDealPay
     *
     * @param certificateFeeCodeForDealPay certificateFeeCodeForDealPay
     */
    public void setCertificateFeeCodeForDealPay(BokDetailId certificateFeeCodeForDealPay) {
        this.certificateFeeCodeForDealPay = certificateFeeCodeForDealPay;
    }

    /**
     * 获取 clearingFeeCodeForDealPay
     *
     * @return clearingFeeCodeForDealPay.
     */
    public BokDetailId getClearingFeeCodeForDealPay() {
        return clearingFeeCodeForDealPay;
    }

    /**
     * 设置 clearingFeeCodeForDealPay
     *
     * @param clearingFeeCodeForDealPay clearingFeeCodeForDealPay
     */
    public void setClearingFeeCodeForDealPay(BokDetailId clearingFeeCodeForDealPay) {
        this.clearingFeeCodeForDealPay = clearingFeeCodeForDealPay;
    }

    /**
     * 获取 commissionCodeForDealPay
     *
     * @return commissionCodeForDealPay.
     */
    public BokDetailId getCommissionCodeForDealPay() {
        return commissionCodeForDealPay;
    }

    /**
     * 设置 commissionCodeForDealPay
     *
     * @param commissionCodeForDealPay commissionCodeForDealPay
     */
    public void setCommissionCodeForDealPay(BokDetailId commissionCodeForDealPay) {
        this.commissionCodeForDealPay = commissionCodeForDealPay;
    }

    /**
     * 获取 transactionFeeCodeForDealPay
     *
     * @return transactionFeeCodeForDealPay.
     */
    public BokDetailId getTransactionFeeCodeForDealPay() {
        return transactionFeeCodeForDealPay;
    }

    /**
     * 设置 transactionFeeCodeForDealPay
     *
     * @param transactionFeeCodeForDealPay transactionFeeCodeForDealPay
     */
    public void setTransactionFeeCodeForDealPay(BokDetailId transactionFeeCodeForDealPay) {
        this.transactionFeeCodeForDealPay = transactionFeeCodeForDealPay;
    }

    /**
     * 获取 settlementFeeCodeForDealPay
     *
     * @return settlementFeeCodeForDealPay.
     */
    public BokDetailId getSettlementFeeCodeForDealPay() {
        return settlementFeeCodeForDealPay;
    }

    /**
     * 设置 settlementFeeCodeForDealPay
     *
     * @param settlementFeeCodeForDealPay settlementFeeCodeForDealPay
     */
    public void setSettlementFeeCodeForDealPay(BokDetailId settlementFeeCodeForDealPay) {
        this.settlementFeeCodeForDealPay = settlementFeeCodeForDealPay;
    }

    /**
     * 获取 otherFee
     *
     * @return otherFee.
     */
    public BigDecimal getOtherFee() {
        return otherFee;
    }

    /**
     * 设置 otherFee
     *
     * @param otherFee
     */
    public void setOtherFee(BigDecimal otherFee) {
        this.otherFee = otherFee;
    }

    /**
     * 获取 subscriptionFee
     *
     * @return subscriptionFee.
     */
    public BigDecimal getSubscriptionFee() {
        return subscriptionFee;
    }

    /**
     * 设置 subscriptionFee
     *
     * @param subscriptionFee
     */
    public void setSubscriptionFee(BigDecimal subscriptionFee) {
        this.subscriptionFee = subscriptionFee;
    }

    /**
     * 获取 redemptionFee
     *
     * @return redemptionFee.
     */
    public BigDecimal getRedemptionFee() {
        return redemptionFee;
    }

    /**
     * 设置 redemptionFee
     *
     * @param redemptionFee
     */
    public void setRedemptionFee(BigDecimal redemptionFee) {
        this.redemptionFee = redemptionFee;
    }

    /**
     * 获取 offerFee
     *
     * @return offerFee.
     */
    public BigDecimal getOfferFee() {
        return offerFee;
    }

    /**
     * 设置 offerFee
     *
     * @param offerFee
     */
    public void setOfferFee(BigDecimal offerFee) {
        this.offerFee = offerFee;
    }

    /**
     * 获取 otherFeeCode
     *
     * @return otherFeeCode.
     */
    public BokDetailId getOtherFeeCode() {
        return otherFeeCode;
    }

    /**
     * 设置 otherFeeCode
     *
     * @param otherFeeCode
     */
    public void setOtherFeeCode(BokDetailId otherFeeCode) {
        this.otherFeeCode = otherFeeCode;
    }

    /**
     * 获取 subscriptionFeeCode
     *
     * @return subscriptionFeeCode.
     */
    public BokDetailId getSubscriptionFeeCode() {
        return subscriptionFeeCode;
    }

    /**
     * 设置 subscriptionFeeCode
     *
     * @param subscriptionFeeCode
     */
    public void setSubscriptionFeeCode(BokDetailId subscriptionFeeCode) {
        this.subscriptionFeeCode = subscriptionFeeCode;
    }

    /**
     * 获取 redemptionFeeCode
     *
     * @return redemptionFeeCode.
     */
    public BokDetailId getRedemptionFeeCode() {
        return redemptionFeeCode;
    }

    /**
     * 设置 redemptionFeeCode
     *
     * @param redemptionFeeCode
     */
    public void setRedemptionFeeCode(BokDetailId redemptionFeeCode) {
        this.redemptionFeeCode = redemptionFeeCode;
    }

    /**
     * 获取 offerFeeCode
     *
     * @return offerFeeCode.
     */
    public BokDetailId getOfferFeeCode() {
        return offerFeeCode;
    }

    /**
     * 设置 offerFeeCode
     *
     * @param offerFeeCode
     */
    public void setOfferFeeCode(BokDetailId offerFeeCode) {
        this.offerFeeCode = offerFeeCode;
    }

    /**
     * 获取 otherFeeForDealPay
     *
     * @return otherFeeForDealPay.
     */
    public BigDecimal getOtherFeeForDealPay() {
        return otherFeeForDealPay;
    }

    /**
     * 设置 otherFeeForDealPay
     *
     * @param otherFeeForDealPay
     */
    public void setOtherFeeForDealPay(BigDecimal otherFeeForDealPay) {
        this.otherFeeForDealPay = otherFeeForDealPay;
    }

    /**
     * 获取 subscriptionFeeForDealPay
     *
     * @return subscriptionFeeForDealPay.
     */
    public BigDecimal getSubscriptionFeeForDealPay() {
        return subscriptionFeeForDealPay;
    }

    /**
     * 设置 subscriptionFeeForDealPay
     *
     * @param subscriptionFeeForDealPay
     */
    public void setSubscriptionFeeForDealPay(BigDecimal subscriptionFeeForDealPay) {
        this.subscriptionFeeForDealPay = subscriptionFeeForDealPay;
    }

    /**
     * 获取 redemptionFeeForDealPay
     *
     * @return redemptionFeeForDealPay.
     */
    public BigDecimal getRedemptionFeeForDealPay() {
        return redemptionFeeForDealPay;
    }

    /**
     * 设置 redemptionFeeForDealPay
     *
     * @param redemptionFeeForDealPay
     */
    public void setRedemptionFeeForDealPay(BigDecimal redemptionFeeForDealPay) {
        this.redemptionFeeForDealPay = redemptionFeeForDealPay;
    }

    /**
     * 获取 offerFeeForDealPay
     *
     * @return offerFeeForDealPay.
     */
    public BigDecimal getOfferFeeForDealPay() {
        return offerFeeForDealPay;
    }

    /**
     * 设置 offerFeeForDealPay
     *
     * @param offerFeeForDealPay
     */
    public void setOfferFeeForDealPay(BigDecimal offerFeeForDealPay) {
        this.offerFeeForDealPay = offerFeeForDealPay;
    }

    /**
     * 获取 otherFeeCodeForDealPay
     *
     * @return otherFeeCodeForDealPay.
     */
    public BokDetailId getOtherFeeCodeForDealPay() {
        return otherFeeCodeForDealPay;
    }

    /**
     * 设置 otherFeeCodeForDealPay
     *
     * @param otherFeeCodeForDealPay
     */
    public void setOtherFeeCodeForDealPay(BokDetailId otherFeeCodeForDealPay) {
        this.otherFeeCodeForDealPay = otherFeeCodeForDealPay;
    }

    /**
     * 获取 subscriptionFeeCodeForDealPay
     *
     * @return subscriptionFeeCodeForDealPay.
     */
    public BokDetailId getSubscriptionFeeCodeForDealPay() {
        return subscriptionFeeCodeForDealPay;
    }

    /**
     * 设置 subscriptionFeeCodeForDealPay
     *
     * @param subscriptionFeeCodeForDealPay
     */
    public void setSubscriptionFeeCodeForDealPay(BokDetailId subscriptionFeeCodeForDealPay) {
        this.subscriptionFeeCodeForDealPay = subscriptionFeeCodeForDealPay;
    }

    /**
     * 获取 redemptionFeeCodeForDealPay
     *
     * @return redemptionFeeCodeForDealPay.
     */
    public BokDetailId getRedemptionFeeCodeForDealPay() {
        return redemptionFeeCodeForDealPay;
    }

    /**
     * 设置 redemptionFeeCodeForDealPay
     *
     * @param redemptionFeeCodeForDealPay
     */
    public void setRedemptionFeeCodeForDealPay(BokDetailId redemptionFeeCodeForDealPay) {
        this.redemptionFeeCodeForDealPay = redemptionFeeCodeForDealPay;
    }

    /**
     * 获取 offerFeeCodeForDealPay
     *
     * @return offerFeeCodeForDealPay.
     */
    public BokDetailId getOfferFeeCodeForDealPay() {
        return offerFeeCodeForDealPay;
    }

    /**
     * 设置 offerFeeCodeForDealPay
     *
     * @param offerFeeCodeForDealPay
     */
    public void setOfferFeeCodeForDealPay(BokDetailId offerFeeCodeForDealPay) {
        this.offerFeeCodeForDealPay = offerFeeCodeForDealPay;
    }


}
