package com.joyintech.tams.valuation.report.common.infodisclosure.reportDeal.dto;

public class ReportDealDto {
	
	/**
     * 产品编号
     */
    private String prodid;
	
    /**
     * 披露状态
     */
	private String revealStatus;
	
	/**
     * 文件类型
     */
	private String fileType;
	
	/**
     * 披露截止日
     */
	private String revealEndDate;
	

	
	/**
     * 生成文件名称
     */
    private String sysFilename;
    
    /**
     * 人工文件名称
     */
    private String filename;

	/**
     * 获取产品编号
     */
	public String getProdid() {
		return prodid;
	}

	/**
     * 设置产品编号
     */
	public void setProdid(String prodid) {
		this.prodid = prodid;
	}

	/**
     * 获取产品编号
     */
	public String getRevealStatus() {
		return revealStatus;
	}

	/**
     * 设置披露状态
     */
	public void setRevealStatus(String revealStatus) {
		this.revealStatus = revealStatus;
	}

	/**
     * 获取产品编号
     */
	public String getFileType() {
		return fileType;
	}

	/**
     * 设置文件类型
     */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	/**
     * 获取披露截止日
     */
	public String getRevealEndDate() {
		return revealEndDate;
	}

	/**
     * 设置披露截止日
     */
	public void setRevealEndDate(String revealEndDate) {
		this.revealEndDate = revealEndDate;
	}

	public String getSysFilename() {
		return sysFilename;
	}

	public void setSysFilename(String sysFilename) {
		this.sysFilename = sysFilename;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	
}
