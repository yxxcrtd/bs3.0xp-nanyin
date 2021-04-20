package com.joyintech.service.bankuplink.cfets.dto.collateralisedrepo.dialogquotation;

import java.util.List;

import com.joyintech.service.bankuplink.cfets.dto.StandardHeaderDto;


/**
 * 质押式回购
 * TradeCaptureReport报文用于发送聊天工具转对话报价交易意向消息给正回购方。
 * 见《第3卷-回购市场V3.2.3.pdf》文档
 * @author Zim
 * @time 2020-12-18
 */
// @ApiModel(value="TradeCaptureReport报文",description="用于发送聊天工具转对话报价交易意向消息给正回购方")
public class TradeCaptureReportBody extends StandardHeaderDto {

	// @ApiModelProperty(required=true,name="marketIndicator",value="10176域-市场标识(4-现券买卖)",example="4")
	private String marketIndicator;
	
	// @ApiModelProperty(required=true,name="tradeRecordID",value="11888域-交易记录编号",example="")
	private String tradeRecordID;
	
	// @ApiModelProperty(required=true,name="channel",value="11911域-渠道(iDeal、CFETS)",example="iDeal")
	private String channel;
	
	// @ApiModelProperty(required=true,name="quoteType",value="537域-报价类型(1-对话报价)",example="1")
	private Integer quoteType;
	
	// @ApiModelProperty(required=true,name="tradeReportTransTy",value="487域-操作类型(0-新增)",example="0")
	private Integer tradeReportTransTy;
	
	// @ApiModelProperty(required=true,name="trdRptStatus",value="939域-报价状态(0-正常)",example="0")
	private Integer trdRptStatus;
	
	// @ApiModelProperty(required=true,name="quoteID",value="117域-原客户参考编号",example="")
	private String quoteID;
	
	// @ApiModelProperty(required=true,name="transactTime",value="60域-业务发生时间",example="20070822-12:33:34.000")
	private String transactTime;
	
	// @ApiModelProperty(required=true,name="repoMethod",value="11376域-回购方式",example="1")
	private String repoMethod;

	// @ApiModelProperty(required=true,name="tradeLimitDays",value="10316域-回购期限",example="9")
	private String tradeLimitDays;
	
	// @ApiModelProperty(required=true,name="settlType",value="63域-清算速度",example="1")
	private String settlType;
	
	// @ApiModelProperty(required=true,name="deliveryType",value="919域-结算方式",example="0")
	private Integer deliveryType;
	
	// @ApiModelProperty(required=true,name="deliveryType2",value="10045域-到期结算方式(详细见文档)",example="0")
	private Integer deliveryType2;
	
	// @ApiModelProperty(required=true,name="clearingMethod",value="11143域-清算类型",example="6")
	private Integer clearingMethod;
	
	// @ApiModelProperty(required=true,name="settlCurrency",value="120域-结算币种",example="USD")
	private String settlCurrency;
	
	// @ApiModelProperty(required=true,name="noSides",value="552域-重复组",example="")
	private List<NoSideDto> noSides;

	public String getMarketIndicator() {
		return marketIndicator;
	}

	public void setMarketIndicator(String marketIndicator) {
		this.marketIndicator = marketIndicator;
	}

	public String getTradeRecordID() {
		return tradeRecordID;
	}

	public void setTradeRecordID(String tradeRecordID) {
		this.tradeRecordID = tradeRecordID;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Integer getQuoteType() {
		return quoteType;
	}

	public void setQuoteType(Integer quoteType) {
		this.quoteType = quoteType;
	}

	public Integer getTradeReportTransTy() {
		return tradeReportTransTy;
	}

	public void setTradeReportTransTy(Integer tradeReportTransTy) {
		this.tradeReportTransTy = tradeReportTransTy;
	}

	public Integer getTrdRptStatus() {
		return trdRptStatus;
	}

	public void setTrdRptStatus(Integer trdRptStatus) {
		this.trdRptStatus = trdRptStatus;
	}

	public String getQuoteID() {
		return quoteID;
	}

	public void setQuoteID(String quoteID) {
		this.quoteID = quoteID;
	}

	public String getTransactTime() {
		return transactTime;
	}

	public void setTransactTime(String transactTime) {
		this.transactTime = transactTime;
	}

	public String getRepoMethod() {
		return repoMethod;
	}

	public void setRepoMethod(String repoMethod) {
		this.repoMethod = repoMethod;
	}

	public String getTradeLimitDays() {
		return tradeLimitDays;
	}

	public void setTradeLimitDays(String tradeLimitDays) {
		this.tradeLimitDays = tradeLimitDays;
	}

	public String getSettlType() {
		return settlType;
	}

	public void setSettlType(String settlType) {
		this.settlType = settlType;
	}

	public Integer getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(Integer deliveryType) {
		this.deliveryType = deliveryType;
	}

	public Integer getDeliveryType2() {
		return deliveryType2;
	}

	public void setDeliveryType2(Integer deliveryType2) {
		this.deliveryType2 = deliveryType2;
	}

	public Integer getClearingMethod() {
		return clearingMethod;
	}

	public void setClearingMethod(Integer clearingMethod) {
		this.clearingMethod = clearingMethod;
	}

	public String getSettlCurrency() {
		return settlCurrency;
	}

	public void setSettlCurrency(String settlCurrency) {
		this.settlCurrency = settlCurrency;
	}

	public List<NoSideDto> getNoSides() {
		return noSides;
	}

	public void setNoSides(List<NoSideDto> noSides) {
		this.noSides = noSides;
	}
	
}
