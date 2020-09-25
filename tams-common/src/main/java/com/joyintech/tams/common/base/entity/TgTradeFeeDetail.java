package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class TgTradeFeeDetail extends TgTradeFeeDetailKey {
    /**
     * 是否随交易支付
     */
    private String isPayWithTrade;

    /**
     * 费用金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal feeAmt;

    /**
     * 费用代码
     */
    private String feeId;

    /**
     * 取得 是否随交易支付
     * @return 是否随交易支付
     */
    public String getIsPayWithTrade() {
        return isPayWithTrade;
    }

    /**
     * 设置 是否随交易支付
     * @param isPayWithTrade 是否随交易支付
     */
    public void setIsPayWithTrade(String isPayWithTrade) {
        this.isPayWithTrade = isPayWithTrade == null ? null : isPayWithTrade.trim();
    }

    /**
     * 取得 费用金额
     * @return 费用金额
     */
    public BigDecimal getFeeAmt() {
        return feeAmt;
    }

    /**
     * 设置 费用金额
     * @param feeAmt 费用金额
     */
    public void setFeeAmt(BigDecimal feeAmt) {
        this.feeAmt = feeAmt;
    }

    /**
     * 取得 费用代码
     * @return 费用代码
     */
    public String getFeeId() {
        return feeId;
    }

    /**
     * 设置 费用代码
     * @param feeId 费用代码
     */
    public void setFeeId(String feeId) {
        this.feeId = feeId == null ? null : feeId.trim();
    }
}