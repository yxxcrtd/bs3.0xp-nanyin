package com.joyintech.tams.exchange.common.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * create By MAXX on 2020/9/30
 */
@ApiModel(value = "交易对手信息", description = "交易对手信息")
public class CounterSeatInfo implements Serializable {

    private static final long serialVersionUID = -1617173314181618123L;

    @ApiModelProperty(value = "交易对手代码")
    private String counterId;

    @ApiModelProperty(value = "关联代码")
    private String linkId;

    @ApiModelProperty(value = "交易对手类型")
    private String counterType;

    @ApiModelProperty(value = "交易对手名称")
    private String counterName;

    @ApiModelProperty(value = "交易对手席位代码")
    private String counterSeatId;

    public String getCounterId() {
        return counterId;
    }

    public void setCounterId(String counterId) {
        this.counterId = counterId;
    }

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public String getCounterType() {
        return counterType;
    }

    public void setCounterType(String counterType) {
        this.counterType = counterType;
    }

    public String getCounterName() {
        return counterName;
    }

    public void setCounterName(String counterName) {
        this.counterName = counterName;
    }

    public String getCounterSeatId() {
        return counterSeatId;
    }

    public void setCounterSeatId(String counterSeatId) {
        this.counterSeatId = counterSeatId;
    }
}
