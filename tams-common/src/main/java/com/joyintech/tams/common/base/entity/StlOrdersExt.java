package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class StlOrdersExt extends BaseEntity {
    /**
     * 指令编号
     */
    private String orderId;

    /**
     * 是否随交易出账
     */
    private String isTrade;

    /**
     * 取得 指令编号
     * @return 指令编号
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 设置 指令编号
     * @param orderId 指令编号
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    /**
     * 取得 是否随交易出账
     * @return 是否随交易出账
     */
    public String getIsTrade() {
        return isTrade;
    }

    /**
     * 设置 是否随交易出账
     * @param isTrade 是否随交易出账
     */
    public void setIsTrade(String isTrade) {
        this.isTrade = isTrade == null ? null : isTrade.trim();
    }
}