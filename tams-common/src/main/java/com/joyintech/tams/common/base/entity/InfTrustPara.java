package com.joyintech.tams.common.base.entity;

public class InfTrustPara extends InfTrustParaKey {
    /**
     * 公司代码，托管给管理人的ID
     */
    private String truDeptCode;

    /**
     * 证书代码，托管给管理人的ID
     */
    private String truCertId;

    /**
     * 取得 公司代码，托管给管理人的ID
     * @return 公司代码，托管给管理人的ID
     */
    public String getTruDeptCode() {
        return truDeptCode;
    }

    /**
     * 设置 公司代码，托管给管理人的ID
     * @param truDeptCode 公司代码，托管给管理人的ID
     */
    public void setTruDeptCode(String truDeptCode) {
        this.truDeptCode = truDeptCode == null ? null : truDeptCode.trim();
    }

    /**
     * 取得 证书代码，托管给管理人的ID
     * @return 证书代码，托管给管理人的ID
     */
    public String getTruCertId() {
        return truCertId;
    }

    /**
     * 设置 证书代码，托管给管理人的ID
     * @param truCertId 证书代码，托管给管理人的ID
     */
    public void setTruCertId(String truCertId) {
        this.truCertId = truCertId == null ? null : truCertId.trim();
    }
}