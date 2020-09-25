package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.common.util.BigDecimalSerializer;
import java.math.BigDecimal;
import java.util.Date;

public class SttSceneCurveKey extends BaseEntity {
    /**
     * 情景因子代码
     */
    private String sceneFtrId;

    /**
     * 曲线日期
     */
    private Date curveDate;

    /**
     * 关键年限点
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal keyPoint;

    /**
     * 数据来源，中债、中证等
     */
    private String valueSource;

    /**
     * 取得 情景因子代码
     * @return 情景因子代码
     */
    public String getSceneFtrId() {
        return sceneFtrId;
    }

    /**
     * 设置 情景因子代码
     * @param sceneFtrId 情景因子代码
     */
    public void setSceneFtrId(String sceneFtrId) {
        this.sceneFtrId = sceneFtrId == null ? null : sceneFtrId.trim();
    }

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

    /**
     * 取得 数据来源，中债、中证等
     * @return 数据来源，中债、中证等
     */
    public String getValueSource() {
        return valueSource;
    }

    /**
     * 设置 数据来源，中债、中证等
     * @param valueSource 数据来源，中债、中证等
     */
    public void setValueSource(String valueSource) {
        this.valueSource = valueSource == null ? null : valueSource.trim();
    }
}