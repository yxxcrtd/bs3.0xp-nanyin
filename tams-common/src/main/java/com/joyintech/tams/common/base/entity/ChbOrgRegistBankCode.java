package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbOrgRegistBankCode extends BaseEntity {
    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 登记银行代码
     */
    private String registBankCode;

    /**
     * 总行机构代码(人行报表)
     */
    private String pbcBankCode;

    /**
     * 是否理财子公司;1 是,2 否
     */
    private String isFinSubCompany;

    /**
     * 母行登记银行代码
     */
    private String fregistBankCode;

    /**
     * 社会信用代码
     */
    private String insitituteCode;



    public String getInsitituteCode() {
        return insitituteCode;
    }

    public void setInsitituteCode(String insitituteCode) {
        this.insitituteCode = insitituteCode;
    }

    /**
     * 取得 机构代码
     * @return 机构代码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 设置 机构代码
     * @param orgCode 机构代码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    /**
     * 取得 登记银行代码
     * @return 登记银行代码
     */
    public String getRegistBankCode() {
        return registBankCode;
    }

    /**
     * 设置 登记银行代码
     * @param registBankCode 登记银行代码
     */
    public void setRegistBankCode(String registBankCode) {
        this.registBankCode = registBankCode == null ? null : registBankCode.trim();
    }

    /**
     * 取得 总行机构代码(人行报表)
     * @return 总行机构代码(人行报表)
     */
    public String getPbcBankCode() {
        return pbcBankCode;
    }

    /**
     * 设置 总行机构代码(人行报表)
     * @param pbcBankCode 总行机构代码(人行报表)
     */
    public void setPbcBankCode(String pbcBankCode) {
        this.pbcBankCode = pbcBankCode == null ? null : pbcBankCode.trim();
    }

    /**
     * 取得 是否理财子公司;1 是,2 否
     * @return 是否理财子公司;1 是,2 否
     */
    public String getIsFinSubCompany() {
        return isFinSubCompany;
    }

    /**
     * 设置 是否理财子公司;1 是,2 否
     * @param isFinSubCompany 是否理财子公司;1 是,2 否
     */
    public void setIsFinSubCompany(String isFinSubCompany) {
        this.isFinSubCompany = isFinSubCompany == null ? null : isFinSubCompany.trim();
    }

    /**
     * 取得 母行登记银行代码
     * @return 母行登记银行代码
     */
    public String getFregistBankCode() {
        return fregistBankCode;
    }

    /**
     * 设置 母行登记银行代码
     * @param fregistBankCode 母行登记银行代码
     */
    public void setFregistBankCode(String fregistBankCode) {
        this.fregistBankCode = fregistBankCode == null ? null : fregistBankCode.trim();
    }
}
