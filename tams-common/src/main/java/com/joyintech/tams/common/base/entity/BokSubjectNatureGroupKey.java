package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class BokSubjectNatureGroupKey extends BaseEntity {
    /**
     * 分组代码
     */
    private String groupId;

    /**
     * 会计准则代码
     */
    private String acctStandardId;

    /**
     * 基础科目性质
     */
    private String baseSubjectNature;

    /**
     * 取得 分组代码
     * @return 分组代码
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * 设置 分组代码
     * @param groupId 分组代码
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    /**
     * 取得 会计准则代码
     * @return 会计准则代码
     */
    public String getAcctStandardId() {
        return acctStandardId;
    }

    /**
     * 设置 会计准则代码
     * @param acctStandardId 会计准则代码
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