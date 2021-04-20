package com.joyintech.service.bankuplink.cfets.dto.collateralisedrepo.dialogquotation;


// @ApiModel(value="295域",description="295域")
public class NoQuoteEntrieDto {

	// @ApiModelProperty(required=true,name="symbol",value="55域-债券名称",example="")
	private String symbol;
	
	// @ApiModelProperty(required=true,name="marketIndicator",value="10176域-市场标识(9-质押式回购)",example="9")
	private String marketIndicator;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getMarketIndicator() {
		return marketIndicator;
	}

	public void setMarketIndicator(String marketIndicator) {
		this.marketIndicator = marketIndicator;
	}
	
}
