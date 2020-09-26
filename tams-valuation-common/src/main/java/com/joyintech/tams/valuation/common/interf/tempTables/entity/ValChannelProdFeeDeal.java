package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ValChannelProdFeeDeal extends BaseEntity {
    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 通道代码
     */
    private String channelId;

    /**
     * 通道名称
     */
    private String channelName;

    /**
     * 数据动作标志
     */
    private String dataActionSign;

    /**
     * 成交编号
     */
    private String tradeId;

    /**
     * 费用ID
     */
    private String feeId;

    /**
     * 计提起始日
     */
    private Date calStartDay;

    /**
     * 计提结束日
     */
    private Date calEndDay;

    /**
     * 实付费用
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal actPayAmt;

    /**
     * 是否结算
     */
    private String isClean2;

    /**
     * 支付日
     */
    private Date payDate;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 有无清算指令
     */
    private String isClean;

    /**
     * 取得 产品代码
     * @return 产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 产品代码
     * @param prodId 产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

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
     * 取得 通道名称
     * @return 通道名称
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     * 设置 通道名称
     * @param channelName 通道名称
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName == null ? null : channelName.trim();
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
     * 取得 成交编号
     * @return 成交编号
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 成交编号
     * @param tradeId 成交编号
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
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
     * 取得 计提起始日
     * @return 计提起始日
     */
    public Date getCalStartDay() {
        return calStartDay;
    }

    /**
     * 设置 计提起始日
     * @param calStartDay 计提起始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setCalStartDay(Date calStartDay) {
        this.calStartDay = calStartDay;
    }

    /**
     * 取得 计提结束日
     * @return 计提结束日
     */
    public Date getCalEndDay() {
        return calEndDay;
    }

    /**
     * 设置 计提结束日
     * @param calEndDay 计提结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setCalEndDay(Date calEndDay) {
        this.calEndDay = calEndDay;
    }

    /**
     * 取得 实付费用
     * @return 实付费用
     */
    public BigDecimal getActPayAmt() {
        return actPayAmt;
    }

    /**
     * 设置 实付费用
     * @param actPayAmt 实付费用
     */
    public void setActPayAmt(BigDecimal actPayAmt) {
        this.actPayAmt = actPayAmt;
    }

    /**
     * 取得 是否结算
     * @return 是否结算
     */
    public String getIsClean2() {
        return isClean2;
    }

    /**
     * 设置 是否结算
     * @param isClean2 是否结算
     */
    public void setIsClean2(String isClean2) {
        this.isClean2 = isClean2 == null ? null : isClean2.trim();
    }

    /**
     * 取得 支付日
     * @return 支付日
     */
    public Date getPayDate() {
        return payDate;
    }

    /**
     * 设置 支付日
     * @param payDate 支付日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
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
     * 取得 有无清算指令
     * @return 有无清算指令
     */
    public String getIsClean() {
        return isClean;
    }

    /**
     * 设置 有无清算指令
     * @param isClean 有无清算指令
     */
    public void setIsClean(String isClean) {
        this.isClean = isClean == null ? null : isClean.trim();
    }
}