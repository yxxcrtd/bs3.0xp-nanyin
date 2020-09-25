package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class BokValScheme extends BaseEntity {
    /**
     * 方案代码
     */
    private String schemeId;

    /**
     * 方案名称
     */
    private String schemeName;

    /**
     * 方案描述
     */
    private String schemeDescription;

    /**
     * 是否默认方案
     */
    private String isdefault;

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
     * 取得 方案描述
     * @return 方案描述
     */
    public String getSchemeDescription() {
        return schemeDescription;
    }

    /**
     * 设置 方案描述
     * @param schemeDescription 方案描述
     */
    public void setSchemeDescription(String schemeDescription) {
        this.schemeDescription = schemeDescription == null ? null : schemeDescription.trim();
    }

    /**
     * 取得 是否默认方案
     * @return 是否默认方案
     */
    public String getIsdefault() {
        return isdefault;
    }

    /**
     * 设置 是否默认方案
     * @param isdefault 是否默认方案
     */
    public void setIsdefault(String isdefault) {
        this.isdefault = isdefault == null ? null : isdefault.trim();
    }
}