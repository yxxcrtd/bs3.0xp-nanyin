package com.joyintech.tams.svreport.interf.dis.boksubject.dto;

import com.joyintech.framework.base.BaseEntity;

public class BokSubjectKey extends BaseEntity {
    /**
     * ????
     */
    private String booksetId;

    /**
     * ???
     */
    private String subjectNo;

    /**
     * 取得 ????
     * @return ????
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 ????
     * @param booksetId ????
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId == null ? null : booksetId.trim();
    }

    /**
     * 取得 ???
     * @return ???
     */
    public String getSubjectNo() {
        return subjectNo;
    }

    /**
     * 设置 ???
     * @param subjectNo ???
     */
    public void setSubjectNo(String subjectNo) {
        this.subjectNo = subjectNo == null ? null : subjectNo.trim();
    }
}