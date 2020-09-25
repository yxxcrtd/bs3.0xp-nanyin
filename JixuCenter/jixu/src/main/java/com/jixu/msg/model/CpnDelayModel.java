package com.jixu.msg.model;

/** 
 * @ClassName 		AccrualLag
 * @author  		Pan Wei 
 * @Date 			2016年9月27日 下午9:48:07 	
 * @Description		[]  
 */

public class CpnDelayModel {
	private int delayFlag;
	private String holidaySD;
	private int delayNums;
	private int delayTypes;
	public int getDelayFlag() {
		return delayFlag;
	}
	public void setDelayFlag(int delayFlag) {
		this.delayFlag = delayFlag;
	}
	public String getHolidaySD() {
		return holidaySD;
	}
	public void setHolidaySD(String holidaySD) {
		this.holidaySD = holidaySD;
	}
	public int getDelayNums() {
		return delayNums;
	}
	public void setDelayNums(int delayNums) {
		this.delayNums = delayNums;
	}
	public int getDelayTypes() {
		return delayTypes;
	}
	public void setDelayTypes(int delayTypes) {
		this.delayTypes = delayTypes;
	}
}
