package com.joyintech.tams.svreport.interf.dis.basiccalendardata.dto;

import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

public class DisBasicCalendarData extends BaseEntity{	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 交易日历代码
     */
    private String tradeCalendarId; 
	/**
     * 日历编号
     */
    private String calendarId;  

	public String getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}

	public String getTradeCalendarId() {
		return tradeCalendarId;
	}

	public void setTradeCalendarId(String tradeCalendarId) {
		this.tradeCalendarId = tradeCalendarId;
	}
}