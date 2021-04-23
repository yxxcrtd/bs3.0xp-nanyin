package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class ValTableInsKey extends BaseEntity {
    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 元素项
     */
    private String itemCode;

    /**
     * 账务日期
     */
    private Date cdate;

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
     * 取得 元素项
     * @return 元素项
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * 设置 元素项
     * @param itemCode 元素项
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode == null ? null : itemCode.trim();
    }

    /**
     * 取得 账务日期
     * @return 账务日期
     */
    public Date getCdate() {
        return cdate;
    }

    /**
     * 设置 账务日期
     * @param cdate 账务日期
     */
    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }
}