package com.joyintech.tams.exchange.common.bean;


import com.joyintech.tams.exchange.common.db.domain.Algorithm;
import org.springframework.beans.BeanUtils;

import java.util.Date;

public class AlgorithmPlaceBean {
    private String algorithmName;
    private long beginTime;
    private long endTime;
    private String parameter;
    private String stockCode;
    private Long volume;
    private String entrustBs;
    private String offsetFlag;
    private int limitAction;
    private int afterAction;
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
