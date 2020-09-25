package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class BokAcctStandardNatureKey extends BaseEntity {
    /**
     * 会计准则
     */
    private String acctStandardId;

    /**
     * 基础科目性质
     */
    private String baseSubjectNature;

    /**
     * 取得 会计准则
     * @return 会计准则
     */
    public String getAcctStandardId() {
        return acctStandardId;
    }

    /**
     * 设置 会计准则
     * @param acctStandardId 会计准则
     */
    public void setAcctStandardId(String acctStandardId) {
        this.acctStandardId = acctStandardId == null ? null : acctStandardId.trim();
    }

    /**
     * 取得 基础科目性质
     * @return 基础科目性质
     */
    public String getBaseSubjectNature() {
        return baseSubjectNature;
    }

    /**
     * 设置 基础科目性质
     * @param baseSubjectNature 基础科目性质
     */
    public void setBaseSubjectNature(String baseSubjectNature) {
        this.baseSubjectNature = baseSubjectNature == null ? null : baseSubjectNature.trim();
    }
}