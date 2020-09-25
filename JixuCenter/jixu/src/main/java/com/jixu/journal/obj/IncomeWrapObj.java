package com.jixu.journal.obj;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class IncomeWrapObj implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7521020950306177511L;
	
	/*****每天每个现金账户的统计信息******/
	List<BankBalanceWrapObj> bankBalanceWrapList = new ArrayList<BankBalanceWrapObj>();
	
	public List<BankBalanceWrapObj> getBankBalanceWrapList() {
		return bankBalanceWrapList;
	}

	public void setBankBalanceWrapList(List<BankBalanceWrapObj> bankBalanceWrapList) {
		this.bankBalanceWrapList = bankBalanceWrapList;
	}

}
