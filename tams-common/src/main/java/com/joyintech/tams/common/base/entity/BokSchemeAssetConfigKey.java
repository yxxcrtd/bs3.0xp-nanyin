package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class BokSchemeAssetConfigKey extends BaseEntity {
    /**
     * null
     */
    private String schemeId;

    /**
     * null
     */
    private String myAssetId;

    /**
     * null
     */
    private String othersideAssetId;

    /**
     * 取得 null
     * @return null
     */
    public String getSchemeId() {
        return schemeId;
    }

    /**
     * 设置 null
     * @param schemeId null
     */
    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId == null ? null : schemeId.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getMyAssetId() {
        return myAssetId;
    }

    /**
     * 设置 null
     * @param myAssetId null
     */
    public void setMyAssetId(String myAssetId) {
        this.myAssetId = myAssetId == null ? null : myAssetId.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getOthersideAssetId() {
        return othersideAssetId;
    }

    /**
     * 设置 null
     * @param othersideAssetId null
     */
    public void setOthersideAssetId(String othersideAssetId) {
        this.othersideAssetId = othersideAssetId == null ? null : othersideAssetId.trim();
    }
}