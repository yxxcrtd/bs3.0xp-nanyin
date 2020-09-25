package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class BokBatchTaskLinkKey extends BaseEntity {
    /**
     * 任务代码
     */
    private String taskId;

    /**
     * 前序任务代码
     */
    private String pTaskId;

    /**
     * 取得 任务代码
     * @return 任务代码
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * 设置 任务代码
     * @param taskId 任务代码
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    /**
     * 取得 前序任务代码
     * @return 前序任务代码
     */
    public String getpTaskId() {
        return pTaskId;
    }

    /**
     * 设置 前序任务代码
     * @param pTaskId 前序任务代码
     */
    public void setpTaskId(String pTaskId) {
        this.pTaskId = pTaskId == null ? null : pTaskId.trim();
    }
}