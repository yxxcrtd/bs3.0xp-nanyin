package com.jixu.msg.dto;

import com.jixu.common.valid.IsIntegerJixu;
import com.jixu.common.valid.IsNumberJixu;
import com.jixu.common.valid.NotEmptyJixu;

public class ForwardCurrencyOpenDto extends TranMsgDto{

	private static final long serialVersionUID = 1L;

	/**
	 * 合同名称
	 */
	@NotEmptyJixu(keymsg="合同名称")
	private String contract;
	/**
	 * 估值方法
	 */
	@IsIntegerJixu(keymsg="估值方法")
	private String valuationMethod;
	/**
	 * 估值方向
	 */
	@IsIntegerJixu(keymsg="估值方向")
	private String valuationSide;
	/**
	 * 空头现金账户
	 */
	@NotEmptyJixu(keymsg="空头现金账户")
	private String bankAcct2;
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
	 * 总账组
	 */
	@IsIntegerJixu(keymsg="总账组")
	private String glgroup;
	/**
	 * 资产类型,普通股，优先股等。这里是外汇远期
	 */
	@IsIntegerJixu(keymsg="证券类型分组")
	private String rpSecType;
	
	/**
	 * 数据源配置
	 */
	@IsIntegerJixu(keymsg="证券数据来源")
	private int source;
	
	/**
	 * 清算模式
	 */
	@NotEmptyJixu(keymsg="清算模式")
	private int settleType;
	
	
	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public String getValuationMethod() {
		return valuationMethod;
	}

	public void setValuationMethod(String valuationMethod) {
		this.valuationMethod = valuationMethod;
	}

	public String getValuationSide() {
		return valuationSide;
	}

	public void setValuationSide(String valuationSide) {
		this.valuationSide = valuationSide;
	}

	public String getBankAcct2() {
		return bankAcct2;
	}

	public void setBankAcct2(String bankAcct2) {
		this.bankAcct2 = bankAcct2;
	}

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

	public String getGlgroup() {
		return glgroup;
	}

	public void setGlgroup(String glgroup) {
		this.glgroup = glgroup;
	}

	public String getRpSecType() {
		return rpSecType;
	}

	public void setRpSecType(String rpSecType) {
		this.rpSecType = rpSecType;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public int getSettleType() {
		return settleType;
	}

	public void setSettleType(int settleType) {
		this.settleType = settleType;
	}
	
}
