package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class BokDetailIdKey extends BaseEntity {
    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 账务明细代码
     */
    private String bokDetailId;

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
     * 取得 账务明细代码
     * @return 账务明细代码
     */
    public String getBokDetailId() {
        return bokDetailId;
    }

    /**
     * 设置 账务明细代码
     * @param bokDetailId 账务明细代码
     */
    public void setBokDetailId(String bokDetailId) {
        this.bokDetailId = bokDetailId == null ? null : bokDetailId.trim();
    }
}