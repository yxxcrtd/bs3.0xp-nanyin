package com.joyintech.tams.disclosure.home.dto;

import java.util.Date;

public class DisHomePageStat {

	private String statId;
	private String repId;
	private Date statDate;
	private int expectCounts;
	private String remark;
	public String getStatId() {
		return statId;
	}
	public void setStatId(String statId) {
		this.statId = statId;
	}
	public String getRepId() {
		return repId;
	}
	public void setRepId(String repId) {
		this.repId = repId;
	}
	public Date getStatDate() {
		return statDate;
	}
	public void setStatDate(Date statDate) {
		this.statDate = statDate;
	}
	public int getExpectCounts() {
		return expectCounts;
	}
	public void setExpectCounts(int expectCounts) {
		this.expectCounts = expectCounts;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
