package com.jixu.job.obj;

/** 
 * @ClassName 		AcctDCBalance
 * @author  		Pan Wei 
 * @Date 			2018年4月30日 下午1:55:30 	
 * @Description		[]  
 */

public class AcctDCBalance {
	//科目号
	private String acctnum;
	
	//证券id
	private String secid;
	
	//借方原币余额
	private double drLocalAmt;
	
	//贷方原币余额
	private double crLocalAmt;
	
	//除去结转的借方原币余额
	private double drLocalAmtUnSet;
	
	//除去结转的贷方原币余额
	private double crLocalAmtUnSet;

	//借方本币余额
	private double drFuncAmt;
		
	//贷方本币余额
	private double crFuncAmt;
	
	//除去结转的借方本币余额
	private double drFuncAmtUnSet;
		
	//除去结转的贷方本币余额
	private double crFuncAmtUnSet;
	
	//当天借方原币
	private double drLocalAmtToday;
		
	//当天贷方原币
	private double crLocalAmtToday;
	
	//当天除去结转的借方原币
	private double drLocalAmtUnSetToday;
		
	//当天除去结转的贷方原币
	private double crLocalAmtUnSetToday;

	//当天借方本币
	private double drFuncAmtToday;
			
	//当天贷方本币
	private double crFuncAmtToday;
	
	//当天除去结转的借方本币
	private double drFuncAmtUnSetToday;
			
	//当天除去结转的贷方本币
	private double crFuncAmtUnSetToday;
	
	//前一天借方原币余额
	private double drLocalAmtLastDay;
		
	//前一天贷方原币余额
	private double crLocalAmtLastDay;
		
	//前一天除去结转的借方原币余额
	private double drLocalAmtUnSetLastDay;
		
	//前一天除去结转的贷方原币余额
	private double crLocalAmtUnSetLastDay;

	//前一天借方本币余额
	private double drFuncAmtLastDay;
		
	//前一天贷方本币余额
	private double crFuncAmtLastDay;
	
	//前一天除去结转的借方本币余额
	private double drFuncAmtUnSetLastDay;
		
	//前一天除去结转的贷方本币余额
	private double crFuncAmtUnSetLastDay;
	
	public String getAcctnum() {
		return acctnum;
	}

	public void setAcctnum(String acctnum) {
		this.acctnum = acctnum;
	}

	/**
	 * 借方原币余额
	 * @return
	 */
	public double getDrLocalAmt() {
		return drLocalAmt;
	}

	public void setDrLocalAmt(double drLocalAmt) {
		this.drLocalAmt = drLocalAmt;
	}

	/**
	 * 贷方原币余额
	 * @return
	 */
	public double getCrLocalAmt() {
		return crLocalAmt;
	}

	public void setCrLocalAmt(double crLocalAmt) {
		this.crLocalAmt = crLocalAmt;
	}

	/**
	 * 借方原币余额-排除结转凭证
	 * @return
	 */
	public double getDrLocalAmtUnSet() {
		return drLocalAmtUnSet;
	}

	public void setDrLocalAmtUnSet(double drLocalAmtUnSet) {
		this.drLocalAmtUnSet = drLocalAmtUnSet;
	}

	/**
	 * 贷方原币余额-排除结转凭证
	 * @return
	 */
	public double getCrLocalAmtUnSet() {
		return crLocalAmtUnSet;
	}

	public void setCrLocalAmtUnSet(double crLocalAmtUnSet) {
		this.crLocalAmtUnSet = crLocalAmtUnSet;
	}

	/**
	 * 借方本币余额
	 * @return
	 */
	public double getDrFuncAmt() {
		return drFuncAmt;
	}

	public void setDrFuncAmt(double drFuncAmt) {
		this.drFuncAmt = drFuncAmt;
	}

	/**
	 * 贷方本币余额
	 * @return
	 */
	public double getCrFuncAmt() {
		return crFuncAmt;
	}

	public void setCrFuncAmt(double crFuncAmt) {
		this.crFuncAmt = crFuncAmt;
	}

	/**
	 * 借方本币余额-排除结转凭证
	 * @return
	 */
	public double getDrFuncAmtUnSet() {
		return drFuncAmtUnSet;
	}

	public void setDrFuncAmtUnSet(double drFuncAmtUnSet) {
		this.drFuncAmtUnSet = drFuncAmtUnSet;
	}

	/**
	 * 贷方本币余额-排除结转凭证
	 * @return
	 */
	public double getCrFuncAmtUnSet() {
		return crFuncAmtUnSet;
	}

	public void setCrFuncAmtUnSet(double crFuncAmtUnSet) {
		this.crFuncAmtUnSet = crFuncAmtUnSet;
	}

	/**
	 * 证券ID
	 * @return
	 */
	public String getSecid() {
		return secid;
	}

	public void setSecid(String secid) {
		this.secid = secid;
	}

	public double getDrLocalAmtToday() {
		return drLocalAmtToday;
	}

	public void setDrLocalAmtToday(double drLocalAmtToday) {
		this.drLocalAmtToday = drLocalAmtToday;
	}

	public double getCrLocalAmtToday() {
		return crLocalAmtToday;
	}

	public void setCrLocalAmtToday(double crLocalAmtToday) {
		this.crLocalAmtToday = crLocalAmtToday;
	}

	public double getDrLocalAmtUnSetToday() {
		return drLocalAmtUnSetToday;
	}

	public void setDrLocalAmtUnSetToday(double drLocalAmtUnSetToday) {
		this.drLocalAmtUnSetToday = drLocalAmtUnSetToday;
	}

	public double getCrLocalAmtUnSetToday() {
		return crLocalAmtUnSetToday;
	}

	public void setCrLocalAmtUnSetToday(double crLocalAmtUnSetToday) {
		this.crLocalAmtUnSetToday = crLocalAmtUnSetToday;
	}

	public double getDrFuncAmtToday() {
		return drFuncAmtToday;
	}

	public void setDrFuncAmtToday(double drFuncAmtToday) {
		this.drFuncAmtToday = drFuncAmtToday;
	}

	public double getCrFuncAmtToday() {
		return crFuncAmtToday;
	}

	public void setCrFuncAmtToday(double crFuncAmtToday) {
		this.crFuncAmtToday = crFuncAmtToday;
	}

	public double getDrFuncAmtUnSetToday() {
		return drFuncAmtUnSetToday;
	}

	public void setDrFuncAmtUnSetToday(double drFuncAmtUnSetToday) {
		this.drFuncAmtUnSetToday = drFuncAmtUnSetToday;
	}

	public double getCrFuncAmtUnSetToday() {
		return crFuncAmtUnSetToday;
	}

	public void setCrFuncAmtUnSetToday(double crFuncAmtUnSetToday) {
		this.crFuncAmtUnSetToday = crFuncAmtUnSetToday;
	}

	public double getDrLocalAmtLastDay() {
		return drLocalAmtLastDay;
	}

	public void setDrLocalAmtLastDay(double drLocalAmtLastDay) {
		this.drLocalAmtLastDay = drLocalAmtLastDay;
	}

	public double getCrLocalAmtLastDay() {
		return crLocalAmtLastDay;
	}

	public void setCrLocalAmtLastDay(double crLocalAmtLastDay) {
		this.crLocalAmtLastDay = crLocalAmtLastDay;
	}

	public double getDrLocalAmtUnSetLastDay() {
		return drLocalAmtUnSetLastDay;
	}

	public void setDrLocalAmtUnSetLastDay(double drLocalAmtUnSetLastDay) {
		this.drLocalAmtUnSetLastDay = drLocalAmtUnSetLastDay;
	}

	public double getCrLocalAmtUnSetLastDay() {
		return crLocalAmtUnSetLastDay;
	}

	public void setCrLocalAmtUnSetLastDay(double crLocalAmtUnSetLastDay) {
		this.crLocalAmtUnSetLastDay = crLocalAmtUnSetLastDay;
	}

	public double getDrFuncAmtLastDay() {
		return drFuncAmtLastDay;
	}

	public void setDrFuncAmtLastDay(double drFuncAmtLastDay) {
		this.drFuncAmtLastDay = drFuncAmtLastDay;
	}

	public double getCrFuncAmtLastDay() {
		return crFuncAmtLastDay;
	}

	public void setCrFuncAmtLastDay(double crFuncAmtLastDay) {
		this.crFuncAmtLastDay = crFuncAmtLastDay;
	}

	public double getDrFuncAmtUnSetLastDay() {
		return drFuncAmtUnSetLastDay;
	}

	public void setDrFuncAmtUnSetLastDay(double drFuncAmtUnSetLastDay) {
		this.drFuncAmtUnSetLastDay = drFuncAmtUnSetLastDay;
	}

	public double getCrFuncAmtUnSetLastDay() {
		return crFuncAmtUnSetLastDay;
	}

	public void setCrFuncAmtUnSetLastDay(double crFuncAmtUnSetLastDay) {
		this.crFuncAmtUnSetLastDay = crFuncAmtUnSetLastDay;
	}
}
