package com.joyintech.tams.invest.batch.prdopenplanupdate.dto;

import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

public class NewFinOpenPlanDto extends BaseEntity {
	
	private String cashId; // 现金流代码
	private String openType;/*开放类型*/
	private Date openDateEndOld;
	  /**
     * 募集起始日
     */
    private Date openDateStrUn;

    /**
     * 募集结束日
     */
    private Date openDateEndUn;

    /**
     * 起息日
     */
    private Date openVdateUn;

    /**
     * 到期日
     */
    private Date openMdateUn;

    /**
     * 募集起始日
     */
    private Date openDateStr;
    
    /**
     * 募集结束日
     */
    private Date openDateEnd;

    /**
     * 起息日
     */
    private Date openVdate;

    /**
     * 到期日
     */
    private Date openMdate;
    /**
     * 净值日
     */
    private Date openNdate;
    
	public Date getOpenDateStrUn() {
		return openDateStrUn;
	}
	public void setOpenDateStrUn(Date openDateStrUn) {
		this.openDateStrUn = openDateStrUn;
	}
	public Date getOpenDateEndUn() {
		return openDateEndUn;
	}
	public void setOpenDateEndUn(Date openDateEndUn) {
		this.openDateEndUn = openDateEndUn;
	}
	public Date getOpenVdateUn() {
		return openVdateUn;
	}
	public void setOpenVdateUn(Date openVdateUn) {
		this.openVdateUn = openVdateUn;
	}
	public Date getOpenMdateUn() {
		return openMdateUn;
	}
	public void setOpenMdateUn(Date openMdateUn) {
		this.openMdateUn = openMdateUn;
	}
	public Date getOpenDateStr() {
		return openDateStr;
	}
	public void setOpenDateStr(Date openDateStr) {
		this.openDateStr = openDateStr;
	}
	public Date getOpenDateEnd() {
		return openDateEnd;
	}
	public void setOpenDateEnd(Date openDateEnd) {
		this.openDateEnd = openDateEnd;
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
	public Date getOpenNdate() {
		return openNdate;
	}
	public void setOpenNdate(Date openNdate) {
		this.openNdate = openNdate;
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
	public Date getOpenDateEndOld() {
		return openDateEndOld;
	}
	public void setOpenDateEndOld(Date openDateEndOld) {
		this.openDateEndOld = openDateEndOld;
	}

}
