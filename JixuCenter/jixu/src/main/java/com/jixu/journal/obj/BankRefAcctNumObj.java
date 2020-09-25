package com.jixu.journal.obj;

import java.io.Serializable;

import com.jixu.common.func.GLACCT;

public class BankRefAcctNumObj implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2924387500872110015L;

	private String cashAcctNO = GLACCT.UNKNOWN; // 现金入账科目号
	private String cashFXRealAcctNO = GLACCT.UNKNOWN; // 已实现外汇损益科目号
	private String cashFXUnRealNO = GLACCT.UNKNOWN; // 未实现外汇损益科目号
	private String cashFXUnRealNO_CX = GLACCT.UNKNOWN; // 未实现外汇损益冲销科目号

	private String interestAcctNO = GLACCT.UNKNOWN; // 利息入账科目
	private String interestRecNO = GLACCT.UNKNOWN; // 应收利息科目。利息结算时计入此科目。
	private String interestFXrealNO = GLACCT.UNKNOWN; // 利息已实现外汇损益
	private String inteFXUnrealNO = GLACCT.UNKNOWN; // 应收利息未实现外汇损益
	private String inteFXUnrealNO_CX = GLACCT.UNKNOWN; // 应收利息未实现外汇损益冲销
	private String inteCalAcctNO = GLACCT.UNKNOWN; // 应计利息科目。（每天算收到的利息入此科目）
	private String inteCalAcctNO_CX = GLACCT.UNKNOWN; // 应计利息冲销科目。（每天算收到的利息入此科目）

	public String getInteCalAcctNO() {
		return inteCalAcctNO;
	}

	public void setInteCalAcctNO(String inteCalAcctNO) {
		this.inteCalAcctNO = inteCalAcctNO;
	}

	public String getInteCalAcctNO_CX() {
		return inteCalAcctNO_CX;
	}

	public void setInteCalAcctNO_CX(String inteCalAcctNO_CX) {
		this.inteCalAcctNO_CX = inteCalAcctNO_CX;
	}

	public String getCashAcctNO() {
		return cashAcctNO;
	}

	public void setCashAcctNO(String cashAcctNO) {
		this.cashAcctNO = cashAcctNO;
	}

	public String getCashFXRealAcctNO() {
		return cashFXRealAcctNO;
	}

	public void setCashFXRealAcctNO(String cashFXRealAcctNO) {
		this.cashFXRealAcctNO = cashFXRealAcctNO;
	}

	public String getCashFXUnRealNO() {
		return cashFXUnRealNO;
	}

	public void setCashFXUnRealNO(String cashFXUnRealNO) {
		this.cashFXUnRealNO = cashFXUnRealNO;
	}

	public String getCashFXUnRealNO_CX() {
		return cashFXUnRealNO_CX;
	}

	public void setCashFXUnRealNO_CX(String cashFXUnRealNO_CX) {
		this.cashFXUnRealNO_CX = cashFXUnRealNO_CX;
	}

	public String getInterestAcctNO() {
		return interestAcctNO;
	}

	public void setInterestAcctNO(String interestAcctNO) {
		this.interestAcctNO = interestAcctNO;
	}

	public String getInterestRecNO() {
		return interestRecNO;
	}

	public void setInterestRecNO(String interestRecNO) {
		this.interestRecNO = interestRecNO;
	}

	public String getInterestFXrealNO() {
		return interestFXrealNO;
	}

	public void setInterestFXrealNO(String interestFXrealNO) {
		this.interestFXrealNO = interestFXrealNO;
	}

	public String getInteFXUnrealNO() {
		return inteFXUnrealNO;
	}

	public void setInteFXUnrealNO(String inteFXUnrealNO) {
		this.inteFXUnrealNO = inteFXUnrealNO;
	}

	public String getInteFXUnrealNO_CX() {
		return inteFXUnrealNO_CX;
	}

	public void setInteFXUnrealNO_CX(String inteFXUnrealNO_CX) {
		this.inteFXUnrealNO_CX = inteFXUnrealNO_CX;
	}


}
