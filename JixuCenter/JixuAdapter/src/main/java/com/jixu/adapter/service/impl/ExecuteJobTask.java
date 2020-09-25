package com.jixu.adapter.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

public class ExecuteJobTask implements Runnable{
	
	private int userid;
	
	private int sourceid;
	
	private Date asOfDate;
	
	@Autowired
	private DataServiceCenter interafaceDataCenter;
	
	public ExecuteJobTask(DataServiceCenter interafaceDataCenter, int userid, int sourceid) {
		this.setUserid(userid);
		this.setSourceid(sourceid);
		this.setInterafaceDataCenter(interafaceDataCenter);
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getSourceid() {
		return sourceid;
	}

	public void setSourceid(int sourceid) {
		this.sourceid = sourceid;
	}

	public Date getAsOfDate() {
		return asOfDate;
	}

	public void setAsOfDate(Date asOfDate) {
		this.asOfDate = asOfDate;
	}

	public DataServiceCenter getInterafaceDataCenter() {
		return interafaceDataCenter;
	}

	public void setInterafaceDataCenter(DataServiceCenter interafaceDataCenter) {
		this.interafaceDataCenter = interafaceDataCenter;
	}
	
	public void run() {
		getInterafaceDataCenter().importData(userid, sourceid, new Date(), 0);
	}
}
