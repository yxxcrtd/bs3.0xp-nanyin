package com.joyintech.service.bankuplink.cfets.dto.cashtransaction.requestquotation;

import java.math.BigDecimal;
import java.util.List;

import com.joyintech.service.bankuplink.cfets.dto.StandardHeaderDto;
import com.joyintech.service.bankuplink.cfets.dto.cashtransaction.dialogquotation.NoStipulationDto;
import com.joyintech.service.bankuplink.cfets.dto.collateralisedrepo.dialogquotation.NoPartyIDDto;


/**
 * QuoteStatusReport报文用于反馈报价的状态及发送报价撤销、拒绝的通知</br>
 * 见《第1卷-交易接口总述V3.2.3.pdf》文档
 * @author yflv
 * @time 2020-12-17
 */
// @ApiModel(value="QuoteStatusReport报文",description="用于反馈报价的状态及发送报价撤销、拒绝的通知")
public class QuoteStatusReportBody extends StandardHeaderDto {

	// @ApiModelProperty(required=true,name="marketIndicator",value="10176域-市场标识(4-现券买卖)",example="4")
	private String marketIndicator;
	
	// @ApiModelProperty(required=true,name="quoteType",value="537域-报价类型(1-对话报价)",example="1")
	private Integer quoteType;
	
	// @ApiModelProperty(required=true,name="quoteStatus",value="297域-报价状态(16:正常,19:撤销,5:拒绝,21:过期,107:全部成交)",example="16")
	private Integer quoteStatus;
	
	// @ApiModelProperty(required=true,name="quoteTransType",value="10272域-操作类型(N:新增,R:修改)",example="N")
	private String quoteTransType;
	
	// @ApiModelProperty(required=true,name="validUntilTime",value="62域-回复有效时间",example="20070822-12:33:34.000")
	private String validUntilTime;
	
	// @ApiModelProperty(required=true,name="clOrdID",value="11域-客户参考编号",example="")
	private String clOrdID;
	
	// @ApiModelProperty(required=true,name="quoteReqID",value="131域-报价请求编号",example="")
	private String quoteReqID;
	
	// @ApiModelProperty(required=true,name="quoteID",value="117域-原客户参考编号",example="")
	private String quoteID;
	
	// @ApiModelProperty(required=true,name="transactTime",value="60域-业务发生时间",example="20070822-12:33:34.000")
	private String transactTime;
	
	// @ApiModelProperty(required=true,name="anonymousIndicator",value="10008域-匿名标识（Y:是，N:否）",example="N")
	private String anonymousIndicator;
	
	// @ApiModelProperty(required=true,name="securityID",value="48域-债券代码",example="010004")
	private String securityID;
	
	// @ApiModelProperty(required=true,name="symbol",value="55域-债券名称",example="")
	private String symbol;
	
	// @ApiModelProperty(required=true,name="side",value="54域-交易方向",example="1")
	private String side;
	
	// @ApiModelProperty(required=true,name="price",value="44域-净价",example="98.0000")
	private BigDecimal price;
	
	// @ApiModelProperty(required=true,name="dirtyPrice",value="10048域-全价",example="99.3245")
	private BigDecimal dirtyPrice;
	
	// @ApiModelProperty(required=true,name="accruedInterestAmt",value="159域-应计利息",example="1000.23541")
	private BigDecimal accruedInterestAmt;
	
	// @ApiModelProperty(required=true,name="accruedInterestTotalAmt",value="10002域-应计利息总额",example="23123.23")
	private BigDecimal accruedInterestTotalAmt;
	
	// @ApiModelProperty(required=true,name="orderQty",value="38域-券面总额",example="100000")
	private BigDecimal orderQty;
	
	// @ApiModelProperty(required=true,name="tradeCashAmt",value="10312域-交易金额",example="3480000.25")
	private BigDecimal tradeCashAmt;
	
	// @ApiModelProperty(required=true,name="settlCurrAmt",value="119域-结算金额",example="100.25")
	private BigDecimal settlCurrAmt;
	
	// @ApiModelProperty(required=true,name="settlCurrency",value="120域-结算币种",example="USD")
	private String settlCurrency;
	
	// @ApiModelProperty(required=true,name="settlCurrFxRate",value="155域-汇率",example="1.126545")
	private BigDecimal settlCurrFxRate;
	
	// @ApiModelProperty(required=true,name="deliveryType",value="919域-结算方式",example="0")
	private Integer deliveryType;
	
	// @ApiModelProperty(required=true,name="clearingMethod",value="11143域-清算类型",example="6")
	private Integer clearingMethod;
	
	// @ApiModelProperty(required=true,name="settlType",value="63域-清算速度",example="1")
	private String settlType;
	
	// @ApiModelProperty(required=true,name="settlDate",value="64域-结算日",example="20121111")
	private String settlDate;
	
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
	
	// @ApiModelProperty(required=true,name="yieldType",value="235域-到期收益率",example="MATURITY")
	private String yieldType;
	
	// @ApiModelProperty(required=true,name="yield",value="236域-到期收益率值",example="3.3500")
	private BigDecimal yield;
	
	// @ApiModelProperty(required=true,name="noStipulations",value="232域-重复组个数-该场景取值固定为 1",example="")
	private List<NoStipulationDto> noStipulations;
	
	// @ApiModelProperty(required=true,name="noPartyIDs",value="453域-发起方信息",example="")
	private List<NoPartyIDDto> noPartyIDs;

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

	public String getQuoteTransType() {
		return quoteTransType;
	}

	public void setQuoteTransType(String quoteTransType) {
		this.quoteTransType = quoteTransType;
	}

	public String getValidUntilTime() {
		return validUntilTime;
	}

	public void setValidUntilTime(String validUntilTime) {
		this.validUntilTime = validUntilTime;
	}

	public String getClOrdID() {
		return clOrdID;
	}

	public void setClOrdID(String clOrdID) {
		this.clOrdID = clOrdID;
	}

	public String getQuoteReqID() {
		return quoteReqID;
	}

	public void setQuoteReqID(String quoteReqID) {
		this.quoteReqID = quoteReqID;
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

	public String getAnonymousIndicator() {
		return anonymousIndicator;
	}

	public void setAnonymousIndicator(String anonymousIndicator) {
		this.anonymousIndicator = anonymousIndicator;
	}

	public String getSecurityID() {
		return securityID;
	}

	public void setSecurityID(String securityID) {
		this.securityID = securityID;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getDirtyPrice() {
		return dirtyPrice;
	}

	public void setDirtyPrice(BigDecimal dirtyPrice) {
		this.dirtyPrice = dirtyPrice;
	}

	public BigDecimal getAccruedInterestAmt() {
		return accruedInterestAmt;
	}

	public void setAccruedInterestAmt(BigDecimal accruedInterestAmt) {
		this.accruedInterestAmt = accruedInterestAmt;
	}

	public BigDecimal getAccruedInterestTotalAmt() {
		return accruedInterestTotalAmt;
	}

	public void setAccruedInterestTotalAmt(BigDecimal accruedInterestTotalAmt) {
		this.accruedInterestTotalAmt = accruedInterestTotalAmt;
	}

	public BigDecimal getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(BigDecimal orderQty) {
		this.orderQty = orderQty;
	}

	public BigDecimal getTradeCashAmt() {
		return tradeCashAmt;
	}

	public void setTradeCashAmt(BigDecimal tradeCashAmt) {
		this.tradeCashAmt = tradeCashAmt;
	}

	public BigDecimal getSettlCurrAmt() {
		return settlCurrAmt;
	}

	public void setSettlCurrAmt(BigDecimal settlCurrAmt) {
		this.settlCurrAmt = settlCurrAmt;
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

	public String getYieldType() {
		return yieldType;
	}

	public void setYieldType(String yieldType) {
		this.yieldType = yieldType;
	}

	public BigDecimal getYield() {
		return yield;
	}

	public void setYield(BigDecimal yield) {
		this.yield = yield;
	}

	public List<NoStipulationDto> getNoStipulations() {
		return noStipulations;
	}

	public void setNoStipulations(List<NoStipulationDto> noStipulations) {
		this.noStipulations = noStipulations;
	}

	public List<NoPartyIDDto> getNoPartyIDs() {
		return noPartyIDs;
	}

	public void setNoPartyIDs(List<NoPartyIDDto> noPartyIDs) {
		this.noPartyIDs = noPartyIDs;
	}
	
}
