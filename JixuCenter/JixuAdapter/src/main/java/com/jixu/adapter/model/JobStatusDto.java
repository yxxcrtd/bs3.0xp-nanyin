package com.jixu.adapter.model;

public class JobStatusDto {
	
	/**
	 * ID
	 */
	private int sourceID;
	
	/**
	 * 任务名称
	 */
	private String jobName;
	
	/**
	 * 任务描述
	 */
	private String jobDesc;
	
	/**
	 * 数据源名称
	 */
	private String dataConn;
	
	/**
	 * 文件格式
	 */
	private String fileFormat;
	
	/**
	 * 上次运行开始时间
	 */
	private String lastStartTime;
	
	/**
	 * 上次运行结束时间
	 */
	private String lastEndTime;
	
	/**
	 * 运行状态
	 */
	private String jobStatus;
	
	/**
	 * 下次运行开始时间
	 */
	private String nextStartTime;
	
	/**
	 * 上次运行是否成功	成功/失败
	 */
	private String lastStatus;
	
	/**
	 * 依赖的适配器
	 */
	private String depenceAdapter;

	public int getSourceID() {
		return sourceID;
	}

	public void setSourceID(int sourceID) {
		this.sourceID = sourceID;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobDesc() {
		return jobDesc;
	}

	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}

	public String getDataConn() {
		return dataConn;
	}

	public void setDataConn(String dataConn) {
		this.dataConn = dataConn;
	}

	public String getLastStartTime() {
		return lastStartTime;
	}

	public void setLastStartTime(String lastStartTime) {
		this.lastStartTime = lastStartTime;
	}

	public String getLastEndTime() {
		return lastEndTime;
	}

	public void setLastEndTime(String lastEndTime) {
		this.lastEndTime = lastEndTime;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getNextStartTime() {
		return nextStartTime;
	}

	public void setNextStartTime(String nextStartTime) {
		this.nextStartTime = nextStartTime;
	}

	public String getLastStatus() {
		return lastStatus;
	}

	public void setLastStatus(String lastStatus) {
		this.lastStatus = lastStatus;
	}

	public String getDepenceAdapter() {
		return depenceAdapter;
	}

	public void setDepenceAdapter(String depenceAdapter) {
		this.depenceAdapter = depenceAdapter;
	}

	public String getFileFormat() {
		return fileFormat;
	}

	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}
	
	
}
