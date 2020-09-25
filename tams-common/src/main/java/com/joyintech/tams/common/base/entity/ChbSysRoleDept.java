package com.joyintech.tams.common.base.entity;

public class ChbSysRoleDept extends ChbSysRoleDeptKey {
    /**
     * 工作流代码
     */
    private String workflowId;

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