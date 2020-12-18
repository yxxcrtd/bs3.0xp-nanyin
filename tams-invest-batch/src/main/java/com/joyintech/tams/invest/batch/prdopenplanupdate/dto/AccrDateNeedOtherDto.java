package com.joyintech.tams.invest.batch.prdopenplanupdate.dto;

import java.util.Date;

public class AccrDateNeedOtherDto {
	/**
	 * 现金流id
	 */
	private String cashId;
	/**
	 * 起息日
	 */
	private Date vdate; 
	/**
	 * 到期日
	 */
	private Date mdate; 
	/**
     * 首次计提日
     */
    private Date provisionFirstDay;
    /**
     * 计提结束日
     */
    private Date eAccrDate;
    
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
	public Date getProvisionFirstDay() {
		return provisionFirstDay;
	}
	public void setProvisionFirstDay(Date provisionFirstDay) {
		this.provisionFirstDay = provisionFirstDay;
	}
	public Date geteAccrDate() {
		return eAccrDate;
	}
	public void seteAccrDate(Date eAccrDate) {
		this.eAccrDate = eAccrDate;
	}
	public String getCashId() {
		return cashId;
	}
	public void setCashId(String cashId) {
		this.cashId = cashId;
	}
}
