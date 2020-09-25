package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class EtrAutoDispenseRuleDetailKey extends BaseEntity {
    /**
     * 规则代码
     */
    private String ruleId;

    /**
     * 分发参数
     */
    private String dispensePara;

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
     * 取得 分发参数
     * @return 分发参数
     */
    public String getDispensePara() {
        return dispensePara;
    }

    /**
     * 设置 分发参数
     * @param dispensePara 分发参数
     */
    public void setDispensePara(String dispensePara) {
        this.dispensePara = dispensePara == null ? null : dispensePara.trim();
    }
}