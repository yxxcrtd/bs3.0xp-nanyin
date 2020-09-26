package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ValPrdBenchmarkDetailN extends BaseEntity {
    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 金融产品类型
     */
    private String finprodType;

    /**
     * 产品起息日
     */
    private Date prodVdate;

    /**
     * 新增、修改、删除
     */
    private String dataActionSign;

    /**
     * 生效日
     */
    private Date effDate;

    /**
     * 利率类型
     */
    private String rateType;

    /**
     * 基准类型
     */
    private String intRateType;

    /**
     * 重置规则
     */
    private String resetType;

    /**
     * 初始利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal proprate;

    /**
     * 基准利率
     */
    private String intRateId;

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

    /**
     * 取得 新增、修改、删除
     * @return 新增、修改、删除
     */
    public String getDataActionSign() {
        return dataActionSign;
    }

    /**
     * 设置 新增、修改、删除
     * @param dataActionSign 新增、修改、删除
     */
    public void setDataActionSign(String dataActionSign) {
        this.dataActionSign = dataActionSign == null ? null : dataActionSign.trim();
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
     * 取得 利率类型
     * @return 利率类型
     */
    public String getRateType() {
        return rateType;
    }

    /**
     * 设置 利率类型
     * @param rateType 利率类型
     */
    public void setRateType(String rateType) {
        this.rateType = rateType == null ? null : rateType.trim();
    }

    /**
     * 取得 基准类型
     * @return 基准类型
     */
    public String getIntRateType() {
        return intRateType;
    }

    /**
     * 设置 基准类型
     * @param intRateType 基准类型
     */
    public void setIntRateType(String intRateType) {
        this.intRateType = intRateType == null ? null : intRateType.trim();
    }

    /**
     * 取得 重置规则
     * @return 重置规则
     */
    public String getResetType() {
        return resetType;
    }

    /**
     * 设置 重置规则
     * @param resetType 重置规则
     */
    public void setResetType(String resetType) {
        this.resetType = resetType == null ? null : resetType.trim();
    }

    /**
     * 取得 初始利率
     * @return 初始利率
     */
    public BigDecimal getProprate() {
        return proprate;
    }

    /**
     * 设置 初始利率
     * @param proprate 初始利率
     */
    public void setProprate(BigDecimal proprate) {
        this.proprate = proprate;
    }

    /**
     * 取得 基准利率
     * @return 基准利率
     */
    public String getIntRateId() {
        return intRateId;
    }

    /**
     * 设置 基准利率
     * @param intRateId 基准利率
     */
    public void setIntRateId(String intRateId) {
        this.intRateId = intRateId == null ? null : intRateId.trim();
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
}