package com.joyintech.tams.valuation.common.interf.base.dto;

import java.math.BigDecimal;

/**
 * 通道费用信息
 *
 * @author zhangwei
 * @createTime 2019-08-02 17:24
 */
public class ChannelFeeInfo {

    /**
     * 费用类型
     */
    private String FeeType;

    /**
     * 费用代码
     */
    private String feeId;

    /**
     * 是否随交易支付
     */
    private String isPayWithTrade;

    /**
     * 费用金额
     */
    private BigDecimal feeAmt;

    public String getFeeType() {
        return FeeType;
    }

    public void setFeeType(String feeType) {
        FeeType = feeType;
    }

    public String getFeeId() {
        return feeId;
    }

    public void setFeeId(String feeId) {
        this.feeId = feeId;
    }

    public String getIsPayWithTrade() {
        return isPayWithTrade;
    }

    public void setIsPayWithTrade(String isPayWithTrade) {
        this.isPayWithTrade = isPayWithTrade;
    }

    public BigDecimal getFeeAmt() {
        return feeAmt;
    }

    public void setFeeAmt(BigDecimal feeAmt) {
        this.feeAmt = feeAmt;
    }
}
