package com.joyintech.tams.exchange.common.bean;


import com.joyintech.tams.exchange.common.db.domain.Algorithm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@ApiModel(value = "算法单创建请求报文", description = "算法单创建请求报文")
public class AlgorithmPlaceBean {
    @ApiModelProperty(required = true, name = "algorithmName", value = "算法单名称")
    private String algorithmName;
    @ApiModelProperty(required = true, name = "beginTime", value = "开始时间")
    private long beginTime;
    @ApiModelProperty(required = true, name = "endTime", value = "结束时间")
    private long endTime;
    @ApiModelProperty(required = true, name = "parameter", value = "参数")
    private String parameter;
    @ApiModelProperty(required = true, name = "stockCode", value = "证券代码")
    private String stockCode;
    @ApiModelProperty(required = true, name = "volume", value = "数量")
    private Long volume;
    @ApiModelProperty(required = true, name = "entrustBs", value = "交易方向")
    private String entrustBs;
    @ApiModelProperty(required = true, name = "offsetFlag", value = "开平仓标志")
    private String offsetFlag;
    @ApiModelProperty(required = true, name = "limitAction", value = "买入若出现跌停时的操作，或者卖出出现涨停时的操作")
    private int limitAction;
    @ApiModelProperty(required = true, name = "afterAction", value = "超过时间后是撤单还是继续交易")
    private int afterAction;
    @ApiModelProperty(required = true, name = "instructionId", value = "指令编号")
    private String instructionId;

    public Algorithm createAlgorithm() {
        Algorithm algorithm = new Algorithm();
        BeanUtils.copyProperties(this, algorithm);
        algorithm.setBeginTime(new Date(this.getBeginTime()));
        algorithm.setEndTime(new Date(this.getEndTime()));
        return algorithm;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public String getEntrustBs() {
        return entrustBs;
    }

    public void setEntrustBs(String entrustBs) {
        this.entrustBs = entrustBs;
    }

    public String getOffsetFlag() {
        return offsetFlag;
    }

    public void setOffsetFlag(String offsetFlag) {
        this.offsetFlag = offsetFlag;
    }

    public int getLimitAction() {
        return limitAction;
    }

    public void setLimitAction(int limitAction) {
        this.limitAction = limitAction;
    }

    public int getAfterAction() {
        return afterAction;
    }

    public void setAfterAction(int afterAction) {
        this.afterAction = afterAction;
    }

    public String getInstructionId() {
        return instructionId;
    }

    public void setInstructionId(String instructionId) {
        this.instructionId = instructionId;
    }
}
