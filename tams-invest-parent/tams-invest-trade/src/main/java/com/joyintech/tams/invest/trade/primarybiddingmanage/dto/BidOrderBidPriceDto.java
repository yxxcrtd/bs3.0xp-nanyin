package com.joyintech.tams.invest.trade.primarybiddingmanage.dto;

import com.joyintech.tams.common.base.entity.BidOrderBidPrice;

import java.math.BigDecimal;


/**
 * @author: 裴家俊
 * @create: 2019-05-10 09:20
 **/
public class BidOrderBidPriceDto extends BidOrderBidPrice {
	
	private static final long serialVersionUID = 1L;
	
	private String finprodId;
	
	private String finprodAbbr;
	
	private String finprodMarketId;
	
	private String portfolioId;
	
	private String portfolioName;
	
	private BigDecimal bidPrinAmt;
	
	private String invManagerId;

	//多个投标标位用逗号分隔
	private String bidPriceStr;

	public String getBidPriceStr() {
		return bidPriceStr;
	}

	public void setBidPriceStr(String bidPriceStr) {
		this.bidPriceStr = bidPriceStr;
	}

	public String getFinprodId() {
		return finprodId;
	}

	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}


	public String getFinprodAbbr() {
		return finprodAbbr;
	}


	public void setFinprodAbbr(String finprodAbbr) {
		this.finprodAbbr = finprodAbbr;
	}


	public String getFinprodMarketId() {
		return finprodMarketId;
	}


	public void setFinprodMarketId(String finprodMarketId) {
		this.finprodMarketId = finprodMarketId;
	}


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

	public BigDecimal getBidPrinAmt() {
		return bidPrinAmt;
	}

	public void setBidPrinAmt(BigDecimal bidPrinAmt) {
		this.bidPrinAmt = bidPrinAmt;
	}

	public String getInvManagerId() {
		return invManagerId;
	}

	public void setInvManagerId(String invManagerId) {
		this.invManagerId = invManagerId;
	}

}
