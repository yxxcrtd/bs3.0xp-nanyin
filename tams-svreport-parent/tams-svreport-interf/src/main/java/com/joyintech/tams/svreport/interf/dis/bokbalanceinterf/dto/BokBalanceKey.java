package com.joyintech.tams.svreport.interf.dis.bokbalanceinterf.dto;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class BokBalanceKey extends BaseEntity {
    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 余额日期
     */
    private Date balanceDate;

    /**
     * 科目号
     */
    private String subjectNo;

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
     * 取得 余额日期
     * @return 余额日期
     */
    public Date getBalanceDate() {
        return balanceDate;
    }

    /**
     * 设置 余额日期
     * @param balanceDate 余额日期
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