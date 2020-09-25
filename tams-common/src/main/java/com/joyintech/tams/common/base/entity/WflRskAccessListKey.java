package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class WflRskAccessListKey extends BaseEntity {
    /**
     * 名单代码
     */
    private String listId;

    /**
     * 数据状态
     */
    private String workflowStatus;

    /**
     * 取得 名单代码
     * @return 名单代码
     */
    public String getListId() {
        return listId;
    }

    /**
     * 设置 名单代码
     * @param listId 名单代码
     */
    public void setListId(String listId) {
        this.listId = listId == null ? null : listId.trim();
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