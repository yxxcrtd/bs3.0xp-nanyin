package com.jixu.job.obj;

import java.io.Serializable;
import java.util.Date;

public class JobParam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5751024858098306829L;
	
	private String portfolio;
	private Date date;
	private String cusip = ""; //为单独计算某一个Cusip接口。
	private int userID;
	
	public String getCusip() {
		return cusip;
	}
	public void setCusip(String cusip) {
		this.cusip = cusip;
	}
	public String getPortfolio() {
		return portfolio;
	}
	public void setPortfolio(String portfolio) {
		this.portfolio = portfolio;
	}
	
	/**
	 * yyyymmdd格式
	 * @return
	 */
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * 获取 userID
	 */
	
	public int getUserID() {
		return userID;
	}
	/**
	 * 设置 userID
	 */
	
	public void setUserID(int userID) {
		this.userID = userID;
	}
}
