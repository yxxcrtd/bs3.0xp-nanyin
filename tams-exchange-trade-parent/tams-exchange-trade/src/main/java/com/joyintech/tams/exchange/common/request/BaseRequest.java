package com.joyintech.tams.exchange.common.request;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="请求报文基类",description="请求报文基类")
public class BaseRequest implements Serializable {

	private static final long serialVersionUID = -3324689214950023143L;
	
	@ApiModelProperty(required = true, name = "userId", value = "用户id", example = "admin")
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
