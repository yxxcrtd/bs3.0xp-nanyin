package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class InfTruCashDaily extends InfTruCashDailyKey {
    /**
     * 账户属性
     */
    private String acctAttr;

    /**
     * 余额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal acctBalance;

    /**
     * 取得 账户属性
     * @return 账户属性
     */
    public String getAcctAttr() {
        return acctAttr;
    }

    /**
     * 设置 账户属性
     * @param acctAttr 账户属性
     */
    public void setAcctAttr(String acctAttr) {
        this.acctAttr = acctAttr == null ? null : acctAttr.trim();
    }

    /**
     * 取得 余额
     * @return 余额
     */
    public BigDecimal getAcctBalance() {
        return acctBalance;
    }

    /**
     * 设置 余额
     * @param acctBalance 余额
     */
    public void setAcctBalance(BigDecimal acctBalance) {
        this.acctBalance = acctBalance;
    }
}