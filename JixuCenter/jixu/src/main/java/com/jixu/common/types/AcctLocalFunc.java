package com.jixu.common.types;

public class AcctLocalFunc {
	/**
	 * 科目号	
	 */
	private String acctnum;
	
	/**
	 * 贷方-本币
	 */
	private double cr_func;
	/**
	 * 借方-本币
	 */
	private double dr_func;
	/**
	 * 贷方-原币
	 */
	private double cr_local;
	/**
	 * 借方-原币
	 */
	private double dr_local;
	
	public AcctLocalFunc(String acctnum, double cr_func, double dr_func, double cr_local, double dr_local) {
		setAcctnum(acctnum);
		setCr_func(cr_func);
		setDr_func(dr_func);
		setCr_local(cr_local);
		setDr_local(dr_local);
	}
	
	public AcctLocalFunc() {
	}
	
	/**
	 * 获取科目号
	 * @return
	 */
	public String getAcctnum() {
		return acctnum;
	}
	
	/**
	 * 设值科目号
	 * @param acctnum
	 */
	public void setAcctnum(String acctnum) {
		this.acctnum = acctnum;
	}
	
	/**
	 * @return 贷方-本币
	 */
	public double getCr_func() {
		return cr_func;
	}
	public void setCr_func(double cr_func) {
		this.cr_func = cr_func;
	}
	
	/**
	 * 借方-本币
	 * @return	借方-本币
	 */
	public double getDr_func() {
		return dr_func;
	}
	public void setDr_func(double dr_func) {
		this.dr_func = dr_func;
	}
	
	/**
	 * 贷方-原币
	 * @return 贷方-原币
	 */
	public double getCr_local() {
		return cr_local;
	}
	public void setCr_local(double cr_local) {
		this.cr_local = cr_local;
	}
	
	/**
	 * 借方-原币
	 * @return 借方-原币
	 */
	public double getDr_local() {
		return dr_local;
	}
	public void setDr_local(double dr_local) {
		this.dr_local = dr_local;
	}
}
