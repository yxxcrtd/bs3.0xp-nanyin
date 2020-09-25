package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class MstBenchmarkInfo extends BaseEntity {
    /**
     * 基准编号
     */
    private String benchmarkNo;

    /**
     * 基准类型
     */
    private String benchmarkType;

    /**
     * 观察频率
     */
    private String observationFre;

    /**
     * 观察开始日
     */
    private Date observationVdate;

    /**
     * 观察结束日
     */
    private Date observationMdate;

    /**
     * 初始利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal initRate;

    /**
     * 系数
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal coefficient;

    /**
     * 利差
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal spreadRate;

    /**
     * 利率上限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal spreadMax;

    /**
     * 利率下限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal spreadMin;

    /**
     * 关联日历
     */
    private String relationCalendar;

    /**
     * 取得 基准编号
     * @return 基准编号
     */
    public String getBenchmarkNo() {
        return benchmarkNo;
    }

    /**
     * 设置 基准编号
     * @param benchmarkNo 基准编号
     */
    public void setBenchmarkNo(String benchmarkNo) {
        this.benchmarkNo = benchmarkNo == null ? null : benchmarkNo.trim();
    }

    /**
     * 取得 基准类型
     * @return 基准类型
     */
    public String getBenchmarkType() {
        return benchmarkType;
    }

    /**
     * 设置 基准类型
     * @param benchmarkType 基准类型
     */
    public void setBenchmarkType(String benchmarkType) {
        this.benchmarkType = benchmarkType == null ? null : benchmarkType.trim();
    }

    /**
     * 取得 观察频率
     * @return 观察频率
     */
    public String getObservationFre() {
        return observationFre;
    }

    /**
     * 设置 观察频率
     * @param observationFre 观察频率
     */
    public void setObservationFre(String observationFre) {
        this.observationFre = observationFre == null ? null : observationFre.trim();
    }

    /**
     * 取得 观察开始日
     * @return 观察开始日
     */
    public Date getObservationVdate() {
        return observationVdate;
    }

    /**
     * 设置 观察开始日
     * @param observationVdate 观察开始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setObservationVdate(Date observationVdate) {
        this.observationVdate = observationVdate;
    }

    /**
     * 取得 观察结束日
     * @return 观察结束日
     */
    public Date getObservationMdate() {
        return observationMdate;
    }

    /**
     * 设置 观察结束日
     * @param observationMdate 观察结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setObservationMdate(Date observationMdate) {
        this.observationMdate = observationMdate;
    }

    /**
     * 取得 初始利率
     * @return 初始利率
     */
    public BigDecimal getInitRate() {
        return initRate;
    }

    /**
     * 设置 初始利率
     * @param initRate 初始利率
     */
    public void setInitRate(BigDecimal initRate) {
        this.initRate = initRate;
    }

    /**
     * 取得 系数
     * @return 系数
     */
    public BigDecimal getCoefficient() {
        return coefficient;
    }

    /**
     * 设置 系数
     * @param coefficient 系数
     */
    public void setCoefficient(BigDecimal coefficient) {
        this.coefficient = coefficient;
    }

    /**
     * 取得 利差
     * @return 利差
     */
    public BigDecimal getSpreadRate() {
        return spreadRate;
    }

    /**
     * 设置 利差
     * @param spreadRate 利差
     */
    public void setSpreadRate(BigDecimal spreadRate) {
        this.spreadRate = spreadRate;
    }

    /**
     * 取得 利率上限
     * @return 利率上限
     */
    public BigDecimal getSpreadMax() {
        return spreadMax;
    }

    /**
     * 设置 利率上限
     * @param spreadMax 利率上限
     */
    public void setSpreadMax(BigDecimal spreadMax) {
        this.spreadMax = spreadMax;
    }

    /**
     * 取得 利率下限
     * @return 利率下限
     */
    public BigDecimal getSpreadMin() {
        return spreadMin;
    }

    /**
     * 设置 利率下限
     * @param spreadMin 利率下限
     */
    public void setSpreadMin(BigDecimal spreadMin) {
        this.spreadMin = spreadMin;
    }

    /**
     * 取得 关联日历
     * @return 关联日历
     */
    public String getRelationCalendar() {
        return relationCalendar;
    }

    /**
     * 设置 关联日历
     * @param relationCalendar 关联日历
     */
    public void setRelationCalendar(String relationCalendar) {
        this.relationCalendar = relationCalendar == null ? null : relationCalendar.trim();
    }
}