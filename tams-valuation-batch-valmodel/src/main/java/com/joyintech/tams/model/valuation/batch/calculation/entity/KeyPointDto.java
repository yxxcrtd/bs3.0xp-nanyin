package com.joyintech.tams.model.valuation.batch.calculation.entity;

import java.math.BigDecimal;

/**
 * 关键年限点
 * @author zznn
 * @date 2021/7/1
 */
public class KeyPointDto {

    /**
     * 关键年限点
     */
    private BigDecimal keyPoint;

    /**
     * 收益率
     */
    private BigDecimal yield;

    public KeyPointDto(){
        super();
    }

    /**
     * 对象初始化
     * @param keyPoint
     * @param yield
     */
    public KeyPointDto(BigDecimal keyPoint,BigDecimal yield){
        super();
        this.keyPoint = keyPoint;
        this.yield = yield;
    }


    public BigDecimal getKeyPoint() {
        return keyPoint;
    }
    public void setKeyPoint(BigDecimal keyPoint) {
        this.keyPoint = keyPoint;
    }
    public BigDecimal getYield() {
        return yield;
    }

    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }
}

