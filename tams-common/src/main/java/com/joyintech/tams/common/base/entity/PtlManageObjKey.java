package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class PtlManageObjKey extends BaseEntity {
    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 管理对象代码，金融产品代码、组合等
     */
    private String manageObjId;

    /**
     * 管理对象类型，金融产品、投资组合等
     */
    private String manageObjType;

    /**
     * 取得 组合代码
     * @return 组合代码
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 组合代码
     * @param portfolioId 组合代码
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId == null ? null : portfolioId.trim();
    }

    /**
     * 取得 管理对象代码，金融产品代码、组合等
     * @return 管理对象代码，金融产品代码、组合等
     */
    public String getManageObjId() {
        return manageObjId;
    }

    /**
     * 设置 管理对象代码，金融产品代码、组合等
     * @param manageObjId 管理对象代码，金融产品代码、组合等
     */
    public void setManageObjId(String manageObjId) {
        this.manageObjId = manageObjId == null ? null : manageObjId.trim();
    }

    /**
     * 取得 管理对象类型，金融产品、投资组合等
     * @return 管理对象类型，金融产品、投资组合等
     */
    public String getManageObjType() {
        return manageObjType;
    }

    /**
     * 设置 管理对象类型，金融产品、投资组合等
     * @param manageObjType 管理对象类型，金融产品、投资组合等
     */
    public void setManageObjType(String manageObjType) {
        this.manageObjType = manageObjType == null ? null : manageObjType.trim();
    }
}