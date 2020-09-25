package com.jixu.journal.obj;

import java.io.Serializable;

public class TranFeeObj implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 544731991739277429L;
	
	private double fxfeeAmount; //原币
	private double fxrate;
	private int glcolumn; //总账栏ID
	
	public double getFxfeeAmount() {
		return fxfeeAmount;
	}
	public void setFxfeeAmount(double fxfeeAmount) {
		this.fxfeeAmount = fxfeeAmount;
	}
	public double getFxrate() {
		return fxrate;
	}
	public void setFxrate(double fxrate) {
		this.fxrate = fxrate;
	}
	public int getGlcolumn() {
		return glcolumn;
	}
	public void setGlcolumn(int glcolumn) {
		this.glcolumn = glcolumn;
	}

}
