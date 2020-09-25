package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class ChbReportSeqno extends ChbReportSeqnoKey {
    /**
     * 批次
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal importNo;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 取得 批次
     * @return 批次
     */
    public BigDecimal getImportNo() {
        return importNo;
    }

    /**
     * 设置 批次
     * @param importNo 批次
     */
    public void setImportNo(BigDecimal importNo) {
        this.importNo = importNo;
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