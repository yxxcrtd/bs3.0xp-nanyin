package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;
import java.util.List;

public class MstBondCreditRating extends MstBondCreditRatingKey {

    /**
     * 数据状态名称
     */
    private String statusName;
    /**
     * 数据状态
     */
    private String status;
    /**
     * 数据来源
     */
    private String svrDataSources;
    /**
     * 数据来源名称
     */
    private String svrDataSourcesName;
    /**
     * 导出类型
     */
    private String type;
    /**
     * 选择项
     */
    private List<MstBondCreditRating> selectedItem;
    private String oldGradeOrgId;
    private String oldGradeDateStr;
    private String oldOType;
    private String gradeDateStr;

    public String getGradeDateStr() {
        return gradeDateStr;
    }

    public void setGradeDateStr(String gradeDateStr) {
        this.gradeDateStr = gradeDateStr;
    }

    public String getOldGradeOrgId() {
        return oldGradeOrgId;
    }

    public void setOldGradeOrgId(String oldGradeOrgId) {
        this.oldGradeOrgId = oldGradeOrgId;
    }

    public String getOldOType() {
        return oldOType;
    }

    public String getOldGradeDateStr() {
        return oldGradeDateStr;
    }

    public void setOldGradeDateStr(String oldGradeDateStr) {
        this.oldGradeDateStr = oldGradeDateStr;
    }

    public void setOldOType(String oldOType) {
        this.oldOType = oldOType;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    public String getSvrDataSources() {
        return svrDataSources;
    }

    public void setSvrDataSources(String svrDataSources) {
        this.svrDataSources = svrDataSources;
    }

    public String getSvrDataSourcesName() {
        return svrDataSourcesName;
    }

    public void setSvrDataSourcesName(String svrDataSourcesName) {
        this.svrDataSourcesName = svrDataSourcesName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<MstBondCreditRating> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<MstBondCreditRating> selectedItem) {
        this.selectedItem = selectedItem;
    }

    /**
     * 长短期
     */
    private String shortLongTerm;

    /**
     * 评级结果
     */
    private String gradeResult;
    /**
     * 评级结果名称
     */
    private String gradeResultName;
    /**
     * 评级类型名称
     */
    private String oTypeName;
    /**
     * 评级机构名称
     */
    private String gradeOrgIdName;

    public String getGradeResultName() {
        return gradeResultName;
    }

    public void setGradeResultName(String gradeResultName) {
        this.gradeResultName = gradeResultName;
    }

    public String getoTypeName() {
        return oTypeName;
    }

    public void setoTypeName(String oTypeName) {
        this.oTypeName = oTypeName;
    }

    public String getGradeOrgIdName() {
        return gradeOrgIdName;
    }

    public void setGradeOrgIdName(String gradeOrgIdName) {
        this.gradeOrgIdName = gradeOrgIdName;
    }

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