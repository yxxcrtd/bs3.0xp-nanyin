package com.joyintech.tams.common.base.entity;

public class SppiTestQa extends SppiTestQaKey {
    /**
     * 值类型，01：下拉框，02：文本框，03：radioBox
     */
    private String answerType;

    /**
     * 问题值，若为下拉框值，则为编码值；若为文本框值，则为汉字内容
     */
    private String answerValue;

    /**
     * 备注，可以填问题描述
     */
    private String remark;

    /**
     * 取得 值类型，01：下拉框，02：文本框，03：radioBox
     * @return 值类型，01：下拉框，02：文本框，03：radioBox
     */
    public String getAnswerType() {
        return answerType;
    }

    /**
     * 设置 值类型，01：下拉框，02：文本框，03：radioBox
     * @param answerType 值类型，01：下拉框，02：文本框，03：radioBox
     */
    public void setAnswerType(String answerType) {
        this.answerType = answerType == null ? null : answerType.trim();
    }

    /**
     * 取得 问题值，若为下拉框值，则为编码值；若为文本框值，则为汉字内容
     * @return 问题值，若为下拉框值，则为编码值；若为文本框值，则为汉字内容
     */
    public String getAnswerValue() {
        return answerValue;
    }

    /**
     * 设置 问题值，若为下拉框值，则为编码值；若为文本框值，则为汉字内容
     * @param answerValue 问题值，若为下拉框值，则为编码值；若为文本框值，则为汉字内容
     */
    public void setAnswerValue(String answerValue) {
        this.answerValue = answerValue == null ? null : answerValue.trim();
    }

    /**
     * 取得 备注，可以填问题描述
     * @return 备注，可以填问题描述
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注，可以填问题描述
     * @param remark 备注，可以填问题描述
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}