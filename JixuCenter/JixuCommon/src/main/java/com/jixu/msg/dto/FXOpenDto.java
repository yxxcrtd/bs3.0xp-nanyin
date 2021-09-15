package com.jixu.msg.dto;

import java.util.List;

public class FXOpenDto extends TranBaseMsgDto{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 多头现金账户
	 */
	private String bankAcct;
	
	/**
	 * 多头金额
	 */
	private String tradeCash;
	
	/**
	 * 多头现金账户
	 */
	private String bankAcct2;
	
	/**
	 * 多头金额
	 */
	private String tradeCash2;
	
	/**
	 * 交易费用
	 */
	private List<TranExpenseDto> expenses;
	
	/**
	 * 总账栏
	 * 可以不提供，自动生成
	 */
	private int glgroup;

	public String getBankAcct() {
		return bankAcct;
	}

	public void setBankAcct(String bankAcct) {
		this.bankAcct = bankAcct;
	}

	public String getTradeCash() {
		return tradeCash;
	}

	public void setTradeCash(String tradeCash) {
		this.tradeCash = tradeCash;
	}

	public String getBankAcct2() {
		return bankAcct2;
	}

	public void setBankAcct2(String bankAcct2) {
		this.bankAcct2 = bankAcct2;
	}

	public String getTradeCash2() {
		return tradeCash2;
	}

	public void setTradeCash2(String tradeCash2) {
		this.tradeCash2 = tradeCash2;
	}

	public List<TranExpenseDto> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<TranExpenseDto> expenses) {
		this.expenses = expenses;
	}

	public int getGlgroup() {
		return glgroup;
	}

	public void setGlgroup(int glgroup) {
		this.glgroup = glgroup;
	}
}
