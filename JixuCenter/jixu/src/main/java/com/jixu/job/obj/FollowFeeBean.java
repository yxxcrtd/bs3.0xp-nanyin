package com.jixu.job.obj;

/** 
 * @ClassName 		FollowFeeBean
 * @author  		Pan Wei 
 * @Date 			2018年6月5日 下午11:43:15 	
 * @Description		[]  
 */

public class FollowFeeBean {
	private String portnum;
	private String cusip;
	private int lotnum;
	private int fixFlag;
	private double feeAmount;
	private String cfcID;
	private int drCol;
	private int crCol;
	private int recOrPay;
	private String feename;
	private int feeCfgID;
	
	public int getFixFlag() {
		return fixFlag;
	}
	public void setFixFlag(int fixFlag) {
		this.fixFlag = fixFlag;
	}
	public double getFeeAmount() {
		return feeAmount;
	}
	public void setFeeAmount(double feeAmount) {
		this.feeAmount = feeAmount;
	}
	public String getCfcID() {
		return cfcID;
	}
	public void setCfcID(String cfcID) {
		this.cfcID = cfcID;
	}
	public int getDrCol() {
		return drCol;
	}
	public void setDrCol(int drCol) {
		this.drCol = drCol;
	}
	public int getCrCol() {
		return crCol;
	}
	public void setCrCol(int crCol) {
		this.crCol = crCol;
	}
	public int getRecOrPay() {
		return recOrPay;
	}
	public void setRecOrPay(int recOrPay) {
		this.recOrPay = recOrPay;
	}
	public String getFeename() {
		return feename;
	}
	public void setFeename(String feename) {
		this.feename = feename;
	}
	public int getFeeCfgID() {
		return feeCfgID;
	}
	public void setFeeCfgID(int feeCfgID) {
		this.feeCfgID = feeCfgID;
	}
	public String getPortnum() {
		return portnum;
	}
	public void setPortnum(String portnum) {
		this.portnum = portnum;
	}
	public String getCusip() {
		return cusip;
	}
	public void setCusip(String cusip) {
		this.cusip = cusip;
	}
	public int getLotnum() {
		return lotnum;
	}
	public void setLotnum(int lotnum) {
		this.lotnum = lotnum;
	}
}
