package com.joyintech.tams.settle.common.dto;

import java.math.BigDecimal;

/**
 * 佣金计算明细
 * @description 
 * @author 王波
 * @version 1.0 2020年7月13日
 * @see CommissionCalcDetail
 * @since 1.0
 */
public class CommissionCalcDetail {
    /**
     * 佣金计算方式
     */
    private String commCalMethod;

    /**
     * 业务编号
     */
    private String bussinessId;

    /**
     * 计算基数
     */
    private BigDecimal calcBase;

    /**
     * 费率
     */
    private BigDecimal feeRate;


    /**
     * 佣金包含经手费金额
     */
    private BigDecimal includeJsfAmt;

    /**
     * 佣金包含结算费金额
     */
    private BigDecimal includeSettleFee;

    /**
     * 佣金包含证管费金额
     */
    private BigDecimal includeZgfAmt;

    /**
     * 佣金包含过户费金额
     */
    private BigDecimal includeGhfAmt;

    /**
     * 佣金包含印花税金额
     */
    private BigDecimal includeStampFee;

    /**
     * 限额最大值
     */
    private BigDecimal maxQuotaAmt;

    /**
     * 限额最小值
     */
    private BigDecimal minQuotaAmt;

    /**
     * 佣金金额
     */
    private BigDecimal commissionAmt;

    /**
     * 批次号
     */
    private String batch;

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getCommCalMethod() {
        return commCalMethod;
    }

    public void setCommCalMethod(String commCalMethod) {
        this.commCalMethod = commCalMethod;
    }

    public String getBussinessId() {
        return bussinessId;
    }

    public void setBussinessId(String bussinessId) {
        this.bussinessId = bussinessId;
    }

    public BigDecimal getCalcBase() {
        return calcBase;
    }

    public void setCalcBase(BigDecimal calcBase) {
        this.calcBase = calcBase;
    }

    public BigDecimal getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(BigDecimal feeRate) {
        this.feeRate = feeRate;
    }

    public BigDecimal getIncludeJsfAmt() {
        return includeJsfAmt;
    }

    public void setIncludeJsfAmt(BigDecimal includeJsfAmt) {
        this.includeJsfAmt = includeJsfAmt;
    }

    public BigDecimal getIncludeSettleFee() {
        return includeSettleFee;
    }

    public void setIncludeSettleFee(BigDecimal includeSettleFee) {
        this.includeSettleFee = includeSettleFee;
    }

    public BigDecimal getIncludeZgfAmt() {
        return includeZgfAmt;
    }

    public void setIncludeZgfAmt(BigDecimal includeZgfAmt) {
        this.includeZgfAmt = includeZgfAmt;
    }

    public BigDecimal getIncludeGhfAmt() {
        return includeGhfAmt;
    }

    public void setIncludeGhfAmt(BigDecimal includeGhfAmt) {
        this.includeGhfAmt = includeGhfAmt;
    }

    public BigDecimal getIncludeStampFee() {
        return includeStampFee;
    }

    public void setIncludeStampFee(BigDecimal includeStampFee) {
        this.includeStampFee = includeStampFee;
    }

    public BigDecimal getMaxQuotaAmt() {
        return maxQuotaAmt;
    }

    public void setMaxQuotaAmt(BigDecimal maxQuotaAmt) {
        this.maxQuotaAmt = maxQuotaAmt;
    }

    public BigDecimal getMinQuotaAmt() {
        return minQuotaAmt;
    }

    public void setMinQuotaAmt(BigDecimal minQuotaAmt) {
        this.minQuotaAmt = minQuotaAmt;
    }

    public BigDecimal getCommissionAmt() {
        return commissionAmt;
    }

    public void setCommissionAmt(BigDecimal commissionAmt) {
        this.commissionAmt = commissionAmt;
    }
}
