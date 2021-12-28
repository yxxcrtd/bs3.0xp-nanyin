package com.joyintech.tams.exchange.common.request;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="账号连接请求报文",description="账号连接请求报文")
public class ConnectAccountRequest extends BaseRequest {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(required = true, name = "userId", value = "账户Id", example = "")
	@NotBlank(message = "账户Id不可为空")
	private String accountId;
	
	@ApiModelProperty(required = true, name = "connectStatus", value = "连接状态（1: 请求登录  0：退出账户）", example = "1")
	@NotBlank(message = "连接状态不可为空")
	private int connectStatus;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public int getConnectStatus() {
		return connectStatus;
	}

	public void setConnectStatus(int connectStatus) {
		this.connectStatus = connectStatus;
	}
	
}
