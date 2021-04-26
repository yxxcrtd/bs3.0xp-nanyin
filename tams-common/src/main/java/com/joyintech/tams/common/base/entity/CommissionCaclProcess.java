package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class CommissionCaclProcess extends BaseEntity {
    /**
     * 业务编号
     */
    private String busiNo;

    /**
     * 佣金计算方式
     */
    private String calcWay;

    /**
     * 计算基数
     */
    private String calcBase;

    /**
     * 费率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yield;

    /**
     * 佣金包含经手费金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal includeJsfAmt;

    /**
     * 佣金包含结算费金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal includeSettleFee;

    /**
     * 佣金包含证管费金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal includeZgfAmt;

    /**
     * 佣金包含过户费金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal includeGhfAmt;

    /**
     * 佣金包含印花税金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal includeStampFee;

    /**
     * 限额最大值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal maxQuotaAmt;

    /**
     * 限额最小值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal minQuotaAmt;

    /**
     * 佣金金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal commissionAmt;

    /**
     * 批次号
     */
    private String batchNumber;

    /**
     * 取得 业务编号
     * @return 业务编号
     */
    public String getBusiNo() {
        return busiNo;
    }

    /**
     * 设置 业务编号
     * @param busiNo 业务编号
     */
    public void setBusiNo(String busiNo) {
        this.busiNo = busiNo == null ? null : busiNo.trim();
    }

    /**
     * 取得 佣金计算方式
     * @return 佣金计算方式
     */
    public String getCalcWay() {
        return calcWay;
    }

    /**
     * 设置 佣金计算方式
     * @param calcWay 佣金计算方式
     */
    public void setCalcWay(String calcWay) {
        this.calcWay = calcWay == null ? null : calcWay.trim();
    }

    /**
     * 取得 计算基数
     * @return 计算基数
     */
    public String getCalcBase() {
        return calcBase;
    }

    /**
     * 设置 计算基数
     * @param calcBase 计算基数
     */
    public void setCalcBase(String calcBase) {
        this.calcBase = calcBase == null ? null : calcBase.trim();
    }

    /**
     * 取得 费率
     * @return 费率
     */
    public BigDecimal getYield() {
        return yield;
    }

    /**
     * 设置 费率
     * @param yield 费率
     */
    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }

    /**
     * 取得 佣金包含经手费金额
     * @return 佣金包含经手费金额
     */
    public BigDecimal getIncludeJsfAmt() {
        return includeJsfAmt;
    }

    /**
     * 设置 佣金包含经手费金额
     * @param includeJsfAmt 佣金包含经手费金额
     */
    public void setIncludeJsfAmt(BigDecimal includeJsfAmt) {
        this.includeJsfAmt = includeJsfAmt;
    }

    /**
     * 取得 佣金包含结算费金额
     * @return 佣金包含结算费金额
     */
    public BigDecimal getIncludeSettleFee() {
        return includeSettleFee;
    }

    /**
     * 设置 佣金包含结算费金额
     * @param includeSettleFee 佣金包含结算费金额
     */
    public void setIncludeSettleFee(BigDecimal includeSettleFee) {
        this.includeSettleFee = includeSettleFee;
    }

    /**
     * 取得 佣金包含证管费金额
     * @return 佣金包含证管费金额
     */
    public BigDecimal getIncludeZgfAmt() {
        return includeZgfAmt;
    }

    /**
     * 设置 佣金包含证管费金额
     * @param includeZgfAmt 佣金包含证管费金额
     */
    public void setIncludeZgfAmt(BigDecimal includeZgfAmt) {
        this.includeZgfAmt = includeZgfAmt;
    }

    /**
     * 取得 佣金包含过户费金额
     * @return 佣金包含过户费金额
     */
    public BigDecimal getIncludeGhfAmt() {
        return includeGhfAmt;
    }

    /**
     * 设置 佣金包含过户费金额
     * @param includeGhfAmt 佣金包含过户费金额
     */
    public void setIncludeGhfAmt(BigDecimal includeGhfAmt) {
        this.includeGhfAmt = includeGhfAmt;
    }

    /**
     * 取得 佣金包含印花税金额
     * @return 佣金包含印花税金额
     */
    public BigDecimal getIncludeStampFee() {
        return includeStampFee;
    }

    /**
     * 设置 佣金包含印花税金额
     * @param includeStampFee 佣金包含印花税金额
     */
    public void setIncludeStampFee(BigDecimal includeStampFee) {
        this.includeStampFee = includeStampFee;
    }

    /**
     * 取得 限额最大值
     * @return 限额最大值
     */
    public BigDecimal getMaxQuotaAmt() {
        return maxQuotaAmt;
    }

    /**
     * 设置 限额最大值
     * @param maxQuotaAmt 限额最大值
     */
    public void setMaxQuotaAmt(BigDecimal maxQuotaAmt) {
        this.maxQuotaAmt = maxQuotaAmt;
    }

    /**
     * 取得 限额最小值
     * @return 限额最小值
     */
    public BigDecimal getMinQuotaAmt() {
        return minQuotaAmt;
    }

    /**
     * 设置 限额最小值
     * @param minQuotaAmt 限额最小值
     */
    public void setMinQuotaAmt(BigDecimal minQuotaAmt) {
        this.minQuotaAmt = minQuotaAmt;
    }

    /**
     * 取得 佣金金额
     * @return 佣金金额
     */
    public BigDecimal getCommissionAmt() {
        return commissionAmt;
    }

    /**
     * 设置 佣金金额
     * @param commissionAmt 佣金金额
     */
    public void setCommissionAmt(BigDecimal commissionAmt) {
        this.commissionAmt = commissionAmt;
    }

    /**
     * 取得 批次号
     * @return 批次号
     */
    public String getBatchNumber() {
        return batchNumber;
    }

    /**
     * 设置 批次号
     * @param batchNumber 批次号
     */
    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber == null ? null : batchNumber.trim();
    }
}