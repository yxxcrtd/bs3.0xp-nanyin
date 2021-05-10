package com.joyintech.tams.invest.batch.prebatchcheck.assetmarketdata.dto;

import com.joyintech.tams.invest.common.prebatch.dto.SysPrebatchResultDetail;

public class AssetMarketDataResultDto extends SysPrebatchResultDetail{
	
	public String getBooksetId() {
		return booksetId;
	}
	public void setBooksetId(String booksetId) {
		this.booksetId = booksetId;
	}
	public String getInvAim() {
		return invAim;
	}
	public void setInvAim(String invAim) {
		this.invAim = invAim;
	}
	public String getSecType() {
		return secType;
	}
	public void setSecType(String secType) {
		this.secType = secType;
	}
	public String getTradeMarket() {
		return tradeMarket;
	}
	public void setTradeMarket(String tradeMarket) {
		this.tradeMarket = tradeMarket;
	}
	public String getCalenderId() {
		return calenderId;
	}
	public void setCalenderId(String calenderId) {
		this.calenderId = calenderId;
	}
	private String booksetId;
	private String invAim;
	
	private String secType;
	
	private String tradeMarket;
	
	private String calenderId;

}
