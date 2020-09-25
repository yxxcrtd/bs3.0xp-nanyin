package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class BokBussDetsubKey extends BaseEntity {
    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 四级科目号
     */
    private String detailSubjectNo;

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
     * 取得 四级科目号
     * @return 四级科目号
     */
    public String getDetailSubjectNo() {
        return detailSubjectNo;
    }

    /**
     * 设置 四级科目号
     * @param detailSubjectNo 四级科目号
     */
    public void setDetailSubjectNo(String detailSubjectNo) {
        this.detailSubjectNo = detailSubjectNo == null ? null : detailSubjectNo.trim();
    }
}