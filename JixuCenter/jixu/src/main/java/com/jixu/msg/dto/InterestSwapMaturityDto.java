package com.jixu.msg.dto;

import java.util.List;

import com.jixu.common.valid.IsIntegerJixu;
import com.jixu.common.valid.IsNumberJixu;

public class InterestSwapMaturityDto extends TranBaseMsgDto{

	private static final long serialVersionUID = -6686142339342693673L;
	
	/**
	 * 交易费用
	 */
	private List<TranExpenseDto> expenses;
	
	/**
	 * 结息的上期截止日
	 */
	private String divBasicDate;
	
	/**
	 * 收入端本金
	 */
	@IsNumberJixu(keymsg="收入端本金")
	private String principal;
	
	/**
	 * 收入端利息
	 */
	@IsNumberJixu(keymsg="收入端利息")
	private String intatmat;

	/**
	 * 是否结清利息
	 */
	private String setAllInterest;
	
	
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
	 * 支出端本金
	 */
	@IsNumberJixu(keymsg="支出端本金")
	private String principal2;
	
	@IsNumberJixu(keymsg="支出利息")
	private String intatmat2;
	
	/**
	 * 是否结清空方利息
	 */
	private String setAllInterest2;
	
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

	public List<TranExpenseDto> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<TranExpenseDto> expenses) {
		this.expenses = expenses;
	}

	public String getIntatmat() {
		return intatmat;
	}

	public void setIntatmat(String intatmat) {
		this.intatmat = intatmat;
	}

	public String getIntatmat2() {
		return intatmat2;
	}

	public void setIntatmat2(String intatmat2) {
		this.intatmat2 = intatmat2;
	}

	public String getSetAllInterest() {
		return setAllInterest;
	}

	public void setSetAllInterest(String setAllInterest) {
		this.setAllInterest = setAllInterest;
	}

	public String getSetAllInterest2() {
		return setAllInterest2;
	}

	public void setSetAllInterest2(String setAllInterest2) {
		this.setAllInterest2 = setAllInterest2;
	}

	public String getDivBasicDate() {
		return divBasicDate;
	}

	public void setDivBasicDate(String divBasicDate) {
		this.divBasicDate = divBasicDate;
	}
}
