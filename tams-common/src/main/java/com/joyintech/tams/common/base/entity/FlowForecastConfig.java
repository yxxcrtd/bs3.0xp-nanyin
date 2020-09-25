package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class FlowForecastConfig extends FlowForecastConfigKey {
    /**
     * 指标值1
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal targetTimeone;

    /**
     * 指标值2
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal targetTimetwo;

    /**
     * 指标值3
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal targetTimethree;

    /**
     * 指标值4
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal targetTimefour;

    /**
     * 指标值5
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal targetTimefive;

    /**
     * 指标值6
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal targetTimesix;

    /**
     * 取得 指标值1
     * @return 指标值1
     */
    public BigDecimal getTargetTimeone() {
        return targetTimeone;
    }

    /**
     * 设置 指标值1
     * @param targetTimeone 指标值1
     */
    public void setTargetTimeone(BigDecimal targetTimeone) {
        this.targetTimeone = targetTimeone;
    }

    /**
     * 取得 指标值2
     * @return 指标值2
     */
    public BigDecimal getTargetTimetwo() {
        return targetTimetwo;
    }

    /**
     * 设置 指标值2
     * @param targetTimetwo 指标值2
     */
    public void setTargetTimetwo(BigDecimal targetTimetwo) {
        this.targetTimetwo = targetTimetwo;
    }

    /**
     * 取得 指标值3
     * @return 指标值3
     */
    public BigDecimal getTargetTimethree() {
        return targetTimethree;
    }

    /**
     * 设置 指标值3
     * @param targetTimethree 指标值3
     */
    public void setTargetTimethree(BigDecimal targetTimethree) {
        this.targetTimethree = targetTimethree;
    }

    /**
     * 取得 指标值4
     * @return 指标值4
     */
    public BigDecimal getTargetTimefour() {
        return targetTimefour;
    }

    /**
     * 设置 指标值4
     * @param targetTimefour 指标值4
     */
    public void setTargetTimefour(BigDecimal targetTimefour) {
        this.targetTimefour = targetTimefour;
    }

    /**
     * 取得 指标值5
     * @return 指标值5
     */
    public BigDecimal getTargetTimefive() {
        return targetTimefive;
    }

    /**
     * 设置 指标值5
     * @param targetTimefive 指标值5
     */
    public void setTargetTimefive(BigDecimal targetTimefive) {
        this.targetTimefive = targetTimefive;
    }

    /**
     * 取得 指标值6
     * @return 指标值6
     */
    public BigDecimal getTargetTimesix() {
        return targetTimesix;
    }

    /**
     * 设置 指标值6
     * @param targetTimesix 指标值6
     */
    public void setTargetTimesix(BigDecimal targetTimesix) {
        this.targetTimesix = targetTimesix;
    }
}