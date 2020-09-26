package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ValChannelRateAdjust extends BaseEntity {
    /**
     * 通道代码
     */
    private String channelId;

    /**
     * 数据动作标志
     */
    private String dataActionSign;

    /**
     * 费用名称
     */
    private String feeName;

    /**
     * 计费基础
     */
    private String chargeBase;

    /**
     * 费用ID
     */
    private String feeId;

    /**
     * 生效日
     */
    private Date effDate;

    /**
     * 费率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal feeRate;

    /**
     * 取得 通道代码
     * @return 通道代码
     */
    public String getChannelId() {
        return channelId;
    }

    /**
     * 设置 通道代码
     * @param channelId 通道代码
     */
    public void setChannelId(String channelId) {
        this.channelId = channelId == null ? null : channelId.trim();
    }

    /**
     * 取得 数据动作标志
     * @return 数据动作标志
     */
    public String getDataActionSign() {
        return dataActionSign;
    }

    /**
     * 设置 数据动作标志
     * @param dataActionSign 数据动作标志
     */
    public void setDataActionSign(String dataActionSign) {
        this.dataActionSign = dataActionSign == null ? null : dataActionSign.trim();
    }

    /**
     * 取得 费用名称
     * @return 费用名称
     */
    public String getFeeName() {
        return feeName;
    }

    /**
     * 设置 费用名称
     * @param feeName 费用名称
     */
    public void setFeeName(String feeName) {
        this.feeName = feeName == null ? null : feeName.trim();
    }

    /**
     * 取得 计费基础
     * @return 计费基础
     */
    public String getChargeBase() {
        return chargeBase;
    }

    /**
     * 设置 计费基础
     * @param chargeBase 计费基础
     */
    public void setChargeBase(String chargeBase) {
        this.chargeBase = chargeBase == null ? null : chargeBase.trim();
    }

    /**
     * 取得 费用ID
     * @return 费用ID
     */
    public String getFeeId() {
        return feeId;
    }

    /**
     * 设置 费用ID
     * @param feeId 费用ID
     */
    public void setFeeId(String feeId) {
        this.feeId = feeId == null ? null : feeId.trim();
    }

    /**
     * 取得 生效日
     * @return 生效日
     */
    public Date getEffDate() {
        return effDate;
    }

    /**
     * 设置 生效日
     * @param effDate 生效日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

    /**
     * 取得 费率
     * @return 费率
     */
    public BigDecimal getFeeRate() {
        return feeRate;
    }

    /**
     * 设置 费率
     * @param feeRate 费率
     */
    public void setFeeRate(BigDecimal feeRate) {
        this.feeRate = feeRate;
    }
}