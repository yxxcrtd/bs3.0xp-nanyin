package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbEquSubKey extends BaseEntity {
    /**
     * 识别标识
     */
    private String indentifyMark;

    /**
     * 登记银行代码
     */
    private String registBankCode;

    /**
     * 报送状态
     */
    private String submitStatus;

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