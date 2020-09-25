package com.jixu.mvc.control.model;
/** 
 * @ClassName 		WfStatus
 * @author  		Pan Wei 
 * @Date 			2016年10月8日 上午11:18:49 	
 * @Description		[]  
 */

public class WfStatus {
	private int operation;
	private boolean status;
	private int totalnum;

	public WfStatus(){
		
	}
	
	public WfStatus(int operation, boolean status, int totalnum) {
		setOperation(operation);
		setStatus(status);
		setTotalnum(totalnum);
	}
	
	public int getOperation() {
		return operation;
	}
	public void setOperation(int operation) {
		this.operation = operation;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getTotalnum() {
		return totalnum;
	}
	public void setTotalnum(int totalnum) {
		this.totalnum = totalnum;
	}
}
