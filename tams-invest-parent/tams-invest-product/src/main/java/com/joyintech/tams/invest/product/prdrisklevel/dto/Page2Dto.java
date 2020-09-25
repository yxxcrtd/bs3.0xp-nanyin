package com.joyintech.tams.invest.product.prdrisklevel.dto;

import java.util.Date;

public class Page2Dto {

	private String finProductName; // 产品设计系列名称
	private String riskLevel; // 风险等级
	private String ajustRiskLevel; // 调整后风险等级
	// 生成时间
	private Date generateTime;
	
	public String getFinProductName() {
		return finProductName;
	}
	public void setFinProductName(String finProductName) {
		this.finProductName = finProductName;
	}
	public String getRiskLevel() {
		return riskLevel;
	}
	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}
	public String getAjustRiskLevel() {
		return ajustRiskLevel;
	}
	public void setAjustRiskLevel(String ajustRiskLevel) {
		this.ajustRiskLevel = ajustRiskLevel;
	}
	public Date getGenerateTime() {
		return generateTime;
	}
	public void setGenerateTime(Date generateTime) {
		this.generateTime = generateTime;
	}
}
