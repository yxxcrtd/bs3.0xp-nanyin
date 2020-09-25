package com.joyintech.tams.common.base.entity;

public class ChbAutoSendBatchExeTime extends ChbAutoSendBatchExeTimeKey {
    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 取得 任务名称
     * @return 任务名称
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * 设置 任务名称
     * @param taskName 任务名称
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
    }
}