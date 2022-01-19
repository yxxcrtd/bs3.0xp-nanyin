/*
 * 文件名：EtrExchangeTradeQuotation001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chendijun
 * 修改时间：2020年7月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.exchange.entrust.etrexchange.blocktrade.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *  行情信息(左上)
 * 〈功能详细描述〉
 * @author wangyang1
 * @version 1.0 2021年12月21日
 * @see EtrExchangeTradeQuotationRequest
 * @since 1.0
 */
@ApiModel(value="竞价行情信息查询请求参数",description="竞价行情信息查询请求参数")
public class EtrExchangeTradeQuotationRequest {


    @ApiModelProperty(name="secId",value="证券代码",example="A1")
    private String secId;

    @ApiModelProperty(name="secName",value="证券名称",example="A1")
    private String secName;

    @ApiModelProperty(name="quotationType",value="行情类型",example="A1")
    private String quotationType;

    @ApiModelProperty(name="entrustPs",value="方向(枚举：买；卖)",example="A1")
    private String entrustPs;


    public String getSecId() {
        return secId;
    }

    public void setSecId(String secId) {
        this.secId = secId;
    }
}
