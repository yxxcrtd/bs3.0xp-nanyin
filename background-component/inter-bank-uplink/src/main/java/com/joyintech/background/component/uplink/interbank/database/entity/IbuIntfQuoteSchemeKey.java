package com.joyintech.background.component.uplink.interbank.database.entity;

import com.joyintech.background.component.common.pack.common.base.BaseEntity;

public class IbuIntfQuoteSchemeKey extends BaseEntity {
    /**
     * 方案代码
     */
    private String schemeId;

    /**
     * 属性组
     */
    private String arrtGrpId;

    /**
     * 取得 方案代码
     * @return 方案代码
     */
    public String getSchemeId() {
        return schemeId;
    }

    /**
     * 设置 方案代码
     * @param schemeId 方案代码
     */
    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId == null ? null : schemeId.trim();
    }

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
}