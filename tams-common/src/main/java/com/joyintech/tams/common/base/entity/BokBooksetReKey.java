package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class BokBooksetReKey extends BaseEntity {
    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 子账套代码
     */
    private String booksetIdSon;

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
     * 取得 子账套代码
     * @return 子账套代码
     */
    public String getBooksetIdSon() {
        return booksetIdSon;
    }

    /**
     * 设置 子账套代码
     * @param booksetIdSon 子账套代码
     */
    public void setBooksetIdSon(String booksetIdSon) {
        this.booksetIdSon = booksetIdSon == null ? null : booksetIdSon.trim();
    }
}