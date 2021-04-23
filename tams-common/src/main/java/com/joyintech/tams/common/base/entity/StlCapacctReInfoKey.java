package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class StlCapacctReInfoKey extends BaseEntity {
    /**
     * 网点账号，网点号+账号
     */
    private String acctNo;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 账户类型
     */
    private String acctType;

    /**
     * 取得 网点账号，网点号+账号
     * @return 网点账号，网点号+账号
     */
    public String getAcctNo() {
        return acctNo;
    }

    /**
     * 设置 网点账号，网点号+账号
     * @param acctNo 网点账号，网点号+账号
     */
    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo == null ? null : acctNo.trim();
    }

    /**
     * 取得 币种
     * @return 币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
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
}