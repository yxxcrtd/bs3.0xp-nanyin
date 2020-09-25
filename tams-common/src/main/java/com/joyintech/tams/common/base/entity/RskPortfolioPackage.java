package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RskPortfolioPackage extends BaseEntity {
    /**
     * 组合包代码
     */
    private String ptlBagId;

    /**
     * 组合包名称
     */
    private String ptlBagName;

    
    /**
     * 机构名称
     */
    private String institutionCode;
    /**
     * 取得 组合包代码
     * @return 组合包代码
     */
    public String getPtlBagId() {
        return ptlBagId;
    }

    /**
     * 设置 组合包代码
     * @param ptlBagId 组合包代码
     */
    public void setPtlBagId(String ptlBagId) {
        this.ptlBagId = ptlBagId == null ? null : ptlBagId.trim();
    }

    /**
     * 取得 组合包名称
     * @return 组合包名称
     */
    public String getPtlBagName() {
        return ptlBagName;
    }

    /**
     * 设置 组合包名称
     * @param ptlBagName 组合包名称
     */
    public void setPtlBagName(String ptlBagName) {
        this.ptlBagName = ptlBagName == null ? null : ptlBagName.trim();
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
    
    
}