package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbSysInstitutionHisKey extends BaseEntity {
    /**
     * ����
     */
    private String institutionCode;

    /**
     * ����
     */
    private Integer hisNo;

    /**
     * 取得 ����
     * @return ����
     */
    public String getInstitutionCode() {
        return institutionCode;
    }

    /**
     * 设置 ����
     * @param institutionCode ����
     */
    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode == null ? null : institutionCode.trim();
    }

    /**
     * 取得 ����
     * @return ����
     */
    public Integer getHisNo() {
        return hisNo;
    }

    /**
     * 设置 ����
     * @param hisNo ����
     */
    public void setHisNo(Integer hisNo) {
        this.hisNo = hisNo;
    }
}