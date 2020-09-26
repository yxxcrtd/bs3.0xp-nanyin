package com.joyintech.tams.svreport.interf.dis.calendarmanagement.dto;


import com.joyintech.framework.base.BaseEntity;

public class DisCalendarManagement extends BaseEntity{	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 日历编号
     */
    private String calendarId;  
	/**
     * 日历名称
     */
    private String calendarName;  
	/**
     * 第三方日历编号，比如万得的交易场所
     */
    private String otherId;  
	/**
     * 是否交易日历，交易日历为多市场日历复合而成
     */
    private String isTradeCalendar;  
	/**
     * 备注
     */
    private String remark;
    

	public String getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}

	public String getCalendarName() {
		return calendarName;
	}

	public void setCalendarName(String calendarName) {
		this.calendarName = calendarName;
	}

	public String getOtherId() {
		return otherId;
	}

	public void setOtherId(String otherId) {
		this.otherId = otherId;
	}

	public String getIsTradeCalendar() {
		return isTradeCalendar;
	}

	public void setIsTradeCalendar(String isTradeCalendar) {
		this.isTradeCalendar = isTradeCalendar;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}