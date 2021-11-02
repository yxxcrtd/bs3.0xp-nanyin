package com.joyintech.tams.cfets.etrdispatcher.dto;

import io.swagger.annotations.ApiModelProperty;

public class CfetsEtrDispatcherBaseInfo {

    @ApiModelProperty(name="entrustDate",value="指令日期",example="2021-01-01")
    private String entrustDate;

    @ApiModelProperty(name="portfolioId",value="组合代码",example="字符串1")
    private String portfolioId;

    @ApiModelProperty(name="portfolioName",value="组合名称",example="字符串1")
    private String portfolioName;

    @ApiModelProperty(name="busiModel",value="业务模式Code",example="01")
    private String busiModel;

    @ApiModelProperty(name="finprodMarketId",value="资产代码",example="字符串1")
    private String finprodMarketId;

    @ApiModelProperty(name="finprodAbbr",value="资产简称",example="字符串1")
    private String finprodAbbr;

    @ApiModelProperty(name="tradeMarket",value="交易场所",example="字符串1")
    private String tradeMarket;

    @ApiModelProperty(name="ccy",value="币种",example="CNY")
    private String ccy;

    @ApiModelProperty(name="ps",value="交易方向",example="字符串1")
    private String ps;

}
