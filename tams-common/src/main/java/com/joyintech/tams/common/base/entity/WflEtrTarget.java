package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class WflEtrTarget extends WflEtrTargetKey {
    /**
     * 标的券面总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal targetFaceValue;

    /**
     * 流程ID
     */
    private String workflowId;

    /**
     * 流程状态
     */
    private String workflowStatus;

    /**
     * 取得 标的券面总额
     * @return 标的券面总额
     */
    public BigDecimal getTargetFaceValue() {
        return targetFaceValue;
    }

    /**
     * 设置 标的券面总额
     * @param targetFaceValue 标的券面总额
     */
    public void setTargetFaceValue(BigDecimal targetFaceValue) {
        this.targetFaceValue = targetFaceValue;
    }

    /**
     * 取得 流程ID
     * @return 流程ID
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 流程ID
     * @param workflowId 流程ID
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
    }

    /**
     * 取得 流程状态
     * @return 流程状态
     */
    public String getWorkflowStatus() {
        return workflowStatus;
    }

    /**
     * 设置 流程状态
     * @param workflowStatus 流程状态
     */
    public void setWorkflowStatus(String workflowStatus) {
        this.workflowStatus = workflowStatus == null ? null : workflowStatus.trim();
    }
}