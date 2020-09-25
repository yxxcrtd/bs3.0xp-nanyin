package com.jixu.mvc.control.model;
/** 
 * @ClassName 		SecAccountModel
 * @author  		Pan Wei 
 * @Date 			2016年9月4日 上午9:23:00 	
 * @Description		[]  
 */

public class SecAccountModel {
	private String acctID;
	private String acctCode;
	private String brokerName;
	private String acctDesc;
	private String portfolioName;
	private String coaaName;
	private int cashAcctNum;
	
	public SecAccountModel(String acctID,String acctCode,String brokerName,String acctDesc,String portfolioName,String coaaName,int cashAcctNum) {
		this.setAcctID(acctID);
		this.setAcctCode(acctCode);
		this.setBrokerName(brokerName);
		this.setAcctDesc(acctDesc);
		this.setPortfolioName(portfolioName);
		this.setCoaaName(coaaName);
		this.setCashAcctNum(cashAcctNum);
	}
	
	public SecAccountModel() {

	}
	
	public String getAcctID() {
		return acctID;
	}
	public void setAcctID(String acctID) {
		this.acctID = acctID;
	}
	public String getAcctCode() {
		return acctCode;
	}
	public void setAcctCode(String acctCode) {
		this.acctCode = acctCode;
	}
	public String getBrokerName() {
		return brokerName;
	}
	public void setBrokerName(String brokerName) {
		this.brokerName = brokerName;
	}
	public String getAcctDesc() {
		return acctDesc;
	}
	public void setAcctDesc(String acctDesc) {
		this.acctDesc = acctDesc;
	}
	public String getPortfolioName() {
		return portfolioName;
	}
	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}
	public String getCoaaName() {
		return coaaName;
	}
	public void setCoaaName(String coaaName) {
		this.coaaName = coaaName;
	}
	public int getCashAcctNum() {
		return cashAcctNum;
	}
	public void setCashAcctNum(int cashAcctNum) {
		this.cashAcctNum = cashAcctNum;
	}
}
