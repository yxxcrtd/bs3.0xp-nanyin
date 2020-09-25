package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class PrdDeclareScheme extends BaseEntity {
    /**
     * 方案代码
     */
    private String schemeId;

    /**
     * 方案名称
     */
    private String schemeName;

    /**
     * 备注
     */
    private String remark;

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
     * 取得 方案名称
     * @return 方案名称
     */
    public String getSchemeName() {
        return schemeName;
    }

    /**
     * 设置 方案名称
     * @param schemeName 方案名称
     */
    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName == null ? null : schemeName.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}