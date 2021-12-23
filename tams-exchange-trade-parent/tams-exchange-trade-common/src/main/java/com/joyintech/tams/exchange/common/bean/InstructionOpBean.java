package com.joyintech.tams.exchange.common.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "指令接收|放弃接口请求报文", description = "指令接收|放弃接口请求报文")
public class InstructionOpBean implements Serializable {
    private static final long serialVersionUID = -2975132243241914898L;

    @ApiModelProperty(required = false, name = "instructionId", value = "指令ID")
    private String instructionId;

    @ApiModelProperty(required = false, name = "recStatus", value = "指令接收状态")
    private String recStatus;

    @ApiModelProperty(required = false, name = "trader", value = "交易员")
    private String trader;

    public String getInstructionId() {
        return instructionId;
    }

    public void setInstructionId(String instructionId) {
        this.instructionId = instructionId;
    }

    public String getRecStatus() {
        return recStatus;
    }

    public void setRecStatus(String recStatus) {
        this.recStatus = recStatus;
    }

    public String getTrader() {
        return trader;
    }

    public void setTrader(String trader) {
        this.trader = trader;
    }
}