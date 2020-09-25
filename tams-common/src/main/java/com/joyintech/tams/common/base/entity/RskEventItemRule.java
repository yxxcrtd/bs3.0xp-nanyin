package com.joyintech.tams.common.base.entity;

public class RskEventItemRule extends RskEventItemRuleKey {
    /**
     * 特征值别名
     */
    private String itemAnoName;

    /**
     * 匹配方式
     */
    private String matchType;

    /**
     * 特征值偏移量
     */
    private Long offsetVal;

    /**
     * 偏移方向
     */
    private String offsetType;

    /**
     * 偏移单位
     */
    private String offsetUnit;

    /**
     * 具体特征值
     */
    private String itemValue;

    /**
     * 取得 特征值别名
     * @return 特征值别名
     */
    public String getItemAnoName() {
        return itemAnoName;
    }

    /**
     * 设置 特征值别名
     * @param itemAnoName 特征值别名
     */
    public void setItemAnoName(String itemAnoName) {
        this.itemAnoName = itemAnoName == null ? null : itemAnoName.trim();
    }

    /**
     * 取得 匹配方式
     * @return 匹配方式
     */
    public String getMatchType() {
        return matchType;
    }

    /**
     * 设置 匹配方式
     * @param matchType 匹配方式
     */
    public void setMatchType(String matchType) {
        this.matchType = matchType == null ? null : matchType.trim();
    }

    /**
     * 取得 特征值偏移量
     * @return 特征值偏移量
     */
    public Long getOffsetVal() {
        return offsetVal;
    }

    /**
     * 设置 特征值偏移量
     * @param offsetVal 特征值偏移量
     */
    public void setOffsetVal(Long offsetVal) {
        this.offsetVal = offsetVal;
    }

    /**
     * 取得 偏移方向
     * @return 偏移方向
     */
    public String getOffsetType() {
        return offsetType;
    }

    /**
     * 设置 偏移方向
     * @param offsetType 偏移方向
     */
    public void setOffsetType(String offsetType) {
        this.offsetType = offsetType == null ? null : offsetType.trim();
    }

    /**
     * 取得 偏移单位
     * @return 偏移单位
     */
    public String getOffsetUnit() {
        return offsetUnit;
    }

    /**
     * 设置 偏移单位
     * @param offsetUnit 偏移单位
     */
    public void setOffsetUnit(String offsetUnit) {
        this.offsetUnit = offsetUnit == null ? null : offsetUnit.trim();
    }

    /**
     * 取得 具体特征值
     * @return 具体特征值
     */
    public String getItemValue() {
        return itemValue;
    }

    /**
     * 设置 具体特征值
     * @param itemValue 具体特征值
     */
    public void setItemValue(String itemValue) {
        this.itemValue = itemValue == null ? null : itemValue.trim();
    }
}