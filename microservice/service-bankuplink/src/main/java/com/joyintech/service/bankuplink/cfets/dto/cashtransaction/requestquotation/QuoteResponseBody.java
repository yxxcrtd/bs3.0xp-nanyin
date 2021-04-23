package com.joyintech.service.bankuplink.cfets.dto.cashtransaction.requestquotation;

import java.util.List;

import com.joyintech.service.bankuplink.cfets.dto.StandardHeaderDto;
import com.joyintech.service.bankuplink.cfets.dto.cashtransaction.dialogquotation.NoAllocDto;
import com.joyintech.service.bankuplink.cfets.dto.collateralisedrepo.dialogquotation.NoPartyIDDto;


/**
 * QuoteResponse（AJ）报文用于非做市方确认做市方回复的报价</br>
 * 见《第1卷-交易接口总述V3.2.3.pdf》文档
 * @author yflv
 * @time 2020-12-18
 */
// @ApiModel(value="QuoteResponse报文",description="用于非做市方确认做市方回复的报价")
public class QuoteResponseBody extends StandardHeaderDto {

	// @ApiModelProperty(required=true,name="marketIndicator",value="10176域-市场标识(4-现券买卖)",example="4")
	private String marketIndicator;
	
	// @ApiModelProperty(required=true,name="quoteType",value="537域-报价类型(1-对话报价)",example="1")
	private Integer quoteType;
	
	// @ApiModelProperty(required=true,name="quoteRespType",value="694域-操作类型(1:成交确认,7:报价拒绝)",example="1")
	private Integer quoteRespType;
	
	// @ApiModelProperty(required=true,name="clOrdID",value="11域-客户参考编号",example="")
	private String clOrdID;
	
	// @ApiModelProperty(required=true,name="allocInd",value="11960域-分账标识",example="1")
	private String allocInd;
	
	// @ApiModelProperty(required=true,name="quoteID",value="117域-原客户参考编号",example="")
	private String quoteID;
	
	// @ApiModelProperty(required=true,name="transactTime",value="60域-业务发生时间",example="20070822-12:33:34.000")
	private String transactTime;
	
	// @ApiModelProperty(required=true,name="securityID",value="48域-债券代码",example="010004")
	private String securityID;
	
	// @ApiModelProperty(required=true,name="side",value="54域-交易方向",example="1")
	private Character side;
	
	// @ApiModelProperty(required=true,name="noPartyIDs",value="453域-发起方信息",example="")
	private List<NoPartyIDDto> noPartyIDs;
	
	// @ApiModelProperty(required=true,name="noAllocs",value="78域-发起方信息",example="")
	private List<NoAllocDto> noAllocs;

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

	public Integer getQuoteRespType() {
		return quoteRespType;
	}

	public void setQuoteRespType(Integer quoteRespType) {
		this.quoteRespType = quoteRespType;
	}

	public String getClOrdID() {
		return clOrdID;
	}

	public void setClOrdID(String clOrdID) {
		this.clOrdID = clOrdID;
	}

	public String getAllocInd() {
		return allocInd;
	}

	public void setAllocInd(String allocInd) {
		this.allocInd = allocInd;
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

	public String getSecurityID() {
		return securityID;
	}

	public void setSecurityID(String securityID) {
		this.securityID = securityID;
	}

	public Character getSide() {
		return side;
	}

	public void setSide(Character side) {
		this.side = side;
	}

	public List<NoPartyIDDto> getNoPartyIDs() {
		return noPartyIDs;
	}

	public void setNoPartyIDs(List<NoPartyIDDto> noPartyIDs) {
		this.noPartyIDs = noPartyIDs;
	}

	public List<NoAllocDto> getNoAllocs() {
		return noAllocs;
	}

	public void setNoAllocs(List<NoAllocDto> noAllocs) {
		this.noAllocs = noAllocs;
	}
}
