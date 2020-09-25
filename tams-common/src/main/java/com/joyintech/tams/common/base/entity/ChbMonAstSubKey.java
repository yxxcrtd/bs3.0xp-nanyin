package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbMonAstSubKey extends BaseEntity {
    /**
     * 资产代码
     */
    private String assetId;

    /**
     * 取得 资产代码
     * @return 资产代码
     */
    public String getAssetId() {
        return assetId;
    }

    /**
     * 设置 资产代码
     * @param assetId 资产代码
     */
    public void setAssetId(String assetId) {
        this.assetId = assetId == null ? null : assetId.trim();
    }
}