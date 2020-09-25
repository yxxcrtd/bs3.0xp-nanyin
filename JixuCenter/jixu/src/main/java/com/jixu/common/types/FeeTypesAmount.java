package com.jixu.common.types;

/** 
 * @ClassName 		FeeTypesAmount
 * @author  		Pan Wei 
 * @Date 			2016年10月13日 下午2:12:20 	
 * @Description		[]  
 */

public class FeeTypesAmount {
	private int recOrPay; 
	private double cashCapitalFee;
	private double rcpyCapitalFee;
	private double cashExpensedFee;
	private double rcpyExpensedFee;
	
	public int getRecOrPay() {
		return recOrPay;
	}
	public void setRecOrPay(int recOrPay) {
		this.recOrPay = recOrPay;
	}
	public double getCashCapitalFee() {
		return cashCapitalFee;
	}
	public void setCashCapitalFee(double cashCapitalFee) {
		this.cashCapitalFee = cashCapitalFee;
	}
	public double getRcpyCapitalFee() {
		return rcpyCapitalFee;
	}
	public void setRcpyCapitalFee(double rcpyCapitalFee) {
		this.rcpyCapitalFee = rcpyCapitalFee;
	}
	public double getCashExpensedFee() {
		return cashExpensedFee;
	}
	public void setCashExpensedFee(double cashExpensedFee) {
		this.cashExpensedFee = cashExpensedFee;
	}
	public double getRcpyExpensedFee() {
		return rcpyExpensedFee;
	}
	public void setRcpyExpensedFee(double rcpyExpensedFee) {
		this.rcpyExpensedFee = rcpyExpensedFee;
	} 
}
