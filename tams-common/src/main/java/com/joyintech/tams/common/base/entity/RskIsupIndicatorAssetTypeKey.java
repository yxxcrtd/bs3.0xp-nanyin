package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RskIsupIndicatorAssetTypeKey extends BaseEntity {
    /**
     * 监督指标编号
     */
    private String indicatorId;

    /**
     * 资产类型
     */
    private String assetType;

    /**
     * 取得 监督指标编号
     * @return 监督指标编号
     */
    public String getIndicatorId() {
        return indicatorId;
    }

    /**
     * 设置 监督指标编号
     * @param indicatorId 监督指标编号
     */
    public void setIndicatorId(String indicatorId) {
        this.indicatorId = indicatorId == null ? null : indicatorId.trim();
    }

    /**
     * 取得 资产类型
     * @return 资产类型
     */
    public String getAssetType() {
        return assetType;
    }

    /**
     * 设置 资产类型
     * @param assetType 资产类型
     */
    public void setAssetType(String assetType) {
        this.assetType = assetType == null ? null : assetType.trim();
    }
}