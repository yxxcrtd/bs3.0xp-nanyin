package com.joyintech.tams.invest.common.dto.common;

import java.math.BigDecimal;

import com.joyintech.framework.base.BaseEntity;

/**
 * 债券交易预录入组合信息实体类
 * @author Administrator
 *
 */
public class TrdTransfenpeiDto extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 委托代码
	 */
	private String entrustId;
	/**
	 * 组合代码
	 */
	private String portfolioId;
	/**
	 * 可委托金额
	 */
	private BigDecimal doEntrustMoney;
	/**
	 * 券面总额
	 */
	private BigDecimal tradeAmt;
	/**
	 * 净价总额
	 */
	private BigDecimal cpriceAmt;
	/**
	 * 应计利息总额
	 */
	private BigDecimal intAmt;
	/**
	 * 全价总额
	 */
	private BigDecimal fpriceAmt;
	/**
	 * 组合T日可用金额
	 */
	private BigDecimal bussTrdAvailableCashQueryavalibleCash;
	/**
	 * 组合T+1日可用金额
	 */
	private BigDecimal bussTrdAvailableCashQueryavalibleCash1;
	
	
	public String getEntrustId() {
		return entrustId;
	}
	public void setEntrustId(String entrustId) {
		this.entrustId = entrustId;
	}
	public String getPortfolioId() {
		return portfolioId;
	}
	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}
	public BigDecimal getDoEntrustMoney() {
		return doEntrustMoney;
	}
	public void setDoEntrustMoney(BigDecimal doEntrustMoney) {
		this.doEntrustMoney = doEntrustMoney;
	}
	public BigDecimal getTradeAmt() {
		return tradeAmt;
	}
	public void setTradeAmt(BigDecimal tradeAmt) {
		this.tradeAmt = tradeAmt;
	}
	public BigDecimal getCpriceAmt() {
		return cpriceAmt;
	}
	public void setCpriceAmt(BigDecimal cpriceAmt) {
		this.cpriceAmt = cpriceAmt;
	}
	public BigDecimal getIntAmt() {
		return intAmt;
	}
	public void setIntAmt(BigDecimal intAmt) {
		this.intAmt = intAmt;
	}
	public BigDecimal getFpriceAmt() {
		return fpriceAmt;
	}
	public void setFpriceAmt(BigDecimal fpriceAmt) {
		this.fpriceAmt = fpriceAmt;
	}
	public BigDecimal getBussTrdAvailableCashQueryavalibleCash() {
		return bussTrdAvailableCashQueryavalibleCash;
	}
	public void setBussTrdAvailableCashQueryavalibleCash(BigDecimal bussTrdAvailableCashQueryavalibleCash) {
		this.bussTrdAvailableCashQueryavalibleCash = bussTrdAvailableCashQueryavalibleCash;
	}
	public BigDecimal getBussTrdAvailableCashQueryavalibleCash1() {
		return bussTrdAvailableCashQueryavalibleCash1;
	}
	public void setBussTrdAvailableCashQueryavalibleCash1(BigDecimal bussTrdAvailableCashQueryavalibleCash1) {
		this.bussTrdAvailableCashQueryavalibleCash1 = bussTrdAvailableCashQueryavalibleCash1;
	}

}
