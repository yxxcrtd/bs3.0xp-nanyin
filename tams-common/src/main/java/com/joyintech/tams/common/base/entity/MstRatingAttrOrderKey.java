package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class MstRatingAttrOrderKey extends BaseEntity {
    /**
     * 评级对象类型
     */
    private String oType;

    /**
     * 属性代码
     */
    private String attrCode;

    /**
     * 属性值
     */
    private String attrValue;

    /**
     * 优先级
     */
    private String attrOrder;

    /**
     * 取得 评级对象类型
     * @return 评级对象类型
     */
    public String getoType() {
        return oType;
    }

    /**
     * 设置 评级对象类型
     * @param oType 评级对象类型
     */
    public void setoType(String oType) {
        this.oType = oType == null ? null : oType.trim();
    }

    /**
     * 取得 属性代码
     * @return 属性代码
     */
    public String getAttrCode() {
        return attrCode;
    }

    /**
     * 设置 属性代码
     * @param attrCode 属性代码
     */
    public void setAttrCode(String attrCode) {
        this.attrCode = attrCode == null ? null : attrCode.trim();
    }

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
     * 取得 优先级
     * @return 优先级
     */
    public String getAttrOrder() {
        return attrOrder;
    }

    /**
     * 设置 优先级
     * @param attrOrder 优先级
     */
    public void setAttrOrder(String attrOrder) {
        this.attrOrder = attrOrder == null ? null : attrOrder.trim();
    }
}