package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ValPrdFloatFeeDetailN extends BaseEntity {
    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 数据动作标志
     */
    private String dataActionSign;

    /**
     * 费用名称
     */
    private String feeName;

    /**
     * 生效日
     */
    private Date effDate;

    /**
     * 费用ID
     */
    private String feeId;

    /**
     * 业绩比较基准层级
     */
    private Long lev;

    /**
     * 基准利率
     */
    private String basicRate;

    /**
     * 调整系数
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal coefficient;

    /**
     * 利差
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal spreadRate;

    /**
     * 银行收取比例
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal chargeRatio;

    /**
     * 研发部分收取比例
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal saleRatio;

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
     * 取得 业绩比较基准层级
     * @return 业绩比较基准层级
     */
    public Long getLev() {
        return lev;
    }

    /**
     * 设置 业绩比较基准层级
     * @param lev 业绩比较基准层级
     */
    public void setLev(Long lev) {
        this.lev = lev;
    }

    /**
     * 取得 基准利率
     * @return 基准利率
     */
    public String getBasicRate() {
        return basicRate;
    }

    /**
     * 设置 基准利率
     * @param basicRate 基准利率
     */
    public void setBasicRate(String basicRate) {
        this.basicRate = basicRate == null ? null : basicRate.trim();
    }

    /**
     * 取得 调整系数
     * @return 调整系数
     */
    public BigDecimal getCoefficient() {
        return coefficient;
    }

    /**
     * 设置 调整系数
     * @param coefficient 调整系数
     */
    public void setCoefficient(BigDecimal coefficient) {
        this.coefficient = coefficient;
    }

    /**
     * 取得 利差
     * @return 利差
     */
    public BigDecimal getSpreadRate() {
        return spreadRate;
    }

    /**
     * 设置 利差
     * @param spreadRate 利差
     */
    public void setSpreadRate(BigDecimal spreadRate) {
        this.spreadRate = spreadRate;
    }

    /**
     * 取得 银行收取比例
     * @return 银行收取比例
     */
    public BigDecimal getChargeRatio() {
        return chargeRatio;
    }

    /**
     * 设置 银行收取比例
     * @param chargeRatio 银行收取比例
     */
    public void setChargeRatio(BigDecimal chargeRatio) {
        this.chargeRatio = chargeRatio;
    }

    /**
     * 取得 研发部分收取比例
     * @return 研发部分收取比例
     */
    public BigDecimal getSaleRatio() {
        return saleRatio;
    }

    /**
     * 设置 研发部分收取比例
     * @param saleRatio 研发部分收取比例
     */
    public void setSaleRatio(BigDecimal saleRatio) {
        this.saleRatio = saleRatio;
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