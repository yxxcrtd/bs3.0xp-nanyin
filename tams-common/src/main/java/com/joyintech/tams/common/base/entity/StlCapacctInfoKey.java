package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class StlCapacctInfoKey extends BaseEntity {
    /**
     * 网点账号，网点号+账号
     */
    private String acctNo;

    /**
     * 币种
     */
    private String ccy;

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
}