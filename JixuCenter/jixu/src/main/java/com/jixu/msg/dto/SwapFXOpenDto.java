package com.jixu.msg.dto;

import java.util.List;

import com.jixu.common.valid.IsDateYYYYMMDDJixu;
import com.jixu.common.valid.IsIntegerJixu;
import com.jixu.common.valid.IsNumberJixu;

public class SwapFXOpenDto extends TranBaseMsgDto{

	private static final long serialVersionUID = -6686142339342693673L;
	
	@IsIntegerJixu(keymsg="基金经理")
	private String manager;
	
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
	@IsNumberJixu(keymsg="近端收入金额")
	private String principal;
	
	/**
	 * 收入端银行账户
	 */
	@IsIntegerJixu(keymsg="近端收入银行账户")
	private String bankAcct;
	
	/**
	 * 收入端汇率
	 */
	@IsNumberJixu(keymsg="近端汇率")
	private String tradeFX;
	
	/**
	 * 支出端本金
	 */
	@IsNumberJixu(keymsg="近端支出金额")
	private String principal2;
	
	/**
	 * 支出端银行账户
	 */
	@IsIntegerJixu(keymsg="近端支出银行账户")
	private String bankAcct2;
	
	/**
	 * 支出端汇率
	 */
	@IsNumberJixu(keymsg="远端汇率")
	private String tradeFX2;


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
	
	//统一同一种货币用同一端
	@IsNumberJixu(keymsg="远端支出金额")
	private String tradeCash;
	
	//统一同一种货币用同一端
	@IsNumberJixu(keymsg="远端收入金额")
	private String tradeCash2;
	
	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
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
}
