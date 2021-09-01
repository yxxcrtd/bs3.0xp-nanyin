package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.joyintech.framework.base.BaseEntity;

import java.util.Date;

public class ValMstBondCreditRating extends BaseEntity {

    private static final long serialVersionUID = 1549554746214269137L;

    /**
     * 债券代码（市场代码）
     */
    private String oCode;

    /**
     * 评级机构
     */
    private String gradeOrgId;

    /**
     * 评级日期
     */
    private Date gradeDate;

    /**
     * 评级类型，主体评级、债项评级
     */
    private String oType;

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
     * 新增、修改、删除
     */
    private String dataActionSign;

    public String getDataActionSign() {
        return dataActionSign;
    }

    public void setDataActionSign(String dataActionSign) {
        this.dataActionSign = dataActionSign;
    }

    public String getoCode() {
        return oCode;
    }

    public void setoCode(String oCode) {
        this.oCode = oCode;
    }

    public String getGradeOrgId() {
        return gradeOrgId;
    }

    public void setGradeOrgId(String gradeOrgId) {
        this.gradeOrgId = gradeOrgId;
    }

    public Date getGradeDate() {
        return gradeDate;
    }

    public void setGradeDate(Date gradeDate) {
        this.gradeDate = gradeDate;
    }

    public String getoType() {
        return oType;
    }

    public void setoType(String oType) {
        this.oType = oType;
    }

    public String getShortLongTerm() {
        return shortLongTerm;
    }

    public void setShortLongTerm(String shortLongTerm) {
        this.shortLongTerm = shortLongTerm;
    }

    public String getGradeResult() {
        return gradeResult;
    }

    public void setGradeResult(String gradeResult) {
        this.gradeResult = gradeResult;
    }

    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    public String getIsRecommand() {
        return isRecommand;
    }

    public void setIsRecommand(String isRecommand) {
        this.isRecommand = isRecommand;
    }

    public String getIssuerRateType() {
        return issuerRateType;
    }

    public void setIssuerRateType(String issuerRateType) {
        this.issuerRateType = issuerRateType;
    }

    public Date getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(Date noticeDate) {
        this.noticeDate = noticeDate;
    }

    public String getGradeExpectation() {
        return gradeExpectation;
    }

    public void setGradeExpectation(String gradeExpectation) {
        this.gradeExpectation = gradeExpectation;
    }

    public String getDirectionChange() {
        return directionChange;
    }

    public void setDirectionChange(String directionChange) {
        this.directionChange = directionChange;
    }
}
