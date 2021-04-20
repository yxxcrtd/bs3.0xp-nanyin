package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class EtrQuoteFinishStatus extends EtrQuoteFinishStatusKey {
    /**
     * 完成日期
     */
    private Date finishDate;

    /**
     * 完成额度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal finishAmt;

    /**
     * 交易员
     */
    private String trader;

    /**
     * 取得 完成日期
     * @return 完成日期
     */
    public Date getFinishDate() {
        return finishDate;
    }

    /**
     * 设置 完成日期
     * @param finishDate 完成日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    /**
     * 取得 完成额度
     * @return 完成额度
     */
    public BigDecimal getFinishAmt() {
        return finishAmt;
    }

    /**
     * 设置 完成额度
     * @param finishAmt 完成额度
     */
    public void setFinishAmt(BigDecimal finishAmt) {
        this.finishAmt = finishAmt;
    }

    /**
     * 取得 交易员
     * @return 交易员
     */
    public String getTrader() {
        return trader;
    }

    /**
     * 设置 交易员
     * @param trader 交易员
     */
    public void setTrader(String trader) {
        this.trader = trader == null ? null : trader.trim();
    }
}