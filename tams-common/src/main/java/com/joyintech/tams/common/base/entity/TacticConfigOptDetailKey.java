package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class TacticConfigOptDetailKey extends BaseEntity {
    /**
     * 战术配置编号
     */
    private String tacticConfigCode;

    /**
     * 资产大类
     */
    private String assetType;

    /**
     * 取得 战术配置编号
     * @return 战术配置编号
     */
    public String getTacticConfigCode() {
        return tacticConfigCode;
    }

    /**
     * 设置 战术配置编号
     * @param tacticConfigCode 战术配置编号
     */
    public void setTacticConfigCode(String tacticConfigCode) {
        this.tacticConfigCode = tacticConfigCode == null ? null : tacticConfigCode.trim();
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