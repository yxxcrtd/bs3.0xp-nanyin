package com.joyintech.tams.svreport.chb.identityrepair.dto;

import com.joyintech.framework.base.BaseEntity;

public class ChbIdentityRepair extends BaseEntity {
    //身份所在表标识
    private String equMark;
    //识别标识
    private String indentifyMark;
    //账号开户所在地
    private String fundAccPlace;
    //合并前识别标识
    private String orgIndentifyMark;
    //机构代码
    private String orgCode;
    //登记银行代码
    private String registBankCode;
    //代销机构代码
    private String agencyCode;
    //备注
    private String remark;

    public String getEquMark() {
        return equMark;
    }

    public void setEquMark(String equMark) {
        this.equMark = equMark;
    }

    public String getIndentifyMark() {
        return indentifyMark;
    }

    public void setIndentifyMark(String indentifyMark) {
        this.indentifyMark = indentifyMark;
    }

    public String getFundAccPlace() {
        return fundAccPlace;
    }

    public void setFundAccPlace(String fundAccPlace) {
        this.fundAccPlace = fundAccPlace;
    }

    public String getOrgIndentifyMark() {
        return orgIndentifyMark;
    }

    public void setOrgIndentifyMark(String orgIndentifyMark) {
        this.orgIndentifyMark = orgIndentifyMark;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRegistBankCode() {
        return registBankCode;
    }

    public void setRegistBankCode(String registBankCode) {
        this.registBankCode = registBankCode;
    }

    public String getAgencyCode() {
        return agencyCode;
    }

    public void setAgencyCode(String agencyCode) {
        this.agencyCode = agencyCode;
    }
}