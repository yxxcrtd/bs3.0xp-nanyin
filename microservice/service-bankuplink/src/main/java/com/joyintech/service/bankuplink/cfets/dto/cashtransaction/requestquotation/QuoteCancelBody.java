package com.joyintech.service.bankuplink.cfets.dto.cashtransaction.requestquotation;

import java.util.List;

import com.joyintech.service.bankuplink.cfets.dto.StandardHeaderDto;
import com.joyintech.service.bankuplink.cfets.dto.cashtransaction.dialogquotation.NoQuoteEntrieDto;
import com.joyintech.service.bankuplink.cfets.dto.collateralisedrepo.dialogquotation.NoPartyIDDto;


/**
 * QuoteCancel报文用于撤销报价</br>
 * 见《第1卷-交易接口总述V3.2.3.pdf》文档
 * @author yflv
 * @time 2020-12-18
 */
// @ApiModel(value="QuoteCancel报文",description="用于撤销报价")
public class QuoteCancelBody extends StandardHeaderDto {

	// @ApiModelProperty(required=true,name="quoteType",value="537域-报价类型(113-请求回复)",example="113")
	private Integer quoteType;
	
	// @ApiModelProperty(required=true,name="quoteStatus",value="297域-报价状态(19:撤销,21:过期)",example="19")
	private Integer quoteStatus;
	
	// @ApiModelProperty(required=true,name="validUntilTime",value="62域-有效时间",example="20070822-12:33:34.000")
	private String validUntilTime;
	
	// @ApiModelProperty(required=true,name="clOrdID",value="11域-客户参考编号",example="")
	private String clOrdID;
	
	// @ApiModelProperty(required=true,name="origClOrdID",value="41域-原客户参考编号，有则必传",example="")
	private String origClOrdID ;
	
	// @ApiModelProperty(required=true,name="quoteReqID",value="131域-报价请求编号",example="")
	private String quoteReqID;
	
	// @ApiModelProperty(required=true,name="quoteID",value="117域-原客户参考编号",example="")
	private String quoteID;
	
	// @ApiModelProperty(required=true,name="transactTime",value="60域-业务发生时间",example="20070822-12:33:34.000")
	private String transactTime;
	
	// @ApiModelProperty(required=true,name="contingencyIndicator",value="10022域-应急标识（Y:是，N:否）",example="N")
	private String contingencyIndicator;
	
	// @ApiModelProperty(required=true,name="side",value="54域-交易方向",example="1")
	private String side;
	
	// @ApiModelProperty(required=true,name="noQuoteEntries",value="295域",example="")
	private List<NoQuoteEntrieDto> noQuoteEntries;
	
	// @ApiModelProperty(required=true,name="noPartyIDs",value="453域",example="")
	private List<NoPartyIDDto> noPartyIDs;

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

	public String getClOrdID() {
		return clOrdID;
	}

	public void setClOrdID(String clOrdID) {
		this.clOrdID = clOrdID;
	}

	public String getOrigClOrdID() {
		return origClOrdID;
	}

	public void setOrigClOrdID(String origClOrdID) {
		this.origClOrdID = origClOrdID;
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

	public String getContingencyIndicator() {
		return contingencyIndicator;
	}

	public void setContingencyIndicator(String contingencyIndicator) {
		this.contingencyIndicator = contingencyIndicator;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public List<NoQuoteEntrieDto> getNoQuoteEntries() {
		return noQuoteEntries;
	}

	public void setNoQuoteEntries(List<NoQuoteEntrieDto> noQuoteEntries) {
		this.noQuoteEntries = noQuoteEntries;
	}

	public List<NoPartyIDDto> getNoPartyIDs() {
		return noPartyIDs;
	}

	public void setNoPartyIDs(List<NoPartyIDDto> noPartyIDs) {
		this.noPartyIDs = noPartyIDs;
	}
	
}
