package com.jixu.msg.dto;

import java.util.List;

import com.jixu.common.valid.IsIntegerJixu;

public class TranMsgDto extends TranBaseMsgDto{

	private static final long serialVersionUID = -4270472729713357163L;
	
	/**
	 * 证券账号
	 */
	private String secAcct;
	
	/**
	 * 现金账号
	 */
	private String bankAcct;
	
	/**
	 * 交易费用
	 */
	private List<TranExpenseDto> expenses;

	/**
	 * 一级二级市场
	 */
	@IsIntegerJixu(keymsg="交易场所")
	private String market;
	
	/**
	 * 基金经理组
	 */
	@IsIntegerJixu(keymsg="基金经理")
	private String manager;
	
	/**
	 * 券商代码
	 */
	@IsIntegerJixu(keymsg="券商/托管行代码")
	private String broker;

	public String getSecAcct() {
		return secAcct;
	}

	public void setSecAcct(String secAcct) {
		this.secAcct = secAcct;
	}

	public String getBankAcct() {
		return bankAcct;
	}

	public void setBankAcct(String bankAcct) {
		this.bankAcct = bankAcct;
	}

	public List<TranExpenseDto> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<TranExpenseDto> expenses) {
		this.expenses = expenses;
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getBroker() {
		return broker;
	}

	public void setBroker(String broker) {
		this.broker = broker;
	}
}
