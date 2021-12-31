package com.joyintech.tams.exchange.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="查询行情请求报文",description="查询行情请求报文")
public class QueryTickRequest extends BaseRequest {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(required = true, name = "stockCode", value = "合约代码", example = "000001.SZ")
	private String stockCode;

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	
}
