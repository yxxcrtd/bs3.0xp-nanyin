package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class ChbSysBatchLogKey extends BaseEntity {
    /**
     * 任务代码
     */
    private String taskId;

    /**
     * 开始时间
     */
    private Date startTime;

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
     * 取得 开始时间
     * @return 开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置 开始时间
     * @param startTime 开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}