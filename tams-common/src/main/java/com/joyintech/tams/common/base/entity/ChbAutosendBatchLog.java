package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class ChbAutosendBatchLog extends BaseEntity {
    /**
     * 批处理批次
     */
    private String batchExeId;

    /**
     * 任务代码
     */
    private String taskId;

    /**
     * 批处理执行开始时间
     */
    private Date startTime;

    /**
     * 批处理执行结束时间
     */
    private Date endTime;

    /**
     * 批处理执行结果
     */
    private String batchResult;

    /**
     * 取得 批处理批次
     * @return 批处理批次
     */
    public String getBatchExeId() {
        return batchExeId;
    }

    /**
     * 设置 批处理批次
     * @param batchExeId 批处理批次
     */
    public void setBatchExeId(String batchExeId) {
        this.batchExeId = batchExeId == null ? null : batchExeId.trim();
    }

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
     * 取得 批处理执行开始时间
     * @return 批处理执行开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置 批处理执行开始时间
     * @param startTime 批处理执行开始时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 取得 批处理执行结束时间
     * @return 批处理执行结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置 批处理执行结束时间
     * @param endTime 批处理执行结束时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 取得 批处理执行结果
     * @return 批处理执行结果
     */
    public String getBatchResult() {
        return batchResult;
    }

    /**
     * 设置 批处理执行结果
     * @param batchResult 批处理执行结果
     */
    public void setBatchResult(String batchResult) {
        this.batchResult = batchResult == null ? null : batchResult.trim();
    }
}