package com.joyintech.tams.component.business.evaluation.dto;

import java.util.Date;

/**
 * yangxiaofeng
 * 金融产品信息
 * 2019年10月11日
 */
public class FinancialProductInformationDto {

	private String finProdId; // 金融产品代码
	private String branch; // 分支序号
	private String finProdType; // 金融产品类型
	private String revenueType; // 收益类型
	private String processingMode; // 处理模式
	private Date valueDate; // 起息日
	
	public String getFinProdId() {
		return finProdId;
	}
	public void setFinProdId(String finProdId) {
		this.finProdId = finProdId;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getFinProdType() {
		return finProdType;
	}
	public void setFinProdType(String finProdType) {
		this.finProdType = finProdType;
	}
	public String getRevenueType() {
		return revenueType;
	}
	public void setRevenueType(String revenueType) {
		this.revenueType = revenueType;
	}
	public String getProcessingMode() {
		return processingMode;
	}
	public void setProcessingMode(String processingMode) {
		this.processingMode = processingMode;
	}
	public Date getValueDate() {
		return valueDate;
	}
	public void setValueDate(Date valueDate) {
		this.valueDate = valueDate;
	}
}
