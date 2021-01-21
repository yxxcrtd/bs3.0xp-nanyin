package com.joyintech.tams.invest.batch.prdopenplanupdate.dto;

import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

public class NewFinFloatPlanDto  extends BaseEntity{
	private String cashId; // 现金流代码	
	private Date accrDateOld;
	private Date accrDate;/*计提日*/
	private Date accrStrDate;/*区间开始日*/
	private Date accrEndDate;/*区间结束日*/
    private Date valDate; /*调整前结束日*/
	public Date getAccrDateOld() {
		return accrDateOld;
	}
	public void setAccrDateOld(Date accrDateOld) {
		this.accrDateOld = accrDateOld;
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
   

}
