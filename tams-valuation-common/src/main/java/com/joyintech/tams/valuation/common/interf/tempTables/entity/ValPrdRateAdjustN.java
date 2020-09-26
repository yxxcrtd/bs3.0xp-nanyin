package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ValPrdRateAdjustN extends BaseEntity {
    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 费用名称
     */
    private String feeName;

    /**
     * 计费基础
     */
    private String chargeBase;

    /**
     * 计费基数
     */
    private String chargeBasis;

    /**
     * 计费基数日期类型
     */
    private String baseDateType;

    /**
     * 费用ID
     */
    private String feeId;

    /**
     * 费率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal feeRate;

    /**
     * 生效日
     */
    private Date effDate;

    /**
     * 金融产品类型
     */
    private String finprodType;

    /**
     * 产品起息日
     */
    private Date prodVdate;

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
     * 取得 计费基数
     * @return 计费基数
     */
    public String getChargeBasis() {
        return chargeBasis;
    }

    /**
     * 设置 计费基数
     * @param chargeBasis 计费基数
     */
    public void setChargeBasis(String chargeBasis) {
        this.chargeBasis = chargeBasis == null ? null : chargeBasis.trim();
    }

    /**
     * 取得 计费基数日期类型
     * @return 计费基数日期类型
     */
    public String getBaseDateType() {
        return baseDateType;
    }

    /**
     * 设置 计费基数日期类型
     * @param baseDateType 计费基数日期类型
     */
    public void setBaseDateType(String baseDateType) {
        this.baseDateType = baseDateType == null ? null : baseDateType.trim();
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
     * 取得 金融产品类型
     * @return 金融产品类型
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 设置 金融产品类型
     * @param finprodType 金融产品类型
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType == null ? null : finprodType.trim();
    }

    /**
     * 取得 产品起息日
     * @return 产品起息日
     */
    public Date getProdVdate() {
        return prodVdate;
    }

    /**
     * 设置 产品起息日
     * @param prodVdate 产品起息日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setProdVdate(Date prodVdate) {
        this.prodVdate = prodVdate;
    }
}