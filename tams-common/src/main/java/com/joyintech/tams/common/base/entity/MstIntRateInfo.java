package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class MstIntRateInfo extends MstIntRateInfoKey {
    /**
     * 行情类型
     */
    private String intRateType;

    /**
     * 发布日
     */
    private Date relaeseDate;

    /**
     * 利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal intRate;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 录入方式
     */
    private String inputType;

    /**
     * 取得 行情类型
     * @return 行情类型
     */
    public String getIntRateType() {
        return intRateType;
    }

    /**
     * 设置 行情类型
     * @param intRateType 行情类型
     */
    public void setIntRateType(String intRateType) {
        this.intRateType = intRateType == null ? null : intRateType.trim();
    }

    /**
     * 取得 发布日
     * @return 发布日
     */
    public Date getRelaeseDate() {
        return relaeseDate;
    }

    /**
     * 设置 发布日
     * @param relaeseDate 发布日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRelaeseDate(Date relaeseDate) {
        this.relaeseDate = relaeseDate;
    }

    /**
     * 取得 利率
     * @return 利率
     */
    public BigDecimal getIntRate() {
        return intRate;
    }

    /**
     * 设置 利率
     * @param intRate 利率
     */
    public void setIntRate(BigDecimal intRate) {
        this.intRate = intRate;
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
     * 取得 录入方式
     * @return 录入方式
     */
    public String getInputType() {
        return inputType;
    }

    /**
     * 设置 录入方式
     * @param inputType 录入方式
     */
    public void setInputType(String inputType) {
        this.inputType = inputType == null ? null : inputType.trim();
    }
}