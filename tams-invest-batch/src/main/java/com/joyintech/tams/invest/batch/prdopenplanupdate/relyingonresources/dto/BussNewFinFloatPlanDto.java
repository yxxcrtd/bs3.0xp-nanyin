package com.joyintech.tams.invest.batch.prdopenplanupdate.relyingonresources.dto;

import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

public class BussNewFinFloatPlanDto  extends BaseEntity {
	private String calendarId; // 交易日历	
	private String cashId; // 现金流代码	
	private Date accrDate;/*计提日*/
	private Date accrStrDate;/*区间开始日*/
	private Date accrEndDate;/*区间结束日*/
    private Date valDate; /*调整前结束日*/   
    private String accrTadyRule; /*营业日准则*/
	public String getCalendarId() {
		return calendarId;
	}
	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}
	public String getCashId() {
		return cashId;
	}
	public void setCashId(String cashId) {
		this.cashId = cashId;
	}
	public Date getAccrDate() {
		return accrDate;
	}
	public void setAccrDate(Date accrDate) {
		this.accrDate = accrDate;
	}
	public Date getAccrStrDate() {
		return accrStrDate;
	}
	public void setAccrStrDate(Date accrStrDate) {
		this.accrStrDate = accrStrDate;
	}
	public Date getAccrEndDate() {
		return accrEndDate;
	}
	public void setAccrEndDate(Date accrEndDate) {
		this.accrEndDate = accrEndDate;
	}
	public Date getValDate() {
		return valDate;
	}
	public void setValDate(Date valDate) {
		this.valDate = valDate;
	}
	public String getAccrTadyRule() {
		return accrTadyRule;
	}
	public void setAccrTadyRule(String accrTadyRule) {
		this.accrTadyRule = accrTadyRule;
	}
}
