package com.jixu.msg.dto;

import com.jixu.common.valid.IsNumberJixu;

public class FutureTranOpenDto extends TranMsgDto{

	private static final long serialVersionUID = 1L;

	
	/**
	 * 数量
	 */
	@IsNumberJixu(keymsg="数量")
	private String shares;
	
	/**
	 * 价格
	 */
	@IsNumberJixu(keymsg="价格")
	private String price;
	
	/**
	 * 本金
	 */
	@IsNumberJixu(keymsg="本金")
	private String principal;
	
	/**
	 * 总费用
	 */
	private String totalExpense;
	
	/**
	 * 交易货币
	 */
	private String tradeCurrency;
	
	/**
	 * 交易金额
	 */
	@IsNumberJixu(keymsg="交易金额")
	private String tradeCash;
	
	/**
	 * 交易汇率
	 */
	@IsNumberJixu(keymsg="交易汇率")
	private String tradeFX;
	
	
	/**
	 * 结算货币
	 */
	private String settleCurrency;
	
	/**
	 * 结算金额
	 */
	@IsNumberJixu(keymsg="结算金额")
	private String settleCash;
	
	/**
	 * 结算汇率
	 */
	@IsNumberJixu(keymsg="结算汇率")
	private String settleFX;
	
	/**
	 * 批次
	 */
	private String lotMethod;
	
	/**
	 * 合同编号
	 */
	private String contract;

	

	/**
	 * @return the shares
	 */
	public String getShares() {
		return shares;
	}

	/**
	 * @param shares the shares to set
	 */
	public void setShares(String shares) {
		this.shares = shares;
	}

	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * @return the principal
	 */
	public String getPrincipal() {
		return principal;
	}

	/**
	 * @param principal the principal to set
	 */
	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	/**
	 * @return the totalExpense
	 */
	public String getTotalExpense() {
		return totalExpense;
	}

	/**
	 * @param totalExpense the totalExpense to set
	 */
	public void setTotalExpense(String totalExpense) {
		this.totalExpense = totalExpense;
	}

	/**
	 * @return the tradeCurrency
	 */
	public String getTradeCurrency() {
		return tradeCurrency;
	}

	/**
	 * @param tradeCurrency the tradeCurrency to set
	 */
	public void setTradeCurrency(String tradeCurrency) {
		this.tradeCurrency = tradeCurrency;
	}

	/**
	 * @return the tradeCash
	 */
	public String getTradeCash() {
		return tradeCash;
	}

	/**
	 * @param tradeCash the tradeCash to set
	 */
	public void setTradeCash(String tradeCash) {
		this.tradeCash = tradeCash;
	}

	/**
	 * @return the tradeFX
	 */
	public String getTradeFX() {
		return tradeFX;
	}

	/**
	 * @param tradeFX the tradeFX to set
	 */
	public void setTradeFX(String tradeFX) {
		this.tradeFX = tradeFX;
	}

	/**
	 * @return the settleCurrency
	 */
	public String getSettleCurrency() {
		return settleCurrency;
	}

	/**
	 * @param settleCurrency the settleCurrency to set
	 */
	public void setSettleCurrency(String settleCurrency) {
		this.settleCurrency = settleCurrency;
	}

	/**
	 * @return the settleCash
	 */
	public String getSettleCash() {
		return settleCash;
	}

	/**
	 * @param settleCash the settleCash to set
	 */
	public void setSettleCash(String settleCash) {
		this.settleCash = settleCash;
	}

	/**
	 * @return the settleFX
	 */
	public String getSettleFX() {
		return settleFX;
	}

	/**
	 * @param settleFX the settleFX to set
	 */
	public void setSettleFX(String settleFX) {
		this.settleFX = settleFX;
	}

	public String getLotMethod() {
		return lotMethod;
	}

	public void setLotMethod(String lotMethod) {
		this.lotMethod = lotMethod;
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}
	
}
