package com.jixu.common.type;

/** 
 * @ClassName 		PortSecCashType
 * @author  		Pan Wei 
 * @Date 			2017年12月21日 下午3:08:56 	
 * @Description		[传给测试部门获取该投资组合交易用到的配置]  
 * @return  
 */

public class PortTranMapType {
	
	private String portnum;
	
	private String curID;
	
	private int cashacctID;

	private int managerID;
	
	private String secacctID;
	
	private String brokerID;
	
	private String portCurID;

	public String getPortnum() {
		return portnum;
	}

	public void setPortnum(String portnum) {
		this.portnum = portnum;
	}

	public String getCurID() {
		return curID;
	}

	public void setCurID(String curID) {
		this.curID = curID;
	}

	public int getCashacctID() {
		return cashacctID;
	}

	public void setCashacctID(int cashacctID) {
		this.cashacctID = cashacctID;
	}

	public int getManagerID() {
		return managerID;
	}

	public void setManagerID(int managerID) {
		this.managerID = managerID;
	}

	public String getSecacctID() {
		return secacctID;
	}

	public void setSecacctID(String secacctID) {
		this.secacctID = secacctID;
	}

	public String getBrokerID() {
		return brokerID;
	}

	public void setBrokerID(String brokerID) {
		this.brokerID = brokerID;
	}

	public String getPortCurID() {
		return portCurID;
	}

	public void setPortCurID(String portCurID) {
		this.portCurID = portCurID;
	}
}
