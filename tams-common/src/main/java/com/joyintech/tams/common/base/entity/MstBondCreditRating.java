package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class MstBondCreditRating extends MstBondCreditRatingKey {
    /**
     * 长短期
     */
    private String shortLongTerm;

    /**
     * 评级结果
     */
    private String gradeResult;

    /**
     * 录入方式
     */
    private String inputType;

    /**
     * null
     */
    private String isRecommand;

    /**
     * 评级发起类型
     */
    private String issuerRateType;

    /**
     * 公告日期
     */
    private Date noticeDate;

    /**
     * 评级展望
     */
    private String gradeExpectation;

    /**
     * 变动方向
     */
    private String directionChange;

    /**
     * 取得 长短期
     * @return 长短期
     */
    public String getShortLongTerm() {
        return shortLongTerm;
    }

    /**
     * 设置 长短期
     * @param shortLongTerm 长短期
     */
    public void setShortLongTerm(String shortLongTerm) {
        this.shortLongTerm = shortLongTerm == null ? null : shortLongTerm.trim();
    }

    /**
     * 取得 评级结果
     * @return 评级结果
     */
    public String getGradeResult() {
        return gradeResult;
    }

    /**
     * 设置 评级结果
     * @param gradeResult 评级结果
     */
    public void setGradeResult(String gradeResult) {
        this.gradeResult = gradeResult == null ? null : gradeResult.trim();
    }

    /**
     * 取得 录入方式
     * @return 录入方式
     */
    public String getInputType() {
        return inputType;
    }

    /**
     * 设置 录入方式
     * @param inputType 录入方式
     */
    public void setInputType(String inputType) {
        this.inputType = inputType == null ? null : inputType.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getIsRecommand() {
        return isRecommand;
    }

    /**
     * 设置 null
     * @param isRecommand null
     */
    public void setIsRecommand(String isRecommand) {
        this.isRecommand = isRecommand == null ? null : isRecommand.trim();
    }

    /**
     * 取得 评级发起类型
     * @return 评级发起类型
     */
    public String getIssuerRateType() {
        return issuerRateType;
    }

    /**
     * 设置 评级发起类型
     * @param issuerRateType 评级发起类型
     */
    public void setIssuerRateType(String issuerRateType) {
        this.issuerRateType = issuerRateType == null ? null : issuerRateType.trim();
    }

    /**
     * 取得 公告日期
     * @return 公告日期
     */
    public Date getNoticeDate() {
        return noticeDate;
    }

    /**
     * 设置 公告日期
     * @param noticeDate 公告日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setNoticeDate(Date noticeDate) {
        this.noticeDate = noticeDate;
    }

    /**
     * 取得 评级展望
     * @return 评级展望
     */
    public String getGradeExpectation() {
        return gradeExpectation;
    }

    /**
     * 设置 评级展望
     * @param gradeExpectation 评级展望
     */
    public void setGradeExpectation(String gradeExpectation) {
        this.gradeExpectation = gradeExpectation == null ? null : gradeExpectation.trim();
    }

    /**
     * 取得 变动方向
     * @return 变动方向
     */
    public String getDirectionChange() {
        return directionChange;
    }

    /**
     * 设置 变动方向
     * @param directionChange 变动方向
     */
    public void setDirectionChange(String directionChange) {
        this.directionChange = directionChange == null ? null : directionChange.trim();
    }
}