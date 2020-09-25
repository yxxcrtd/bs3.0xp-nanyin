package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class BokTablelistKey extends BaseEntity {
    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 业务场景代码
     */
    private String tableId;

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
     * 取得 业务场景代码
     * @return 业务场景代码
     */
    public String getTableId() {
        return tableId;
    }

    /**
     * 设置 业务场景代码
     * @param tableId 业务场景代码
     */
    public void setTableId(String tableId) {
        this.tableId = tableId == null ? null : tableId.trim();
    }
}