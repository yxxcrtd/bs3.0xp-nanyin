package com.joyintech.tams.exchange.common.request;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="账号数据比对请求报文",description="账号数据比对请求报文")
public class CompareAccountRequest extends BaseRequest {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(required = true, name = "accountId", value = "账户Id", example = "")
	@NotBlank(message = "账户Id不可为空")
	private String accountId;
	
	@ApiModelProperty(required = true, name = "compareType", value = "比较类型（0：对比数据，1：全量覆盖，2：委托覆盖，3：成交覆盖）", example = "1")
	@NotBlank(message = "比较类型")
	private int compareType;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public int getCompareType() {
		return compareType;
	}

	public void setCompareType(int compareType) {
		this.compareType = compareType;
	}

	
}
