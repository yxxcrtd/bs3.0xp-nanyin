package com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valbasicsubadjust.dto;

import com.joyintech.framework.base.BaseEntity;

import java.util.List;

public class ValBasicSubAdjust001Dto extends BaseEntity {
    // 账套代码
    private List<String> booksetIds;
    // 科目号
    private String subjectNo;
    // 基础科目性质
    private String baseSubjectNature;

    public List<String> getBooksetIds() {
        return booksetIds;
    }

    public void setBooksetIds(List<String> booksetIds) {
        this.booksetIds = booksetIds;
    }

    public String getSubjectNo() {
        return subjectNo;
    }

    public void setSubjectNo(String subjectNo) {
        this.subjectNo = subjectNo;
    }

    public String getBaseSubjectNature() {
        return baseSubjectNature;
    }

    public void setBaseSubjectNature(String baseSubjectNature) {
        this.baseSubjectNature = baseSubjectNature;
    }
}
