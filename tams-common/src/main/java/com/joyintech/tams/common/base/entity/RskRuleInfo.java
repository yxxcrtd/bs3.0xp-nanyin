package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RskRuleInfo extends BaseEntity {
    /**
     * 组合包代码
     */
    private String ruleId;

    /**
     * 规则名称
     */
    private String ruleName;

    
    /**
     * 机构号
     */
    private String institutionCode;
    
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
     * 取得 规则名称
     * @return 规则名称
     */
    public String getRuleName() {
        return ruleName;
    }

    /**
     * 设置 规则名称
     * @param ruleName 规则名称
     */
    public void setRuleName(String ruleName) {
        this.ruleName = ruleName == null ? null : ruleName.trim();
    }

    
    /**
     * 获取 institutionCode
     * @return institutionCode.
     */
    public String getInstitutionCode() {
        return institutionCode;
    }

    
    /**
     * 设置 institutionCode
     * @param institutionCode institutionCode
     */
    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode;
    }
    
    
}