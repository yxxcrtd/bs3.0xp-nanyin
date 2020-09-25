package com.joyintech.tams.common.base.entity;

public class StlFinProductAcct extends StlFinProductAcctKey {
    /**
     * 金融产品类型（估值核算），债券、理财产品、基金、理财产品模板、理财产品分层
     */
    private String finprodType;

    /**
     * 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     */
    private String finprodType2;

    /**
     * 账户类型
     */
    private String acctType;

    /**
     * 备注
     */
    private String remark;

    /**
     * 现金流代码，对应费用在现金流表里的代码
     */
    private String cashId;

    /**
     * 取得 金融产品类型（估值核算），债券、理财产品、基金、理财产品模板、理财产品分层
     * @return 金融产品类型（估值核算），债券、理财产品、基金、理财产品模板、理财产品分层
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 设置 金融产品类型（估值核算），债券、理财产品、基金、理财产品模板、理财产品分层
     * @param finprodType 金融产品类型（估值核算），债券、理财产品、基金、理财产品模板、理财产品分层
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType == null ? null : finprodType.trim();
    }

    /**
     * 取得 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     * @return 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     */
    public String getFinprodType2() {
        return finprodType2;
    }

    /**
     * 设置 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     * @param finprodType2 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     */
    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2 == null ? null : finprodType2.trim();
    }

    /**
     * 取得 账户类型
     * @return 账户类型
     */
    public String getAcctType() {
        return acctType;
    }

    /**
     * 设置 账户类型
     * @param acctType 账户类型
     */
    public void setAcctType(String acctType) {
        this.acctType = acctType == null ? null : acctType.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 取得 现金流代码，对应费用在现金流表里的代码
     * @return 现金流代码，对应费用在现金流表里的代码
     */
    public String getCashId() {
        return cashId;
    }

    /**
     * 设置 现金流代码，对应费用在现金流表里的代码
     * @param cashId 现金流代码，对应费用在现金流表里的代码
     */
    public void setCashId(String cashId) {
        this.cashId = cashId == null ? null : cashId.trim();
    }
}