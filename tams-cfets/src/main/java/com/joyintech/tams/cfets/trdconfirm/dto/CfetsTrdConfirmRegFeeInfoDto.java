/*
 * 文件名：CfetsTrdConfirmRegInfoListResponse.java
 * 版权：Copyright 2020-2022 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：
 * 修改时间：2021年01月25日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */

package com.joyintech.tams.cfets.trdconfirm.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;

/**
 * 银行间交易成交确认
 * 查询交易登记列表返回数据DTO
 * @author 1
 * @version 1.0 2021年10月26日
 * @see CfetsTrdConfirmRegFeeInfoDto
 * @since 1.0
 */
@ApiModel(value="查询登记费用返回数据",description="查询登记费用返回数据")
public class CfetsTrdConfirmRegFeeInfoDto {


    @ApiModelProperty(name="tradeId",value="汇总登记编号",example="字符串1")
    private String tradeId;
    @ApiModelProperty(name="feeType",value="费用类型",example="字符串1")
    private String feeType;
    @ApiModelProperty(name="isPayWithTrade",value="是否随交易支付",example="字符串1")
    private String isPayWithTrade;
    @ApiModelProperty(name="feeAmt",value="费用金额",example="字符串1")
    private BigDecimal feeAmt;


    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getIsPayWithTrade() {
        return isPayWithTrade;
    }

    public void setIsPayWithTrade(String isPayWithTrade) {
        this.isPayWithTrade = isPayWithTrade;
    }

    public BigDecimal getFeeAmt() {
        return feeAmt;
    }

    public void setFeeAmt(BigDecimal feeAmt) {
        this.feeAmt = feeAmt;
    }
}
