package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RskRulePoolConfigKey extends BaseEntity {
    /**
     * 组合包代码
     */
    private String ruleId;

    /**
     * 分类维度
     */
    private String classifyDimension;

    /**
     * 取得 组合包代码
     * @return 组合包代码
     */
    public String getRuleId() {
        return ruleId;
    }

    /**
     * 设置 组合包代码
     * @param ruleId 组合包代码
     */
    public void setRuleId(String ruleId) {
        this.ruleId = ruleId == null ? null : ruleId.trim();
    }

    /**
     * 取得 分类维度
     * @return 分类维度
     */
    public String getClassifyDimension() {
        return classifyDimension;
    }

    /**
     * 设置 分类维度
     * @param classifyDimension 分类维度
     */
    public void setClassifyDimension(String classifyDimension) {
        this.classifyDimension = classifyDimension == null ? null : classifyDimension.trim();
    }
}