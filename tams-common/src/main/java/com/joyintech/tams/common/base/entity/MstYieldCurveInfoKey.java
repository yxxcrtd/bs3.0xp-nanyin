package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;
import java.util.Date;

public class MstYieldCurveInfoKey extends BaseEntity {
    /**
     * 曲线日期
     */
    private Date curveDate;

    /**
     * 曲线代码
     */
    private String curveCode;

    /**
     * 关键年限点
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal keyPoint;

    /**
     * 取得 曲线日期
     * @return 曲线日期
     */
    public Date getCurveDate() {
        return curveDate;
    }

    /**
     * 设置 曲线日期
     * @param curveDate 曲线日期
     */
    public void setCurveDate(Date curveDate) {
        this.curveDate = curveDate;
    }

    /**
     * 取得 曲线代码
     * @return 曲线代码
     */
    public String getCurveCode() {
        return curveCode;
    }

    /**
     * 设置 曲线代码
     * @param curveCode 曲线代码
     */
    public void setCurveCode(String curveCode) {
        this.curveCode = curveCode == null ? null : curveCode.trim();
    }

    /**
     * 取得 关键年限点
     * @return 关键年限点
     */
    public BigDecimal getKeyPoint() {
        return keyPoint;
    }

    /**
     * 设置 关键年限点
     * @param keyPoint 关键年限点
     */
    public void setKeyPoint(BigDecimal keyPoint) {
        this.keyPoint = keyPoint;
    }
}