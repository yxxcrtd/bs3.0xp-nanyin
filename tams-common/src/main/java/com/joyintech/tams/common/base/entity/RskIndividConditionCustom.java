package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RskIndividConditionCustom extends BaseEntity {
    /**
     * 条件代码
     */
    private String conditionItem;

    /**
     * 条件名称
     */
    private String conditionName;

    /**
     * 单位
     */
    private String conditionUnit;

    /**
     * 类型
     */
    private String conditionType;

    /**
     * 内容
     */
    private String content;

    /**
     * 取得 条件代码
     * @return 条件代码
     */
    public String getConditionItem() {
        return conditionItem;
    }

    /**
     * 设置 条件代码
     * @param conditionItem 条件代码
     */
    public void setConditionItem(String conditionItem) {
        this.conditionItem = conditionItem == null ? null : conditionItem.trim();
    }

    /**
     * 取得 条件名称
     * @return 条件名称
     */
    public String getConditionName() {
        return conditionName;
    }

    /**
     * 设置 条件名称
     * @param conditionName 条件名称
     */
    public void setConditionName(String conditionName) {
        this.conditionName = conditionName == null ? null : conditionName.trim();
    }

    /**
     * 取得 单位
     * @return 单位
     */
    public String getConditionUnit() {
        return conditionUnit;
    }

    /**
     * 设置 单位
     * @param conditionUnit 单位
     */
    public void setConditionUnit(String conditionUnit) {
        this.conditionUnit = conditionUnit == null ? null : conditionUnit.trim();
    }

    /**
     * 取得 类型
     * @return 类型
     */
    public String getConditionType() {
        return conditionType;
    }

    /**
     * 设置 类型
     * @param conditionType 类型
     */
    public void setConditionType(String conditionType) {
        this.conditionType = conditionType == null ? null : conditionType.trim();
    }

    /**
     * 取得 内容
     * @return 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置 内容
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}