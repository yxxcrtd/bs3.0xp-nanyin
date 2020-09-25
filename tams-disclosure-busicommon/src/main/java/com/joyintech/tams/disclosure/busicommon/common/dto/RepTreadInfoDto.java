package com.joyintech.tams.disclosure.busicommon.common.dto;

import java.math.BigDecimal;

public class RepTreadInfoDto {

	/**
	 * 资产名称
	 */
	private  String assetName;
	
	/**
	 * 买入方
	 */
	private  String buyParty;
	
	/**
	 * 卖出方
	 */
	private  String  sellParty;
	
	/**
	 * 交易金额
	 */
	private  BigDecimal  tradeAmt;
	
	/**
	 * 合同号
	 */
    private  String contracNo;
	
    
    
	public String getContracNo() {
		return contracNo;
	}

	public void setContracNo(String contracNo) {
		this.contracNo = contracNo;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public String getBuyParty() {
		return buyParty;
	}

	public void setBuyParty(String buyParty) {
		this.buyParty = buyParty;
	}

	public String getSellParty() {
		return sellParty;
	}

	public void setSellParty(String sellParty) {
		this.sellParty = sellParty;
	}

	public BigDecimal getTradeAmt() {
		return tradeAmt;
	}

	public void setTradeAmt(BigDecimal tradeAmt) {
		this.tradeAmt = tradeAmt;
	}
	
	
}
