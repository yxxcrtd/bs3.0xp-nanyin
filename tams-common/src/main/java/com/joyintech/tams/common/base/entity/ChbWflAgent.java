package com.joyintech.tams.common.base.entity;

import java.util.Date;

public class ChbWflAgent extends ChbWflAgentKey {
    /**
     * 代办人类别  参照数据字典
     */
    private String agentType;

    /**
     * 代办人代码
     */
    private String agentId;

    /**
     * 有效结束日
     */
    private Date endDate;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 取得 代办人类别  参照数据字典
     * @return 代办人类别  参照数据字典
     */
    public String getAgentType() {
        return agentType;
    }

    /**
     * 设置 代办人类别  参照数据字典
     * @param agentType 代办人类别  参照数据字典
     */
    public void setAgentType(String agentType) {
        this.agentType = agentType == null ? null : agentType.trim();
    }

    /**
     * 取得 代办人代码
     * @return 代办人代码
     */
    public String getAgentId() {
        return agentId;
    }

    /**
     * 设置 代办人代码
     * @param agentId 代办人代码
     */
    public void setAgentId(String agentId) {
        this.agentId = agentId == null ? null : agentId.trim();
    }

    /**
     * 取得 有效结束日
     * @return 有效结束日
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置 有效结束日
     * @param endDate 有效结束日
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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