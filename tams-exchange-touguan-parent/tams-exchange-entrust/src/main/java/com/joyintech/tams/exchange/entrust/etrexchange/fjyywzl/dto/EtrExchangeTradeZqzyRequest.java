/*
 * 文件名：EtrExchangeTradeQuotation001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chendijun
 * 修改时间：2020年7月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.exchange.entrust.etrexchange.fjyywzl.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *  债券质押持仓数据
 * 〈功能详细描述〉
 * @author yangwenjun
 * @version 1.0 2021年12月28日
 * @see EtrExchangeTradeZqzyRequest
 * @since 1.0
 */
@ApiModel(value="债券质押持仓数据查询请求参数",description="债券质押持仓数据查询请求参数")
public class EtrExchangeTradeZqzyRequest {

    @ApiModelProperty(name="portfolioId",value="组合代码",example="A1")
    private String portfolioId;

    @ApiModelProperty(name="tradeMarket",value="交易场所",example="A1")
    private String tradeMarket;

    @ApiModelProperty(name="finprodId",value="资产代码",example="A1")
    private String finprodId;


}
