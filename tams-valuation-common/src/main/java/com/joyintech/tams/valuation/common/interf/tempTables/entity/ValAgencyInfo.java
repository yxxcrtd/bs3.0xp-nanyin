package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.joyintech.framework.base.BaseEntity;

public class ValAgencyInfo extends BaseEntity {
    /**
     * 机构代码
     */
    private String agencyId;

    /**
     * 机构名称
     */
    private String agencyName;

    /**
     * 机构简称
     */
    private String agencyAbbr;

    /**
     * 数据动作标志
     */
    private String dataActionSign;

    /**
     * 是否法人机构
     */
    private String isCorporation;

    /**
     * 是否交易对手
     */
    private String isCounterParty;

    /**
     * 是否发行人
     */
    private String isIssuer;

    /**
     * 是否管理人
     */
    private String isManager;

    /**
     * 是否托管人
     */
    private String isTruster;

    /**
     * 是否承销商
     */
    private String isSaler;

    /**
     * 企业性质
     */
    private String corporationType;

    /**
     * 机构类型
     */
    private String agencyType;

    /**
     * 境内外
     */
    private String blnArea;

    /**
     * 核算分类
     */
    private String valClas;

    /**
     * 取得 机构代码
     * @return 机构代码
     */
    public String getAgencyId() {
        return agencyId;
    }

    /**
     * 设置 机构代码
     * @param agencyId 机构代码
     */
    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId == null ? null : agencyId.trim();
    }

    /**
     * 取得 机构名称
     * @return 机构名称
     */
    public String getAgencyName() {
        return agencyName;
    }

    /**
     * 设置 机构名称
     * @param agencyName 机构名称
     */
    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName == null ? null : agencyName.trim();
    }

    /**
     * 取得 机构简称
     * @return 机构简称
     */
    public String getAgencyAbbr() {
        return agencyAbbr;
    }

    /**
     * 设置 机构简称
     * @param agencyAbbr 机构简称
     */
    public void setAgencyAbbr(String agencyAbbr) {
        this.agencyAbbr = agencyAbbr == null ? null : agencyAbbr.trim();
    }

    /**
     * 取得 数据动作标志
     * @return 数据动作标志
     */
    public String getDataActionSign() {
        return dataActionSign;
    }

    /**
     * 设置 数据动作标志
     * @param dataActionSign 数据动作标志
     */
    public void setDataActionSign(String dataActionSign) {
        this.dataActionSign = dataActionSign == null ? null : dataActionSign.trim();
    }

    /**
     * 取得 是否法人机构
     * @return 是否法人机构
     */
    public String getIsCorporation() {
        return isCorporation;
    }

    /**
     * 设置 是否法人机构
     * @param isCorporation 是否法人机构
     */
    public void setIsCorporation(String isCorporation) {
        this.isCorporation = isCorporation == null ? null : isCorporation.trim();
    }

    /**
     * 取得 是否交易对手
     * @return 是否交易对手
     */
    public String getIsCounterParty() {
        return isCounterParty;
    }

    /**
     * 设置 是否交易对手
     * @param isCounterParty 是否交易对手
     */
    public void setIsCounterParty(String isCounterParty) {
        this.isCounterParty = isCounterParty == null ? null : isCounterParty.trim();
    }

    /**
     * 取得 是否发行人
     * @return 是否发行人
     */
    public String getIsIssuer() {
        return isIssuer;
    }

    /**
     * 设置 是否发行人
     * @param isIssuer 是否发行人
     */
    public void setIsIssuer(String isIssuer) {
        this.isIssuer = isIssuer == null ? null : isIssuer.trim();
    }

    /**
     * 取得 是否管理人
     * @return 是否管理人
     */
    public String getIsManager() {
        return isManager;
    }

    /**
     * 设置 是否管理人
     * @param isManager 是否管理人
     */
    public void setIsManager(String isManager) {
        this.isManager = isManager == null ? null : isManager.trim();
    }

    /**
     * 取得 是否托管人
     * @return 是否托管人
     */
    public String getIsTruster() {
        return isTruster;
    }

    /**
     * 设置 是否托管人
     * @param isTruster 是否托管人
     */
    public void setIsTruster(String isTruster) {
        this.isTruster = isTruster == null ? null : isTruster.trim();
    }

    /**
     * 取得 是否承销商
     * @return 是否承销商
     */
    public String getIsSaler() {
        return isSaler;
    }

    /**
     * 设置 是否承销商
     * @param isSaler 是否承销商
     */
    public void setIsSaler(String isSaler) {
        this.isSaler = isSaler == null ? null : isSaler.trim();
    }

    /**
     * 取得 企业性质
     * @return 企业性质
     */
    public String getCorporationType() {
        return corporationType;
    }

    /**
     * 设置 企业性质
     * @param corporationType 企业性质
     */
    public void setCorporationType(String corporationType) {
        this.corporationType = corporationType == null ? null : corporationType.trim();
    }

    /**
     * 取得 机构类型
     * @return 机构类型
     */
    public String getAgencyType() {
        return agencyType;
    }

    /**
     * 设置 机构类型
     * @param agencyType 机构类型
     */
    public void setAgencyType(String agencyType) {
        this.agencyType = agencyType == null ? null : agencyType.trim();
    }

    /**
     * 取得 境内外
     * @return 境内外
     */
    public String getBlnArea() {
        return blnArea;
    }

    /**
     * 设置 境内外
     * @param blnArea 境内外
     */
    public void setBlnArea(String blnArea) {
        this.blnArea = blnArea == null ? null : blnArea.trim();
    }

    /**
     * 取得 核算分类
     * @return 核算分类
     */
    public String getValClas() {
        return valClas;
    }

    /**
     * 设置 核算分类
     * @param valClas 核算分类
     */
    public void setValClas(String valClas) {
        this.valClas = valClas == null ? null : valClas.trim();
    }
}