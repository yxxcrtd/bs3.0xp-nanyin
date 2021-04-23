package com.joyintech.service.bankuplink.cfets.dto.cashtransaction.requestquotation;

import java.util.List;

import com.joyintech.service.bankuplink.cfets.dto.StandardHeaderDto;


/**
 * QuoteRequestCancel报文用于非做市方撤销请求报价或者接收交易完成通知或请求报价过期或者所有做市方拒绝请求报价或请求报价系统自动撤销通知。</br>
 * 见《第1卷-交易接口总述V3.2.3.pdf》文档
 * @author yflv
 * @time 2020-12-18
 */
// @ApiModel(value="QuoteRequestCancel报文",description="用于非做市方撤销请求报价或者接收交易完成通知或请求报价过期或者所有做市方拒绝请求报价或请求报价系统自动撤销通知")
public class QuoteRequestCancelBody extends StandardHeaderDto {
	
	// @ApiModelProperty(required=false,name="clOrdID",value="11域-客户参考编号",example="")
	private String clOrdID;
	
	// @ApiModelProperty(required=false,name="origClOrdID",value="41域-原客户参考编号",example="")
	private String origClOrdID;
	
	// @ApiModelProperty(required=false,name="quoteReqID",value="131域-请求报价编号",example="")
	private String quoteReqID;
	
	// @ApiModelProperty(required=true,name="noRelatedSym",value="146域-重复组个数-该场景取值固定为 1",example="")
	private List<NoRelatedSymReqCxlDto> noRelatedSym;

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

	public List<NoRelatedSymReqCxlDto> getNoRelatedSym() {
		return noRelatedSym;
	}

	public void setNoRelatedSym(List<NoRelatedSymReqCxlDto> noRelatedSym) {
		this.noRelatedSym = noRelatedSym;
	}
	
}
