package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class WflEtrEntrustBidPrice extends WflEtrEntrustBidPriceKey {
    /**
     * 投标券面
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bidPrinAmt;

    /**
     * 流程id
     */
    private String workflowId;

    /**
     * 数据状态
     */
    private String workflowStatus;

    /**
     * 取得 投标券面
     * @return 投标券面
     */
    public BigDecimal getBidPrinAmt() {
        return bidPrinAmt;
    }

    /**
     * 设置 投标券面
     * @param bidPrinAmt 投标券面
     */
    public void setBidPrinAmt(BigDecimal bidPrinAmt) {
        this.bidPrinAmt = bidPrinAmt;
    }

    /**
     * 取得 流程id
     * @return 流程id
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 流程id
     * @param workflowId 流程id
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
    }

    /**
     * 取得 数据状态
     * @return 数据状态
     */
    public String getWorkflowStatus() {
        return workflowStatus;
    }

    /**
     * 设置 数据状态
     * @param workflowStatus 数据状态
     */
    public void setWorkflowStatus(String workflowStatus) {
        this.workflowStatus = workflowStatus == null ? null : workflowStatus.trim();
    }
}