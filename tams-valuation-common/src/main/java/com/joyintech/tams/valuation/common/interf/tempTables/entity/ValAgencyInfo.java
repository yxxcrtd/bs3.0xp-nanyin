package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;

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

    /** add by yanya on 20210831 市场机构增加部分信息 start**/
    /**
     * 机构类型（技术领域），高新技术企业、其他
     */
    private String partyType3;

    /**
     * 机构类型（规模），大型企业、中小微企业
     */
    private String partyType1;

    /**
     * 国标一级分类
     */
    private String industry;

    /**
     * 国标二级分类
     */
    private String industry2;

    /**
     * 组织机构代码/统一社会信用代码
     */
    private String insitituteCode;

    /**
     * 是否政府融资平台
     */
    private String isGov;

    /**
     * 核心资本（万元），存的是万元
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal mainCapital;

    /**
     * 注册资本（万元），存的是万元
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal registerCapital;

    /**
     * 所属省
     */
    private String province;

    /**
     * 所属市
     */
    private String city;

    /**
     * 所属县
     */
    private String county;

    /**
     * 控股类型
     */
    private String holdingType;

    /**
     * 客户主体类型，我国中央政府、境外主权国家或经济实体区域政府、中国人民银行、境外中央银行等
     */
    private String customerObjType;

    /**
     * 是否上市公司
     */
    private String isListedCompany;

    /**
     * 是否集团客户
     */
    private String isGroupCustomer;

    /**
     * 所属集团客户名称
     */
    private String parentCustomerName;

    /**
     * 是否民营企业
     */
    private String isPrivate;

    /**
     * 是否为逃废债企业
     */
    private String isEscapeDept;

    /**
     * 授信策略
     */
    private String creditType;

    /**
     * 是否授信类关联交易
     */
    private String isCreditRelatedTrd;

    /**
     * 产业结构调整类型
     */
    private String structAdjustType;

    /**
     * 工业转型升级标识
     */
    private String indusUpgrateType;

    /**
     * 经营范围
     */
    private String busiScope;

    /**
     * 主营业务
     */
    private String mainBusi;

    public String getPartyType3() {
        return partyType3;
    }

    public void setPartyType3(String partyType3) {
        this.partyType3 = partyType3;
    }

    public String getPartyType1() {
        return partyType1;
    }

    public void setPartyType1(String partyType1) {
        this.partyType1 = partyType1;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getIndustry2() {
        return industry2;
    }

    public void setIndustry2(String industry2) {
        this.industry2 = industry2;
    }

    public String getInsitituteCode() {
        return insitituteCode;
    }

    public void setInsitituteCode(String insitituteCode) {
        this.insitituteCode = insitituteCode;
    }

    public String getIsGov() {
        return isGov;
    }

    public void setIsGov(String isGov) {
        this.isGov = isGov;
    }

    public BigDecimal getMainCapital() {
        return mainCapital;
    }

    public void setMainCapital(BigDecimal mainCapital) {
        this.mainCapital = mainCapital;
    }

    public BigDecimal getRegisterCapital() {
        return registerCapital;
    }

    public void setRegisterCapital(BigDecimal registerCapital) {
        this.registerCapital = registerCapital;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getHoldingType() {
        return holdingType;
    }

    public void setHoldingType(String holdingType) {
        this.holdingType = holdingType;
    }

    public String getCustomerObjType() {
        return customerObjType;
    }

    public void setCustomerObjType(String customerObjType) {
        this.customerObjType = customerObjType;
    }

    public String getIsListedCompany() {
        return isListedCompany;
    }

    public void setIsListedCompany(String isListedCompany) {
        this.isListedCompany = isListedCompany;
    }

    public String getIsGroupCustomer() {
        return isGroupCustomer;
    }

    public void setIsGroupCustomer(String isGroupCustomer) {
        this.isGroupCustomer = isGroupCustomer;
    }

    public String getParentCustomerName() {
        return parentCustomerName;
    }

    public void setParentCustomerName(String parentCustomerName) {
        this.parentCustomerName = parentCustomerName;
    }

    public String getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(String isPrivate) {
        this.isPrivate = isPrivate;
    }

    public String getIsEscapeDept() {
        return isEscapeDept;
    }

    public void setIsEscapeDept(String isEscapeDept) {
        this.isEscapeDept = isEscapeDept;
    }

    public String getCreditType() {
        return creditType;
    }

    public void setCreditType(String creditType) {
        this.creditType = creditType;
    }

    public String getIsCreditRelatedTrd() {
        return isCreditRelatedTrd;
    }

    public void setIsCreditRelatedTrd(String isCreditRelatedTrd) {
        this.isCreditRelatedTrd = isCreditRelatedTrd;
    }

    public String getStructAdjustType() {
        return structAdjustType;
    }

    public void setStructAdjustType(String structAdjustType) {
        this.structAdjustType = structAdjustType;
    }

    public String getIndusUpgrateType() {
        return indusUpgrateType;
    }

    public void setIndusUpgrateType(String indusUpgrateType) {
        this.indusUpgrateType = indusUpgrateType;
    }

    public String getBusiScope() {
        return busiScope;
    }

    public void setBusiScope(String busiScope) {
        this.busiScope = busiScope;
    }

    public String getMainBusi() {
        return mainBusi;
    }

    public void setMainBusi(String mainBusi) {
        this.mainBusi = mainBusi;
    }

    /** add by yanya on 20210831 市场机构增加部分信息 end**/


}