package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class PaFinProductAssetAttach extends BaseEntity {
    /**
     * 资产附件类型编号
     */
    private String assetAttachNo;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 资产附件类型
     */
    private String assetAttachType;

    /**
     * 取得 资产附件类型编号
     * @return 资产附件类型编号
     */
    public String getAssetAttachNo() {
        return assetAttachNo;
    }

    /**
     * 设置 资产附件类型编号
     * @param assetAttachNo 资产附件类型编号
     */
    public void setAssetAttachNo(String assetAttachNo) {
        this.assetAttachNo = assetAttachNo == null ? null : assetAttachNo.trim();
    }

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
     * 取得 资产附件类型
     * @return 资产附件类型
     */
    public String getAssetAttachType() {
        return assetAttachType;
    }

    /**
     * 设置 资产附件类型
     * @param assetAttachType 资产附件类型
     */
    public void setAssetAttachType(String assetAttachType) {
        this.assetAttachType = assetAttachType == null ? null : assetAttachType.trim();
    }
}