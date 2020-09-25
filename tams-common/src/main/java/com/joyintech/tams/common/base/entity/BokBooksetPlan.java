package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class BokBooksetPlan extends BaseEntity {
    /**
     * 账套估值方案代码
     */
    private String booksetSchemeId;

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 方案代码
     */
    private String schemeId;

    /**
     * 方案代码
     */
    private String schemeType;

    /**
     * 取得 账套估值方案代码
     * @return 账套估值方案代码
     */
    public String getBooksetSchemeId() {
        return booksetSchemeId;
    }

    /**
     * 设置 账套估值方案代码
     * @param booksetSchemeId 账套估值方案代码
     */
    public void setBooksetSchemeId(String booksetSchemeId) {
        this.booksetSchemeId = booksetSchemeId == null ? null : booksetSchemeId.trim();
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
     * 取得 方案代码
     * @return 方案代码
     */
    public String getSchemeId() {
        return schemeId;
    }

    /**
     * 设置 方案代码
     * @param schemeId 方案代码
     */
    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId == null ? null : schemeId.trim();
    }

    /**
     * 取得 方案代码
     * @return 方案代码
     */
    public String getSchemeType() {
        return schemeType;
    }

    /**
     * 设置 方案代码
     * @param schemeType 方案代码
     */
    public void setSchemeType(String schemeType) {
        this.schemeType = schemeType == null ? null : schemeType.trim();
    }
}