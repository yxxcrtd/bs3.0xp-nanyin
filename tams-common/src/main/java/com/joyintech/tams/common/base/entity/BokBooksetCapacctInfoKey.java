package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class BokBooksetCapacctInfoKey extends BaseEntity {
    /**
     * 账户账号
     */
    private String acctNo;

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 取得 账户账号
     * @return 账户账号
     */
    public String getAcctNo() {
        return acctNo;
    }

    /**
     * 设置 账户账号
     * @param acctNo 账户账号
     */
    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo == null ? null : acctNo.trim();
    }

    /**
     * 取得 账套代码
     * @return 账套代码
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 账套代码
     * @param booksetId 账套代码
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId == null ? null : booksetId.trim();
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