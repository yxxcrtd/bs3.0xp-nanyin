package com.jixu.msg.dto;

import java.util.List;

import com.jixu.common.valid.IsDateYYYYMMDDJixu;
import com.jixu.common.valid.IsIntegerJixu;
import com.jixu.common.valid.IsNumberJixu;

public class InterestSwapOpenDto extends TranBaseMsgDto{

	private static final long serialVersionUID = -6686142339342693673L;
	
	@IsIntegerJixu(keymsg="基金经理")
	private String manager;
	
	/**
	 * 估值方法
	 */
	@IsIntegerJixu(keymsg="估值方法")
	private int valuationMethod;
	
	/**
	 * 交易费用
	 */
	private List<TranExpenseDto> expenses;
	
	/**
	 * 到期日
	 */
	@IsDateYYYYMMDDJixu(keymsg="到期日")
	private String maturityDate;
	
	/**
	 * 总账组
	 */
	@IsIntegerJixu(keymsg="总账组")
	private String glgroup;
	
	/**
	 * 本金互换的方式
	 */
	@IsIntegerJixu(keymsg="本金互换方式")
	private int swapExchangeInd;
	
	
	/**
	 * 收入端本金
	 */
	@IsNumberJixu(keymsg="收入端本金")
	private String principal;
	
	/**
	 * 收入端银行账户
	 */
	@IsIntegerJixu(keymsg="收入端银行账户")
	private String bankAcct;
	
	/**
	 * 收入端收入货币
	 */
	private String incurrency;
	
	/**
	 * 收入端汇率
	 */
	@IsNumberJixu(keymsg="收入端汇率")
	private String tradeFX;
	
	/**
	 * 收入端现金流配置
	 */
	private String cashflowcfg;
	
	/**
	 * 支出端本金
	 */
	@IsNumberJixu(keymsg="支出端本金")
	private String principal2;
	
	/**
	 * 支出端银行账户
	 */
	@IsIntegerJixu(keymsg="支出端银行账户")
	private String bankAcct2;
	
	/**
	 * 支出端收入货币
	 */
	private String incurrency2;
	
	/**
	 * 支出端汇率
	 */
	@IsNumberJixu(keymsg="支出端汇率")
	private String tradeFX2;
	
	/**
	 * 支出端现金流配置
	 */
	private String cashflowcfg2;

	/**
	 * 资产类型,普通股，优先股等。这里是利率互换
	 */
	@IsIntegerJixu(keymsg="证券类型分组")
	private String rpSecType;
	
	/**
	 * 数据源配置
	 */
	@IsIntegerJixu(keymsg="证券数据来源")
	private int source;
	
	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public int getValuationMethod() {
		return valuationMethod;
	}

	public void setValuationMethod(int valuationMethod) {
		this.valuationMethod = valuationMethod;
	}

	public String getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(String maturityDate) {
		this.maturityDate = maturityDate;
	}

	public String getGlgroup() {
		return glgroup;
	}

	public void setGlgroup(String glgroup) {
		this.glgroup = glgroup;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getBankAcct() {
		return bankAcct;
	}

	public void setBankAcct(String bankAcct) {
		this.bankAcct = bankAcct;
	}

	public String getIncurrency() {
		return incurrency;
	}

	public void setIncurrency(String incurrency) {
		this.incurrency = incurrency;
	}

	public String getTradeFX() {
		return tradeFX;
	}

	public void setTradeFX(String tradeFX) {
		this.tradeFX = tradeFX;
	}

	public String getCashflowcfg() {
		return cashflowcfg;
	}

	public void setCashflowcfg(String cashflowcfg) {
		this.cashflowcfg = cashflowcfg;
	}

	public String getPrincipal2() {
		return principal2;
	}

	public void setPrincipal2(String principal2) {
		this.principal2 = principal2;
	}

	public String getBankAcct2() {
		return bankAcct2;
	}

	public void setBankAcct2(String bankAcct2) {
		this.bankAcct2 = bankAcct2;
	}

	public String getIncurrency2() {
		return incurrency2;
	}

	public void setIncurrency2(String incurrency2) {
		this.incurrency2 = incurrency2;
	}

	public String getTradeFX2() {
		return tradeFX2;
	}

	public void setTradeFX2(String tradeFX2) {
		this.tradeFX2 = tradeFX2;
	}

	public String getCashflowcfg2() {
		return cashflowcfg2;
	}

	public void setCashflowcfg2(String cashflowcfg2) {
		this.cashflowcfg2 = cashflowcfg2;
	}

	public List<TranExpenseDto> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<TranExpenseDto> expenses) {
		this.expenses = expenses;
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

	public int getSwapExchangeInd() {
		return swapExchangeInd;
	}

	public void setSwapExchangeInd(int swapExchangeInd) {
		this.swapExchangeInd = swapExchangeInd;
	}
}
