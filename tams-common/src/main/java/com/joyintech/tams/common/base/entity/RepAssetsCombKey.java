package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class RepAssetsCombKey extends BaseEntity {
    /**
     * 产品主代码
     */
    private String prodId;

    /**
     * 日期
     */
    private Date pubDate;

    /**
     * 资产代码
     */
    private String assetsCode;

    /**
     * 取得 产品主代码
     * @return 产品主代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 产品主代码
     * @param prodId 产品主代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 日期
     * @return 日期
     */
    public Date getPubDate() {
        return pubDate;
    }

    /**
     * 设置 日期
     * @param pubDate 日期
     */
    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    /**
     * 取得 资产代码
     * @return 资产代码
     */
    public String getAssetsCode() {
        return assetsCode;
    }

    /**
     * 设置 资产代码
     * @param assetsCode 资产代码
     */
    public void setAssetsCode(String assetsCode) {
        this.assetsCode = assetsCode == null ? null : assetsCode.trim();
    }
}