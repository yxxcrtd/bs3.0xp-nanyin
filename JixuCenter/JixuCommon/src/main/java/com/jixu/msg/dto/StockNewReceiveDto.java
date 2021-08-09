package com.jixu.msg.dto;

import com.jixu.common.valid.IsNumberJixu;

public class StockNewReceiveDto extends TranMsgDto {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
	 * 申购份数
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
	

	public String getTradeCurrency() {
		return tradeCurrency;
	}

	public void setTradeCurrency(String tradeCurrency) {
		this.tradeCurrency = tradeCurrency;
	}

	public String getTradeCash() {
		return tradeCash;
	}

	public void setTradeCash(String tradeCash) {
		this.tradeCash = tradeCash;
	}

	public String getTradeFX() {
		return tradeFX;
	}

	public void setTradeFX(String tradeFX) {
		this.tradeFX = tradeFX;
	}

	public String getSettleCurrency() {
		return settleCurrency;
	}

	public void setSettleCurrency(String settleCurrency) {
		this.settleCurrency = settleCurrency;
	}

	public String getSettleCash() {
		return settleCash;
	}

	public void setSettleCash(String settleCash) {
		this.settleCash = settleCash;
	}

	public String getSettleFX() {
		return settleFX;
	}

	public void setSettleFX(String settleFX) {
		this.settleFX = settleFX;
	}

	public String getShares() {
		return shares;
	}

	public void setShares(String shares) {
		this.shares = shares;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}
}
