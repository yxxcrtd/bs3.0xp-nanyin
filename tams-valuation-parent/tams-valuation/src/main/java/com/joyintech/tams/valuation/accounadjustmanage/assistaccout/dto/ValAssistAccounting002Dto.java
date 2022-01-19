package com.joyintech.tams.valuation.accounadjustmanage.assistaccout.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.List;

public class ValAssistAccounting002Dto extends BaseEntity implements ProcessEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 账套代码
     */
    private String booksetId;
    /**
     * 账套名称
     */
    private String booksetName;
    /**
     * 内部科目编码
     */
    private String subjectInternalCode;
    /**
     * 内部科目名称
     */
    private String subjectInternalName;


    /**
     * 辅助核算项集合
     */

    List<ValAssistHandDto> hands;


    public String getBooksetId() {
        return booksetId;
    }

    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    public String getBooksetName() {
        return booksetName;
    }

    public void setBooksetName(String booksetName) {
        this.booksetName = booksetName;
    }

    public String getSubjectInternalCode() {
        return subjectInternalCode;
    }

    public void setSubjectInternalCode(String subjectInternalCode) {
        this.subjectInternalCode = subjectInternalCode;
    }

    public String getSubjectInternalName() {
        return subjectInternalName;
    }

    public void setSubjectInternalName(String subjectInternalName) {
        this.subjectInternalName = subjectInternalName;
    }

    public void setHands(List<ValAssistHandDto> hands) {
        this.hands = hands;
    }

    public List<ValAssistHandDto> getHands() {
        return hands;
    }
}
