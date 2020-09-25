package com.joyintech.tams.common.base.entity;

public class ChbUserWindow extends ChbUserWindowKey {
    /**
     * 排列顺序
     */
    private Short orderNo;

    /**
     * 取得 排列顺序
     * @return 排列顺序
     */
    public Short getOrderNo() {
        return orderNo;
    }

    /**
     * 设置 排列顺序
     * @param orderNo 排列顺序
     */
    public void setOrderNo(Short orderNo) {
        this.orderNo = orderNo;
    }
}