package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class BokBooksetExtKey extends BaseEntity {
    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 参数项
     */
    private String paramCode;

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
     * 取得 参数项
     * @return 参数项
     */
    public String getParamCode() {
        return paramCode;
    }

    /**
     * 设置 参数项
     * @param paramCode 参数项
     */
    public void setParamCode(String paramCode) {
        this.paramCode = paramCode == null ? null : paramCode.trim();
    }
}