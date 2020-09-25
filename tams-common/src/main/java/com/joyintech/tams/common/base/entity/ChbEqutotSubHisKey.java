package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbEqutotSubHisKey extends BaseEntity {
    /**
     * 履历番号
     */
    private Long hisNo;

    /**
     * 产品登记编码
     */
    private String regCode;

    /**
     * 登记银行代码
     */
    private String registBankCode;

    /**
     * 取得 履历番号
     * @return 履历番号
     */
    public Long getHisNo() {
        return hisNo;
    }

    /**
     * 设置 履历番号
     * @param hisNo 履历番号
     */
    public void setHisNo(Long hisNo) {
        this.hisNo = hisNo;
    }

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
}