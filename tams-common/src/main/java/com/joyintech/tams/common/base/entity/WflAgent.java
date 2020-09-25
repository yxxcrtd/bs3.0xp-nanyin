package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class WflAgent extends WflAgentKey {
    /**
     * 代办人用户名称
     */
    private String agentName;

    /**
     * 有效结束日
     */
    private Date endDate;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 取得 代办人用户名称
     * @return 代办人用户名称
     */
    public String getAgentName() {
        return agentName;
    }

    /**
     * 设置 代办人用户名称
     * @param agentName 代办人用户名称
     */
    public void setAgentName(String agentName) {
        this.agentName = agentName == null ? null : agentName.trim();
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
    @JsonDeserialize(using = CustomDateDeserialize.class)
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