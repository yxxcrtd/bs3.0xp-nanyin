package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbEquSubHisKey extends BaseEntity {
    /**
     * 履历番号
     */
    private Long hisNo;

    /**
     * 识别标识
     */
    private String indentifyMark;

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
     * 取得 识别标识
     * @return 识别标识
     */
    public String getIndentifyMark() {
        return indentifyMark;
    }

    /**
     * 设置 识别标识
     * @param indentifyMark 识别标识
     */
    public void setIndentifyMark(String indentifyMark) {
        this.indentifyMark = indentifyMark == null ? null : indentifyMark.trim();
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