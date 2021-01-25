package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;

public class CreditLinesPierce extends CreditLinesPierceKey {

    /**
     * 资管计划代码
     */
    private String planAssetId;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 占用额度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal creditAmt;

    /**
     * 机构代码
     */
    private String institutionCode;

    /**
     * 授信配置代码
     */
    private String creditMstId;

    public String getPlanAssetId() {
        return planAssetId;
    }

    public void setPlanAssetId(String planAssetId) {
        this.planAssetId = planAssetId;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public BigDecimal getCreditAmt() {
        return creditAmt;
    }

    public void setCreditAmt(BigDecimal creditAmt) {
        this.creditAmt = creditAmt;
    }

    public String getInstitutionCode() {
        return institutionCode;
    }

    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode;
    }

    public String getCreditMstId() {
        return creditMstId;
    }

    public void setCreditMstId(String creditMstId) {
        this.creditMstId = creditMstId;
    }
}