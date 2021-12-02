package com.joyintech.service.entrust.etrbondbiddingresult.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.tams.common.base.entity.EtrEntrust;

import java.math.BigDecimal;
import java.util.Date;

public class EtrbondbiddingresultDto extends EtrEntrust {

	private String bidId;		// 投标编号
	private String portfolioName;	// 组合名称
	private String finprodMarketId;	// 资产代码
	private String finprodName;	// 资产名称
	private Date payDate; 		// 缴款日
	private String payType;		// 缴款方式，一级分销、一级缴款、二级交易
	private BigDecimal bidPrinAmt;	// 投标券面
	private BigDecimal winPrinAmt;	// 中标券面总额
	private BigDecimal bidPrice;	// 投标标位
	private BigDecimal winPrice;	// 中标标位
	private BigDecimal winCprice;	// 中标净价
	private Date bidDate;			// 投标日期
	private String paymentStatus;	// 缴款状态

	private String bidPriceStr;

	public String getPortfolioName() {
		return portfolioName;
	}

	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}

	public String getFinprodMarketId() {
		return finprodMarketId;
	}

	public void setFinprodMarketId(String finprodMarketId) {
		this.finprodMarketId = finprodMarketId;
	}

	public String getFinprodName() {
		return finprodName;
	}

	public void setFinprodName(String finprodName) {
		this.finprodName = finprodName;
	}

	public String getBidId() {
		return bidId;
	}

	public void setBidId(String bidId) {
		this.bidId = bidId;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public BigDecimal getBidPrinAmt() {
		return bidPrinAmt;
	}

	public void setBidPrinAmt(BigDecimal bidPrinAmt) {
		this.bidPrinAmt = bidPrinAmt;
	}

	public BigDecimal getWinPrinAmt() {
		return winPrinAmt;
	}

	public void setWinPrinAmt(BigDecimal winPrinAmt) {
		this.winPrinAmt = winPrinAmt;
	}

	public BigDecimal getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(BigDecimal bidPrice) {
		this.bidPrice = bidPrice;
	}

	public BigDecimal getWinPrice() {
		return winPrice;
	}

	public void setWinPrice(BigDecimal winPrice) {
		this.winPrice = winPrice;
	}

	public Date getBidDate() {
		return bidDate;
	}

	public void setBidDate(Date bidDate) {
		this.bidDate = bidDate;
	}

	public BigDecimal getWinCprice() {
		return winCprice;
	}

	public void setWinCprice(BigDecimal winCprice) {
		this.winCprice = winCprice;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getBidPriceStr() {
		return bidPriceStr;
	}

	public void setBidPriceStr(String bidPriceStr) {
		this.bidPriceStr = bidPriceStr;
	}
}
