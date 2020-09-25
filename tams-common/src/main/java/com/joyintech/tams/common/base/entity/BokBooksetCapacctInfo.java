package com.joyintech.tams.common.base.entity;

public class BokBooksetCapacctInfo extends BokBooksetCapacctInfoKey {
    /**
     * 资金账户类型
     */
    private String acctType;

    /**
     * 取得 资金账户类型
     * @return 资金账户类型
     */
    public String getAcctType() {
        return acctType;
    }

    /**
     * 设置 资金账户类型
     * @param acctType 资金账户类型
     */
    public void setAcctType(String acctType) {
        this.acctType = acctType == null ? null : acctType.trim();
    }
}