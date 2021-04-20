package com.joyintech.service.bankuplink.cfets.dto.cashtransaction.requestquotation;

import java.util.List;

import com.joyintech.service.bankuplink.cfets.dto.StandardHeaderDto;
import com.joyintech.service.bankuplink.cfets.dto.cashtransaction.dialogquotation.NoRelatedSymDto;


/**
 * QuoteRequest报文用于非做市方发送请求报价、接收请求查询结果反馈等。</br>
 * 见《第1卷-交易接口总述V3.2.3.pdf》文档
 * @author yflv
 * @time 2020-12-18
 */
// @ApiModel(value="QuoteRequest报文",description="用于非做市方发送请求报价、接收请求查询结果反馈等")
public class QuoteRequestBody extends StandardHeaderDto {
	
	// @ApiModelProperty(required=false,name="clOrdID",value="11域-客户参考编号",example="")
	private String clOrdID;
	
	// @ApiModelProperty(required=false,name="queryRequestID",value="10168域-查询请求编号",example="")
	private String queryRequestID;
	
	// @ApiModelProperty(required=false,name="allocInd",value="11960域-分账标识",example="1")
	private String allocInd;
	
	// @ApiModelProperty(required=false,name="quoteReqID",value="131域-请求报价编号",example="")
	private String quoteReqID;
	
	// @ApiModelProperty(required=false,name="reference",value="10471域-指示性报价编号",example="")
	private String reference;
	
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
	
	// @ApiModelProperty(required=true,name="noRelatedSym",value="146域-重复组个数-该场景取值固定为 1",example="")
	private List<NoRelatedSymDto> noRelatedSym;

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

	public String getAllocInd() {
		return allocInd;
	}

	public void setAllocInd(String allocInd) {
		this.allocInd = allocInd;
	}

	public String getQuoteReqID() {
		return quoteReqID;
	}

	public void setQuoteReqID(String quoteReqID) {
		this.quoteReqID = quoteReqID;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
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

	public List<NoRelatedSymDto> getNoRelatedSym() {
		return noRelatedSym;
	}

	public void setNoRelatedSym(List<NoRelatedSymDto> noRelatedSym) {
		this.noRelatedSym = noRelatedSym;
	}
}
