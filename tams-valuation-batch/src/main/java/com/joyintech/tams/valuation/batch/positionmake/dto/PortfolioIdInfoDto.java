package com.joyintech.tams.valuation.batch.positionmake.dto;

/**
 * 投组相关信息
 * 
 * @author Administrator
 *
 */
public class PortfolioIdInfoDto {

	/**
	 * 投资组合
	 */
	private String portfolioId;

	/**
	 * 账套代码
	 */
	private String booksetId;

	public String getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getBooksetId() {
		return booksetId;
	}

	public void setBooksetId(String booksetId) {
		this.booksetId = booksetId;
	}

}
