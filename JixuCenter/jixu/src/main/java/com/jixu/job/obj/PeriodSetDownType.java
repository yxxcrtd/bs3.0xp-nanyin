package com.jixu.job.obj;
/** 
 * @ClassName 		PeriodSetDownType
 * @author  		Pan Wei 
 * @Date 			2016年11月14日 下午2:07:54 	
 * @Description		[]  
 */

public class PeriodSetDownType {
	private String acctnum;
	private String closeToacctnum;
	private double dr_func;
	private double dr_local;
	public String getAcctnum() {
		return acctnum;
	}
	public void setAcctnum(String acctnum) {
		this.acctnum = acctnum;
	}
	public String getCloseToacctnum() {
		return closeToacctnum;
	}
	public void setCloseToacctnum(String closeToacctnum) {
		this.closeToacctnum = closeToacctnum;
	}
	public double getDr_func() {
		return dr_func;
	}
	public void setDr_func(double dr_func) {
		this.dr_func = dr_func;
	}
	public double getDr_local() {
		return dr_local;
	}
	public void setDr_local(double dr_local) {
		this.dr_local = dr_local;
	}
}
