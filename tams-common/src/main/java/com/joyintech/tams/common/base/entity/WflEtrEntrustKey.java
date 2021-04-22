package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class WflEtrEntrustKey extends BaseEntity {
    /**
     * 委托代码
     */
    private String entrustId;

    /**
     * 流程状态
     */
    private String workflowStatus;

    /**
     * 取得 委托代码
     * @return 委托代码
     */
    public String getEntrustId() {
        return entrustId;
    }

    /**
     * 设置 委托代码
     * @param entrustId 委托代码
     */
    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId == null ? null : entrustId.trim();
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