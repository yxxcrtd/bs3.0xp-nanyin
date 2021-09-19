package com.jixu.msg.dto;

import com.jixu.framework.msg.BaseMsgDto;

/**
 * 现金流消息Dto
 * @author Wang Zhongyuan
 */
public class CashFlowPaymentsDto extends BaseMsgDto{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//现金流ID
	private String cashFlowID;
	
	//付款计划日期
	private String settleDate;


	public String getCashFlowID() {
		return cashFlowID;
	}

	public void setCashFlowID(String cashFlowID) {
		this.cashFlowID = cashFlowID;
	}

	public String getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}
}
