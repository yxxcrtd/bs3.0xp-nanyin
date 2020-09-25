package com.joyintech.tams.common.base.entity;

public class RskIsupIndicatorAssetType extends RskIsupIndicatorAssetTypeKey {
    /**
     * 资产二级类型
     */
    private String assetSecType;

    /**
     * 取得 资产二级类型
     * @return 资产二级类型
     */
    public String getAssetSecType() {
        return assetSecType;
    }

    /**
     * 设置 资产二级类型
     * @param assetSecType 资产二级类型
     */
    public void setAssetSecType(String assetSecType) {
        this.assetSecType = assetSecType == null ? null : assetSecType.trim();
    }
}