package com.joyintech.tams.invest.batch.prdopenplanupdate.relyingonresources.dto;

import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

public class BussNewFinPayPlanDto  extends BaseEntity {
	private String cashId; // 现金流代码
	private String calendarId; // 交易日历
	private Date vdateUnadjust;/*未调整的计算开始日*/
	private Date mdateUnadjust;/*未调整的计算结束日*/
	private Date payDateUnadjust;/*未调整的计划支付日*/
    private Date vdate;/*付费开始日*/
    private Date mdate;/*付费结束日*/
    private Date payDate;  /*支付日*/ 
    private String payTdayRule;//付费营业日准则
    private String endTdayRule;//计提营业日准则
	public Date getVdateUnadjust() {
		return vdateUnadjust;
	}
	public void setVdateUnadjust(Date vdateUnadjust) {
		this.vdateUnadjust = vdateUnadjust;
	}
	public Date getMdateUnadjust() {
		return mdateUnadjust;
	}
	public void setMdateUnadjust(Date mdateUnadjust) {
		this.mdateUnadjust = mdateUnadjust;
	}
	public Date getPayDateUnadjust() {
		return payDateUnadjust;
	}
	public void setPayDateUnadjust(Date payDateUnadjust) {
		this.payDateUnadjust = payDateUnadjust;
	}
	public Date getVdate() {
		return vdate;
	}
	public void setVdate(Date vdate) {
		this.vdate = vdate;
	}
	public Date getMdate() {
		return mdate;
	}
	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public String getPayTdayRule() {
		return payTdayRule;
	}
	public void setPayTdayRule(String payTdayRule) {
		this.payTdayRule = payTdayRule;
	}
	public String getEndTdayRule() {
		return endTdayRule;
	}
	public void setEndTdayRule(String endTdayRule) {
		this.endTdayRule = endTdayRule;
	}
	public String getCashId() {
		return cashId;
	}
	public void setCashId(String cashId) {
		this.cashId = cashId;
	}
	public String getCalendarId() {
		return calendarId;
	}
	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}

}
