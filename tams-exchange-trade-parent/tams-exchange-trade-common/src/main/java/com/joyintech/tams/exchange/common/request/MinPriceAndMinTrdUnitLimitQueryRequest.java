package com.joyintech.tams.exchange.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

@ApiModel(value = "最小价差及交易上下限查询请求报文", description = "最小价差及交易上下限查询请求报文")
public class MinPriceAndMinTrdUnitLimitQueryRequest implements Serializable {
    private static final long serialVersionUID = 3977965577315317778L;

    @ApiModelProperty(required = true, name = "market", value = "市场")
    @NotBlank(message = "市场不能为空")
    String market;
    @ApiModelProperty(required = false, name = "tradePlat", value = "交易平台")
    String tradePlat = "02";
    @ApiModelProperty(required = true, name = "assetType", value = "资产一级分类")
    @NotBlank(message = "资产一级分类不能为空")
    String assetType;
    @ApiModelProperty(required = false, name = "assetType1", value = "资产二级分类")
    String assetType1;
    @ApiModelProperty(required = false, name = "assetType2", value = "资产三级分类")
    String assetType2;
    @ApiModelProperty(required = false, name = "instructionPs", value = "交易方向")
    String instructionPs = "T01";

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getTradePlat() {
        return tradePlat;
    }

    public void setTradePlat(String tradePlat) {
        this.tradePlat = tradePlat;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getAssetType1() {
        return assetType1;
    }

    public void setAssetType1(String assetType1) {
        this.assetType1 = assetType1;
    }

    public String getAssetType2() {
        return assetType2;
    }

    public void setAssetType2(String assetType2) {
        this.assetType2 = assetType2;
    }

    public String getInstructionPs() {
        return instructionPs;
    }

    public void setInstructionPs(String instructionPs) {
        this.instructionPs = instructionPs;
    }
}
