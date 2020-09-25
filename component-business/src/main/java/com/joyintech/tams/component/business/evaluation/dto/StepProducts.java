package com.joyintech.tams.component.business.evaluation.dto;

/**
 * 分档产品
 */
public class StepProducts {

	private String financialProductCode; // 金融产品代码
	private String branchNumber; // 分支序号
	
	public String getFinancialProductCode() {
		return financialProductCode;
	}
	public void setFinancialProductCode(String financialProductCode) {
		this.financialProductCode = financialProductCode;
	}
	public String getBranchNumber() {
		return branchNumber;
	}
	public void setBranchNumber(String branchNumber) {
		this.branchNumber = branchNumber;
	}
}
