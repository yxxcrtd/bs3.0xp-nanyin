package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class PrdIssueplanAssetDatailKey extends BaseEntity {
    /**
     * 产品发行规划编号
     */
    private String prdIssueplanCode;

    /**
     * 资产大类
     */
    private String assetType;

    /**
     * 取得 产品发行规划编号
     * @return 产品发行规划编号
     */
    public String getPrdIssueplanCode() {
        return prdIssueplanCode;
    }

    /**
     * 设置 产品发行规划编号
     * @param prdIssueplanCode 产品发行规划编号
     */
    public void setPrdIssueplanCode(String prdIssueplanCode) {
        this.prdIssueplanCode = prdIssueplanCode == null ? null : prdIssueplanCode.trim();
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