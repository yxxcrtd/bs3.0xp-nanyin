package com.joyintech.service.bankuplink.cfets.dto.cashtransaction.dialogquotation;

import java.util.List;

import com.joyintech.service.bankuplink.cfets.dto.StandardHeaderDto;


/**
 * QuoteCancel报文用于撤销报价</br>
 * 见《第1卷-交易接口总述V3.2.3.pdf》文档
 * @author yflv
 * @time 2020-12-18
 */
// @ApiModel(value="QuoteCancel报文",description="用于撤销报价")
public class QuoteCancelBody extends StandardHeaderDto {

	// @ApiModelProperty(required=true,name="quoteType",value="537域-报价类型(1-对话报价)",example="1")
	private Integer quoteType;
	
	// @ApiModelProperty(required=true,name="clOrdID",value="11域-客户参考编号",example="")
	private String clOrdID;
	
	// @ApiModelProperty(required=true,name="origClOrdID",value="41域-原客户参考编号，有则必传",example="")
	private String origClOrdID ;
	
	// @ApiModelProperty(required=true,name="quoteID",value="117域-原客户参考编号",example="")
	private String quoteID;
	
	// @ApiModelProperty(required=true,name="transactTime",value="60域-业务发生时间",example="20070822-12:33:34.000")
	private String transactTime;
	
	// @ApiModelProperty(required=true,name="noQuoteEntries",value="295域",example="")
	private List<NoQuoteEntrieDto> noQuoteEntries;

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

	public List<NoQuoteEntrieDto> getNoQuoteEntries() {
		return noQuoteEntries;
	}

	public void setNoQuoteEntries(List<NoQuoteEntrieDto> noQuoteEntries) {
		this.noQuoteEntries = noQuoteEntries;
	}
	
	
}
