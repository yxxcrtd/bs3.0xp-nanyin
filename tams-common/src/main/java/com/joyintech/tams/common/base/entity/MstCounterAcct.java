package com.joyintech.tams.common.base.entity;

public class MstCounterAcct extends MstCounterAcctKey {
    /**
     * 账户类型
     */
    private String acctType;

    /**
     * 是否启用
     */
    private String isOpen;

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

    /**
     * 取得 是否启用
     * @return 是否启用
     */
    public String getIsOpen() {
        return isOpen;
    }

    /**
     * 设置 是否启用
     * @param isOpen 是否启用
     */
    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen == null ? null : isOpen.trim();
    }
}