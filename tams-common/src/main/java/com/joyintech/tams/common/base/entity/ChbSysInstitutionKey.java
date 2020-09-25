package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbSysInstitutionKey extends BaseEntity {
    /**
     * 机构代码
     */
    private String institutionCode;

    /**
     * 取得 机构代码
     * @return 机构代码
     */
    public String getInstitutionCode() {
        return institutionCode;
    }

    /**
     * 设置 机构代码
     * @param institutionCode 机构代码
     */
    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode == null ? null : institutionCode.trim();
    }
}