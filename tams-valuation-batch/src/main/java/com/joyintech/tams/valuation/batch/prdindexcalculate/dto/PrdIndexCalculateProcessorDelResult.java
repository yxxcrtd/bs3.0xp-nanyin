/*
 * 文件名：PrdIndexCalculateProcessorDelResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年12月21日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.prdindexcalculate.dto;

import java.io.Serializable;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;

/**
 * 信息披露-产品指标计算(数据删除)-processor返回值
 * @author yangjian
 * @version 1.0 2018年12月21日
 * @see PrdIndexCalculateProcessorDelResult
 * @since 1.0
 */
public class PrdIndexCalculateProcessorDelResult extends NeedToWriteEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public PrdIndexCalculateProcessorDelResult(String taskId) {
		this.taskId = taskId;
	}

	/**
	 * 任务代码
	 */
	private String taskId;

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
	 * @param taskId
	 *            taskId
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

}
