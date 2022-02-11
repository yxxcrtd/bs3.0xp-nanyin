package com.joyintech.tams.exchange.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="委托查询请求报文",description="委托查询请求报文")
public class EntrustQueryRequest extends BaseRequest {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(required = false, name = "accountId", value = "账户Id", example = "")
	private String accountId;
	
	@ApiModelProperty(required = false, name = "instructionId", value = "指令Id", example = "")
	private String instructionId;
	
	@ApiModelProperty(required = false, name = "orderId", value = "订单Id", example = "")
	private String orderId;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getInstructionId() {
		return instructionId;
	}

	public void setInstructionId(String instructionId) {
		this.instructionId = instructionId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
}
