package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class WflRegTradeFee extends WflRegTradeFeeKey {
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
     * 工作流代码
     */
    private String workflowId;

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
     * 取得 工作流代码
     * @return 工作流代码
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 工作流代码
     * @param workflowId 工作流代码
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
    }
}