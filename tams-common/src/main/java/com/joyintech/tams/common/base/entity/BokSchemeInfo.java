package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class BokSchemeInfo extends BaseEntity {
    /**
     * 流水号
     */
    private String schId;

    /**
     * 流水号
     */
    private String schemeType;

    /**
     * 流水号
     */
    private String schName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建人
     */
    private String isDefault;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSchId() {
        return schId;
    }

    /**
     * 设置 流水号
     * @param schId 流水号
     */
    public void setSchId(String schId) {
        this.schId = schId == null ? null : schId.trim();
    }

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSchemeType() {
        return schemeType;
    }

    /**
     * 设置 流水号
     * @param schemeType 流水号
     */
    public void setSchemeType(String schemeType) {
        this.schemeType = schemeType == null ? null : schemeType.trim();
    }

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSchName() {
        return schName;
    }

    /**
     * 设置 流水号
     * @param schName 流水号
     */
    public void setSchName(String schName) {
        this.schName = schName == null ? null : schName.trim();
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

    /**
     * 取得 创建人
     * @return 创建人
     */
    public String getIsDefault() {
        return isDefault;
    }

    /**
     * 设置 创建人
     * @param isDefault 创建人
     */
    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault == null ? null : isDefault.trim();
    }
}