package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class BokBalanceKey extends BaseEntity {
    /**
     * 产品主代码
     */
    private String booksetId;

    /**
     * 日期
     */
    private Date balanceDate;

    /**
     * 科目号
     */
    private String subjectNo;

    /**
     * 取得 产品主代码
     * @return 产品主代码
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 产品主代码
     * @param booksetId 产品主代码
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId == null ? null : booksetId.trim();
    }

    /**
     * 取得 日期
     * @return 日期
     */
    public Date getBalanceDate() {
        return balanceDate;
    }

    /**
     * 设置 日期
     * @param balanceDate 日期
     */
    public void setBalanceDate(Date balanceDate) {
        this.balanceDate = balanceDate;
    }

    /**
     * 取得 科目号
     * @return 科目号
     */
    public String getSubjectNo() {
        return subjectNo;
    }

    /**
     * 设置 科目号
     * @param subjectNo 科目号
     */
    public void setSubjectNo(String subjectNo) {
        this.subjectNo = subjectNo == null ? null : subjectNo.trim();
    }
}