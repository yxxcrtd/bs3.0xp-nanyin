package com.joyintech.tams.common.base.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

public class WflTrdTradeFeeDetail extends WflTrdTradeFeeDetailKey {
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
     * 通道代码，通道费时填该值。
     */
    private String chlFinprodId;

    /**
     * 费用代码，计提式费用存现金流代码，一次性费用存费用类型
     */
    private String feeId;

    /**
     * 费率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal feeRate;

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
     * 取得 通道代码，通道费时填该值。
     * @return 通道代码，通道费时填该值。
     */
    public String getChlFinprodId() {
        return chlFinprodId;
    }

    /**
     * 设置 通道代码，通道费时填该值。
     * @param chlFinprodId 通道代码，通道费时填该值。
     */
    public void setChlFinprodId(String chlFinprodId) {
        this.chlFinprodId = chlFinprodId == null ? null : chlFinprodId.trim();
    }

    /**
     * 取得 费用代码，计提式费用存现金流代码，一次性费用存费用类型
     * @return 费用代码，计提式费用存现金流代码，一次性费用存费用类型
     */
    public String getFeeId() {
        return feeId;
    }

    /**
     * 设置 费用代码，计提式费用存现金流代码，一次性费用存费用类型
     * @param feeId 费用代码，计提式费用存现金流代码，一次性费用存费用类型
     */
    public void setFeeId(String feeId) {
        this.feeId = feeId == null ? null : feeId.trim();
    }

    /**
     * 取得 费率
     * @return 费率
     */
    public BigDecimal getFeeRate() {
        return feeRate;
    }

    /**
     * 设置 费率
     * @param feeRate 费率
     */
    public void setFeeRate(BigDecimal feeRate) {
        this.feeRate = feeRate;
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