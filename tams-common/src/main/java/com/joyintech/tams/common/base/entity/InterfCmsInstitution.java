package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class InterfCmsInstitution extends BaseEntity {
    /**
     * 客户编号
     */
    private String customerId;

    /**
     * 市场机构名称
     */
    private String institutionName;

    /**
     * 技术领域，是：01 高新技术企业，否：99 其他
     */
    private String partyType3;

    /**
     * 所属行业
     */
    private String repIndustry;

    /**
     * 行业二级分类
     */
    private String repIndustryLevel2;

    /**
     * 机构类型（经济类型）
     */
    private String partyType2;

    /**
     * 机构类型（规模）
     */
    private String partyType1;

    /**
     * 公司性质，否：下属公司，是：总公司
     */
    private String isTopCompany;

    /**
     * 组织机构代码/统一社会信用代码
     */
    private String institutionCode;

    /**
     * 是否政府融资平台
     */
    private String isGov;

    /**
     * BECIF
     */
    private String becif;

    /**
     * 融资人所属省
     */
    private String province;

    /**
     * 融资人所属市
     */
    private String city;

    /**
     * 融资人所属县
     */
    private String county;

    /**
     * 控股类型
     */
    private String holdingType;

    /**
     * 客户主体类型
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
     * 所属集团客户编号
     */
    private String parentCustomerId;

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

    /**
     * 客户评级
     */
    private String customerLevel;

    /**
     * 注册资本
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal registCapital;

    /**
     * 实收资本
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal actualCapital;

    /**
     * 取得 客户编号
     * @return 客户编号
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * 设置 客户编号
     * @param customerId 客户编号
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    /**
     * 取得 市场机构名称
     * @return 市场机构名称
     */
    public String getInstitutionName() {
        return institutionName;
    }

    /**
     * 设置 市场机构名称
     * @param institutionName 市场机构名称
     */
    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName == null ? null : institutionName.trim();
    }

    /**
     * 取得 技术领域，是：01 高新技术企业，否：99 其他
     * @return 技术领域，是：01 高新技术企业，否：99 其他
     */
    public String getPartyType3() {
        return partyType3;
    }

    /**
     * 设置 技术领域，是：01 高新技术企业，否：99 其他
     * @param partyType3 技术领域，是：01 高新技术企业，否：99 其他
     */
    public void setPartyType3(String partyType3) {
        this.partyType3 = partyType3 == null ? null : partyType3.trim();
    }

    /**
     * 取得 所属行业
     * @return 所属行业
     */
    public String getRepIndustry() {
        return repIndustry;
    }

    /**
     * 设置 所属行业
     * @param repIndustry 所属行业
     */
    public void setRepIndustry(String repIndustry) {
        this.repIndustry = repIndustry == null ? null : repIndustry.trim();
    }

    /**
     * 取得 行业二级分类
     * @return 行业二级分类
     */
    public String getRepIndustryLevel2() {
        return repIndustryLevel2;
    }

    /**
     * 设置 行业二级分类
     * @param repIndustryLevel2 行业二级分类
     */
    public void setRepIndustryLevel2(String repIndustryLevel2) {
        this.repIndustryLevel2 = repIndustryLevel2 == null ? null : repIndustryLevel2.trim();
    }

    /**
     * 取得 机构类型（经济类型）
     * @return 机构类型（经济类型）
     */
    public String getPartyType2() {
        return partyType2;
    }

    /**
     * 设置 机构类型（经济类型）
     * @param partyType2 机构类型（经济类型）
     */
    public void setPartyType2(String partyType2) {
        this.partyType2 = partyType2 == null ? null : partyType2.trim();
    }

    /**
     * 取得 机构类型（规模）
     * @return 机构类型（规模）
     */
    public String getPartyType1() {
        return partyType1;
    }

    /**
     * 设置 机构类型（规模）
     * @param partyType1 机构类型（规模）
     */
    public void setPartyType1(String partyType1) {
        this.partyType1 = partyType1 == null ? null : partyType1.trim();
    }

    /**
     * 取得 公司性质，否：下属公司，是：总公司
     * @return 公司性质，否：下属公司，是：总公司
     */
    public String getIsTopCompany() {
        return isTopCompany;
    }

    /**
     * 设置 公司性质，否：下属公司，是：总公司
     * @param isTopCompany 公司性质，否：下属公司，是：总公司
     */
    public void setIsTopCompany(String isTopCompany) {
        this.isTopCompany = isTopCompany == null ? null : isTopCompany.trim();
    }

    /**
     * 取得 组织机构代码/统一社会信用代码
     * @return 组织机构代码/统一社会信用代码
     */
    public String getInstitutionCode() {
        return institutionCode;
    }

    /**
     * 设置 组织机构代码/统一社会信用代码
     * @param institutionCode 组织机构代码/统一社会信用代码
     */
    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode == null ? null : institutionCode.trim();
    }

    /**
     * 取得 是否政府融资平台
     * @return 是否政府融资平台
     */
    public String getIsGov() {
        return isGov;
    }

    /**
     * 设置 是否政府融资平台
     * @param isGov 是否政府融资平台
     */
    public void setIsGov(String isGov) {
        this.isGov = isGov == null ? null : isGov.trim();
    }

    /**
     * 取得 BECIF
     * @return BECIF
     */
    public String getBecif() {
        return becif;
    }

    /**
     * 设置 BECIF
     * @param becif BECIF
     */
    public void setBecif(String becif) {
        this.becif = becif == null ? null : becif.trim();
    }

    /**
     * 取得 融资人所属省
     * @return 融资人所属省
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置 融资人所属省
     * @param province 融资人所属省
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     * 取得 融资人所属市
     * @return 融资人所属市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置 融资人所属市
     * @param city 融资人所属市
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * 取得 融资人所属县
     * @return 融资人所属县
     */
    public String getCounty() {
        return county;
    }

    /**
     * 设置 融资人所属县
     * @param county 融资人所属县
     */
    public void setCounty(String county) {
        this.county = county == null ? null : county.trim();
    }

    /**
     * 取得 控股类型
     * @return 控股类型
     */
    public String getHoldingType() {
        return holdingType;
    }

    /**
     * 设置 控股类型
     * @param holdingType 控股类型
     */
    public void setHoldingType(String holdingType) {
        this.holdingType = holdingType == null ? null : holdingType.trim();
    }

    /**
     * 取得 客户主体类型
     * @return 客户主体类型
     */
    public String getCustomerObjType() {
        return customerObjType;
    }

    /**
     * 设置 客户主体类型
     * @param customerObjType 客户主体类型
     */
    public void setCustomerObjType(String customerObjType) {
        this.customerObjType = customerObjType == null ? null : customerObjType.trim();
    }

    /**
     * 取得 是否上市公司
     * @return 是否上市公司
     */
    public String getIsListedCompany() {
        return isListedCompany;
    }

    /**
     * 设置 是否上市公司
     * @param isListedCompany 是否上市公司
     */
    public void setIsListedCompany(String isListedCompany) {
        this.isListedCompany = isListedCompany == null ? null : isListedCompany.trim();
    }

    /**
     * 取得 是否集团客户
     * @return 是否集团客户
     */
    public String getIsGroupCustomer() {
        return isGroupCustomer;
    }

    /**
     * 设置 是否集团客户
     * @param isGroupCustomer 是否集团客户
     */
    public void setIsGroupCustomer(String isGroupCustomer) {
        this.isGroupCustomer = isGroupCustomer == null ? null : isGroupCustomer.trim();
    }

    /**
     * 取得 所属集团客户编号
     * @return 所属集团客户编号
     */
    public String getParentCustomerId() {
        return parentCustomerId;
    }

    /**
     * 设置 所属集团客户编号
     * @param parentCustomerId 所属集团客户编号
     */
    public void setParentCustomerId(String parentCustomerId) {
        this.parentCustomerId = parentCustomerId == null ? null : parentCustomerId.trim();
    }

    /**
     * 取得 所属集团客户名称
     * @return 所属集团客户名称
     */
    public String getParentCustomerName() {
        return parentCustomerName;
    }

    /**
     * 设置 所属集团客户名称
     * @param parentCustomerName 所属集团客户名称
     */
    public void setParentCustomerName(String parentCustomerName) {
        this.parentCustomerName = parentCustomerName == null ? null : parentCustomerName.trim();
    }

    /**
     * 取得 是否民营企业
     * @return 是否民营企业
     */
    public String getIsPrivate() {
        return isPrivate;
    }

    /**
     * 设置 是否民营企业
     * @param isPrivate 是否民营企业
     */
    public void setIsPrivate(String isPrivate) {
        this.isPrivate = isPrivate == null ? null : isPrivate.trim();
    }

    /**
     * 取得 是否为逃废债企业
     * @return 是否为逃废债企业
     */
    public String getIsEscapeDept() {
        return isEscapeDept;
    }

    /**
     * 设置 是否为逃废债企业
     * @param isEscapeDept 是否为逃废债企业
     */
    public void setIsEscapeDept(String isEscapeDept) {
        this.isEscapeDept = isEscapeDept == null ? null : isEscapeDept.trim();
    }

    /**
     * 取得 授信策略
     * @return 授信策略
     */
    public String getCreditType() {
        return creditType;
    }

    /**
     * 设置 授信策略
     * @param creditType 授信策略
     */
    public void setCreditType(String creditType) {
        this.creditType = creditType == null ? null : creditType.trim();
    }

    /**
     * 取得 是否授信类关联交易
     * @return 是否授信类关联交易
     */
    public String getIsCreditRelatedTrd() {
        return isCreditRelatedTrd;
    }

    /**
     * 设置 是否授信类关联交易
     * @param isCreditRelatedTrd 是否授信类关联交易
     */
    public void setIsCreditRelatedTrd(String isCreditRelatedTrd) {
        this.isCreditRelatedTrd = isCreditRelatedTrd == null ? null : isCreditRelatedTrd.trim();
    }

    /**
     * 取得 产业结构调整类型
     * @return 产业结构调整类型
     */
    public String getStructAdjustType() {
        return structAdjustType;
    }

    /**
     * 设置 产业结构调整类型
     * @param structAdjustType 产业结构调整类型
     */
    public void setStructAdjustType(String structAdjustType) {
        this.structAdjustType = structAdjustType == null ? null : structAdjustType.trim();
    }

    /**
     * 取得 工业转型升级标识
     * @return 工业转型升级标识
     */
    public String getIndusUpgrateType() {
        return indusUpgrateType;
    }

    /**
     * 设置 工业转型升级标识
     * @param indusUpgrateType 工业转型升级标识
     */
    public void setIndusUpgrateType(String indusUpgrateType) {
        this.indusUpgrateType = indusUpgrateType == null ? null : indusUpgrateType.trim();
    }

    /**
     * 取得 经营范围
     * @return 经营范围
     */
    public String getBusiScope() {
        return busiScope;
    }

    /**
     * 设置 经营范围
     * @param busiScope 经营范围
     */
    public void setBusiScope(String busiScope) {
        this.busiScope = busiScope == null ? null : busiScope.trim();
    }

    /**
     * 取得 主营业务
     * @return 主营业务
     */
    public String getMainBusi() {
        return mainBusi;
    }

    /**
     * 设置 主营业务
     * @param mainBusi 主营业务
     */
    public void setMainBusi(String mainBusi) {
        this.mainBusi = mainBusi == null ? null : mainBusi.trim();
    }

    /**
     * 取得 客户评级
     * @return 客户评级
     */
    public String getCustomerLevel() {
        return customerLevel;
    }

    /**
     * 设置 客户评级
     * @param customerLevel 客户评级
     */
    public void setCustomerLevel(String customerLevel) {
        this.customerLevel = customerLevel == null ? null : customerLevel.trim();
    }

    /**
     * 取得 注册资本
     * @return 注册资本
     */
    public BigDecimal getRegistCapital() {
        return registCapital;
    }

    /**
     * 设置 注册资本
     * @param registCapital 注册资本
     */
    public void setRegistCapital(BigDecimal registCapital) {
        this.registCapital = registCapital;
    }

    /**
     * 取得 实收资本
     * @return 实收资本
     */
    public BigDecimal getActualCapital() {
        return actualCapital;
    }

    /**
     * 设置 实收资本
     * @param actualCapital 实收资本
     */
    public void setActualCapital(BigDecimal actualCapital) {
        this.actualCapital = actualCapital;
    }
}