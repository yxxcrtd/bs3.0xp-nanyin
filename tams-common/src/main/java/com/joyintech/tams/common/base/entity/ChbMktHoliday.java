package com.joyintech.tams.common.base.entity;

public class ChbMktHoliday extends ChbMktHolidayKey {
    /**
     * 是否节假日：0 工作日/1 节假日
     */
    private String isHoliday;

    /**
     * null
     */
    private String workflowId;

    /**
     * null
     */
    private String deptCode;

    /**
     * null
     */
    private String agent;

    /**
     * 取得 是否节假日：0 工作日/1 节假日
     * @return 是否节假日：0 工作日/1 节假日
     */
    public String getIsHoliday() {
        return isHoliday;
    }

    /**
     * 设置 是否节假日：0 工作日/1 节假日
     * @param isHoliday 是否节假日：0 工作日/1 节假日
     */
    public void setIsHoliday(String isHoliday) {
        this.isHoliday = isHoliday == null ? null : isHoliday.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 null
     * @param workflowId null
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * 设置 null
     * @param deptCode null
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getAgent() {
        return agent;
    }

    /**
     * 设置 null
     * @param agent null
     */
    public void setAgent(String agent) {
        this.agent = agent == null ? null : agent.trim();
    }
}