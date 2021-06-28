package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class CreditLinesDeal extends CreditLinesDealKey {
    /**
     * 占用类型（实际占用、预占用）
     */
    private String creditOccureType;

    /**
     * 占用额度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tradeAmt;

    /**
     * 交易日期
     */
    private Date tradeDate;

    /**
     * 取得 占用类型（实际占用、预占用）
     * @return 占用类型（实际占用、预占用）
     */
    public String getCreditOccureType() {
        return creditOccureType;
    }

    /**
     * 设置 占用类型（实际占用、预占用）
     * @param creditOccureType 占用类型（实际占用、预占用）
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
     * 取得 交易日期
     * @return 交易日期
     */
    public Date getTradeDate() {
        return tradeDate;
    }

    /**
     * 设置 交易日期
     * @param tradeDate 交易日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }
}