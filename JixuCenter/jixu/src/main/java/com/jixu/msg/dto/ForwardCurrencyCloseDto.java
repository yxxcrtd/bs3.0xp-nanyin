package com.jixu.msg.dto;

import com.jixu.common.valid.IsNumberJixu;
import com.jixu.common.valid.NotEmptyJixu;

public class ForwardCurrencyCloseDto extends TranMsgDto{

	private static final long serialVersionUID = 1L;

	/**
	 * 多头货币
	 */
	@NotEmptyJixu(keymsg="多头货币")
	private String longCurrency;
	/**
	 * 空头货币
	 */
	@NotEmptyJixu(keymsg="空头货币")
	private String shortCurrency;
	/**
	 * 多头金额
	 */
	@IsNumberJixu(keymsg="多头金额")
	private String tradeCash;
	/**
	 * 空头金额
	 */
	@IsNumberJixu(keymsg="空头金额")
	private String tradeCash2;
	/**
	 * 多头合约汇率
	 */
	@IsNumberJixu(keymsg="多头合约汇率")
	private String longFX;
	/**
	 * 空头合约汇率
	 */
	@IsNumberJixu(keymsg="空头合约汇率")
	private String shortFX;
	/**
	 * 费用总计
	 */
	@IsNumberJixu(keymsg="费用总计")
	private String totalExpense;
	/**
	 * 结算货币
	 */
	@NotEmptyJixu(keymsg="结算货币")
	private String settleCurrency;
		

	public String getLongCurrency() {
		return longCurrency;
	}

	public void setLongCurrency(String longCurrency) {
		this.longCurrency = longCurrency;
	}

	public String getShortCurrency() {
		return shortCurrency;
	}

	public void setShortCurrency(String shortCurrency) {
		this.shortCurrency = shortCurrency;
	}

	public String getTradeCash() {
		return tradeCash;
	}

	public void setTradeCash(String tradeCash) {
		this.tradeCash = tradeCash;
	}

	public String getTradeCash2() {
		return tradeCash2;
	}

	public void setTradeCash2(String tradeCash2) {
		this.tradeCash2 = tradeCash2;
	}

	public String getLongFX() {
		return longFX;
	}

	public void setLongFX(String longFX) {
		this.longFX = longFX;
	}

	public String getShortFX() {
		return shortFX;
	}

	public void setShortFX(String shortFX) {
		this.shortFX = shortFX;
	}

	public String getTotalExpense() {
		return totalExpense;
	}

	public void setTotalExpense(String totalExpense) {
		this.totalExpense = totalExpense;
	}

	public String getSettleCurrency() {
		return settleCurrency;
	}

	public void setSettleCurrency(String settleCurrency) {
		this.settleCurrency = settleCurrency;
	}
}
