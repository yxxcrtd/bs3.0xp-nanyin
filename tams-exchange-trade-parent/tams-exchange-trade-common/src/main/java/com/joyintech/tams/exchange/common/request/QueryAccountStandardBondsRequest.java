package com.joyintech.tams.exchange.common.request;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="查询账号请求报文",description="查询账号请求报文")
public class QueryAccountStandardBondsRequest extends BaseRequest {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(required = true, name = "userId", value = "账户Id", example = "")
	@NotBlank(message = "账户Id不可为空")
	private String accountId;
	
	@ApiModelProperty(required = false, name = "market", value = "市场（深证或上海）", example = "")
	private String market;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	
}
