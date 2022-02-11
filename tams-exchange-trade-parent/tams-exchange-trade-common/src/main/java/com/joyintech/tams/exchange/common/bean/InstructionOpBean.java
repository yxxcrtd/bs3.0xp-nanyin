package com.joyintech.tams.exchange.common.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

@ApiModel(value = "指令接收|放弃接口请求报文", description = "指令接收|放弃接口请求报文")
public class InstructionOpBean implements Serializable {
    private static final long serialVersionUID = -2975132243241914898L;

    @ApiModelProperty(required = true, name = "instructionId", value = "指令ID")
    @NotBlank(message = "指令ID不能为空")
    private String instructionId;

    @ApiModelProperty(required = true, name = "recStatus", value = "指令接收状态")
    @NotBlank(message = "指令接收状态不能为空")
    private String recStatus;

    @ApiModelProperty(required = true, name = "trader", value = "交易员")
    @NotBlank(message = "交易员不能为空")
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