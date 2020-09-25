package com.jixu.msg.dto;

import com.jixu.common.valid.IsNumberJixu;

/**
 * 股票股利Dto
 * @author Wang Zhongyuan
 *
 */
public class StockDividendDto extends TranBaseMsgDto{
	
	private static final long serialVersionUID = -1654952588701364775L;
	/**
	 * 分红比例
	 */
	@IsNumberJixu(keymsg="分红比例")
	private String alRate;
	
	/**
	 * 证券账户
	 */
	private String secAcct;
	
	/**
	 * 送股股数
	 */
	private double amount;
	
	/**
	 * 股利登记日
	 */
	private String divDate;
	
	/**
	 * 分红基准股数
	 */
	private double baseAmount;


	public String getAlRate() {
		return alRate;
	}

	public void setAlRate(String alRate) {
		this.alRate = alRate;
	}

	public String getDivDate() {
		return divDate;
	}

	public void setDivDate(String divDate) {
		this.divDate = divDate;
	}

	public String getSecAcct() {
		return secAcct;
	}

	/**
	 * 证券账户
	 * @param secAcct 证券账户
	 */
	public void setSecAcct(String secAcct) {
		this.secAcct = secAcct;
	}

	public double getAmount() {
		return amount;
	}

	/**
	 * 送股股数
	 * @param amount 送股股数
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getBaseAmount() {
		return baseAmount;
	}

	/**
	 * 送股基准股数
	 * @param baseAmount 送股基准股数
	 */
	public void setBaseAmount(double baseAmount) {
		this.baseAmount = baseAmount;
	}
}
