package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class PtlGoalKey extends BaseEntity {
    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 资产分类
     */
    private String assetClassify;

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码
     * @param finprodId 金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 资产分类
     * @return 资产分类
     */
    public String getAssetClassify() {
        return assetClassify;
    }

    /**
     * 设置 资产分类
     * @param assetClassify 资产分类
     */
    public void setAssetClassify(String assetClassify) {
        this.assetClassify = assetClassify == null ? null : assetClassify.trim();
    }
}