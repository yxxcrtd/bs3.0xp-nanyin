package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class FinSubscriptionInfo extends FinSubscriptionInfoKey {
    /**
     * 认购金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal subscribAmout;

    /**
     * 认购日期
     */
    private Date subscribDate;

    /**
     * 认购利率（%）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal subscribRate;

    /**
     * 取得 认购金额
     * @return 认购金额
     */
    public BigDecimal getSubscribAmout() {
        return subscribAmout;
    }

    /**
     * 设置 认购金额
     * @param subscribAmout 认购金额
     */
    public void setSubscribAmout(BigDecimal subscribAmout) {
        this.subscribAmout = subscribAmout;
    }

    /**
     * 取得 认购日期
     * @return 认购日期
     */
    public Date getSubscribDate() {
        return subscribDate;
    }

    /**
     * 设置 认购日期
     * @param subscribDate 认购日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSubscribDate(Date subscribDate) {
        this.subscribDate = subscribDate;
    }

    /**
     * 取得 认购利率（%）
     * @return 认购利率（%）
     */
    public BigDecimal getSubscribRate() {
        return subscribRate;
    }

    /**
     * 设置 认购利率（%）
     * @param subscribRate 认购利率（%）
     */
    public void setSubscribRate(BigDecimal subscribRate) {
        this.subscribRate = subscribRate;
    }
}