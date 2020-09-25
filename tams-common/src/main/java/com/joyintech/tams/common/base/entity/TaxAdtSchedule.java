package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class TaxAdtSchedule extends BaseEntity {
    /**
     * 税则代码
     */
    private String taxId;

    /**
     * 附加税类型
     */
    private String schId;

    /**
     * 附加税类型
     */
    private String addTaxType;

    /**
     * 应税税率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal taxRate;

    /**
     * 生效日
     */
    private Date effDate;

    /**
     * 通道协议代码
     */
    private String chlCpctId;

    /**
     * 取得 税则代码
     * @return 税则代码
     */
    public String getTaxId() {
        return taxId;
    }

    /**
     * 设置 税则代码
     * @param taxId 税则代码
     */
    public void setTaxId(String taxId) {
        this.taxId = taxId == null ? null : taxId.trim();
    }

    /**
     * 取得 附加税类型
     * @return 附加税类型
     */
    public String getSchId() {
        return schId;
    }

    /**
     * 设置 附加税类型
     * @param schId 附加税类型
     */
    public void setSchId(String schId) {
        this.schId = schId == null ? null : schId.trim();
    }

    /**
     * 取得 附加税类型
     * @return 附加税类型
     */
    public String getAddTaxType() {
        return addTaxType;
    }

    /**
     * 设置 附加税类型
     * @param addTaxType 附加税类型
     */
    public void setAddTaxType(String addTaxType) {
        this.addTaxType = addTaxType == null ? null : addTaxType.trim();
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
     * 取得 通道协议代码
     * @return 通道协议代码
     */
    public String getChlCpctId() {
        return chlCpctId;
    }

    /**
     * 设置 通道协议代码
     * @param chlCpctId 通道协议代码
     */
    public void setChlCpctId(String chlCpctId) {
        this.chlCpctId = chlCpctId == null ? null : chlCpctId.trim();
    }
}