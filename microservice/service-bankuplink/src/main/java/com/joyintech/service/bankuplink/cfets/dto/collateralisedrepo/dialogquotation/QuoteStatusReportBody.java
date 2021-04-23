package com.joyintech.service.bankuplink.cfets.dto.collateralisedrepo.dialogquotation;

import java.math.BigDecimal;
import java.util.List;

import com.joyintech.service.bankuplink.cfets.dto.StandardHeaderDto;


/**
 * 质押式回购
 * QuoteStatusReport报文用于反馈报价的状态及发送报价撤销、拒绝的通知。
 * 见《第3卷-回购市场V3.2.3.pdf》文档
 * @author Zim
 * @time 2020-12-18
 */
// @ApiModel(value="QuoteStatusReport报文",description="用于反馈报价的状态及发送报价撤销、拒绝的通知")
public class QuoteStatusReportBody extends StandardHeaderDto {
	// @ApiModelProperty(required=true,name="marketIndicator",value="10176域-市场标识(4-现券买卖)",example="4")
	private String marketIndicator;
	
	// @ApiModelProperty(required=true,name="tradeRecordID",value="11888域-交易记录编号",example="")
	private String tradeRecordID;
	
	// @ApiModelProperty(required=true,name="channel",value="11911域-渠道(iDeal、CFETS)",example="iDeal")
	private String channel;
	
	// @ApiModelProperty(required=true,name="quoteType",value="537域-报价类型(1-对话报价)",example="1")
	private Integer quoteType;
	
	// @ApiModelProperty(required=true,name="quoteTransType",value="10272域-操作类型(N:新增,R:修改,1:交谈)",example="N")
	private String quoteTransType;
	
	// @ApiModelProperty(required=true,name="quoteStatus",value="297域-报价状态(16:正常,19:撤销,5:拒绝,21:过期,107:全部成交)",example="16")
	private Integer quoteStatus;
	
	// @ApiModelProperty(required=true,name="clOrdID",value="11域-客户参考编号",example="")
	private String clOrdID;
	
	// @ApiModelProperty(required=true,name="quoteID",value="117域-原客户参考编号",example="")
	private String quoteID;
	
	// @ApiModelProperty(required=true,name="transactTime",value="60域-业务发生时间",example="20070822-12:33:34.000")
	private String transactTime;
	
	// @ApiModelProperty(required=true,name="side",value="54域-交易方向",example="2")
	private String side;
	
	// @ApiModelProperty(required=true,name="repoMethod",value="11376域-回购方式",example="1")
	private String repoMethod;

	// @ApiModelProperty(required=true,name="tradeLimitDays",value="10316域-回购期限",example="9")
	private String tradeLimitDays;
	
	// @ApiModelProperty(required=true,name="price",value="44域-净价",example="98.0000")
	private BigDecimal price;
	
	// @ApiModelProperty(required=true,name="tradeCashAmt",value="10312域-交易金额",example="3480000.25")
	private BigDecimal tradeCashAmt;
	
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
	
	// @ApiModelProperty(required=true,name="settlCurrFxRate",value="155域-汇率",example="1.126545")
	private BigDecimal settlCurrFxRate;
	
	// @ApiModelProperty(required=true,name="userReference1",value="11405域-用户参考数据1",example="")
	private String userReference1;
	
	// @ApiModelProperty(required=true,name="userReference2",value="11406域-用户参考数据2",example="")
	private String userReference2;
	
	// @ApiModelProperty(required=true,name="userReference3",value="11407域-用户参考数据3",example="")
	private String userReference3;
	
	// @ApiModelProperty(required=true,name="userReference4",value="11498域-用户参考数据4",example="")
	private String userReference4;
	
	// @ApiModelProperty(required=true,name="userReference5",value="11499域-用户参考数据5",example="")
	private String userReference5;
	
	// @ApiModelProperty(required=true,name="userReference6",value="11500域-用户参考数据6",example="")
	private String userReference6;
	
	// @ApiModelProperty(required=true,name="noUnderlyings",value="711域-质押券",example="")
	private List<NoUnderlyingDto> noUnderlyings;
	
	// @ApiModelProperty(required=true,name="noCrossCustodians",value="12048域-跨托管信息",example="")
	private List<NoCrossCustodianDto> noCrossCustodians;
	
	// @ApiModelProperty(required=true,name="noPartyIDs",value="453域-发起方信息",example="")
	private List<NoPartyIDDto> noPartyIDs;

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

	public String getQuoteTransType() {
		return quoteTransType;
	}

	public void setQuoteTransType(String quoteTransType) {
		this.quoteTransType = quoteTransType;
	}

	public Integer getQuoteStatus() {
		return quoteStatus;
	}

	public void setQuoteStatus(Integer quoteStatus) {
		this.quoteStatus = quoteStatus;
	}

	public String getClOrdID() {
		return clOrdID;
	}

	public void setClOrdID(String clOrdID) {
		this.clOrdID = clOrdID;
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

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getTradeCashAmt() {
		return tradeCashAmt;
	}

	public void setTradeCashAmt(BigDecimal tradeCashAmt) {
		this.tradeCashAmt = tradeCashAmt;
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

	public BigDecimal getSettlCurrFxRate() {
		return settlCurrFxRate;
	}

	public void setSettlCurrFxRate(BigDecimal settlCurrFxRate) {
		this.settlCurrFxRate = settlCurrFxRate;
	}

	public String getUserReference1() {
		return userReference1;
	}

	public void setUserReference1(String userReference1) {
		this.userReference1 = userReference1;
	}

	public String getUserReference2() {
		return userReference2;
	}

	public void setUserReference2(String userReference2) {
		this.userReference2 = userReference2;
	}

	public String getUserReference3() {
		return userReference3;
	}

	public void setUserReference3(String userReference3) {
		this.userReference3 = userReference3;
	}

	public String getUserReference4() {
		return userReference4;
	}

	public void setUserReference4(String userReference4) {
		this.userReference4 = userReference4;
	}

	public String getUserReference5() {
		return userReference5;
	}

	public void setUserReference5(String userReference5) {
		this.userReference5 = userReference5;
	}

	public String getUserReference6() {
		return userReference6;
	}

	public void setUserReference6(String userReference6) {
		this.userReference6 = userReference6;
	}

	public List<NoUnderlyingDto> getNoUnderlyings() {
		return noUnderlyings;
	}

	public void setNoUnderlyings(List<NoUnderlyingDto> noUnderlyings) {
		this.noUnderlyings = noUnderlyings;
	}

	public List<NoCrossCustodianDto> getNoCrossCustodians() {
		return noCrossCustodians;
	}

	public void setNoCrossCustodians(List<NoCrossCustodianDto> noCrossCustodians) {
		this.noCrossCustodians = noCrossCustodians;
	}

	public List<NoPartyIDDto> getNoPartyIDs() {
		return noPartyIDs;
	}

	public void setNoPartyIDs(List<NoPartyIDDto> noPartyIDs) {
		this.noPartyIDs = noPartyIDs;
	}
	
}
