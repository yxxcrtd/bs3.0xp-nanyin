package com.joyintech.service.bankuplink.cfets.dto.cashtransaction.requestquotation;

import java.util.List;

import com.joyintech.service.bankuplink.cfets.dto.collateralisedrepo.dialogquotation.NoPartyIDDto;


// @ApiModel(value="146域",description="重复组个数，该场景取值固定为 1")
public class NoRelatedSymReqACKDto {
	
	// @ApiModelProperty(required=true,name="symbol",value="55域-债券名称",example="")
	private String symbol;
	
	// @ApiModelProperty(required=true,name="securityID",value="48域-债券代码",example="010004")
	private String securityID;
	
	// @ApiModelProperty(required=true,name="marketIndicator",value="10176域-市场标识(4-现券买卖)",example="4")
	private String marketIndicator;
	
	// @ApiModelProperty(required=true,name="quoteType",value="537域-报价类型(112-请求报价)",example="112")
	private Integer quoteType;
	
	// @ApiModelProperty(required=true,name="quoteStatus",value="297域-报价状态(16:正常,19:撤销,115:做市方拒绝,21:过期,107:全部成交)",example="16")
	private Integer quoteStatus;
	
	// @ApiModelProperty(required=true,name="transactTime",value="60域-业务发生时间",example="20070822-12:33:34.000")
	private String transactTime;
	
	// @ApiModelProperty(required=true,name="noPartyIDs",value="453域-发起方信息",example="")
	private List<NoPartyIDDto> noPartyIDs;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getSecurityID() {
		return securityID;
	}

	public void setSecurityID(String securityID) {
		this.securityID = securityID;
	}

	public String getMarketIndicator() {
		return marketIndicator;
	}

	public void setMarketIndicator(String marketIndicator) {
		this.marketIndicator = marketIndicator;
	}

	public Integer getQuoteType() {
		return quoteType;
	}

	public void setQuoteType(Integer quoteType) {
		this.quoteType = quoteType;
	}

	public Integer getQuoteStatus() {
		return quoteStatus;
	}

	public void setQuoteStatus(Integer quoteStatus) {
		this.quoteStatus = quoteStatus;
	}

	public String getTransactTime() {
		return transactTime;
	}

	public void setTransactTime(String transactTime) {
		this.transactTime = transactTime;
	}

	public List<NoPartyIDDto> getNoPartyIDs() {
		return noPartyIDs;
	}

	public void setNoPartyIDs(List<NoPartyIDDto> noPartyIDs) {
		this.noPartyIDs = noPartyIDs;
	}
	
}
