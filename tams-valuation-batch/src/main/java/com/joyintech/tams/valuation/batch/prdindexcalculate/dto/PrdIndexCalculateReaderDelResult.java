/*
 * 文件名：PrdIndexCalculateReaderDelResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年12月21日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.prdindexcalculate.dto;

import java.io.Serializable;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;

/**
 * 信息披露-产品指标计算(数据删除)-reader返回值
 * 
 * @author yangjian
 * @version 1.0 2018年12月21日
 * @see PrdIndexCalculateReaderDelResult
 * @since 1.0
 */
public class PrdIndexCalculateReaderDelResult extends NeedOperateEntity implements Serializable {

	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = 1L;

	public PrdIndexCalculateReaderDelResult(String taskId) {
		this.taskId = taskId;
	}

	public PrdIndexCalculateProcessorDelResult toResult() {
		return new PrdIndexCalculateProcessorDelResult(taskId);
	}

	/**
	 * 任务代码
	 */
	private String taskId;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

}
