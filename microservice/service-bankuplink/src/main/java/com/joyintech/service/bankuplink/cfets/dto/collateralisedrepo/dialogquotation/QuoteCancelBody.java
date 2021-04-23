package com.joyintech.service.bankuplink.cfets.dto.collateralisedrepo.dialogquotation;

import java.util.List;

import com.joyintech.service.bankuplink.cfets.dto.StandardHeaderDto;


/**
 * 质押式回购
 * QuoteCancel报文用于询价方撤销报价或撤销报价交谈。
 * 见《第3卷-回购市场V3.2.3.pdf》文档
 * @author Zim
 * @time 2020-12-18
 */
// @ApiModel(value="QuoteCancel报文",description="询价方撤销报价或撤销报价交谈")
public class QuoteCancelBody extends StandardHeaderDto {
	
	// @ApiModelProperty(required=true,name="tradeRecordID",value="11888域-交易记录编号",example="")
	private String tradeRecordID;
	
	// @ApiModelProperty(required=true,name="quoteType",value="537域-报价类型(1-对话报价)",example="1")
	private Integer quoteType;
	
	// @ApiModelProperty(required=true,name="clOrdID",value="11域-客户参考编号",example="")
	private String clOrdID;
	
//	// @ApiModelProperty(required=true,name="qrigClOrdID",value="41域-原客户参考编号",example="")
//	private String qrigClOrdID;
	
	// @ApiModelProperty(required=true,name="quoteID",value="117域-原客户参考编号",example="")
	private String quoteID;
	
	// @ApiModelProperty(required=true,name="transactTime",value="60域-业务发生时间",example="20070822-12:33:34.000")
	private String transactTime;
	
	// @ApiModelProperty(required=true,name="noQuoteEntries",value="295域",example="")
	private List<NoQuoteEntrieDto> noQuoteEntries;
	
	// @ApiModelProperty(required=true,name="noPartyIDs",value="453域-发起方信息",example="")
	private List<NoPartyIDDto> noPartyIDs;

	public String getTradeRecordID() {
		return tradeRecordID;
	}

	public void setTradeRecordID(String tradeRecordID) {
		this.tradeRecordID = tradeRecordID;
	}

	public Integer getQuoteType() {
		return quoteType;
	}

	public void setQuoteType(Integer quoteType) {
		this.quoteType = quoteType;
	}

	public String getClOrdID() {
		return clOrdID;
	}

	public void setClOrdID(String clOrdID) {
		this.clOrdID = clOrdID;
	}

//	public String getQrigClOrdID() {
//		return qrigClOrdID;
//	}
//
//	public void setQrigClOrdID(String qrigClOrdID) {
//		this.qrigClOrdID = qrigClOrdID;
//	}

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
