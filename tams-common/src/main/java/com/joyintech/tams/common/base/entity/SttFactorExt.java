package com.joyintech.tams.common.base.entity;

public class SttFactorExt extends SttFactorExtKey {
    /**
     * 输入值
     */
    private String inputValue;

    /**
     * 扩展条件
     */
    private String conditionExt;

    /**
     * 启用状态
     */
    private String effStatus;

    /**
     * 取得 输入值
     * @return 输入值
     */
    public String getInputValue() {
        return inputValue;
    }

    /**
     * 设置 输入值
     * @param inputValue 输入值
     */
    public void setInputValue(String inputValue) {
        this.inputValue = inputValue == null ? null : inputValue.trim();
    }

    /**
     * 取得 扩展条件
     * @return 扩展条件
     */
    public String getConditionExt() {
        return conditionExt;
    }

    /**
     * 设置 扩展条件
     * @param conditionExt 扩展条件
     */
    public void setConditionExt(String conditionExt) {
        this.conditionExt = conditionExt == null ? null : conditionExt.trim();
    }

    /**
     * 取得 启用状态
     * @return 启用状态
     */
    public String getEffStatus() {
        return effStatus;
    }

    /**
     * 设置 启用状态
     * @param effStatus 启用状态
     */
    public void setEffStatus(String effStatus) {
        this.effStatus = effStatus == null ? null : effStatus.trim();
    }
}