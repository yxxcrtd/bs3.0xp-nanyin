package com.joyintech.tams.exchange.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "查询账户资金请求报文", description = "查询账户资金请求报文")
public class CapitalAccountQueryRequest implements Serializable {
    private static final long serialVersionUID = -7725435055673129005L;

    @ApiModelProperty(required = false, name = "portfolioId", value = "组合代码")
    private String portfolioId;
    @ApiModelProperty(required = false, name = "acctNo", value = "网点账号")
    private String acctNo;
    @ApiModelProperty(required = false, name = "busiAcctNo", value = "资金账号")
    private String busiAcctNo;
    @ApiModelProperty(required = false, name = "acctType", value = "资金账户类型")
    private String acctType;

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    public String getBusiAcctNo() {
        return busiAcctNo;
    }

    public void setBusiAcctNo(String busiAcctNo) {
        this.busiAcctNo = busiAcctNo;
    }

    public String getAcctType() {
        return acctType;
    }

    public void setAcctType(String acctType) {
        this.acctType = acctType;
    }
}
