package com.joyintech.tams.valuation.report.common.infodisclosure.ftpDetail.dto;

import com.joyintech.tams.common.base.entity.RepFtpDetail;

public class FtpDetailDto extends RepFtpDetail{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String sysFilepath;
	
	// 报表类别
	private String reportCategory;

	public String getSysFilepath() {
		return sysFilepath;
	}

	public void setSysFilepath(String sysFilepath) {
		this.sysFilepath = sysFilepath;
	}

	public String getReportCategory() {
		return reportCategory;
	}

	public void setReportCategory(String reportCategory) {
		this.reportCategory = reportCategory;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
