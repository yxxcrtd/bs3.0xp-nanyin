package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.tams.common.util.BigDecimalSerializer;
import java.math.BigDecimal;

public class SttSceneCurve extends SttSceneCurveKey {
    /**
     * 情景代码
     */
    private String sceneId;

    /**
     * 因子代码
     */
    private String ftrId;

    /**
     * 收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yield;

    /**
     * 取得 情景代码
     * @return 情景代码
     */
    public String getSceneId() {
        return sceneId;
    }

    /**
     * 设置 情景代码
     * @param sceneId 情景代码
     */
    public void setSceneId(String sceneId) {
        this.sceneId = sceneId == null ? null : sceneId.trim();
    }

    /**
     * 取得 因子代码
     * @return 因子代码
     */
    public String getFtrId() {
        return ftrId;
    }

    /**
     * 设置 因子代码
     * @param ftrId 因子代码
     */
    public void setFtrId(String ftrId) {
        this.ftrId = ftrId == null ? null : ftrId.trim();
    }

    /**
     * 取得 收益率
     * @return 收益率
     */
    public BigDecimal getYield() {
        return yield;
    }

    /**
     * 设置 收益率
     * @param yield 收益率
     */
    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }
}