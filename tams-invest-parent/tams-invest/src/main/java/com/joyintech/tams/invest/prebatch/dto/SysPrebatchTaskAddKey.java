package com.joyintech.tams.invest.prebatch.dto;

public class SysPrebatchTaskAddKey {
    /**
     * 任务组id
     */
    private String groupId;

    /**
     * 批处理任务id
     */
    private String taskId;

    /**
     * 任务组id
     * @return GROUP_ID 任务组id
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * 任务组id
     * @param groupId 任务组id
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    /**
     * 批处理任务id
     * @return TASK_ID 批处理任务id
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * 批处理任务id
     * @param taskId 批处理任务id
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }
}