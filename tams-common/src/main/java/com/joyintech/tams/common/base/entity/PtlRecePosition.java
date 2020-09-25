package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class PtlRecePosition extends PtlRecePositionKey {
    /**
     * 应收款类型，还本结转，收息结转，还本不带息利息结转
     */
    private String recType;

    /**
     * 计划支付日期
     */
    private Date payDate;

    /**
     * 数量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal amount;

    /**
     * 币种，资产对应的币种
     */
    private String ccy;

    /**
     * 母金融产品代码
     */
    private String pFinprodId;

    /**
     * 百元面值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal faceValue;

    /**
     * 取得 应收款类型，还本结转，收息结转，还本不带息利息结转
     * @return 应收款类型，还本结转，收息结转，还本不带息利息结转
     */
    public String getRecType() {
        return recType;
    }

    /**
     * 设置 应收款类型，还本结转，收息结转，还本不带息利息结转
     * @param recType 应收款类型，还本结转，收息结转，还本不带息利息结转
     */
    public void setRecType(String recType) {
        this.recType = recType == null ? null : recType.trim();
    }

    /**
     * 取得 计划支付日期
     * @return 计划支付日期
     */
    public Date getPayDate() {
        return payDate;
    }

    /**
     * 设置 计划支付日期
     * @param payDate 计划支付日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    /**
     * 取得 数量
     * @return 数量
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置 数量
     * @param amount 数量
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 取得 币种，资产对应的币种
     * @return 币种，资产对应的币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种，资产对应的币种
     * @param ccy 币种，资产对应的币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 母金融产品代码
     * @return 母金融产品代码
     */
    public String getpFinprodId() {
        return pFinprodId;
    }

    /**
     * 设置 母金融产品代码
     * @param pFinprodId 母金融产品代码
     */
    public void setpFinprodId(String pFinprodId) {
        this.pFinprodId = pFinprodId == null ? null : pFinprodId.trim();
    }

    /**
     * 取得 百元面值
     * @return 百元面值
     */
    public BigDecimal getFaceValue() {
        return faceValue;
    }

    /**
     * 设置 百元面值
     * @param faceValue 百元面值
     */
    public void setFaceValue(BigDecimal faceValue) {
        this.faceValue = faceValue;
    }
}