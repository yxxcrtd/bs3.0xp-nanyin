package com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valsubdevelopment.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;

import java.util.List;

public class ValSubDevelopment001Dto extends BaseEntity {
    // 账套模板
    private List<String> booksetIds;
    // 待拓展科目号
    private List<String> thirdSubjectId;

    public List<String> getBooksetIds() {
        return booksetIds;
    }

    public void setBooksetIds(List<String> booksetIds) {
        this.booksetIds = booksetIds;
    }

    public List<String> getThirdSubjectId() {
        return thirdSubjectId;
    }

    public void setThirdSubjectId(List<String> thirdSubjectId) {
        this.thirdSubjectId = thirdSubjectId;
    }
}
