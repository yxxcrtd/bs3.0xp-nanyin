package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbEqutotDetSubKey extends BaseEntity {
    /**
     * 产品登记编码
     */
    private String regCode;

    /**
     * 登记银行代码
     */
    private String registBankCode;

    /**
     * 业务种类
     */
    private String busiType;

    /**
     * 报送状态
     */
    private String submitStatus;

    /**
     * 取得 产品登记编码
     * @return 产品登记编码
     */
    public String getRegCode() {
        return regCode;
    }

    /**
     * 设置 产品登记编码
     * @param regCode 产品登记编码
     */
    public void setRegCode(String regCode) {
        this.regCode = regCode == null ? null : regCode.trim();
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
     * 取得 业务种类
     * @return 业务种类
     */
    public String getBusiType() {
        return busiType;
    }

    /**
     * 设置 业务种类
     * @param busiType 业务种类
     */
    public void setBusiType(String busiType) {
        this.busiType = busiType == null ? null : busiType.trim();
    }

    /**
     * 取得 报送状态
     * @return 报送状态
     */
    public String getSubmitStatus() {
        return submitStatus;
    }

    /**
     * 设置 报送状态
     * @param submitStatus 报送状态
     */
    public void setSubmitStatus(String submitStatus) {
        this.submitStatus = submitStatus == null ? null : submitStatus.trim();
    }
}