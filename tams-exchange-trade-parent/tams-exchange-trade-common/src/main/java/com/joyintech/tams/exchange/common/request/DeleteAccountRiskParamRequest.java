package com.joyintech.tams.exchange.common.request;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="删除账号风控参数请求报文",description="删除账号风控参数请求报文")
public class DeleteAccountRiskParamRequest extends BaseRequest {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(required = true, name = "accountId", value = "账户Id", example = "")
	@NotBlank(message = "账户Id不可为空")
	private String accountId;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
}
