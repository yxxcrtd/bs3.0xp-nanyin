package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class RskIsupFixRecodeKey extends BaseEntity {
    /**
     * 参数代码
     */
    private String paraCode;

    /**
     * 有效日期
     */
    private Date validDate;

    /**
     * null
     */
    private String institutionCode;

    /**
     * 取得 参数代码
     * @return 参数代码
     */
    public String getParaCode() {
        return paraCode;
    }

    /**
     * 设置 参数代码
     * @param paraCode 参数代码
     */
    public void setParaCode(String paraCode) {
        this.paraCode = paraCode == null ? null : paraCode.trim();
    }

    /**
     * 取得 有效日期
     * @return 有效日期
     */
    public Date getValidDate() {
        return validDate;
    }

    /**
     * 设置 有效日期
     * @param validDate 有效日期
     */
    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    /**
     * 取得 null
     * @return null
     */
    public String getInstitutionCode() {
        return institutionCode;
    }

    /**
     * 设置 null
     * @param institutionCode null
     */
    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode == null ? null : institutionCode.trim();
    }
}