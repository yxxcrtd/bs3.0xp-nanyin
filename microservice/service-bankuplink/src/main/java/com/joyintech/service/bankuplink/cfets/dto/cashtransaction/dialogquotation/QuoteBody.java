package com.joyintech.service.bankuplink.cfets.dto.cashtransaction.dialogquotation;

import java.util.List;

import com.joyintech.service.bankuplink.cfets.dto.StandardHeaderDto;
import com.joyintech.service.bankuplink.cfets.dto.collateralisedrepo.dialogquotation.NoPartyIDDto;


/**
 * quote报文用于发送、修改报价、报价接收及报价查询反馈</br>
 * 见《第1卷-交易接口总述V3.2.3.pdf》文档
 * @author Zim
 * @time 2020-12-17
 */
// @ApiModel(value="Quote报文",description="用于发送、修改报价、报价接收及报价查询反馈")
public class QuoteBody extends StandardHeaderDto {

	// @ApiModelProperty(required=true,name="marketIndicator",value="10176域-市场标识(4-现券买卖)",example="4")
	private String marketIndicator;
	
	// @ApiModelProperty(required=false,name="dataSourceString",value="10687域-报价来源(保留扩展字段)",example="")
	private String dataSourceString;
	
	// @ApiModelProperty(required=true,name="quoteType",value="537域-报价类型(1-对话报价)",example="1")
	private Integer quoteType;
	
	// @ApiModelProperty(required=true,name="quoteTransType",value="10272域-操作类型(N:新增,R:修改)",example="N")
	private Character quoteTransType;
	
	// @ApiModelProperty(required=false,name="quoteStatus",value="297域-报价状态(16:正常,19:撤销,5:拒绝,21:过期,107:全部成交)",example="16")
	private Integer quoteStatus;
	
	// @ApiModelProperty(required=false,name="clOrdID",value="11域-客户参考编号",example="")
	private String clOrdID;
	
	// @ApiModelProperty(required=false,name="queryRequestID",value="10168域-查询请求编号",example="")
	private String queryRequestID;
	
	// @ApiModelProperty(required=false,name="origClOrdID",value="41域-原客户参考编号",example="")
	private String origClOrdID;
	
	// @ApiModelProperty(required=false,name="quoteID",value="117域-报价编号",example="")
	private String quoteID;
	
	// @ApiModelProperty(required=false,name="transactTime",value="60域-业务发生时间",example="20070822-12:33:34.000")
	private String transactTime;
	
	// @ApiModelProperty(required=false,name="quoteTime",value="10271域-报价发送时间",example="20070820-12:40:00.000")
	private String quoteTime;
	
	// @ApiModelProperty(required=true,name="securityID",value="48域-债券代码",example="010004")
	private String securityID;
	
	// @ApiModelProperty(required=false,name="symbol",value="55域-债券名称",example="")
	private String symbol;
	
	// @ApiModelProperty(required=true,name="side",value="54域-交易方向",example="1")
	private Character side;
	
	//-----------------
	
	// @ApiModelProperty(required=false,name="price",value="44域-净价",example="98.0000")
	private Double price;
	
	// @ApiModelProperty(required=false,name="dirtyPrice",value="10048域-全价",example="99.3245")
	private Double dirtyPrice;
	
	// @ApiModelProperty(required=false,name="accruedInterestAmt",value="159域-应计利息",example="1000.23541")
	private String accruedInterestAmt;
	
	// @ApiModelProperty(required=false,name="orderQty",value="38域-券面总额",example="100000")
	private String orderQty;
	
	// @ApiModelProperty(required=false,name="tradeCashAmt",value="10312域-交易金额",example="3480000.25")
	private String tradeCashAmt;
	
	// @ApiModelProperty(required=false,name="accruedInterestTotalAmt",value="10002域-应计利息总额",example="23123.23")
	private String accruedInterestTotalAmt;
	
	// @ApiModelProperty(required=false,name="settlCurrAmt",value="119域-结算金额",example="100.25")
	private String settlCurrAmt;
	
	// @ApiModelProperty(required=false,name="settlCurrency",value="120域-结算币种",example="USD")
	private String settlCurrency;
	
	// @ApiModelProperty(required=false,name="settlCurrFxRate",value="155域-汇率",example="1.126545")
	private String settlCurrFxRate;
	
	// @ApiModelProperty(required=false,name="deliveryType",value="919域-结算方式",example="0")
	private Integer deliveryType;
	
	// @ApiModelProperty(required=true,name="clearingMethod",value="11143域-清算类型",example="6")
	private Integer clearingMethod;
	
	// @ApiModelProperty(required=false,name="settlType",value="63域-清算速度",example="1")
	private String settlType;
	
	// @ApiModelProperty(required=false,name="settlDate",value="64域-结算日",example="20121111")
	private String settlDate;
	
	// @ApiModelProperty(required=false,name="principal",value="10239域-每百元本金额",example="12.22")
	private String principal;
	
	// @ApiModelProperty(required=false,name="totalPrincipal",value="10310域-总本金或者当前本金总额",example="1122.00")
	private String totalPrincipal;
	
	// @ApiModelProperty(required=false,name="userReference1",value="11405域-用户参考数据1",example="")
	private String userReference1;
	
	// @ApiModelProperty(required=false,name="userReference2",value="11406域-用户参考数据2",example="")
	private String userReference2;
	
	// @ApiModelProperty(required=false,name="userReference3",value="11407域-用户参考数据3",example="")
	private String userReference3;
	
	// @ApiModelProperty(required=false,name="userReference4",value="11498域-用户参考数据4",example="")
	private String userReference4;
	
	// @ApiModelProperty(required=false,name="userReference5",value="11499域-用户参考数据5",example="")
	private String userReference5;
	
	// @ApiModelProperty(required=false,name="userReference6",value="11500域-用户参考数据6",example="")
	private String userReference6;
	
	// @ApiModelProperty(required=false,name="yieldType",value="235域-到期收益率",example="MATURITY")
	private String yieldType;
	
	// @ApiModelProperty(required=false,name="yield",value="236域-到期收益率值",example="3.3500")
	private String yield;
	
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

	public String getDataSourceString() {
		return dataSourceString;
	}

	public void setDataSourceString(String dataSourceString) {
		this.dataSourceString = dataSourceString;
	}

	public Integer getQuoteType() {
		return quoteType;
	}

	public void setQuoteType(Integer quoteType) {
		this.quoteType = quoteType;
	}

	public Character getQuoteTransType() {
		return quoteTransType;
	}

	public void setQuoteTransType(Character quoteTransType) {
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

	public String getQueryRequestID() {
		return queryRequestID;
	}

	public void setQueryRequestID(String queryRequestID) {
		this.queryRequestID = queryRequestID;
	}

	public String getOrigClOrdID() {
		return origClOrdID;
	}

	public void setOrigClOrdID(String origClOrdID) {
		this.origClOrdID = origClOrdID;
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

	public String getQuoteTime() {
		return quoteTime;
	}

	public void setQuoteTime(String quoteTime) {
		this.quoteTime = quoteTime;
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

	public Character getSide() {
		return side;
	}

	public void setSide(Character side) {
		this.side = side;
	}


	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getDirtyPrice() {
		return dirtyPrice;
	}

	public void setDirtyPrice(Double dirtyPrice) {
		this.dirtyPrice = dirtyPrice;
	}

	public String getAccruedInterestAmt() {
		return accruedInterestAmt;
	}

	public void setAccruedInterestAmt(String accruedInterestAmt) {
		this.accruedInterestAmt = accruedInterestAmt;
	}

	public String getTradeCashAmt() {
		return tradeCashAmt;
	}

	public void setTradeCashAmt(String tradeCashAmt) {
		this.tradeCashAmt = tradeCashAmt;
	}

	public String getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(String orderQty) {
		this.orderQty = orderQty;
	}

	public String getAccruedInterestTotalAmt() {
		return accruedInterestTotalAmt;
	}

	public void setAccruedInterestTotalAmt(String accruedInterestTotalAmt) {
		this.accruedInterestTotalAmt = accruedInterestTotalAmt;
	}

	public String getSettlCurrAmt() {
		return settlCurrAmt;
	}

	public void setSettlCurrAmt(String settlCurrAmt) {
		this.settlCurrAmt = settlCurrAmt;
	}

	public String getSettlCurrency() {
		return settlCurrency;
	}

	public void setSettlCurrency(String settlCurrency) {
		this.settlCurrency = settlCurrency;
	}

	public String getSettlCurrFxRate() {
		return settlCurrFxRate;
	}

	public void setSettlCurrFxRate(String settlCurrFxRate) {
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

	public String getYield() {
		return yield;
	}

	public void setYield(String yield) {
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
