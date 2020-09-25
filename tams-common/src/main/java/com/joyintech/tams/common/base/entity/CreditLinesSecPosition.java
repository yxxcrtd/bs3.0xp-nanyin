package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class CreditLinesSecPosition extends CreditLinesSecPositionKey {
    /**
     * 占用类型（固定实际占用）
     */
    private String creditOccureType;

    /**
     * 占用额度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tradeAmt;

    /**
     * 持仓量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal assetPosition;

    /**
     * 取得 占用类型（固定实际占用）
     * @return 占用类型（固定实际占用）
     */
    public String getCreditOccureType() {
        return creditOccureType;
    }

    /**
     * 设置 占用类型（固定实际占用）
     * @param creditOccureType 占用类型（固定实际占用）
     */
    public void setCreditOccureType(String creditOccureType) {
        this.creditOccureType = creditOccureType == null ? null : creditOccureType.trim();
    }

    /**
     * 取得 占用额度
     * @return 占用额度
     */
    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    /**
     * 设置 占用额度
     * @param tradeAmt 占用额度
     */
    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    /**
     * 取得 持仓量
     * @return 持仓量
     */
    public BigDecimal getAssetPosition() {
        return assetPosition;
    }

    /**
     * 设置 持仓量
     * @param assetPosition 持仓量
     */
    public void setAssetPosition(BigDecimal assetPosition) {
        this.assetPosition = assetPosition;
    }
}