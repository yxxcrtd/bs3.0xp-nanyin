/*
 * 文件名：InterfLc0010Entity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2019年1月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ilc0010.entity;

import java.math.BigDecimal;
import java.util.List;

import com.joyintech.tams.common.base.entity.InterfCmsInstitution;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbName;

/**
 * cms机构信息评级信息entity
 * 
 * @author changwenbin
 * @version 1.0 2019年1月3日
 * @see InterfLc0010Entity
 * @since 1.0
 */
public class InterfLc0010Entity extends InterfCmsInstitution {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -3676487660707433008L;

    /**
     * 机构评级list
     */
    private List<InterfLc0010GradeLvEntity> gradeList;

    /**
     * 获取 机构评级list
     * 
     * @return 机构评级list
     */
    public List<InterfLc0010GradeLvEntity> getGradeList() {
        return gradeList;
    }

    /**
     * 设置 机构评级list
     * 
     * @param gradeList 机构评级list
     */
    @EsbFieldType(value = EsbFieldType.ARRAY)
    @EsbName(value = "GRADE_LIST")
    public void setGradeList(List<InterfLc0010GradeLvEntity> gradeList) {
        this.gradeList = gradeList;
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "CUSTOMER_ID")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setCustomerId(String customerId) {
        super.setCustomerId(customerId);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "INSTITUTION_NAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setInstitutionName(String institutionName) {
        super.setInstitutionName(institutionName);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "PARTY_TYPE3")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setPartyType3(String partyType3) {
        super.setPartyType3(partyType3);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "REP_INDUSTRY")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setRepIndustry(String repIndustry) {
        super.setRepIndustry(repIndustry);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "REP_INDUSTRY_LEVEL2")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setRepIndustryLevel2(String repIndustryLevel2) {
        super.setRepIndustryLevel2(repIndustryLevel2);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "PARTY_TYPE2")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setPartyType2(String partyType2) {
        super.setPartyType2(partyType2);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "PARTY_TYPE1")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setPartyType1(String partyType1) {
        super.setPartyType1(partyType1);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "IS_TOP_COMPANY")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setIsTopCompany(String isTopCompany) {
        super.setIsTopCompany(isTopCompany);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "INSTITUTION_CODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setInstitutionCode(String institutionCode) {
        super.setInstitutionCode(institutionCode);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "IS_GOV")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setIsGov(String isGov) {
        super.setIsGov(isGov);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "BECIF")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setBecif(String becif) {
        super.setBecif(becif);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "PROVINCE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setProvince(String province) {
        super.setProvince(province);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "CITY")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setCity(String city) {
        super.setCity(city);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "COUNTY")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setCounty(String county) {
        super.setCounty(county);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "HOLDING_TYPE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setHoldingType(String holdingType) {
        super.setHoldingType(holdingType);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "CUSTOMER_OBJ_TYPE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setCustomerObjType(String customerObjType) {
        super.setCustomerObjType(customerObjType);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "IS_LISTED_COMPANY")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setIsListedCompany(String isListedCompany) {
        super.setIsListedCompany(isListedCompany);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "IS_GROUP_CUSTOMER")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setIsGroupCustomer(String isGroupCustomer) {
        super.setIsGroupCustomer(isGroupCustomer);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "PARENT_CUSTOMER_ID")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setParentCustomerId(String parentCustomerId) {
        super.setParentCustomerId(parentCustomerId);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "PARENT_CUSTOMER_NAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setParentCustomerName(String parentCustomerName) {
        super.setParentCustomerName(parentCustomerName);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "IS_PRIVATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setIsPrivate(String isPrivate) {
        super.setIsPrivate(isPrivate);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "IS_ESCAPE_DEPT")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setIsEscapeDept(String isEscapeDept) {
        super.setIsEscapeDept(isEscapeDept);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "CREDIT_TYPE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setCreditType(String creditType) {
        super.setCreditType(creditType);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "IS_CREDIT_RELATED_TRD")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setIsCreditRelatedTrd(String isCreditRelatedTrd) {
        super.setIsCreditRelatedTrd(isCreditRelatedTrd);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "STRUCT_ADJUST_TYPE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setStructAdjustType(String structAdjustType) {
        super.setStructAdjustType(structAdjustType);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "INDUS_UPGRATE_TYPE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setIndusUpgrateType(String indusUpgrateType) {
        super.setIndusUpgrateType(indusUpgrateType);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "BUSI_SCOPE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setBusiScope(String busiScope) {
        super.setBusiScope(busiScope);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "MAIN_BUSI")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setMainBusi(String mainBusi) {
        super.setMainBusi(mainBusi);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "CUSTOMER_LEVEL")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setCustomerLevel(String customerLevel) {
        super.setCustomerLevel(customerLevel);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "REGIST_CAPITAL")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @Override
    public void setRegistCapital(BigDecimal registCapital) {
        super.setRegistCapital(registCapital);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "ACTUAL_CAPITAL")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @Override
    public void setActualCapital(BigDecimal actualCapital) {
        super.setActualCapital(actualCapital);
    }
}
