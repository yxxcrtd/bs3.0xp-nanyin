package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class DisProdAgency extends BaseEntity {
    /**
     * 代销机构id,与DIS_SALE_AGENCY表的SEQUENCE_NO关联
     */
    private String agencyId;

    /**
     * 产品代码
     */
    private String prodId;
	
	 /**
     * 代销机构名称
     */
    private String agencyName;

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
     * 取得 代销机构id,与DIS_SALE_AGENCY表的SEQUENCE_NO关联
     * @return 代销机构id,与DIS_SALE_AGENCY表的SEQUENCE_NO关联
     */
    public String getAgencyId() {
        return agencyId;
    }

    /**
     * 设置 代销机构id,与DIS_SALE_AGENCY表的SEQUENCE_NO关联
     * @param agencyId 代销机构id,与DIS_SALE_AGENCY表的SEQUENCE_NO关联
     */
    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId == null ? null : agencyId.trim();
    }

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
}