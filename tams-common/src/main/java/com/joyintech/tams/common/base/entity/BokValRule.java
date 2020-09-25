package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class BokValRule extends BaseEntity {
    /**
     * 规则代码
     */
    private String ruleId;

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 规则名称
     */
    private String ruleName;

    /**
     * 规则描述
     */
    private String ruleDescription;

    /**
     * 取得 规则代码
     * @return 规则代码
     */
    public String getRuleId() {
        return ruleId;
    }

    /**
     * 设置 规则代码
     * @param ruleId 规则代码
     */
    public void setRuleId(String ruleId) {
        this.ruleId = ruleId == null ? null : ruleId.trim();
    }

    /**
     * 取得 账套代码
     * @return 账套代码
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 账套代码
     * @param booksetId 账套代码
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId == null ? null : booksetId.trim();
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
     * 取得 规则描述
     * @return 规则描述
     */
    public String getRuleDescription() {
        return ruleDescription;
    }

    /**
     * 设置 规则描述
     * @param ruleDescription 规则描述
     */
    public void setRuleDescription(String ruleDescription) {
        this.ruleDescription = ruleDescription == null ? null : ruleDescription.trim();
    }
}