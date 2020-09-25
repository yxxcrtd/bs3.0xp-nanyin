package com.jixu.msg.dto;

public class TranExpenseDto {
	
	private double cashAmount;
	
	/**
	 * 交易费用货币
	 */
	private String feeTradeCurrency;
	
	/**
	 * 费用化费用
	 */
	private double expenseAmount;
	
	/**
	 * 费用类型
	 */
	private int expense;
	
	/**
	 * 总费用
	 */
	private double totalAmount;
	
	/**
	 * 费用汇率
	 */
	private double fxRate;
	
	/**
	 * 银行账户
	 */
	private String bankAccount;
	
	/**
	 * 费用结算日期
	 */
	private String expenseSettleDate;
	
	private int rpType;

	public double getCashAmount() {
		return cashAmount;
	}

	public void setCashAmount(double cashAmount) {
		this.cashAmount = cashAmount;
	}

	public String getFeeTradeCurrency() {
		return feeTradeCurrency;
	}

	public void setFeeTradeCurrency(String feeTradeCurrency) {
		this.feeTradeCurrency = feeTradeCurrency;
	}

	public double getExpenseAmount() {
		return expenseAmount;
	}

	public void setExpenseAmount(double expenseAmount) {
		this.expenseAmount = expenseAmount;
	}

	public int getExpense() {
		return expense;
	}

	public void setExpense(int expense) {
		this.expense = expense;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getFxRate() {
		return fxRate;
	}

	public void setFxRate(double fxRate) {
		this.fxRate = fxRate;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public int getRpType() {
		return rpType;
	}

	public void setRpType(int rpType) {
		this.rpType = rpType;
	}

	public String getExpenseSettleDate() {
		return expenseSettleDate;
	}

	public void setExpenseSettleDate(String expenseSettleDate) {
		this.expenseSettleDate = expenseSettleDate;
	}
}
