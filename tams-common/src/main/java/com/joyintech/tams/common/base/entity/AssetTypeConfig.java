package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class AssetTypeConfig extends BaseEntity {
    /**
     * 分类6（行内一级类型）
     */
    private String type6;

    /**
     * 分类7（行内二级类型）
     */
    private String type7;

    /**
     * 分类8（投管资产大类）
     */
    private String type8;

    /**
     * 是否自动创建组合
     */
    private String isCreatePortfolio;

    /**
     * 取得 分类6（行内一级类型）
     * @return 分类6（行内一级类型）
     */
    public String getType6() {
        return type6;
    }

    /**
     * 设置 分类6（行内一级类型）
     * @param type6 分类6（行内一级类型）
     */
    public void setType6(String type6) {
        this.type6 = type6 == null ? null : type6.trim();
    }

    /**
     * 取得 分类7（行内二级类型）
     * @return 分类7（行内二级类型）
     */
    public String getType7() {
        return type7;
    }

    /**
     * 设置 分类7（行内二级类型）
     * @param type7 分类7（行内二级类型）
     */
    public void setType7(String type7) {
        this.type7 = type7 == null ? null : type7.trim();
    }

    /**
     * 取得 分类8（投管资产大类）
     * @return 分类8（投管资产大类）
     */
    public String getType8() {
        return type8;
    }

    /**
     * 设置 分类8（投管资产大类）
     * @param type8 分类8（投管资产大类）
     */
    public void setType8(String type8) {
        this.type8 = type8 == null ? null : type8.trim();
    }

    /**
     * 取得 是否自动创建组合
     * @return 是否自动创建组合
     */
    public String getIsCreatePortfolio() {
        return isCreatePortfolio;
    }

    /**
     * 设置 是否自动创建组合
     * @param isCreatePortfolio 是否自动创建组合
     */
    public void setIsCreatePortfolio(String isCreatePortfolio) {
        this.isCreatePortfolio = isCreatePortfolio == null ? null : isCreatePortfolio.trim();
    }
}