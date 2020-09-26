/*
 * 文件名：AutBatchProResultQuery001Dto.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：zuoyinyi
 * 修改时间：2017年11月14日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.report.system.autbatchproresultquery.dto;

import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

/**
 * 自动报送批处理结果查询dto
 * 
 * @author zuoyinyi
 * @version 1.0 2017年11月14日
 * @see AutBatchProResultQuery001Dto
 * @since 1.0
 */
public class AutBatchProResultQuery001Dto extends BaseEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1879496081761660933L;

	/**
     * 任务代码
     */
    private String taskId;

    /**
     * 任务名称
     */
    private String taskName;

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
     * 获取 batchResult
     * @return batchResult.
     */
    public String getBatchResult() {
        return batchResult;
    }

    /**
     * 设置 batchResult
     * @param batchResult batchResult
     */
    public void setBatchResult(String batchResult) {
        this.batchResult = batchResult;
    }
}
