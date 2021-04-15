package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class FinPledgeDispTemp extends BaseEntity {
    /**
     * 业务编号
     */
    private String tradeId;

    /**
     * 原始业务编号
     */
    private String fTradeId;

    /**
     * 置换日期
     */
    private Date tradeDate;

    /**
     * 置换费
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal exchangeFee;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 业务编号
     * @return 业务编号
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 业务编号
     * @param tradeId 业务编号
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }

    /**
     * 取得 原始业务编号
     * @return 原始业务编号
     */
    public String getfTradeId() {
        return fTradeId;
    }

    /**
     * 设置 原始业务编号
     * @param fTradeId 原始业务编号
     */
    public void setfTradeId(String fTradeId) {
        this.fTradeId = fTradeId == null ? null : fTradeId.trim();
    }

    /**
     * 取得 置换日期
     * @return 置换日期
     */
    public Date getTradeDate() {
        return tradeDate;
    }

    /**
     * 设置 置换日期
     * @param tradeDate 置换日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    /**
     * 取得 置换费
     * @return 置换费
     */
    public BigDecimal getExchangeFee() {
        return exchangeFee;
    }

    /**
     * 设置 置换费
     * @param exchangeFee 置换费
     */
    public void setExchangeFee(BigDecimal exchangeFee) {
        this.exchangeFee = exchangeFee;
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}