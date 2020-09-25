package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbAutoSendBatchExeTimeKey extends BaseEntity {
    /**
     * 任务代码
     */
    private String taskId;

    /**
     * 任务执行时点（时分四位字符串）
     */
    private String taskExeTime;

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
     * 取得 任务执行时点（时分四位字符串）
     * @return 任务执行时点（时分四位字符串）
     */
    public String getTaskExeTime() {
        return taskExeTime;
    }

    /**
     * 设置 任务执行时点（时分四位字符串）
     * @param taskExeTime 任务执行时点（时分四位字符串）
     */
    public void setTaskExeTime(String taskExeTime) {
        this.taskExeTime = taskExeTime == null ? null : taskExeTime.trim();
    }
}