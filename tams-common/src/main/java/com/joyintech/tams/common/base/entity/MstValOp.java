package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class MstValOp extends MstValOpKey {
    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal valuation;

    /**
     * null
     */
    private String valmodelId;

    /**
     * null
     */
    private String ccy;

    /**
     * null
     */
    private String inputType;

    /**
     * null
     */
    private Date publishDate;

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getValuation() {
        return valuation;
    }

    /**
     * 设置 null
     * @param valuation null
     */
    public void setValuation(BigDecimal valuation) {
        this.valuation = valuation;
    }

    /**
     * 取得 null
     * @return null
     */
    public String getValmodelId() {
        return valmodelId;
    }

    /**
     * 设置 null
     * @param valmodelId null
     */
    public void setValmodelId(String valmodelId) {
        this.valmodelId = valmodelId == null ? null : valmodelId.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 null
     * @param ccy null
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getInputType() {
        return inputType;
    }

    /**
     * 设置 null
     * @param inputType null
     */
    public void setInputType(String inputType) {
        this.inputType = inputType == null ? null : inputType.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public Date getPublishDate() {
        return publishDate;
    }

    /**
     * 设置 null
     * @param publishDate null
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}