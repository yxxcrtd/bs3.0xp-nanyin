package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class AssetClassKey extends BaseEntity {
    /**
     * 资产二级分类
     */
    private String customAssetTypeTwo;

    /**
     * 资产三级分类
     */
    private String customAssetTypeThree;

    /**
     * 资产四级分类
     */
    private String customAssetTypeFour;

    /**
     * 资产五级分类
     */
    private String customAssetTypeFive;

    /**
     * 取得 资产二级分类
     * @return 资产二级分类
     */
    public String getCustomAssetTypeTwo() {
        return customAssetTypeTwo;
    }

    /**
     * 设置 资产二级分类
     * @param customAssetTypeTwo 资产二级分类
     */
    public void setCustomAssetTypeTwo(String customAssetTypeTwo) {
        this.customAssetTypeTwo = customAssetTypeTwo == null ? null : customAssetTypeTwo.trim();
    }

    /**
     * 取得 资产三级分类
     * @return 资产三级分类
     */
    public String getCustomAssetTypeThree() {
        return customAssetTypeThree;
    }

    /**
     * 设置 资产三级分类
     * @param customAssetTypeThree 资产三级分类
     */
    public void setCustomAssetTypeThree(String customAssetTypeThree) {
        this.customAssetTypeThree = customAssetTypeThree == null ? null : customAssetTypeThree.trim();
    }

    /**
     * 取得 资产四级分类
     * @return 资产四级分类
     */
    public String getCustomAssetTypeFour() {
        return customAssetTypeFour;
    }

    /**
     * 设置 资产四级分类
     * @param customAssetTypeFour 资产四级分类
     */
    public void setCustomAssetTypeFour(String customAssetTypeFour) {
        this.customAssetTypeFour = customAssetTypeFour == null ? null : customAssetTypeFour.trim();
    }

    /**
     * 取得 资产五级分类
     * @return 资产五级分类
     */
    public String getCustomAssetTypeFive() {
        return customAssetTypeFive;
    }

    /**
     * 设置 资产五级分类
     * @param customAssetTypeFive 资产五级分类
     */
    public void setCustomAssetTypeFive(String customAssetTypeFive) {
        this.customAssetTypeFive = customAssetTypeFive == null ? null : customAssetTypeFive.trim();
    }
}