package com.joyintech.tams.valuation.batch.valscheme.dto;

import java.util.Date;

public class ValLayeringInfo {
	
	/**
	 * 账套代码
	 */
	private String booksetId;
	
	/**
	 * 业务明细代码
	 */
	private String busiId;
	
	/**
	 * 业务明细类型
	 */
	private String busiDetailType;
	
	/**
	 * 起息日
	 */
	private Date vDate;
	
	/**
	 * 分支序号
	 */
	private Long branch;
	
	/**
	 * 现金流代码
	 */
	private String cashId;
	
	/**
	 * 计息基础
	 */
	private String basis;

	public String getBooksetId() {
		return booksetId;
	}

	public void setBooksetId(String booksetId) {
		this.booksetId = booksetId;
	}

	public String getBusiId() {
		return busiId;
	}

	public void setBusiId(String busiId) {
		this.busiId = busiId;
	}

	public String getBusiDetailType() {
		return busiDetailType;
	}

	public void setBusiDetailType(String busiDetailType) {
		this.busiDetailType = busiDetailType;
	}

	public Date getvDate() {
		return vDate;
	}

	public void setvDate(Date vDate) {
		this.vDate = vDate;
	}

	public Long getBranch() {
		return branch;
	}

	public void setBranch(Long branch) {
		this.branch = branch;
	}

	public String getCashId() {
		return cashId;
	}

	public void setCashId(String cashId) {
		this.cashId = cashId;
	}

	public String getBasis() {
		return basis;
	}

	public void setBasis(String basis) {
		this.basis = basis;
	}
	
	
}
