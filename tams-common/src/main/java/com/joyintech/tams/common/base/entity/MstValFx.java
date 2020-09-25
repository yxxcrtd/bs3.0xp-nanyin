package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class MstValFx extends MstValFxKey {
    /**
     * 每日估值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal valuation;

    /**
     * 估值模型
     */
    private String valmodelId;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 录入方式，接口、手工等
     */
    private String inputType;

    /**
     * 取得 每日估值
     * @return 每日估值
     */
    public BigDecimal getValuation() {
        return valuation;
    }

    /**
     * 设置 每日估值
     * @param valuation 每日估值
     */
    public void setValuation(BigDecimal valuation) {
        this.valuation = valuation;
    }

    /**
     * 取得 估值模型
     * @return 估值模型
     */
    public String getValmodelId() {
        return valmodelId;
    }

    /**
     * 设置 估值模型
     * @param valmodelId 估值模型
     */
    public void setValmodelId(String valmodelId) {
        this.valmodelId = valmodelId == null ? null : valmodelId.trim();
    }

    /**
     * 取得 币种
     * @return 币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 录入方式，接口、手工等
     * @return 录入方式，接口、手工等
     */
    public String getInputType() {
        return inputType;
    }

    /**
     * 设置 录入方式，接口、手工等
     * @param inputType 录入方式，接口、手工等
     */
    public void setInputType(String inputType) {
        this.inputType = inputType == null ? null : inputType.trim();
    }
}