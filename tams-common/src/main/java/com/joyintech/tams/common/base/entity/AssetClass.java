package com.joyintech.tams.common.base.entity;

public class AssetClass extends AssetClassKey {
    /**
     * 资产大类
     */
    private String assetType;

    /**
     * 大类资产配置系统类型
     */
    private String pinanAssetType;

    /**
     * 是否非标
     */
    private String isNonstandard;

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
     * 取得 大类资产配置系统类型
     * @return 大类资产配置系统类型
     */
    public String getPinanAssetType() {
        return pinanAssetType;
    }

    /**
     * 设置 大类资产配置系统类型
     * @param pinanAssetType 大类资产配置系统类型
     */
    public void setPinanAssetType(String pinanAssetType) {
        this.pinanAssetType = pinanAssetType == null ? null : pinanAssetType.trim();
    }

    /**
     * 取得 是否非标
     * @return 是否非标
     */
    public String getIsNonstandard() {
        return isNonstandard;
    }

    /**
     * 设置 是否非标
     * @param isNonstandard 是否非标
     */
    public void setIsNonstandard(String isNonstandard) {
        this.isNonstandard = isNonstandard == null ? null : isNonstandard.trim();
    }
}