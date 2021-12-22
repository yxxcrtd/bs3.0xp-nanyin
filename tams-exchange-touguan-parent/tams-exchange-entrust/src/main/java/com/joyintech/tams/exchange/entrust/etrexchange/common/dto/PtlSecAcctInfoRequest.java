package com.joyintech.tams.exchange.entrust.etrexchange.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="组合资金账户请求参数",description="组合资金账户请求参数")
public class PtlSecAcctInfoRequest {

    /**
     * 组合代码
     */
    @ApiModelProperty(name="portfolioId",value="组合代码",example="A1")
    private String portfolioId;

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }
}
