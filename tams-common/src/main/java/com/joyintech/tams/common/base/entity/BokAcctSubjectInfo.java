package com.joyintech.tams.common.base.entity;

public class BokAcctSubjectInfo extends BokAcctSubjectInfoKey {
    /**
     * 核算主体名称
     */
    private String acctSubjectName;

    /**
     * 核算主体全称
     */
    private String acctSubjectFullname;

    /**
     * 取得 核算主体名称
     * @return 核算主体名称
     */
    public String getAcctSubjectName() {
        return acctSubjectName;
    }

    /**
     * 设置 核算主体名称
     * @param acctSubjectName 核算主体名称
     */
    public void setAcctSubjectName(String acctSubjectName) {
        this.acctSubjectName = acctSubjectName == null ? null : acctSubjectName.trim();
    }

    /**
     * 取得 核算主体全称
     * @return 核算主体全称
     */
    public String getAcctSubjectFullname() {
        return acctSubjectFullname;
    }

    /**
     * 设置 核算主体全称
     * @param acctSubjectFullname 核算主体全称
     */
    public void setAcctSubjectFullname(String acctSubjectFullname) {
        this.acctSubjectFullname = acctSubjectFullname == null ? null : acctSubjectFullname.trim();
    }
}