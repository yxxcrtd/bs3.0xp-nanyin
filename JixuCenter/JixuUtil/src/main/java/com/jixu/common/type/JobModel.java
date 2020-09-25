package com.jixu.common.type;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/** 
 * @ClassName 		Wsdoc
 * @author  		Pan Wei 
 * @Date 			2017年5月11日 下午10:25:31 	
 * @Description		[]  
 */

public class JobModel<T> implements Serializable{
	
	private static final long serialVersionUID = -1515967014114783793L;

	private int jobID;
	
	// 设置Job名称
	private String jobName;
	
	private String jobGroup;
	
	private String jobStatus;
	
	private Class<T> jobClass;

	private String cronExp;
	
	private String desc;
	
	private List<Date> holidayList;
	
	private Date jobStartTime;

	public int getJobID() {
		return jobID;
	}

	public void setJobID(int jobID) {
		this.jobID = jobID;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCronExp() {
		return cronExp;
	}

	public void setCronExp(String cronExp) {
		this.cronExp = cronExp;
	}

	public Class<T> getJobClass() {
		return jobClass;
	}

	public void setJobClass(Class<T> jobClass) {
		this.jobClass = jobClass;
	}

	public List<Date> getHolidayList() {
		return holidayList;
	}

	public void setHolidayList(List<Date> holidayList) {
		this.holidayList = holidayList;
	}

	public Date getJobStartTime() {
		return jobStartTime;
	}

	public void setJobStartTime(Date jobStartTime) {
		this.jobStartTime = jobStartTime;
	}
}
