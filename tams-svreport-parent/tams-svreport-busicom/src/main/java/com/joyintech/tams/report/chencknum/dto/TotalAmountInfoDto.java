package com.joyintech.tams.report.chencknum.dto;

import java.util.Date;



/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author Administrator
 * @version 1.0 2017年9月15日
 * @see TotalAmountInfoDto
 * @since 1.0
 */
public class TotalAmountInfoDto {
	private String  id;
	
	private String  reportDate;
	
	private String  interfaceType;
	
	private String  fileName;
	
	private String  fileAmount;
	
	private String  fileCount;
	
	private String  fileInData;
	
	private String  isEqual;
	
	private String  haldFlag;
	
	private String  amount;
	
	private String  amountReport;
	
	private Date  updateTime;
	
	private Date  createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getInterfaceType() {
		return interfaceType;
	}

	public void setInterfaceType(String interfaceType) {
		this.interfaceType = interfaceType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileAmount() {
		return fileAmount;
	}

	public void setFileAmount(String fileAmount) {
		this.fileAmount = fileAmount;
	}

	public String getFileCount() {
		return fileCount;
	}

	public void setFileCount(String fileCount) {
		this.fileCount = fileCount;
	}

	public String getFileInData() {
		return fileInData;
	}

	public void setFileInData(String fileInData) {
		this.fileInData = fileInData;
	}

	public String getIsEqual() {
		return isEqual;
	}

	public void setIsEqual(String isEqual) {
		this.isEqual = isEqual;
	}

	public String getHaldFlag() {
		return haldFlag;
	}

	public void setHaldFlag(String haldFlag) {
		this.haldFlag = haldFlag;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getAmountReport() {
		return amountReport;
	}

	public void setAmountReport(String amountReport) {
		this.amountReport = amountReport;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


}
