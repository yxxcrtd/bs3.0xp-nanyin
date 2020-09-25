package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class AssetPlanDetailKey extends BaseEntity {
    /**
     * 资产规划编号
     */
    private String assetPlanCode;

    /**
     * 资产大类
     */
    private String assetType;

    /**
     * 取得 资产规划编号
     * @return 资产规划编号
     */
    public String getAssetPlanCode() {
        return assetPlanCode;
    }

    /**
     * 设置 资产规划编号
     * @param assetPlanCode 资产规划编号
     */
    public void setAssetPlanCode(String assetPlanCode) {
        this.assetPlanCode = assetPlanCode == null ? null : assetPlanCode.trim();
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
}