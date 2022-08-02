package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RepInvestAccount extends BaseEntity {
    private String prodId;
    /**
     * 产品主代码
     */
    private String prodName;
    /**
     * 账户类型：托管账户
     */
    private String accountType;
    /**
     * 账号
     */
    private String accountNum;
    /**
     * 账户名称
     */
    private String accountName;
    /**
     * 开户单位
     */
    private String accountOpener;
    /**
     * 数据状态
     */
    private String status;
    /**
     * 错误信息
     */
    private String errormsg;

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountOpener() {
        return accountOpener;
    }

    public void setAccountOpener(String accountOpener) {
        this.accountOpener = accountOpener;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrormsg() {
        return errormsg;
    }

    public void setErrormsg(String errorMsg) {
        this.errormsg = errorMsg;
    }
}
