/*
 * 文件名：ReconcileSchemeAssetConfigDto.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算POC
 * 修改人：jiangchuanzhi
 * 修改时间：2018年1月23日
 * 修改内容：新建
 * 系统名称：估值核算POC
 */

package com.joyintech.tams.valuation.valuationparametermanage.reconcilescheme.dto;

import java.util.Objects;

import com.joyintech.framework.base.BaseEntity;

/**
 * 资产映射DTO
 * 
 * @author weipeng
 * @version 1.0 2018年11月13日
 * @see ValReconcileSchemeAssetConfigDto
 * @since 1.0
 */
public class ValReconcileSchemeAssetConfigDto extends BaseEntity {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1608528884375817269L;

    /**
     * 方案代码
     */
    private String schemeId;

    /**
     * 本系统资产代码
     */
    private String myAssetId;

    /**
     * 对方系统资产代码
     */
    private String othersideAssetId;

    /**
     * 取得 方案代码
     * 
     * @return 方案代码
     */
    public String getSchemeId() {
        return schemeId;
    }

    /**
     * 设置 方案代码
     * 
     * @param schemeId 方案代码
     */
    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId == null ? null : schemeId.trim();
    }

    /**
     * 取得 本系统资产代码
     * 
     * @return 本系统资产代码
     */
    public String getMyAssetId() {
        return myAssetId;
    }

    /**
     * 设置 本系统资产代码
     * 
     * @param myAssetId 本系统资产代码
     */
    public void setMyAssetId(String myAssetId) {
        this.myAssetId = myAssetId == null ? null : myAssetId.trim();
    }

    /**
     * 取得 对方系统资产代码
     * 
     * @return 对方系统资产代码
     */
    public String getOthersideAssetId() {
        return othersideAssetId;
    }

    /**
     * 设置 对方系统资产代码
     * 
     * @param othersideAssetId 对方系统资产代码
     */
    public void setOthersideAssetId(String othersideAssetId) {
        this.othersideAssetId = othersideAssetId == null ? null : othersideAssetId.trim();
    }
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ValReconcileSchemeAssetConfigDto)) {
            return false;
        }
        ValReconcileSchemeAssetConfigDto asset = (ValReconcileSchemeAssetConfigDto)o;
        return Objects.equals(myAssetId, asset.myAssetId)
               && Objects.equals(othersideAssetId, asset.othersideAssetId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(myAssetId, othersideAssetId);
    }
}
