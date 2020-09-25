package com.jixu.report.service;

/** 
 * @ClassName 		ReportEntity
 * @author  		Pan Wei 
 * @Date 			2016年12月16日 上午10:33:47 	
 * @Description		[]  
 */

public class ReportEntity {
	private String reportName; // 要调用的jasperreports的模板文件名(不包括后缀)，该文件名必须以-report结尾

	private String format; // 显示格式：html、pdf、xls、csv

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
}
