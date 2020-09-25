package com.joyintech.tams.disclosure.information.manage.estimatereveal.dto;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.common.base.entity.RepExpectedLog;

@SuppressWarnings("unused")
public class EstimateReveal001Dto extends RepExpectedLog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String reportDate;
	private String jobName;
	private String repTypeName;
	private String revealEndDateStr;
	public String getReportDate() {
		return reportDate;
	}
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getRepTypeName() {
		return repTypeName;
	}
	public void setRepTypeName(String repTypeName) {
		this.repTypeName = repTypeName;
	}
	public String getRevealEndDateStr() {
		return DateUtil.format(this.getRevealEndDate());
	}
	public void setRevealEndDateStr(String revealEndDateStr) {
		this.revealEndDateStr = revealEndDateStr;
	}
}
