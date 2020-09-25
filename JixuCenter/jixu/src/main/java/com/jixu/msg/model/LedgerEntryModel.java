package com.jixu.msg.model;

/** 
 * @ClassName 		LedgerEntryModel
 * @author  		Pan Wei 
 * @Date 			2016年11月9日 下午11:48:46 	
 * @Description		[]  
 */

public class LedgerEntryModel {
	//Glacct,DrFunc,CrFunc,Cur(),DrLocal,CrLocal,FXRate
	private String glacct;
	private double drfunc;
	private double crfunc;
	private String cur;
	private double drlocal;
	private double crlocal;
	private double fxrate;
	public String getGlacct() {
		return glacct;
	}
	public void setGlacct(String glacct) {
		this.glacct = glacct;
	}
	public double getDrfunc() {
		return drfunc;
	}
	public void setDrfunc(double drfunc) {
		this.drfunc = drfunc;
	}
	public double getCrfunc() {
		return crfunc;
	}
	public void setCrfunc(double crfunc) {
		this.crfunc = crfunc;
	}
	public String getCur() {
		return cur;
	}
	public void setCur(String cur) {
		this.cur = cur;
	}
	public double getDrlocal() {
		return drlocal;
	}
	public void setDrlocal(double drlocal) {
		this.drlocal = drlocal;
	}
	public double getCrlocal() {
		return crlocal;
	}
	public void setCrlocal(double crlocal) {
		this.crlocal = crlocal;
	}
	public double getFxrate() {
		return fxrate;
	}
	public void setFxrate(double fxrate) {
		this.fxrate = fxrate;
	}
}
