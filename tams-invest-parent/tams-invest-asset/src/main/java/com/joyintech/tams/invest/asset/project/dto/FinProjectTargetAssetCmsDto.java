/*
 * 文件名：FinProjectTargetAssetCmsDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年11月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.asset.project.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.tams.common.base.entity.InterfCreditItemInfo;
import com.joyintech.framework.json.BigDecimalSerializer;

/**
 *  项目类资产CMS DTO
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月21日
 * @see FinProjectTargetAssetCmsDto
 * @since 1.0
 */
public class FinProjectTargetAssetCmsDto extends InterfCreditItemInfo{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
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
     * ECIF
     */
    private String ecif;

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
    private String creditType1;

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
     * 客户信息
     */
    private String gradeOrgId;

    /**
     * 评级类型
     */
    private String gradeType;

    /**
     * 评级认定结果
     */
    private String gradeResult;

    /**
     * 评级报表年度
     */
    private String gradeRptYear;

    /**
     * 使用模型类别
     */
    private String gradeModelType;

    /**
     * 评级生效日
     */
    private Date gradeEffDate;

    /**
     * 评级到期日
     */
    private Date gradeEndDate;

    
    /**
     * 获取 customerId
     * @return customerId.
     */
    public String getCustomerId() {
        return customerId;
    }

    
    /**
     * 设置 customerId
     * @param customerId customerId
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    
    /**
     * 获取 institutionName
     * @return institutionName.
     */
    public String getInstitutionName() {
        return institutionName;
    }

    
    /**
     * 设置 institutionName
     * @param institutionName institutionName
     */
    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    
    /**
     * 获取 partyType3
     * @return partyType3.
     */
    public String getPartyType3() {
        return partyType3;
    }

    
    /**
     * 设置 partyType3
     * @param partyType3 partyType3
     */
    public void setPartyType3(String partyType3) {
        this.partyType3 = partyType3;
    }

    
    /**
     * 获取 repIndustry
     * @return repIndustry.
     */
    public String getRepIndustry() {
        return repIndustry;
    }

    
    /**
     * 设置 repIndustry
     * @param repIndustry repIndustry
     */
    public void setRepIndustry(String repIndustry) {
        this.repIndustry = repIndustry;
    }

    
    /**
     * 获取 repIndustryLevel2
     * @return repIndustryLevel2.
     */
    public String getRepIndustryLevel2() {
        return repIndustryLevel2;
    }

    
    /**
     * 设置 repIndustryLevel2
     * @param repIndustryLevel2 repIndustryLevel2
     */
    public void setRepIndustryLevel2(String repIndustryLevel2) {
        this.repIndustryLevel2 = repIndustryLevel2;
    }

    
    /**
     * 获取 partyType2
     * @return partyType2.
     */
    public String getPartyType2() {
        return partyType2;
    }

    
    /**
     * 设置 partyType2
     * @param partyType2 partyType2
     */
    public void setPartyType2(String partyType2) {
        this.partyType2 = partyType2;
    }

    
    /**
     * 获取 partyType1
     * @return partyType1.
     */
    public String getPartyType1() {
        return partyType1;
    }

    
    /**
     * 设置 partyType1
     * @param partyType1 partyType1
     */
    public void setPartyType1(String partyType1) {
        this.partyType1 = partyType1;
    }

    
    /**
     * 获取 isTopCompany
     * @return isTopCompany.
     */
    public String getIsTopCompany() {
        return isTopCompany;
    }

    
    /**
     * 设置 isTopCompany
     * @param isTopCompany isTopCompany
     */
    public void setIsTopCompany(String isTopCompany) {
        this.isTopCompany = isTopCompany;
    }

    
    /**
     * 获取 institutionCode
     * @return institutionCode.
     */
    public String getInstitutionCode() {
        return institutionCode;
    }

    
    /**
     * 设置 institutionCode
     * @param institutionCode institutionCode
     */
    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode;
    }

    
    /**
     * 获取 isGov
     * @return isGov.
     */
    public String getIsGov() {
        return isGov;
    }

    
    /**
     * 设置 isGov
     * @param isGov isGov
     */
    public void setIsGov(String isGov) {
        this.isGov = isGov;
    }

    
    /**
     * 获取 ecif
     * @return ecif.
     */
    public String getEcif() {
        return ecif;
    }

    
    /**
     * 设置 ecif
     * @param ecif ecif
     */
    public void setEcif(String ecif) {
        this.ecif = ecif;
    }

    
    /**
     * 获取 province
     * @return province.
     */
    public String getProvince() {
        return province;
    }

    
    /**
     * 设置 province
     * @param province province
     */
    public void setProvince(String province) {
        this.province = province;
    }

    
    /**
     * 获取 city
     * @return city.
     */
    public String getCity() {
        return city;
    }

    
    /**
     * 设置 city
     * @param city city
     */
    public void setCity(String city) {
        this.city = city;
    }

    
    /**
     * 获取 county
     * @return county.
     */
    public String getCounty() {
        return county;
    }

    
    /**
     * 设置 county
     * @param county county
     */
    public void setCounty(String county) {
        this.county = county;
    }

    
    /**
     * 获取 holdingType
     * @return holdingType.
     */
    public String getHoldingType() {
        return holdingType;
    }

    
    /**
     * 设置 holdingType
     * @param holdingType holdingType
     */
    public void setHoldingType(String holdingType) {
        this.holdingType = holdingType;
    }

    
    /**
     * 获取 customerObjType
     * @return customerObjType.
     */
    public String getCustomerObjType() {
        return customerObjType;
    }

    
    /**
     * 设置 customerObjType
     * @param customerObjType customerObjType
     */
    public void setCustomerObjType(String customerObjType) {
        this.customerObjType = customerObjType;
    }

    
    /**
     * 获取 isListedCompany
     * @return isListedCompany.
     */
    public String getIsListedCompany() {
        return isListedCompany;
    }

    
    /**
     * 设置 isListedCompany
     * @param isListedCompany isListedCompany
     */
    public void setIsListedCompany(String isListedCompany) {
        this.isListedCompany = isListedCompany;
    }

    
    /**
     * 获取 isGroupCustomer
     * @return isGroupCustomer.
     */
    public String getIsGroupCustomer() {
        return isGroupCustomer;
    }

    
    /**
     * 设置 isGroupCustomer
     * @param isGroupCustomer isGroupCustomer
     */
    public void setIsGroupCustomer(String isGroupCustomer) {
        this.isGroupCustomer = isGroupCustomer;
    }

    
    /**
     * 获取 parentCustomerId
     * @return parentCustomerId.
     */
    public String getParentCustomerId() {
        return parentCustomerId;
    }

    
    /**
     * 设置 parentCustomerId
     * @param parentCustomerId parentCustomerId
     */
    public void setParentCustomerId(String parentCustomerId) {
        this.parentCustomerId = parentCustomerId;
    }

    
    /**
     * 获取 parentCustomerName
     * @return parentCustomerName.
     */
    public String getParentCustomerName() {
        return parentCustomerName;
    }

    
    /**
     * 设置 parentCustomerName
     * @param parentCustomerName parentCustomerName
     */
    public void setParentCustomerName(String parentCustomerName) {
        this.parentCustomerName = parentCustomerName;
    }

    
    /**
     * 获取 isPrivate
     * @return isPrivate.
     */
    public String getIsPrivate() {
        return isPrivate;
    }

    
    /**
     * 设置 isPrivate
     * @param isPrivate isPrivate
     */
    public void setIsPrivate(String isPrivate) {
        this.isPrivate = isPrivate;
    }

    
    /**
     * 获取 isEscapeDept
     * @return isEscapeDept.
     */
    public String getIsEscapeDept() {
        return isEscapeDept;
    }

    
    /**
     * 设置 isEscapeDept
     * @param isEscapeDept isEscapeDept
     */
    public void setIsEscapeDept(String isEscapeDept) {
        this.isEscapeDept = isEscapeDept;
    }

    
    

    
    
    /**
     * 获取 creditType1
     * @return creditType1.
     */
    public String getCreditType1() {
        return creditType1;
    }


    
    /**
     * 设置 creditType1
     * @param creditType1 creditType1
     */
    public void setCreditType1(String creditType1) {
        this.creditType1 = creditType1;
    }


    /**
     * 获取 isCreditRelatedTrd
     * @return isCreditRelatedTrd.
     */
    public String getIsCreditRelatedTrd() {
        return isCreditRelatedTrd;
    }

    
    /**
     * 设置 isCreditRelatedTrd
     * @param isCreditRelatedTrd isCreditRelatedTrd
     */
    public void setIsCreditRelatedTrd(String isCreditRelatedTrd) {
        this.isCreditRelatedTrd = isCreditRelatedTrd;
    }

    
    /**
     * 获取 structAdjustType
     * @return structAdjustType.
     */
    public String getStructAdjustType() {
        return structAdjustType;
    }

    
    /**
     * 设置 structAdjustType
     * @param structAdjustType structAdjustType
     */
    public void setStructAdjustType(String structAdjustType) {
        this.structAdjustType = structAdjustType;
    }

    
    /**
     * 获取 indusUpgrateType
     * @return indusUpgrateType.
     */
    public String getIndusUpgrateType() {
        return indusUpgrateType;
    }

    
    /**
     * 设置 indusUpgrateType
     * @param indusUpgrateType indusUpgrateType
     */
    public void setIndusUpgrateType(String indusUpgrateType) {
        this.indusUpgrateType = indusUpgrateType;
    }

    
    /**
     * 获取 busiScope
     * @return busiScope.
     */
    public String getBusiScope() {
        return busiScope;
    }

    
    /**
     * 设置 busiScope
     * @param busiScope busiScope
     */
    public void setBusiScope(String busiScope) {
        this.busiScope = busiScope;
    }

    
    /**
     * 获取 mainBusi
     * @return mainBusi.
     */
    public String getMainBusi() {
        return mainBusi;
    }

    
    /**
     * 设置 mainBusi
     * @param mainBusi mainBusi
     */
    public void setMainBusi(String mainBusi) {
        this.mainBusi = mainBusi;
    }

    
    /**
     * 获取 customerLevel
     * @return customerLevel.
     */
    public String getCustomerLevel() {
        return customerLevel;
    }

    
    /**
     * 设置 customerLevel
     * @param customerLevel customerLevel
     */
    public void setCustomerLevel(String customerLevel) {
        this.customerLevel = customerLevel;
    }

    
    /**
     * 获取 gradeOrgId
     * @return gradeOrgId.
     */
    public String getGradeOrgId() {
        return gradeOrgId;
    }

    
    /**
     * 设置 gradeOrgId
     * @param gradeOrgId gradeOrgId
     */
    public void setGradeOrgId(String gradeOrgId) {
        this.gradeOrgId = gradeOrgId;
    }

    
    /**
     * 获取 gradeType
     * @return gradeType.
     */
    public String getGradeType() {
        return gradeType;
    }

    
    /**
     * 设置 gradeType
     * @param gradeType gradeType
     */
    public void setGradeType(String gradeType) {
        this.gradeType = gradeType;
    }

    
    /**
     * 获取 gradeResult
     * @return gradeResult.
     */
    public String getGradeResult() {
        return gradeResult;
    }

    
    /**
     * 设置 gradeResult
     * @param gradeResult gradeResult
     */
    public void setGradeResult(String gradeResult) {
        this.gradeResult = gradeResult;
    }

    
    /**
     * 获取 gradeRptYear
     * @return gradeRptYear.
     */
    public String getGradeRptYear() {
        return gradeRptYear;
    }

    
    /**
     * 设置 gradeRptYear
     * @param gradeRptYear gradeRptYear
     */
    public void setGradeRptYear(String gradeRptYear) {
        this.gradeRptYear = gradeRptYear;
    }

    
    /**
     * 获取 gradeModelType
     * @return gradeModelType.
     */
    public String getGradeModelType() {
        return gradeModelType;
    }

    
    /**
     * 设置 gradeModelType
     * @param gradeModelType gradeModelType
     */
    public void setGradeModelType(String gradeModelType) {
        this.gradeModelType = gradeModelType;
    }

    
    /**
     * 获取 gradeEffDate
     * @return gradeEffDate.
     */
    public Date getGradeEffDate() {
        return gradeEffDate;
    }

    
    /**
     * 设置 gradeEffDate
     * @param gradeEffDate gradeEffDate
     */
    public void setGradeEffDate(Date gradeEffDate) {
        this.gradeEffDate = gradeEffDate;
    }

    
    /**
     * 获取 gradeEndDate
     * @return gradeEndDate.
     */
    public Date getGradeEndDate() {
        return gradeEndDate;
    }

    
    /**
     * 设置 gradeEndDate
     * @param gradeEndDate gradeEndDate
     */
    public void setGradeEndDate(Date gradeEndDate) {
        this.gradeEndDate = gradeEndDate;
    }


    
    /**
     * 获取 registCapital
     * @return registCapital.
     */
    public BigDecimal getRegistCapital() {
        return registCapital;
    }


    
    /**
     * 设置 registCapital
     * @param registCapital registCapital
     */
    public void setRegistCapital(BigDecimal registCapital) {
        this.registCapital = registCapital;
    }


    
    /**
     * 获取 actualCapital
     * @return actualCapital.
     */
    public BigDecimal getActualCapital() {
        return actualCapital;
    }


    
    /**
     * 设置 actualCapital
     * @param actualCapital actualCapital
     */
    public void setActualCapital(BigDecimal actualCapital) {
        this.actualCapital = actualCapital;
    }
    
    

}
