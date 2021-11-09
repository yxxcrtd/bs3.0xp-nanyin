package com.joyintech.tams.cfets.etrib.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 头寸信息查询，请求参数
 */
@ApiModel(value="指令关闭发送头寸参数",description="指令关闭发送头寸参数")
public class CfetsEtrIbClosePosEtrRequest {

    @ApiModelProperty(name="busiType",value="业务类型",example="1")
    private String busiType;

    @ApiModelProperty(name="ps",value="交易方向",example="T01")
    private String ps;

    @ApiModelProperty(name="entrustId",value="指令编号",example="1")
    private String entrustId;

    public String getEntrustId() {
        return entrustId;
    }

    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }
}
