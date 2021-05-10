package com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valsubdevelopment.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;

import java.util.List;

@PageId("ValSubDevelopment001")
public class ValSubDevelopment001Dto extends BaseEntity {
    // 账套模板
    private List<String> booksetIds;
    // 待拓展科目号
    private List<String> thirdSubjectId;

    public List<String> getBooksetIds() {
        return booksetIds;
    }

    public List<String> getThirdSubjectId() {
        return thirdSubjectId;
    }

    public void setThirdSubjectId(List<String> thirdSubjectId) {
        this.thirdSubjectId = thirdSubjectId;
    }

    public void setBooksetIds(List<String> booksetIds) {
        this.booksetIds = booksetIds;
    }

}
