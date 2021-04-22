package com.joyintech.tams.valuation.common.batch.finproduct.entity;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.common.base.entity.BokDetailId;

import java.math.BigDecimal;

/**
 * @ClassName ValExecFeeDiffDto
 * @Description: 行权交易差额信息dto
 * @Author yanya
 * @Date 2021/3/10 0010 
 * @Version V1.0
 **/
public class ValExecFeeDiffDto extends BaseEntity {


    private static final long serialVersionUID = 8492129806368270320L;
    /**
     * 交易金额差额
     */
    private BigDecimal tradeAmtDiff = BigDecimal.ZERO;

    /**
     * 利息金额差额
     */
    private BigDecimal intAmtDiff = BigDecimal.ZERO;

    /**
     * 印花税差额
     */
    private BigDecimal stampDutyDiff = BigDecimal.ZERO;

    /**
     * 经手费差额
     */
    private BigDecimal brokerageDiff = BigDecimal.ZERO;

    /**
     * 证管费差额
     */
    private BigDecimal certificateFeeDiff = BigDecimal.ZERO;

    /**
     * 结算费差额
     */
    private BigDecimal clearingFeeDiff = BigDecimal.ZERO;

    /**
     * 佣金差额
     */
    private BigDecimal commissionDiff = BigDecimal.ZERO;

    /**
     * 交易手续费差额
     */
    private BigDecimal transactionFeeDiff = BigDecimal.ZERO;

    /**
     * 结算手续费差额
     */
    private BigDecimal settlementFeeDiff = BigDecimal.ZERO;

    /**
     * 随交易支付_印花税差额
     */
    private BigDecimal stampDutyForDealPayDiff  = BigDecimal.ZERO;

    /**
     * 随交易支付_经手费差额
     */
    private BigDecimal brokerageForDealPayDiff  = BigDecimal.ZERO;

    /**
     * 随交易支付_证管费差额
     */
    private BigDecimal certificateFeeForDealPayDiff  = BigDecimal.ZERO;

    /**
     * 随交易支付_结算费差额
     */
    private BigDecimal clearingFeeForDealPayDiff = BigDecimal.ZERO;

    /**
     * 随交易支付_佣金差额
     */
    private BigDecimal commissionForDealPayDiff = BigDecimal.ZERO;

    /**
     * 随交易支付_交易手续费差额
     */
    private BigDecimal transactionFeeForDealPayDiff = BigDecimal.ZERO;

    /**
     * 随交易支付_结算手续费差额
     */
    private BigDecimal settlementFeeForDealPayDiff = BigDecimal.ZERO;

    /**
     * 通道费差额
     */
    private BigDecimal chlFeeDiff = BigDecimal.ZERO;

    /**
     * 交易费用差额
     */
    private BigDecimal feeAmtDiff = BigDecimal.ZERO;

    /**
     * 随交易支付交易费用差额
     */
    private BigDecimal feeAmtForDealPayDiff = BigDecimal.ZERO;

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
     * 不随交易支付费用差额
     */
    private BigDecimal noFeeAmtDiff = BigDecimal.ZERO;

    public BigDecimal getTradeAmtDiff() {
        return tradeAmtDiff;
    }

    public void setTradeAmtDiff(BigDecimal tradeAmtDiff) {
        this.tradeAmtDiff = tradeAmtDiff;
    }

    public BigDecimal getIntAmtDiff() {
        return intAmtDiff;
    }

    public void setIntAmtDiff(BigDecimal intAmtDiff) {
        this.intAmtDiff = intAmtDiff;
    }

    public BigDecimal getStampDutyDiff() {
        return stampDutyDiff;
    }

    public void setStampDutyDiff(BigDecimal stampDutyDiff) {
        this.stampDutyDiff = stampDutyDiff;
    }

    public BigDecimal getBrokerageDiff() {
        return brokerageDiff;
    }

    public void setBrokerageDiff(BigDecimal brokerageDiff) {
        this.brokerageDiff = brokerageDiff;
    }

    public BigDecimal getCertificateFeeDiff() {
        return certificateFeeDiff;
    }

    public void setCertificateFeeDiff(BigDecimal certificateFeeDiff) {
        this.certificateFeeDiff = certificateFeeDiff;
    }

    public BigDecimal getClearingFeeDiff() {
        return clearingFeeDiff;
    }

    public void setClearingFeeDiff(BigDecimal clearingFeeDiff) {
        this.clearingFeeDiff = clearingFeeDiff;
    }

    public BigDecimal getCommissionDiff() {
        return commissionDiff;
    }

    public void setCommissionDiff(BigDecimal commissionDiff) {
        this.commissionDiff = commissionDiff;
    }

    public BigDecimal getTransactionFeeDiff() {
        return transactionFeeDiff;
    }

    public void setTransactionFeeDiff(BigDecimal transactionFeeDiff) {
        this.transactionFeeDiff = transactionFeeDiff;
    }

    public BigDecimal getSettlementFeeDiff() {
        return settlementFeeDiff;
    }

    public void setSettlementFeeDiff(BigDecimal settlementFeeDiff) {
        this.settlementFeeDiff = settlementFeeDiff;
    }

    public BigDecimal getChlFeeDiff() {
        return chlFeeDiff;
    }

    public void setChlFeeDiff(BigDecimal chlFeeDiff) {
        this.chlFeeDiff = chlFeeDiff;
    }

    public BigDecimal getStampDutyForDealPayDiff() {
        return stampDutyForDealPayDiff;
    }

    public void setStampDutyForDealPayDiff(BigDecimal stampDutyForDealPayDiff) {
        this.stampDutyForDealPayDiff = stampDutyForDealPayDiff;
    }

    public BigDecimal getBrokerageForDealPayDiff() {
        return brokerageForDealPayDiff;
    }

    public void setBrokerageForDealPayDiff(BigDecimal brokerageForDealPayDiff) {
        this.brokerageForDealPayDiff = brokerageForDealPayDiff;
    }

    public BigDecimal getCertificateFeeForDealPayDiff() {
        return certificateFeeForDealPayDiff;
    }

    public void setCertificateFeeForDealPayDiff(BigDecimal certificateFeeForDealPayDiff) {
        this.certificateFeeForDealPayDiff = certificateFeeForDealPayDiff;
    }

    public BigDecimal getClearingFeeForDealPayDiff() {
        return clearingFeeForDealPayDiff;
    }

    public void setClearingFeeForDealPayDiff(BigDecimal clearingFeeForDealPayDiff) {
        this.clearingFeeForDealPayDiff = clearingFeeForDealPayDiff;
    }

    public BigDecimal getCommissionForDealPayDiff() {
        return commissionForDealPayDiff;
    }

    public void setCommissionForDealPayDiff(BigDecimal commissionForDealPayDiff) {
        this.commissionForDealPayDiff = commissionForDealPayDiff;
    }

    public BigDecimal getTransactionFeeForDealPayDiff() {
        return transactionFeeForDealPayDiff;
    }

    public void setTransactionFeeForDealPayDiff(BigDecimal transactionFeeForDealPayDiff) {
        this.transactionFeeForDealPayDiff = transactionFeeForDealPayDiff;
    }

    public BigDecimal getSettlementFeeForDealPayDiff() {
        return settlementFeeForDealPayDiff;
    }

    public void setSettlementFeeForDealPayDiff(BigDecimal settlementFeeForDealPayDiff) {
        this.settlementFeeForDealPayDiff = settlementFeeForDealPayDiff;
    }

    public BigDecimal getFeeAmtDiff() {
        return feeAmtDiff;
    }

    public void setFeeAmtDiff(BigDecimal feeAmtDiff) {
        this.feeAmtDiff = feeAmtDiff;
    }

    public BigDecimal getFeeAmtForDealPayDiff() {
        return feeAmtForDealPayDiff;
    }

    public void setFeeAmtForDealPayDiff(BigDecimal feeAmtForDealPayDiff) {
        this.feeAmtForDealPayDiff = feeAmtForDealPayDiff;
    }

    public BigDecimal getNoFeeAmtDiff() {
        return noFeeAmtDiff;
    }

    public void setNoFeeAmtDiff(BigDecimal noFeeAmtDiff) {
        this.noFeeAmtDiff = noFeeAmtDiff;
    }

    public BokDetailId getStampDutyCode() {
        return stampDutyCode;
    }

    public void setStampDutyCode(BokDetailId stampDutyCode) {
        this.stampDutyCode = stampDutyCode;
    }

    public BokDetailId getBrokerageCode() {
        return brokerageCode;
    }

    public void setBrokerageCode(BokDetailId brokerageCode) {
        this.brokerageCode = brokerageCode;
    }

    public BokDetailId getCertificateFeeCode() {
        return certificateFeeCode;
    }

    public void setCertificateFeeCode(BokDetailId certificateFeeCode) {
        this.certificateFeeCode = certificateFeeCode;
    }

    public BokDetailId getClearingFeeCode() {
        return clearingFeeCode;
    }

    public void setClearingFeeCode(BokDetailId clearingFeeCode) {
        this.clearingFeeCode = clearingFeeCode;
    }

    public BokDetailId getCommissionCode() {
        return commissionCode;
    }

    public void setCommissionCode(BokDetailId commissionCode) {
        this.commissionCode = commissionCode;
    }

    public BokDetailId getTransactionFeeCode() {
        return transactionFeeCode;
    }

    public void setTransactionFeeCode(BokDetailId transactionFeeCode) {
        this.transactionFeeCode = transactionFeeCode;
    }

    public BokDetailId getSettlementFeeCode() {
        return settlementFeeCode;
    }

    public void setSettlementFeeCode(BokDetailId settlementFeeCode) {
        this.settlementFeeCode = settlementFeeCode;
    }

    public BokDetailId getChlFeeCode() {
        return chlFeeCode;
    }

    public void setChlFeeCode(BokDetailId chlFeeCode) {
        this.chlFeeCode = chlFeeCode;
    }
}
