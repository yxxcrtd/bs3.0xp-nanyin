package com.joyintech.tams.invest.common.risktrial.dto;

/**
 * 监督指标输出结果DTO
 * @author 裴家俊
 * @version 1.0 2018年11月28日
 * @see InvestTradeTrialOutput
 * @since 1.0
 */
public class InvestTradeTrialOutput {

	/**
     * 日期/必填
     */
	private String valDate; 
	/**
     * 组合代码/必填
     */
	private String portfolioCode;
	/**
     * 资产代码/非必填
     */
	private String assetCode;
	 /**
     * 交易金额/交易份额/此金额实时影响指标表达式的计算/必填
     */
	private String tradeAmount;
	/**
     * 监控时点/必填
     */
	private String timePoint;
	/**
     * 资产类型/必填
     */
	private String assetType;
	/**
     * 资产二级类型/非必填
     */
	private String assetSecType;
	
	public String getValDate() {
		return valDate;
	}
	public void setValDate(String valDate) {
		this.valDate = valDate;
	}
	public String getPortfolioCode() {
		return portfolioCode;
	}
	public void setPortfolioCode(String portfolioCode) {
		this.portfolioCode = portfolioCode;
	}
	public String getAssetCode() {
		return assetCode;
	}
	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}
	public String getTradeAmount() {
		return tradeAmount;
	}
	public void setTradeAmount(String tradeAmount) {
		this.tradeAmount = tradeAmount;
	}
	public String getTimePoint() {
		return timePoint;
	}
	public void setTimePoint(String timePoint) {
		this.timePoint = timePoint;
	}
	public String getAssetType() {
		return assetType;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	public String getAssetSecType() {
		return assetSecType;
	}
	public void setAssetSecType(String assetSecType) {
		this.assetSecType = assetSecType;
	}
	public String replaceForString(String sql) {
		return sql.replaceAll("\\$\\{valDate\\}", valDate)
			.replaceAll("\\$\\{portfolioCode\\}", portfolioCode)
			.replaceAll("\\$\\{assetCode\\}", assetCode);
	}
}
