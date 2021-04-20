package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class InterfCfetsCustomerTmp extends BaseEntity {
    /**
     * 交易成员ID，21位编号
     */
    private String partyId;

    /**
     * 交易机构ID，6位编号
     */
    private String partyCustomerId;

    /**
     * 交易成员简称
     */
    private String partyAbbr;

    /**
     * 交易成员全称
     */
    private String partyName;

    /**
     * 交易成员英文简称
     */
    private String partyAbbrEn;

    /**
     * 交易成员英文全称
     */
    private String partyNameEn;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 交易成员ID，21位编号
     * @return 交易成员ID，21位编号
     */
    public String getPartyId() {
        return partyId;
    }

    /**
     * 设置 交易成员ID，21位编号
     * @param partyId 交易成员ID，21位编号
     */
    public void setPartyId(String partyId) {
        this.partyId = partyId == null ? null : partyId.trim();
    }

    /**
     * 取得 交易机构ID，6位编号
     * @return 交易机构ID，6位编号
     */
    public String getPartyCustomerId() {
        return partyCustomerId;
    }

    /**
     * 设置 交易机构ID，6位编号
     * @param partyCustomerId 交易机构ID，6位编号
     */
    public void setPartyCustomerId(String partyCustomerId) {
        this.partyCustomerId = partyCustomerId == null ? null : partyCustomerId.trim();
    }

    /**
     * 取得 交易成员简称
     * @return 交易成员简称
     */
    public String getPartyAbbr() {
        return partyAbbr;
    }

    /**
     * 设置 交易成员简称
     * @param partyAbbr 交易成员简称
     */
    public void setPartyAbbr(String partyAbbr) {
        this.partyAbbr = partyAbbr == null ? null : partyAbbr.trim();
    }

    /**
     * 取得 交易成员全称
     * @return 交易成员全称
     */
    public String getPartyName() {
        return partyName;
    }

    /**
     * 设置 交易成员全称
     * @param partyName 交易成员全称
     */
    public void setPartyName(String partyName) {
        this.partyName = partyName == null ? null : partyName.trim();
    }

    /**
     * 取得 交易成员英文简称
     * @return 交易成员英文简称
     */
    public String getPartyAbbrEn() {
        return partyAbbrEn;
    }

    /**
     * 设置 交易成员英文简称
     * @param partyAbbrEn 交易成员英文简称
     */
    public void setPartyAbbrEn(String partyAbbrEn) {
        this.partyAbbrEn = partyAbbrEn == null ? null : partyAbbrEn.trim();
    }

    /**
     * 取得 交易成员英文全称
     * @return 交易成员英文全称
     */
    public String getPartyNameEn() {
        return partyNameEn;
    }

    /**
     * 设置 交易成员英文全称
     * @param partyNameEn 交易成员英文全称
     */
    public void setPartyNameEn(String partyNameEn) {
        this.partyNameEn = partyNameEn == null ? null : partyNameEn.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}