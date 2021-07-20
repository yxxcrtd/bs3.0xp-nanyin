package com.joyintech.tams.common.base.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

public class CreditConfInfo extends BaseEntity {
    /**
     * 授信配置代码
     */
    private String creditMstId;

    /**
     * 授信标签代码
     */
    private String creditTagId;

    /**
     * 最大投资期限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal term;

    /**
     * 期限单位，日，月，年
     */
    private String termUnit;

    /**
     * 授信使用条件
     */
    private String creditCondition;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 授信配置代码
     * @return 授信配置代码
     */
    public String getCreditMstId() {
        return creditMstId;
    }

    /**
     * 设置 授信配置代码
     * @param creditMstId 授信配置代码
     */
    public void setCreditMstId(String creditMstId) {
        this.creditMstId = creditMstId == null ? null : creditMstId.trim();
    }

    /**
     * 取得 授信标签代码
     * @return 授信标签代码
     */
    public String getCreditTagId() {
        return creditTagId;
    }

    /**
     * 设置 授信标签代码
     * @param creditTagId 授信标签代码
     */
    public void setCreditTagId(String creditTagId) {
        this.creditTagId = creditTagId == null ? null : creditTagId.trim();
    }

    /**
     * 取得 最大投资期限
     * @return 最大投资期限
     */
    public BigDecimal getTerm() {
        return term;
    }

    /**
     * 设置 最大投资期限
     * @param term 最大投资期限
     */
    public void setTerm(BigDecimal term) {
        this.term = term;
    }

    /**
     * 取得 期限单位，日，月，年
     * @return 期限单位，日，月，年
     */
    public String getTermUnit() {
        return termUnit;
    }

    /**
     * 设置 期限单位，日，月，年
     * @param termUnit 期限单位，日，月，年
     */
    public void setTermUnit(String termUnit) {
        this.termUnit = termUnit == null ? null : termUnit.trim();
    }

    /**
     * 取得 授信使用条件
     * @return 授信使用条件
     */
    public String getCreditCondition() {
        return creditCondition;
    }

    /**
     * 设置 授信使用条件
     * @param creditCondition 授信使用条件
     */
    public void setCreditCondition(String creditCondition) {
        this.creditCondition = creditCondition == null ? null : creditCondition.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}