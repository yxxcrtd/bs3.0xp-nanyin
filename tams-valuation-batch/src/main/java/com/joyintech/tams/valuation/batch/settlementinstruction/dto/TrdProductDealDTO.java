package com.joyintech.tams.valuation.batch.settlementinstruction.dto;

import java.math.BigDecimal;

import com.joyintech.tams.common.base.entity.BokDetailId;

public class TrdProductDealDTO {

	//交易编号
	private String tradeId;
	//交易金额
	private BigDecimal tradeAmt;
	
	//全价总额
	private  BigDecimal fpriceAmt;
	
	//保证金金额
	private BigDecimal depositAmt;
	//交易币种
	private  String ccy;
	
	//前置交易编号
	private String bTradeId;
	
	//账务明细代码3
	private BokDetailId BokDetailId3;
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	public BigDecimal getTradeAmt() {
		return tradeAmt;
	}
	public void setTradeAmt(BigDecimal tradeAmt) {
		this.tradeAmt = tradeAmt;
	}
	public BigDecimal getDepositAmt() {
		return depositAmt;
	}
	public void setDepositAmt(BigDecimal depositAmt) {
		this.depositAmt = depositAmt;
	}
	
	public String getCcy() {
		return ccy;
	}
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}
	public BokDetailId getBokDetailId3() {
		return BokDetailId3;
	}
	public void setBokDetailId3(BokDetailId bokDetailId3) {
		BokDetailId3 = bokDetailId3;
	}
	public BigDecimal getFpriceAmt() {
		return fpriceAmt;
	}
	public void setFpriceAmt(BigDecimal fpriceAmt) {
		this.fpriceAmt = fpriceAmt;
	}
	public String getbTradeId() {
		return bTradeId;
	}
	public void setbTradeId(String bTradeId) {
		this.bTradeId = bTradeId;
	}
	
	
}
