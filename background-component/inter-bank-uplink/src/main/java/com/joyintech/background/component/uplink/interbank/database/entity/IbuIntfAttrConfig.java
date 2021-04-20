package com.joyintech.background.component.uplink.interbank.database.entity;

public class IbuIntfAttrConfig extends IbuIntfAttrConfigKey {
    /**
     * 属性值
     */
    private String attrValue;

    /**
     * 属性类型
     */
    private String attrType;

    /**
     * 取得 属性值
     * @return 属性值
     */
    public String getAttrValue() {
        return attrValue;
    }

    /**
     * 设置 属性值
     * @param attrValue 属性值
     */
    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue == null ? null : attrValue.trim();
    }

    /**
     * 取得 属性类型
     * @return 属性类型
     */
    public String getAttrType() {
        return attrType;
    }

    /**
     * 设置 属性类型
     * @param attrType 属性类型
     */
    public void setAttrType(String attrType) {
        this.attrType = attrType == null ? null : attrType.trim();
    }
}