package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class AutoDispenseRightKey extends BaseEntity {
    /**
     * 用户ID
     */
    private String userId;

    /**
     * 资产类型，对应金融产品分类的分类8
     */
    private String assetType;

    /**
     * 取得 用户ID
     * @return 用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置 用户ID
     * @param userId 用户ID
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 取得 资产类型，对应金融产品分类的分类8
     * @return 资产类型，对应金融产品分类的分类8
     */
    public String getAssetType() {
        return assetType;
    }

    /**
     * 设置 资产类型，对应金融产品分类的分类8
     * @param assetType 资产类型，对应金融产品分类的分类8
     */
    public void setAssetType(String assetType) {
        this.assetType = assetType == null ? null : assetType.trim();
    }
}