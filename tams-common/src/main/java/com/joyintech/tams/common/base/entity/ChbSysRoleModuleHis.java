package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbSysRoleModuleHis extends BaseEntity {
    /**
     * ����
     */
    private String roleId;

    /**
     * ����
     */
    private Integer hisNo;

    /**
     * ����
     */
    private String pageId;

    /**
     * ����
     */
    private String elementId;

    /**
     * �����
     */
    private String workflowId;

    /**
     * 取得 ����
     * @return ����
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * 设置 ����
     * @param roleId ����
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    /**
     * 取得 ����
     * @return ����
     */
    public Integer getHisNo() {
        return hisNo;
    }

    /**
     * 设置 ����
     * @param hisNo ����
     */
    public void setHisNo(Integer hisNo) {
        this.hisNo = hisNo;
    }

    /**
     * 取得 ����
     * @return ����
     */
    public String getPageId() {
        return pageId;
    }

    /**
     * 设置 ����
     * @param pageId ����
     */
    public void setPageId(String pageId) {
        this.pageId = pageId == null ? null : pageId.trim();
    }

    /**
     * 取得 ����
     * @return ����
     */
    public String getElementId() {
        return elementId;
    }

    /**
     * 设置 ����
     * @param elementId ����
     */
    public void setElementId(String elementId) {
        this.elementId = elementId == null ? null : elementId.trim();
    }

    /**
     * 取得 �����
     * @return �����
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 �����
     * @param workflowId �����
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
    }
}