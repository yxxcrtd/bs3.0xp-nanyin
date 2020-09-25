package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class TaxTaxableInfo extends BaseEntity {
    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 资产代码
     */
    private String schId;

    /**
     * 资产代码
     */
    private String assetId;

    /**
     * 资产分类
     */
    private String assetClassify;

    /**
     * 本金收益特征
     */
    private String capiIncomeFeature;

    /**
     * 收入类型
     */
    private String incomeType;

    /**
     * 应税标志
     */
    private String taxableSign;

    /**
     * 应税税率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal taxRate;

    /**
     * 生效日
     */
    private String feeType;

    /**
     * 生效日
     */
    private Date effDate;

    /**
     * 维护方式
     */
    private String maintenanceMode;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 流水号
     * @param seqNo 流水号
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 取得 资产代码
     * @return 资产代码
     */
    public String getSchId() {
        return schId;
    }

    /**
     * 设置 资产代码
     * @param schId 资产代码
     */
    public void setSchId(String schId) {
        this.schId = schId == null ? null : schId.trim();
    }

    /**
     * 取得 资产代码
     * @return 资产代码
     */
    public String getAssetId() {
        return assetId;
    }

    /**
     * 设置 资产代码
     * @param assetId 资产代码
     */
    public void setAssetId(String assetId) {
        this.assetId = assetId == null ? null : assetId.trim();
    }

    /**
     * 取得 资产分类
     * @return 资产分类
     */
    public String getAssetClassify() {
        return assetClassify;
    }

    /**
     * 设置 资产分类
     * @param assetClassify 资产分类
     */
    public void setAssetClassify(String assetClassify) {
        this.assetClassify = assetClassify == null ? null : assetClassify.trim();
    }

    /**
     * 取得 本金收益特征
     * @return 本金收益特征
     */
    public String getCapiIncomeFeature() {
        return capiIncomeFeature;
    }

    /**
     * 设置 本金收益特征
     * @param capiIncomeFeature 本金收益特征
     */
    public void setCapiIncomeFeature(String capiIncomeFeature) {
        this.capiIncomeFeature = capiIncomeFeature == null ? null : capiIncomeFeature.trim();
    }

    /**
     * 取得 收入类型
     * @return 收入类型
     */
    public String getIncomeType() {
        return incomeType;
    }

    /**
     * 设置 收入类型
     * @param incomeType 收入类型
     */
    public void setIncomeType(String incomeType) {
        this.incomeType = incomeType == null ? null : incomeType.trim();
    }

    /**
     * 取得 应税标志
     * @return 应税标志
     */
    public String getTaxableSign() {
        return taxableSign;
    }

    /**
     * 设置 应税标志
     * @param taxableSign 应税标志
     */
    public void setTaxableSign(String taxableSign) {
        this.taxableSign = taxableSign == null ? null : taxableSign.trim();
    }

    /**
     * 取得 应税税率
     * @return 应税税率
     */
    public BigDecimal getTaxRate() {
        return taxRate;
    }

    /**
     * 设置 应税税率
     * @param taxRate 应税税率
     */
    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    /**
     * 取得 生效日
     * @return 生效日
     */
    public String getFeeType() {
        return feeType;
    }

    /**
     * 设置 生效日
     * @param feeType 生效日
     */
    public void setFeeType(String feeType) {
        this.feeType = feeType == null ? null : feeType.trim();
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
     * 取得 维护方式
     * @return 维护方式
     */
    public String getMaintenanceMode() {
        return maintenanceMode;
    }

    /**
     * 设置 维护方式
     * @param maintenanceMode 维护方式
     */
    public void setMaintenanceMode(String maintenanceMode) {
        this.maintenanceMode = maintenanceMode == null ? null : maintenanceMode.trim();
    }
}