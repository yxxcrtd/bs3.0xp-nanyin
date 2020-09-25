package com.jixu.msg.dto;

import com.jixu.common.valid.IsNumberJixu;

/**
 * 现金股利Dto
 * @author Wang Zhongyuan
 *
 */
public class CashDividendDto extends TranBaseMsgDto{
	
	private static final long serialVersionUID = -1654952588701364775L;
	/**
	 * 每股分红金额
	 */
	@IsNumberJixu(keymsg="每股分红金额")
	private String alRate;
	
	/**
	 * 股利登记日
	 */
	private String divDate;
	
	/**
	 * 证券账户
	 */
	private String secAcct;
	
	/**
	 * 银行账户
	 */
	private String bankAcct;
	
	/**
	 * 分红基准股数
	 */
	private double baseAmount;
	
	/**
	 * 分红金额
	 */
	private double tradeCash;


	public String getAlRate() {
		return alRate;
	}

	/**
	 * 分红比例
	 * @param alRate 分红比例
	 */
	public void setAlRate(String alRate) {
		this.alRate = alRate;
	}

	public String getDivDate() {
		return divDate;
	}

	/**
	 * 分红基准日
	 * @param divDate 分红基准日
	 */
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

	public double getBaseAmount() {
		return baseAmount;
	}

	/**
	 * 基准数量
	 * @param baseAmount 基准数量
	 */
	public void setBaseAmount(double baseAmount) {
		this.baseAmount = baseAmount;
	}

	public double getTradeCash() {
		return tradeCash;
	}

	/**
	 * 分红金额
	 * @param tradeCash 分红金额
	 */
	public void setTradeCash(double tradeCash) {
		this.tradeCash = tradeCash;
	}

	/**
	 * 银行账户
	 * @return 银行账户
	 */
	public String getBankAcct() {
		return bankAcct;
	}

	/**
	 * 银行账户
	 * @param bankAcct 银行账户
	 */
	public void setBankAcct(String bankAcct) {
		this.bankAcct = bankAcct;
	}
}
