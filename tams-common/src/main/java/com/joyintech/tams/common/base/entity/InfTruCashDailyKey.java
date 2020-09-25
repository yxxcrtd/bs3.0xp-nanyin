package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class InfTruCashDailyKey extends BaseEntity {
    /**
     * 帐号
     */
    private String account;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 日期
     */
    private Date cdate;

    /**
     * 取得 帐号
     * @return 帐号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置 帐号
     * @param account 帐号
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
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
     * 取得 日期
     * @return 日期
     */
    public Date getCdate() {
        return cdate;
    }

    /**
     * 设置 日期
     * @param cdate 日期
     */
    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }
}