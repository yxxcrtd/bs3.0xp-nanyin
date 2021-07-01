package com.joyintech.tams.common.base.entity;

public class FinClawbackCalcRule extends FinClawbackCalcRuleKey {
    /**
     * 回补规则
     */
    private String cawlbackRule;

    /**
     * 回补方式
     */
    private String cawlbackType;

    /**
     * 计算基础
     */
    private String basis;

    /**
     * 生效规则
     */
    private String actRule;

    /**
     * 是否初始回补记录
     */
    private String isInitial;

    /**
     * 取得 回补规则
     * @return 回补规则
     */
    public String getCawlbackRule() {
        return cawlbackRule;
    }

    /**
     * 设置 回补规则
     * @param cawlbackRule 回补规则
     */
    public void setCawlbackRule(String cawlbackRule) {
        this.cawlbackRule = cawlbackRule == null ? null : cawlbackRule.trim();
    }

    /**
     * 取得 回补方式
     * @return 回补方式
     */
    public String getCawlbackType() {
        return cawlbackType;
    }

    /**
     * 设置 回补方式
     * @param cawlbackType 回补方式
     */
    public void setCawlbackType(String cawlbackType) {
        this.cawlbackType = cawlbackType == null ? null : cawlbackType.trim();
    }

    /**
     * 取得 计算基础
     * @return 计算基础
     */
    public String getBasis() {
        return basis;
    }

    /**
     * 设置 计算基础
     * @param basis 计算基础
     */
    public void setBasis(String basis) {
        this.basis = basis == null ? null : basis.trim();
    }

    /**
     * 取得 生效规则
     * @return 生效规则
     */
    public String getActRule() {
        return actRule;
    }

    /**
     * 设置 生效规则
     * @param actRule 生效规则
     */
    public void setActRule(String actRule) {
        this.actRule = actRule == null ? null : actRule.trim();
    }

    /**
     * 取得 是否初始回补记录
     * @return 是否初始回补记录
     */
    public String getIsInitial() {
        return isInitial;
    }

    /**
     * 设置 是否初始回补记录
     * @param isInitial 是否初始回补记录
     */
    public void setIsInitial(String isInitial) {
        this.isInitial = isInitial == null ? null : isInitial.trim();
    }
}