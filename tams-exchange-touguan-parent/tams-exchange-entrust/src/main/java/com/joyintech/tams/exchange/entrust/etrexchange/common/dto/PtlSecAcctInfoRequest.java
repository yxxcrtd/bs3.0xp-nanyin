package com.joyintech.tams.exchange.entrust.etrexchange.common.dto;

import io.swagger.annotations.ApiModel;

@ApiModel(value="组合资金账户请求参数",description="组合资金账户请求参数")
public class PtlSecAcctInfoRequest {

    /**
     * 组合代码
     */
    private String portfolioId;

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }
}
