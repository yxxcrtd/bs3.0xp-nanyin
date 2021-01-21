package com.joyintech.tams.invest.batch.prdopenplanupdate.dto;

import java.util.Date;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;

/**
 * 批处理-市场日历接入后更新产品开放计划 dto
 */
public class PrdOpenPlanUpdateDto extends NeedOperateEntity{
	
	private String planType;//计划类型		
	private String calendarId; // 交易日历	
	private String cashId; // 现金流代码	
	
	/*******************开放计划字段 start*********************/
	private String openType;/*开放类型*/  	
	private Date openDateStr; // 开放计划开始日
	private Date openDateEnd; // 开放计划结束日
	private Date openVdate;/*起息日*/
	private Date openMdate;/*开放确认日*/   
	private Date openNdate;/*净值日*/
	private String traRule;/*营业日准则*/
	/*******************开放计划字段 end*********************/
	
	/*******************分红/付息/付费计划字段 start*********************/
	private Date vdateUnadjust;/*未调整的计算开始日*/
	private Date mdateUnadjust;/*未调整的计算结束日*/
	private Date payDateUnadjust;/*未调整的计划支付日*/
    private Date vdate;/*计息区间开始日*/ /*付费开始日*/
    private Date mdate;/*计息区间结束日*/ /*付费结束日*/
    private Date payDate;    /*支付日*/ 
    private String payTdayRule;// 支付日营业日准则  付费营业日准则
    private String endTdayRule;// 截止日营业日准则   计提营业日准则
	/*******************分红/付息/付费计划字段 end*********************/
    
    /*******************业绩报酬计提/回补计划字段 start*********************/
	private Date accrDate;/*计提日*//*回补日*/
	private Date accrStrDate;/*区间开始日*//*回补周期开始日*/
	private Date accrEndDate;/*区间结束日*//*回补周期结束日*/
    private Date valDate; /*调整前结束日*/   
    private String accrTadyRule; /*营业日准则*/ 
	/*******************业绩报酬计提/回补计划字段 end*********************/
	
    
	public String getPlanType() {
		return planType;
	}
	public void setPlanType(String planType) {
		this.planType = planType;
	}

	public Date getVdate() {
		return vdate;
	}
	public void setVdate(Date vdate) {
		this.vdate = vdate;
	}
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
	public String getOpenType() {
		return openType;
	}
	public void setOpenType(String openType) {
		this.openType = openType;
	}
	public Date getOpenVdate() {
		return openVdate;
	}
	public void setOpenVdate(Date openVdate) {
		this.openVdate = openVdate;
	}
	public Date getOpenMdate() {
		return openMdate;
	}
	public void setOpenMdate(Date openMdate) {
		this.openMdate = openMdate;
	}
	public Date getOpenDateEnd() {
		return openDateEnd;
	}
	public void setOpenDateEnd(Date openDateEnd) {
		this.openDateEnd = openDateEnd;
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

	public Date getOpenDateStr() {
		return openDateStr;
	}
	public void setOpenDateStr(Date openDateStr) {
		this.openDateStr = openDateStr;
	}
	public Date getOpenNdate() {
		return openNdate;
	}
	public void setOpenNdate(Date openNdate) {
		this.openNdate = openNdate;
	}
	public String getTraRule() {
		return traRule;
	}
	public void setTraRule(String traRule) {
		this.traRule = traRule;
	}
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
