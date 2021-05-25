package com.joyintech.tams.report.interf.common.dto;


public class JobContainer{
	/**
	 * 任务执行状态
	 */
	private String jobStatus;
	
	/**
	 * 批处理执行消息
	 */
	private String jobMsg;
	
	/**
	 * 任务运行时编号
	 */
	private String instanceid;
	
	

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getJobMsg() {
		return jobMsg;
	}

	public void setJobMsg(String jobMsg) {
		this.jobMsg = jobMsg;
	}

	public String getInstanceid() {
		return instanceid;
	}

	public void setInstanceid(String instanceid) {
		this.instanceid = instanceid;
	}
}
