package com.joyintech.tams.invest.common.risktrial.dto;

import java.util.Date;

import com.joyintech.framework.base.BaseEntity;
/**
 * 风险试算明细结果DTO
 * @author 裴家俊
 * @version 1.0 2018年11月30日
 * @see RiskTrialRetDetailDto
 * @since 1.0
 */
public class RiskTrialRetDetailDto extends BaseEntity {

	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * 组合(包)代码
	 */
	private String portfolioId;
	/*
	 * 指标编号
	 */
	private String indId;
	/*
	 * 指标名称
	 */
	private String indName;
	/*
	 * 监督日期
	 */
	private Date supDate;
	/*
	 * 监督值
	 */
	private String supVal;
	/*
	 * 监督状态
	 */
	private String supStat;
	
    private String assetName;
    
    private String assetCode;



	
	
	public String getPortfolioId() {
		return portfolioId;
	}
	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}
	public String getIndId() {
		return indId;
	}
	public void setIndId(String indId) {
		this.indId = indId;
	}
	public String getIndName() {
		return indName;
	}
	public void setIndName(String indName) {
		this.indName = indName;
	}
	public Date getSupDate() {
		return supDate;
	}
	public void setSupDate(Date supDate) {
		this.supDate = supDate;
	}
	public String getSupVal() {
		return supVal;
	}
	public void setSupVal(String supVal) {
		this.supVal = supVal;
	}
	public String getSupStat() {
		return supStat;
	}
	public void setSupStat(String supStat) {
		this.supStat = supStat;
	}
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public String getAssetCode() {
		return assetCode;
	}
	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}

	
}
