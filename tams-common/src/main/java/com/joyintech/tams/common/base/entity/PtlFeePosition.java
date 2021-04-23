package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class PtlFeePosition extends PtlFeePositionKey {
    /**
     * 当日计费本金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyFeeCapital;

    /**
     * 当日费率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyFeeRate;

    /**
     * 当日区间应付费用
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyIntervalPayFee;

    /**
     * 当日累计应付费用
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyAccumulPayFee;

    /**
     * 原币
     */
    private String oCcy;

    /**
     * 本位币
     */
    private String bCcy;

    /**
     * 当日区间应付费用_本位币
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyIntervalPayFeeB;

    /**
     * 当日累计应付费用_本位币
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyAccumulPayFeeB;

    /**
     * 券商代码
     */
    private String customerId;

    /**
     * 席位号
     */
    private String seatId;

    /**
     * 取得 当日计费本金
     * @return 当日计费本金
     */
    public BigDecimal getTdyFeeCapital() {
        return tdyFeeCapital;
    }

    /**
     * 设置 当日计费本金
     * @param tdyFeeCapital 当日计费本金
     */
    public void setTdyFeeCapital(BigDecimal tdyFeeCapital) {
        this.tdyFeeCapital = tdyFeeCapital;
    }

    /**
     * 取得 当日费率
     * @return 当日费率
     */
    public BigDecimal getTdyFeeRate() {
        return tdyFeeRate;
    }

    /**
     * 设置 当日费率
     * @param tdyFeeRate 当日费率
     */
    public void setTdyFeeRate(BigDecimal tdyFeeRate) {
        this.tdyFeeRate = tdyFeeRate;
    }

    /**
     * 取得 当日区间应付费用
     * @return 当日区间应付费用
     */
    public BigDecimal getTdyIntervalPayFee() {
        return tdyIntervalPayFee;
    }

    /**
     * 设置 当日区间应付费用
     * @param tdyIntervalPayFee 当日区间应付费用
     */
    public void setTdyIntervalPayFee(BigDecimal tdyIntervalPayFee) {
        this.tdyIntervalPayFee = tdyIntervalPayFee;
    }

    /**
     * 取得 当日累计应付费用
     * @return 当日累计应付费用
     */
    public BigDecimal getTdyAccumulPayFee() {
        return tdyAccumulPayFee;
    }

    /**
     * 设置 当日累计应付费用
     * @param tdyAccumulPayFee 当日累计应付费用
     */
    public void setTdyAccumulPayFee(BigDecimal tdyAccumulPayFee) {
        this.tdyAccumulPayFee = tdyAccumulPayFee;
    }

    /**
     * 取得 原币
     * @return 原币
     */
    public String getoCcy() {
        return oCcy;
    }

    /**
     * 设置 原币
     * @param oCcy 原币
     */
    public void setoCcy(String oCcy) {
        this.oCcy = oCcy == null ? null : oCcy.trim();
    }

    /**
     * 取得 本位币
     * @return 本位币
     */
    public String getbCcy() {
        return bCcy;
    }

    /**
     * 设置 本位币
     * @param bCcy 本位币
     */
    public void setbCcy(String bCcy) {
        this.bCcy = bCcy == null ? null : bCcy.trim();
    }

    /**
     * 取得 当日区间应付费用_本位币
     * @return 当日区间应付费用_本位币
     */
    public BigDecimal getTdyIntervalPayFeeB() {
        return tdyIntervalPayFeeB;
    }

    /**
     * 设置 当日区间应付费用_本位币
     * @param tdyIntervalPayFeeB 当日区间应付费用_本位币
     */
    public void setTdyIntervalPayFeeB(BigDecimal tdyIntervalPayFeeB) {
        this.tdyIntervalPayFeeB = tdyIntervalPayFeeB;
    }

    /**
     * 取得 当日累计应付费用_本位币
     * @return 当日累计应付费用_本位币
     */
    public BigDecimal getTdyAccumulPayFeeB() {
        return tdyAccumulPayFeeB;
    }

    /**
     * 设置 当日累计应付费用_本位币
     * @param tdyAccumulPayFeeB 当日累计应付费用_本位币
     */
    public void setTdyAccumulPayFeeB(BigDecimal tdyAccumulPayFeeB) {
        this.tdyAccumulPayFeeB = tdyAccumulPayFeeB;
    }

    /**
     * 取得 券商代码
     * @return 券商代码
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * 设置 券商代码
     * @param customerId 券商代码
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    /**
     * 取得 席位号
     * @return 席位号
     */
    public String getSeatId() {
        return seatId;
    }

    /**
     * 设置 席位号
     * @param seatId 席位号
     */
    public void setSeatId(String seatId) {
        this.seatId = seatId == null ? null : seatId.trim();
    }
}