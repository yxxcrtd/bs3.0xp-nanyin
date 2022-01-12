package com.joyintech.tams.exchange.entrust.etrexchange.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value="偏离度接口",description="偏离度接口请求参数")
public class CalcDeviationDegreeRequest {


    @ApiModelProperty(required = true,name="secAcctId",value="证券管理账户/通道代码，无通道无证券管理账户的存999999",example="A1")
    private String secAcctId;

    @ApiModelProperty(name="finprodId",value="金融产品代码",example="A1")
    private String finprodId;


    /**
     * 日期
     */
    private Date cdate;
}