package com.joyintech.tams.exchange.common.bean;

import java.io.Serializable;

/**
 * create By MAXX on 2020/9/30
 */
public class CounterSeatInfo implements Serializable {
    private String counterId;//交易对手代码
    private String linkId;//关联代码
    private String counterType;//交易对手类型
    private String counterName;//交易对手名称
    private String counterSeatId;//交易对手席位代码

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
