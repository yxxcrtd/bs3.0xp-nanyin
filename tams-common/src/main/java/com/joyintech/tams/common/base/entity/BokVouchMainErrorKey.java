package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class BokVouchMainErrorKey extends BaseEntity {
    /**
     * null
     */
    private String vouchNum;

    /**
     * null
     */
    private String booksetId;

    /**
     * null
     */
    private Date bookDate;

    /**
     * 取得 null
     * @return null
     */
    public String getVouchNum() {
        return vouchNum;
    }

    /**
     * 设置 null
     * @param vouchNum null
     */
    public void setVouchNum(String vouchNum) {
        this.vouchNum = vouchNum == null ? null : vouchNum.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 null
     * @param booksetId null
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId == null ? null : booksetId.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public Date getBookDate() {
        return bookDate;
    }

    /**
     * 设置 null
     * @param bookDate null
     */
    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }
}