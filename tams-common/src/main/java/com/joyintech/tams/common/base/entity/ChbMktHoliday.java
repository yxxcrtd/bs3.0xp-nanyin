package com.joyintech.tams.common.base.entity;

public class ChbMktHoliday extends ChbMktHolidayKey {
    /**
     * 是否节假日
     */
    private String isHoliday;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 经办人
     */
    private String agent;

    /**
     * 取得 是否节假日
     * @return 是否节假日
     */
    public String getIsHoliday() {
        return isHoliday;
    }

    /**
     * 设置 是否节假日
     * @param isHoliday 是否节假日
     */
    public void setIsHoliday(String isHoliday) {
        this.isHoliday = isHoliday == null ? null : isHoliday.trim();
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

    /**
     * 取得 部门代码
     * @return 部门代码
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * 设置 部门代码
     * @param deptCode 部门代码
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    /**
     * 取得 经办人
     * @return 经办人
     */
    public String getAgent() {
        return agent;
    }

    /**
     * 设置 经办人
     * @param agent 经办人
     */
    public void setAgent(String agent) {
        this.agent = agent == null ? null : agent.trim();
    }
}