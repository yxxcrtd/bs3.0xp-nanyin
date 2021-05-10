package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class SppiTestQaKey extends BaseEntity {
    /**
     * SPPI测试ID
     */
    private String sppiTestId;

    /**
     * 金融产品代码（标的代码）
     */
    private String finprodId;

    /**
     * 问题代码，对应模板界面HTML上的FORM项的name
     */
    private String questionNo;

    /**
     * 取得 SPPI测试ID
     * @return SPPI测试ID
     */
    public String getSppiTestId() {
        return sppiTestId;
    }

    /**
     * 设置 SPPI测试ID
     * @param sppiTestId SPPI测试ID
     */
    public void setSppiTestId(String sppiTestId) {
        this.sppiTestId = sppiTestId == null ? null : sppiTestId.trim();
    }

    /**
     * 取得 金融产品代码（标的代码）
     * @return 金融产品代码（标的代码）
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码（标的代码）
     * @param finprodId 金融产品代码（标的代码）
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 问题代码，对应模板界面HTML上的FORM项的name
     * @return 问题代码，对应模板界面HTML上的FORM项的name
     */
    public String getQuestionNo() {
        return questionNo;
    }

    /**
     * 设置 问题代码，对应模板界面HTML上的FORM项的name
     * @param questionNo 问题代码，对应模板界面HTML上的FORM项的name
     */
    public void setQuestionNo(String questionNo) {
        this.questionNo = questionNo == null ? null : questionNo.trim();
    }
}