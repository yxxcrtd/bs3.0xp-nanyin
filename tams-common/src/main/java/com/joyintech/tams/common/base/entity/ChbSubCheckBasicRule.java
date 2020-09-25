package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbSubCheckBasicRule extends BaseEntity {
    /**
     * 规则编号
     */
    private String ruleId;

    /**
     * 规则名称
     */
    private String ruleName;

    /**
     * 规则MSG
     */
    private String ruleMsg;

    /**
     * 取得 规则编号
     * @return 规则编号
     */
    public String getRuleId() {
        return ruleId;
    }

    /**
     * 设置 规则编号
     * @param ruleId 规则编号
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
     * 取得 规则MSG
     * @return 规则MSG
     */
    public String getRuleMsg() {
        return ruleMsg;
    }

    /**
     * 设置 规则MSG
     * @param ruleMsg 规则MSG
     */
    public void setRuleMsg(String ruleMsg) {
        this.ruleMsg = ruleMsg == null ? null : ruleMsg.trim();
    }
}