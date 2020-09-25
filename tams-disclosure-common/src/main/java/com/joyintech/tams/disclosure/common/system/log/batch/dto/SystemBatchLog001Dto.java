/*
 * 文件名：SystemBatchLog001Dto.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：何阳阳
 * 修改时间：2017年12月4日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.disclosure.common.system.log.batch.dto;

import java.util.Date;

import com.joyintech.tams.disclosure.common.common.base.TableRow;


/**
 * 批处理日志列表显示用DTO
 * 
 * @author 何阳阳
 * @version 1.0 2017年12月4日
 * @see SystemBatchLog001Dto
 * @since 1.0
 */
public class SystemBatchLog001Dto extends TableRow {

    /**
     * 批次编号
     */
    private String batchId;

    /**
     * 任务编号
     */
    private String taskId;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 执行状态
     */
    private String taskStatus;

    /**
     * 开始执行时间
     */
    private Date startTime;

    /**
     * 结束执行时间
     */
    private Date endTime;

    /**
     * 业务起始日期
     */
    private Date busiStart;

    /**
     * 业务截止日期
     */
    private Date busiEnd;

    /**
     * 执行结果
     */
    private String taskResult;

    /**
     * 提交任务的操作员
     */
    private String taskAssign;

    /**
     * 启动模式
     */
    private String startModule;

    /**
     * 优先级
     */
    private String taskPriority;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 获取 batchId
     * 
     * @return batchId.
     */
    public String getBatchId() {
        return batchId;
    }

    /**
     * 设置 batchId
     * 
     * @param batchId batchId
     */
    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    /**
     * 获取 taskId
     * 
     * @return taskId.
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * 设置 taskId
     * 
     * @param taskId taskId
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     * 获取 taskName
     * 
     * @return taskName.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * 设置 taskName
     * 
     * @param taskName taskName
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * 获取 taskStatus
     * 
     * @return taskStatus.
     */
    public String getTaskStatus() {
        return taskStatus;
    }

    /**
     * 设置 taskStatus
     * 
     * @param taskStatus taskStatus
     */
    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    /**
     * 获取 startTime
     * 
     * @return startTime.
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置 startTime
     * 
     * @param startTime startTime
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取 endTime
     * 
     * @return endTime.
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置 endTime
     * 
     * @param endTime endTime
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取 busiStart
     * 
     * @return busiStart.
     */
    public Date getBusiStart() {
        return busiStart;
    }

    /**
     * 设置 busiStart
     * 
     * @param busiStart busiStart
     */
    public void setBusiStart(Date busiStart) {
        this.busiStart = busiStart;
    }

    /**
     * 获取 busiEnd
     * 
     * @return busiEnd.
     */
    public Date getBusiEnd() {
        return busiEnd;
    }

    /**
     * 设置 busiEnd
     * 
     * @param busiEnd busiEnd
     */
    public void setBusiEnd(Date busiEnd) {
        this.busiEnd = busiEnd;
    }

    /**
     * 获取 taskResult
     * 
     * @return taskResult.
     */
    public String getTaskResult() {
        return taskResult;
    }

    /**
     * 设置 taskResult
     * 
     * @param taskResult taskResult
     */
    public void setTaskResult(String taskResult) {
        this.taskResult = taskResult;
    }

    /**
     * 获取 taskAssign
     * 
     * @return taskAssign.
     */
    public String getTaskAssign() {
        return taskAssign;
    }

    /**
     * 设置 taskAssign
     * 
     * @param taskAssign taskAssign
     */
    public void setTaskAssign(String taskAssign) {
        this.taskAssign = taskAssign;
    }

    /**
     * 获取 startModule
     * 
     * @return startModule.
     */
    public String getStartModule() {
        return startModule;
    }

    /**
     * 设置 startModule
     * 
     * @param startModule startModule
     */
    public void setStartModule(String startModule) {
        this.startModule = startModule;
    }

    /**
     * 获取 taskPriority
     * 
     * @return taskPriority.
     */
    public String getTaskPriority() {
        return taskPriority;
    }

    /**
     * 设置 taskPriority
     * 
     * @param taskPriority taskPriority
     */
    public void setTaskPriority(String taskPriority) {
        this.taskPriority = taskPriority;
    }

    /**
     * 获取 createTime
     * 
     * @return createTime.
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置 createTime
     * 
     * @param createTime createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
