package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class RepLiabilitiesOther extends BaseEntity {
    /**
     * 金融产品代码
     */
    private String repFinprodId;

    /**
     * 所属国家或地区
     */
    private String repCountryArea;

    /**
     * 期限(天)
     */
    private Integer repDuration;

    /**
     * 负债规模
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repLiabilitiesMoney;

    /**
     * 利率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repInterestRate;

    /**
     * 备注
     */
    private String repRemark;

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getRepFinprodId() {
        return repFinprodId;
    }

    /**
     * 设置 金融产品代码
     * @param repFinprodId 金融产品代码
     */
    public void setRepFinprodId(String repFinprodId) {
        this.repFinprodId = repFinprodId == null ? null : repFinprodId.trim();
    }

    /**
     * 取得 所属国家或地区
     * @return 所属国家或地区
     */
    public String getRepCountryArea() {
        return repCountryArea;
    }

    /**
     * 设置 所属国家或地区
     * @param repCountryArea 所属国家或地区
     */
    public void setRepCountryArea(String repCountryArea) {
        this.repCountryArea = repCountryArea == null ? null : repCountryArea.trim();
    }

    /**
     * 取得 期限(天)
     * @return 期限(天)
     */
    public Integer getRepDuration() {
        return repDuration;
    }

    /**
     * 设置 期限(天)
     * @param repDuration 期限(天)
     */
    public void setRepDuration(Integer repDuration) {
        this.repDuration = repDuration;
    }

    /**
     * 取得 负债规模
     * @return 负债规模
     */
    public BigDecimal getRepLiabilitiesMoney() {
        return repLiabilitiesMoney;
    }

    /**
     * 设置 负债规模
     * @param repLiabilitiesMoney 负债规模
     */
    public void setRepLiabilitiesMoney(BigDecimal repLiabilitiesMoney) {
        this.repLiabilitiesMoney = repLiabilitiesMoney;
    }

    /**
     * 取得 利率%
     * @return 利率%
     */
    public BigDecimal getRepInterestRate() {
        return repInterestRate;
    }

    /**
     * 设置 利率%
     * @param repInterestRate 利率%
     */
    public void setRepInterestRate(BigDecimal repInterestRate) {
        this.repInterestRate = repInterestRate;
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRepRemark() {
        return repRemark;
    }

    /**
     * 设置 备注
     * @param repRemark 备注
     */
    public void setRepRemark(String repRemark) {
        this.repRemark = repRemark == null ? null : repRemark.trim();
    }
}