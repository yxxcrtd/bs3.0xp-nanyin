package com.joyintech.service.trade.transfer.sectransfer.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;

/**
*
* 债券交易拆分
* @author zcw
* @version 1.0 2019年11月21日
* @see SecTransferDto
* @since 1.0
*/
public class SecTransferDto extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5576407185793143146L;
	
	/**
     * 拆分交易编号
     */
    private String tradeId;
    /**
     * 组合代码
     */
    private String portfolioId;
    /**
     * 组合名称
     */
    private String portfolioName;
    /**
     * 资产代码
     */
    private String finprodMarketId;
    /**
     * 交易日期
     */
    private String tradeDate;
    /**
     * 本金金额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal prinAmount;
    /**
     * 券面总额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal tradeAmt;
    /**
     * 净价总额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal cpriceAmt;
    /**
     * 应计利息总额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal intAmt;
    /**
     * 全价总额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal fpriceAmt;
    /**
     * 交易手续费
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal procedureAmt;
    /**
     * 结算费
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal settleAmt;
    /**
     * 佣金
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal commissionAmt;
    /**
     * 通道费
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal channelFee;
    /**
     * 交易金额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal tradeRepAmt;
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
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
	public String getFinprodMarketId() {
		return finprodMarketId;
	}
	public void setFinprodMarketId(String finprodMarketId) {
		this.finprodMarketId = finprodMarketId;
	}
	public String getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}
	public BigDecimal getPrinAmount() {
		return prinAmount;
	}
	public void setPrinAmount(BigDecimal prinAmount) {
		this.prinAmount = prinAmount;
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
	public BigDecimal getProcedureAmt() {
		return procedureAmt;
	}
	public void setProcedureAmt(BigDecimal procedureAmt) {
		this.procedureAmt = procedureAmt;
	}
	public BigDecimal getSettleAmt() {
		return settleAmt;
	}
	public void setSettleAmt(BigDecimal settleAmt) {
		this.settleAmt = settleAmt;
	}
	public BigDecimal getCommissionAmt() {
		return commissionAmt;
	}
	public void setCommissionAmt(BigDecimal commissionAmt) {
		this.commissionAmt = commissionAmt;
	}
	public BigDecimal getChannelFee() {
		return channelFee;
	}
	public void setChannelFee(BigDecimal channelFee) {
		this.channelFee = channelFee;
	}
	public BigDecimal getTradeRepAmt() {
		return tradeRepAmt;
	}
	public void setTradeRepAmt(BigDecimal tradeRepAmt) {
		this.tradeRepAmt = tradeRepAmt;
	}

}
