package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RskAccessListType extends BaseEntity {
    /**
     * 名单代码
     */
    private String listId;

    /**
     * 风险资产分类1
     */
    private String rskAssetType1;

    /**
     * 风险资产分类2
     */
    private String rskAssetType2;

    /**
     * 风险资产分类3
     */
    private String rskAssetType3;

    /**
     * 风险资产分类4
     */
    private String rskAssetType4;

    /**
     * 风险资产分类5
     */
    private String rskAssetType5;

    /**
     * 名单主体类型，资产代码、托管人、
     */
    private String listDetailIdType;

    /**
     * 取得 名单代码
     * @return 名单代码
     */
    public String getListId() {
        return listId;
    }

    /**
     * 设置 名单代码
     * @param listId 名单代码
     */
    public void setListId(String listId) {
        this.listId = listId == null ? null : listId.trim();
    }

    /**
     * 取得 风险资产分类1
     * @return 风险资产分类1
     */
    public String getRskAssetType1() {
        return rskAssetType1;
    }

    /**
     * 设置 风险资产分类1
     * @param rskAssetType1 风险资产分类1
     */
    public void setRskAssetType1(String rskAssetType1) {
        this.rskAssetType1 = rskAssetType1 == null ? null : rskAssetType1.trim();
    }

    /**
     * 取得 风险资产分类2
     * @return 风险资产分类2
     */
    public String getRskAssetType2() {
        return rskAssetType2;
    }

    /**
     * 设置 风险资产分类2
     * @param rskAssetType2 风险资产分类2
     */
    public void setRskAssetType2(String rskAssetType2) {
        this.rskAssetType2 = rskAssetType2 == null ? null : rskAssetType2.trim();
    }

    /**
     * 取得 风险资产分类3
     * @return 风险资产分类3
     */
    public String getRskAssetType3() {
        return rskAssetType3;
    }

    /**
     * 设置 风险资产分类3
     * @param rskAssetType3 风险资产分类3
     */
    public void setRskAssetType3(String rskAssetType3) {
        this.rskAssetType3 = rskAssetType3 == null ? null : rskAssetType3.trim();
    }

    /**
     * 取得 风险资产分类4
     * @return 风险资产分类4
     */
    public String getRskAssetType4() {
        return rskAssetType4;
    }

    /**
     * 设置 风险资产分类4
     * @param rskAssetType4 风险资产分类4
     */
    public void setRskAssetType4(String rskAssetType4) {
        this.rskAssetType4 = rskAssetType4 == null ? null : rskAssetType4.trim();
    }

    /**
     * 取得 风险资产分类5
     * @return 风险资产分类5
     */
    public String getRskAssetType5() {
        return rskAssetType5;
    }

    /**
     * 设置 风险资产分类5
     * @param rskAssetType5 风险资产分类5
     */
    public void setRskAssetType5(String rskAssetType5) {
        this.rskAssetType5 = rskAssetType5 == null ? null : rskAssetType5.trim();
    }

    /**
     * 取得 名单主体类型，资产代码、托管人、
     * @return 名单主体类型，资产代码、托管人、
     */
    public String getListDetailIdType() {
        return listDetailIdType;
    }

    /**
     * 设置 名单主体类型，资产代码、托管人、
     * @param listDetailIdType 名单主体类型，资产代码、托管人、
     */
    public void setListDetailIdType(String listDetailIdType) {
        this.listDetailIdType = listDetailIdType == null ? null : listDetailIdType.trim();
    }
}