/*
 * 文件名：EtrExchangeTradeDto001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chendijun
 * 修改时间：2020年7月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.exchange.entrust.etrexchange.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 *  交易信息 （左边下）
 * @author meifaxin
 * @version 1.0 2021年12月24日
 * @see EtrExchangeTradePortfolioResponse
 * @since 1.0
 */
@ApiModel(value="竞价指令下单组合返回信息",description="竞价指令下单组合返回信息")
public class EtrExchangeTradePortfolioResponse {



    @ApiModelProperty(name="portfolioName",value="组合名称",example="submit/save", required = true)
    private String portfolioName;

    @ApiModelProperty(name="portfolioId",value="组合代码",example="submit/save", required = true)
    private String portfolioId;

    @ApiModelProperty(name="acctNo",value="资金账户",example="submit/save", required = true)
    private String acctNo;

    @ApiModelProperty(name="entrustAmt",value="指令金额",example="")
    private BigDecimal entrustAmt;

    @ApiModelProperty(name="entrustNum",value="指令数量",example="")
    private BigDecimal entrustNum;




}
