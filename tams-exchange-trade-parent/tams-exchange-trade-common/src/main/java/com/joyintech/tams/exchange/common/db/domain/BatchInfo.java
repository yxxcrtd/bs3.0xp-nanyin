package com.joyintech.tams.exchange.common.db.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.tams.exchange.common.utils.DoubleSerializerUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

@ApiModel(value = "批量下单信息", description = "批量下单信息")
public class BatchInfo implements Serializable, KeyMethodInterface<String> {
    private static final long serialVersionUID = -3384458329856922196L;

    @ApiModelProperty(name = "id", value = "批量下单信息ID")
    @Id
    private String id;

    @ApiModelProperty( name = "orderId", value = "订单ID")
    private String orderId;

    @ApiModelProperty(name = "priceMode", value = "价格类型")
    private int priceMode;

    @ApiModelProperty(name = "beginPrice", value = "起始价格")
    @JsonSerialize(using = DoubleSerializerUtils.class)
    private BigDecimal beginPrice;

    @ApiModelProperty( name = "priceOffset", value = "价格偏移")
    @JsonSerialize(using = DoubleSerializerUtils.class)
    private BigDecimal priceOffset;

    @ApiModelProperty(name = "beginVolume", value = "起始数量")
    private int beginVolume;

    @ApiModelProperty( name = "volumeOffset", value = "数量偏移")
    private int volumeOffset;

    @ApiModelProperty( name = "entrustTimes", value = "委托笔数")
    private int entrustTimes;

    @ApiModelProperty(name = "totalVolume", value = "批量总数量")
    private int totalVolume;

    @ApiModelProperty( name = "orderVolume", value = "订单数量")
    private int orderVolume;

    @ApiModelProperty( name = "stepTime", value = "委托间隔")
    private int stepTime;

    @ApiModelProperty( name = "completeTimes", value = "下单完成次数")
    private int completeTimes;

    @ApiModelProperty( name = "updateTime", value = "更新时间")
    private Date updateTime;

    private int count;

    public BatchInfo() {
        this.beginPrice = new BigDecimal("0.00");
        this.priceOffset = new BigDecimal("0.00");
    }

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getPriceMode() {
        return priceMode;
    }

    public void setPriceMode(int priceMode) {
        this.priceMode = priceMode;
    }

    public BigDecimal getBeginPrice() {
        return beginPrice;
    }

    public void setBeginPrice(BigDecimal beginPrice) {
        this.beginPrice = beginPrice;
    }

    public BigDecimal getPriceOffset() {
        return priceOffset;
    }

    public void setPriceOffset(BigDecimal priceOffset) {
        this.priceOffset = priceOffset;
    }

    public int getBeginVolume() {
        return beginVolume;
    }

    public void setBeginVolume(int beginVolume) {
        this.beginVolume = beginVolume;
    }

    public int getVolumeOffset() {
        return volumeOffset;
    }

    public void setVolumeOffset(int volumeOffset) {
        this.volumeOffset = volumeOffset;
    }

    public int getEntrustTimes() {
        return entrustTimes;
    }

    public void setEntrustTimes(int entrustTimes) {
        this.entrustTimes = entrustTimes;
    }

    public int getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(int totalVolume) {
        this.totalVolume = totalVolume;
    }

    public int getOrderVolume() {
        return orderVolume;
    }

    public void setOrderVolume(int orderVolume) {
        this.orderVolume = orderVolume;
    }

    public int getStepTime() {
        return stepTime;
    }

    public void setStepTime(int stepTime) {
        this.stepTime = stepTime;
    }

    public int getCompleteTimes() {
        return completeTimes;
    }

    public void setCompleteTimes(int completeTimes) {
        this.completeTimes = completeTimes;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
