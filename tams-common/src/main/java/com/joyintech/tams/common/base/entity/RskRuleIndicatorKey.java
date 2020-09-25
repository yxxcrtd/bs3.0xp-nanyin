package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RskRuleIndicatorKey extends BaseEntity {
    /**
     * 规则编号
     */
    private String ruleId;

    /**
     * 监督指标编号
     */
    private String indId;

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
     * 取得 监督指标编号
     * @return 监督指标编号
     */
    public String getIndId() {
        return indId;
    }

    /**
     * 设置 监督指标编号
     * @param indId 监督指标编号
     */
    public void setIndId(String indId) {
        this.indId = indId == null ? null : indId.trim();
    }
}