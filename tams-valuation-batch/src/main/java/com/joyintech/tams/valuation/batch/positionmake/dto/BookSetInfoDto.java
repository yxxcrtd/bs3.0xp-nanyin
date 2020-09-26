package com.joyintech.tams.valuation.batch.positionmake.dto;

/**
 * 账套组合信息
 * 
 * @author Administrator
 *
 */
public class BookSetInfoDto {
	/**
	 * 账套代码
	 */
	private String booksetId;

	/**
	 * 投资组合代码
	 */
	private String portfolioId;

	public String getBooksetId() {
		return booksetId;
	}

	public void setBooksetId(String booksetId) {
		this.booksetId = booksetId;
	}

	public String getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}

}
