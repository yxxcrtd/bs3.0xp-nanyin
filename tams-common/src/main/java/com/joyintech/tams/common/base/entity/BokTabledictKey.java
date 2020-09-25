package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class BokTabledictKey extends BaseEntity {
    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 会计元素代码
     */
    private String dictId;

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
     * 取得 会计元素代码
     * @return 会计元素代码
     */
    public String getDictId() {
        return dictId;
    }

    /**
     * 设置 会计元素代码
     * @param dictId 会计元素代码
     */
    public void setDictId(String dictId) {
        this.dictId = dictId == null ? null : dictId.trim();
    }
}