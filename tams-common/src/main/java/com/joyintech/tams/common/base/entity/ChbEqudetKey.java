package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbEqudetKey extends BaseEntity {
    /**
     * 业务流水号
     */
    private String busiSeqno;

    /**
     * 登记银行代码
     */
    private String registBankCode;

    /**
     * 识别标识
     */
    private String indentifyMark;

    /**
     * 取得 业务流水号
     * @return 业务流水号
     */
    public String getBusiSeqno() {
        return busiSeqno;
    }

    /**
     * 设置 业务流水号
     * @param busiSeqno 业务流水号
     */
    public void setBusiSeqno(String busiSeqno) {
        this.busiSeqno = busiSeqno == null ? null : busiSeqno.trim();
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
}