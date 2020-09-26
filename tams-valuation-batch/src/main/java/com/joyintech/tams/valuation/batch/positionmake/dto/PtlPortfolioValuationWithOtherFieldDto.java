package com.joyintech.tams.valuation.batch.positionmake.dto;

import com.joyintech.tams.common.base.entity.PtlPortfolioValuation;

/**
 * 包含其他字段的【组合估值头寸】实体类
 * 
 * @author Administrator
 *
 */
public class PtlPortfolioValuationWithOtherFieldDto extends PtlPortfolioValuation {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 收益类型
	 */
	private String profitType;

	/**
	 * 层级（级别序号）
	 */
	private String lev;

	/**
	 * 账套代码
	 */
	private String booksetId;

	public String getProfitType() {
		return profitType;
	}

	public void setProfitType(String profitType) {
		this.profitType = profitType;
	}

	public String getLev() {
		return lev;
	}

	public void setLev(String lev) {
		this.lev = lev;
	}

	public String getBooksetId() {
		return booksetId;
	}

	public void setBooksetId(String booksetId) {
		this.booksetId = booksetId;
	}

}
