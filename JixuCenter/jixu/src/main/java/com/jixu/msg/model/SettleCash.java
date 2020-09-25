package com.jixu.msg.model;
/** 
 * @ClassName 		SettleCash
 * @author  		Pan Wei 
 * @Date 			2016年8月12日 下午4:20:45 	
 * @Description		[]  
 */

public class SettleCash {
	private String curID;
	private double fxrate;
	private double cash;
	
	public String getCurID() {
		return curID;
	}
	public void setCurID(String curID) {
		this.curID = curID;
	}
	public double getFxrate() {
		return fxrate;
	}
	public void setFxrate(double fxrate) {
		this.fxrate = fxrate;
	}
	public double getCash() {
		return cash;
	}
	public void setCash(double cash) {
		this.cash = cash;
	}
}
