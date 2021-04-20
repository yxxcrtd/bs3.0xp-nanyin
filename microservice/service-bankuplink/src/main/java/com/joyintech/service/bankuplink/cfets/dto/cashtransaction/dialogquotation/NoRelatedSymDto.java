package com.joyintech.service.bankuplink.cfets.dto.cashtransaction.dialogquotation;

import java.util.List;

import com.joyintech.service.bankuplink.cfets.dto.collateralisedrepo.dialogquotation.NoPartyIDDto;


// @ApiModel(value="146域",description="重复组个数，该场景取值固定为 1")
public class NoRelatedSymDto {
	
	// @ApiModelProperty(required=true,name="symbol",value="55域-债券名称",example="")
	private String symbol;
	
	// @ApiModelProperty(required=true,name="securityID",value="48域-债券代码",example="010004")
	private String securityID;
	
	// @ApiModelProperty(required=true,name="marketIndicator",value="10176域-市场标识(4-现券买卖)",example="4")
	private String marketIndicator;
	
	// @ApiModelProperty(required=true,name="quoteType",value="537域-报价类型(112-请求报价)",example="112")
	private Integer quoteType;
	
	// @ApiModelProperty(required=false,name="quoteStatus",value="297域-报价状态(16:正常,19:撤销,115:做市方拒绝,21:过期,107:全部成交)",example="16")
	private Integer quoteStatus;
	
	// @ApiModelProperty(required=true,name="validUntilTime",value="62域-报价有效时间",example="20070822-12:33:34.000")
	private String validUntilTime;
	
	// @ApiModelProperty(required=false,name="transactTime",value="60域-业务发生时间",example="20070822-12:33:34.000")
	private String transactTime;
	
	// @ApiModelProperty(required=true,name="side",value="54域-交易方向",example="1")
	private Character side;
	
	// @ApiModelProperty(required=false,name="accruedInterestAmt",value="159域-应计利息",example="1000.23541")
	private String accruedInterestAmt;
	
	// @ApiModelProperty(required=false,name="accruedInterestTotalAmt",value="10002域-应计利息总额",example="23123.23")
	private String accruedInterestTotalAmt;
	
	// @ApiModelProperty(required=true,name="orderQty",value="38域-券面总额",example="100000")
	private String orderQty;
	
	// @ApiModelProperty(required=true,name="deliveryType",value="919域-结算方式",example="0")
	private Integer deliveryType;
	
	// @ApiModelProperty(required=true,name="clearingMethod",value="11143域-清算类型",example="6")
	private Integer clearingMethod;
	
	// @ApiModelProperty(required=true,name="settlType",value="63域-清算速度",example="1")
	private String settlType;
	
	// @ApiModelProperty(required=false,name="settlDate",value="64域-结算日",example="20121111")
	private String settlDate;
	
	// @ApiModelProperty(required=false,name="principal",value="10239域-每百元本金额",example="12.22")
	private String principal;
	
	// @ApiModelProperty(required=false,name="totalPrincipal",value="10310域-总本金或者当前本金总额",example="1122.00")
	private String totalPrincipal;
	
	// @ApiModelProperty(required=true,name="noPartyIDs",value="453域-发起方信息",example="")
	private List<NoPartyIDDto> noPartyIDs;
	
	// @ApiModelProperty(required=false,name="noPartyIDs",value="78域-发起方信息",example="")
	private List<NoAllocDto> noAllocDtos;

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

	public String getValidUntilTime() {
		return validUntilTime;
	}

	public void setValidUntilTime(String validUntilTime) {
		this.validUntilTime = validUntilTime;
	}

	public String getTransactTime() {
		return transactTime;
	}

	public void setTransactTime(String transactTime) {
		this.transactTime = transactTime;
	}


	public Character getSide() {
		return side;
	}

	public void setSide(Character side) {
		this.side = side;
	}

	public String getAccruedInterestAmt() {
		return accruedInterestAmt;
	}

	public void setAccruedInterestAmt(String accruedInterestAmt) {
		this.accruedInterestAmt = accruedInterestAmt;
	}

	public String getAccruedInterestTotalAmt() {
		return accruedInterestTotalAmt;
	}

	public void setAccruedInterestTotalAmt(String accruedInterestTotalAmt) {
		this.accruedInterestTotalAmt = accruedInterestTotalAmt;
	}

	public String getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(String orderQty) {
		this.orderQty = orderQty;
	}

	public Integer getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(Integer deliveryType) {
		this.deliveryType = deliveryType;
	}

	public Integer getClearingMethod() {
		return clearingMethod;
	}

	public void setClearingMethod(Integer clearingMethod) {
		this.clearingMethod = clearingMethod;
	}

	public String getSettlType() {
		return settlType;
	}

	public void setSettlType(String settlType) {
		this.settlType = settlType;
	}

	public String getSettlDate() {
		return settlDate;
	}

	public void setSettlDate(String settlDate) {
		this.settlDate = settlDate;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getTotalPrincipal() {
		return totalPrincipal;
	}

	public void setTotalPrincipal(String totalPrincipal) {
		this.totalPrincipal = totalPrincipal;
	}

	public List<NoPartyIDDto> getNoPartyIDs() {
		return noPartyIDs;
	}

	public void setNoPartyIDs(List<NoPartyIDDto> noPartyIDs) {
		this.noPartyIDs = noPartyIDs;
	}

	public List<NoAllocDto> getNoAllocDtos() {
		return noAllocDtos;
	}

	public void setNoAllocDtos(List<NoAllocDto> noAllocDtos) {
		this.noAllocDtos = noAllocDtos;
	}
	
}
