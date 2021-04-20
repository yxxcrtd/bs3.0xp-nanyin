package com.joyintech.service.bankuplink.cfets.dto.cashtransaction.requestquotation;

import java.util.List;

import com.joyintech.service.bankuplink.cfets.dto.StandardHeaderDto;
import com.joyintech.service.bankuplink.cfets.dto.cashtransaction.dialogquotation.NoRelatedSymDto;


/**
 * QuoteRequestReject报文用于做市方拒绝请求报价、接收拒绝反馈，以及非做市方接收某一个做市商的请求报价拒绝通知。</br>
 * 见《第1卷-交易接口总述V3.2.3.pdf》文档
 * @author yflv
 * @time 2020-12-18
 */
// @ApiModel(value="QuoteRequestReject报文",description="用于做市方拒绝请求报价、接收拒绝反馈，以及非做市方接收某一个做市商的请求报价拒绝通知")
public class QuoteRequestRejectBody extends StandardHeaderDto {
	
	// @ApiModelProperty(required=true,name="quoteRequestRejectType",value="11307域-操作类型(1-做市方拒绝,3-请求报价拒绝反馈)",example="1")
	private String quoteRequestRejectType;
	
	// @ApiModelProperty(required=true,name="clOrdID",value="11域-客户参考编号",example="")
	private String clOrdID;
	
	// @ApiModelProperty(required=true,name="quoteReqID",value="131域-请求报价编号",example="")
	private String quoteReqID;
	
	// @ApiModelProperty(required=true,name="noRelatedSym",value="146域-重复组个数-该场景取值固定为 1",example="")
	private List<NoRelatedSymDto> noRelatedSym;

	public String getQuoteRequestRejectType() {
		return quoteRequestRejectType;
	}

	public void setQuoteRequestRejectType(String quoteRequestRejectType) {
		this.quoteRequestRejectType = quoteRequestRejectType;
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

	public List<NoRelatedSymDto> getNoRelatedSym() {
		return noRelatedSym;
	}

	public void setNoRelatedSym(List<NoRelatedSymDto> noRelatedSym) {
		this.noRelatedSym = noRelatedSym;
	}
	
}
