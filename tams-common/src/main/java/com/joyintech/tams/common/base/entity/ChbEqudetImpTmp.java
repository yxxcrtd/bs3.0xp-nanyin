package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbEqudetImpTmp extends BaseEntity {
    /**
     * 业务流水号
     */
    private String busiSeqno;

    /**
     * 识别标识
     */
    private String indentifyMark;

    /**
     * 登记银行代码
     */
    private String registBankCode;

    /**
     * 产品代码
     */
    private String account;

    /**
     * 机构代码
     */
    private String orgCode;

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
     * 取得 产品代码
     * @return 产品代码
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置 产品代码
     * @param account 产品代码
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
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
}