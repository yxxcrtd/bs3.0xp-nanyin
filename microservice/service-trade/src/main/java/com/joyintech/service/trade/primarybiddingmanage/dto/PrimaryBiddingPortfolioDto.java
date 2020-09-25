package com.joyintech.service.trade.primarybiddingmanage.dto;


import com.joyintech.tams.common.base.entity.EtrEntrustBidPrice;

import java.math.BigDecimal;


public class PrimaryBiddingPortfolioDto extends EtrEntrustBidPrice {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    /**
     * 组合代码
     */
    private String portfolioId;
    
    /**
     * 组合名称
     */
    private String portfolioName;
    
    /**
     * 投标券面总额
     */
    private BigDecimal allBidPrinAmt;
    
    /**
     * 汇总投标列表--投标券面总额
     */
    private BigDecimal hzallBidPrinAmt;
    
	public String getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getPortfolioName() {
		return portfolioName;
	}

	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}

	public BigDecimal getAllBidPrinAmt() {
		return allBidPrinAmt;
	}

	public void setAllBidPrinAmt(BigDecimal allBidPrinAmt) {
		this.allBidPrinAmt = allBidPrinAmt;
	}

	public BigDecimal getHzallBidPrinAmt() {
		return hzallBidPrinAmt;
	}

	public void setHzallBidPrinAmt(BigDecimal hzallBidPrinAmt) {
		this.hzallBidPrinAmt = hzallBidPrinAmt;
	}
}
