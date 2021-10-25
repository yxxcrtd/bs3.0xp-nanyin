package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class StlSecpRecon extends StlSecpReconKey {
    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 持仓余额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal amout;

    /**
     * null
     */
    private String column12;

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码
     * @param finprodId 金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 持仓余额
     * @return 持仓余额
     */
    public BigDecimal getAmout() {
        return amout;
    }

    /**
     * 设置 持仓余额
     * @param amout 持仓余额
     */
    public void setAmout(BigDecimal amout) {
        this.amout = amout;
    }

    /**
     * 取得 null
     * @return null
     */
    public String getColumn12() {
        return column12;
    }

    /**
     * 设置 null
     * @param column12 null
     */
    public void setColumn12(String column12) {
        this.column12 = column12 == null ? null : column12.trim();
    }
}