package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class AssetConfigMonDetailKey extends BaseEntity {
    /**
     * 资产配置编号
     */
    private String assetConfigCode;

    /**
     * 资产大类
     */
    private String assetType;

    /**
     * 月份
     */
    private String assetMonth;

    /**
     * 取得 资产配置编号
     * @return 资产配置编号
     */
    public String getAssetConfigCode() {
        return assetConfigCode;
    }

    /**
     * 设置 资产配置编号
     * @param assetConfigCode 资产配置编号
     */
    public void setAssetConfigCode(String assetConfigCode) {
        this.assetConfigCode = assetConfigCode == null ? null : assetConfigCode.trim();
    }

    /**
     * 取得 资产大类
     * @return 资产大类
     */
    public String getAssetType() {
        return assetType;
    }

    /**
     * 设置 资产大类
     * @param assetType 资产大类
     */
    public void setAssetType(String assetType) {
        this.assetType = assetType == null ? null : assetType.trim();
    }

    /**
     * 取得 月份
     * @return 月份
     */
    public String getAssetMonth() {
        return assetMonth;
    }

    /**
     * 设置 月份
     * @param assetMonth 月份
     */
    public void setAssetMonth(String assetMonth) {
        this.assetMonth = assetMonth == null ? null : assetMonth.trim();
    }
}