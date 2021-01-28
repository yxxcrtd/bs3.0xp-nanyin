package com.joyintech.tams.invest.batch.prdopenplanupdate.dto;

import java.util.Date;

public class AccrDateOtherDto {
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
     * 首次回补日
     */
    private Date firstAnapleroticDate;
    /**
     * 回补结束日
     */
    private Date anapleroticEndDate;
    
    /**
     * 首次回补周期开始日
     */
    private Date firstAnapleroticCycleBegDate;
    
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
	public String getCashId() {
		return cashId;
	}
	public void setCashId(String cashId) {
		this.cashId = cashId;
	}
	public Date getFirstAnapleroticDate() {
		return firstAnapleroticDate;
	}
	public void setFirstAnapleroticDate(Date firstAnapleroticDate) {
		this.firstAnapleroticDate = firstAnapleroticDate;
	}
	public Date getAnapleroticEndDate() {
		return anapleroticEndDate;
	}
	public void setAnapleroticEndDate(Date anapleroticEndDate) {
		this.anapleroticEndDate = anapleroticEndDate;
	}
	public Date getFirstAnapleroticCycleBegDate() {
		return firstAnapleroticCycleBegDate;
	}
	public void setFirstAnapleroticCycleBegDate(Date firstAnapleroticCycleBegDate) {
		this.firstAnapleroticCycleBegDate = firstAnapleroticCycleBegDate;
	}
}
