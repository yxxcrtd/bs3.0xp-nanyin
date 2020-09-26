package com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valdataimport.dto;

import com.joyintech.framework.base.BaseEntity;

/**
 * 接口任务表
 * @author leipeng
 * @version 1.0 2018年8月2日
 * @see ValPublicDataImport001Dto
 * @since 1.0
 */
public class ValPublicDataImport001Dto extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String groupCName;
	
	private String groupEName;

	private String taskGroupCName;
	
	private String taskGroupEName;

	private String taskGroupOrderNo;

	private String tmpCName;

	private String tmpEName;
	
	

	public String getGroupCName() {
		return groupCName;
	}

	public void setGroupCName(String groupCName) {
		this.groupCName = groupCName;
	}

	public String getGroupEName() {
		return groupEName;
	}

	public void setGroupEName(String groupEName) {
		this.groupEName = groupEName;
	}

	public String getTaskGroupCName() {
		return taskGroupCName;
	}

	public void setTaskGroupCName(String taskGroupCName) {
		this.taskGroupCName = taskGroupCName;
	}

	public String getTaskGroupEName() {
		return taskGroupEName;
	}

	public void setTaskGroupEName(String taskGroupEName) {
		this.taskGroupEName = taskGroupEName;
	}

	public String getTaskGroupOrderNo() {
		return taskGroupOrderNo;
	}

	public void setTaskGroupOrderNo(String taskGroupOrderNo) {
		this.taskGroupOrderNo = taskGroupOrderNo;
	}

	public String getTmpCName() {
		return tmpCName;
	}

	public void setTmpCName(String tmpCName) {
		this.tmpCName = tmpCName;
	}

	public String getTmpEName() {
		return tmpEName;
	}

	public void setTmpEName(String tmpEName) {
		this.tmpEName = tmpEName;
	}

	

}
