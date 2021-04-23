package com.joyintech.background.component.uplink.interbank.database.entity;

import com.joyintech.background.component.common.pack.common.base.BaseEntity;

public class IbuIntfAttrConfigKey extends BaseEntity {
    /**
     * 属性组
     */
    private String arrtGrpId;

    /**
     * 属性ID
     */
    private String attrId;

    /**
     * 取得 属性组
     * @return 属性组
     */
    public String getArrtGrpId() {
        return arrtGrpId;
    }

    /**
     * 设置 属性组
     * @param arrtGrpId 属性组
     */
    public void setArrtGrpId(String arrtGrpId) {
        this.arrtGrpId = arrtGrpId == null ? null : arrtGrpId.trim();
    }

    /**
     * 取得 属性ID
     * @return 属性ID
     */
    public String getAttrId() {
        return attrId;
    }

    /**
     * 设置 属性ID
     * @param attrId 属性ID
     */
    public void setAttrId(String attrId) {
        this.attrId = attrId == null ? null : attrId.trim();
    }
}