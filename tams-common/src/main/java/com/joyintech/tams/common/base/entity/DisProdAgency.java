package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class DisProdAgency extends BaseEntity {
    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 代销机构id
     */
    private String agencyId;

    /**
     * 代销机构名称
     */
    private String agencyName;

    /**
     * 产品名称
     */
    private String prodName;

    /**
     * 产品系列
     */
    private String prodSeries;

    /**
     * 取得 产品代码
     * @return 产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 产品代码
     * @param prodId 产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 代销机构id
     * @return 代销机构id
     */
    public String getAgencyId() {
        return agencyId;
    }

    /**
     * 设置 代销机构id
     * @param agencyId 代销机构id
     */
    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId == null ? null : agencyId.trim();
    }

    /**
     * 取得 代销机构名称
     * @return 代销机构名称
     */
    public String getAgencyName() {
        return agencyName;
    }

    /**
     * 设置 代销机构名称
     * @param agencyName 代销机构名称
     */
    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName == null ? null : agencyName.trim();
    }

    /**
     * 取得 产品名称
     * @return 产品名称
     */
    public String getProdName() {
        return prodName;
    }

    /**
     * 设置 产品名称
     * @param prodName 产品名称
     */
    public void setProdName(String prodName) {
        this.prodName = prodName == null ? null : prodName.trim();
    }

    /**
     * 取得 产品系列
     * @return 产品系列
     */
    public String getProdSeries() {
        return prodSeries;
    }

    /**
     * 设置 产品系列
     * @param prodSeries 产品系列
     */
    public void setProdSeries(String prodSeries) {
        this.prodSeries = prodSeries == null ? null : prodSeries.trim();
    }
}