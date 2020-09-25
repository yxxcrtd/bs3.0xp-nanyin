/*
 * 文件名：FlowRiskStressTest001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2019年8月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.stresstestmanage.flowrisksstresstest.dto;

import java.util.Date;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.invest.risk.stresstestmanage.flowrisksstresstest.validator.FlowRiskStressTest001Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangkang
 * @version 1.0 2019年8月13日
 * @see FlowRiskStressTest001Dto
 * @since 1.0
 */
@PageId("FlowRiskStressTest001")
@ValidLogic(targetClass = FlowRiskStressTest001Validator.class)
public class FlowRiskStressTest001Dto extends BaseEntity implements ProcessEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -5377311058111060192L;
    private String  portfolioSingleName;//组合
    private String  profitType; //收益类型
    private String  issueMode; //开放类型
    private String  raiseType; //募集方式
    private String  investType; //投资性质
    private String  sceneId ; //情景选择
    private Date    baseDate; //压力测试基准时间
    private String    analysisVdate; //指标计算开始时间
    private String    analysisMdate; //指标计算结束时间
    private String  analysisname; // 压力测试名称
    private String  null_remark; //备注
    private Date    testDate; //压力测试执行时间
    private String  testId; //测试代码
    private String  testName; //测试名称

    /**
     * 画面处理模式
     */
    private String pageModel;

    /**
     * 操作类型 - 工作流参数
     */
    private String operateType;

    /**
     * 按钮ID - 工作流参数
     */
    private String elementId;
    /**
     * 画面Id - 工作流参数
     */
    private String pageId;
    /**
     * 备注
     */
    private String remark;
    /**
     * 是否节假日
     */
    private String isHoliday;
    /**
     * 日历代码
     */
    private String calendarId;
    
    /**
     * 获取 portfolioSingleName
     * @return portfolioSingleName.
     */
    public String getPortfolioSingleName() {
        return portfolioSingleName;
    }
    
    /**
     * 设置 portfolioSingleName
     * @param portfolioSingleName portfolioSingleName
     */
    public void setPortfolioSingleName(String portfolioSingleName) {
        this.portfolioSingleName = portfolioSingleName;
    }
    
    /**
     * 获取 profitType
     * @return profitType.
     */
    public String getProfitType() {
        return profitType;
    }
    
    /**
     * 设置 profitType
     * @param profitType profitType
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }
    
    /**
     * 获取 issueMode
     * @return issueMode.
     */
    public String getIssueMode() {
        return issueMode;
    }
    
    /**
     * 设置 issueMode
     * @param issueMode issueMode
     */
    public void setIssueMode(String issueMode) {
        this.issueMode = issueMode;
    }
    
    /**
     * 获取 raiseType
     * @return raiseType.
     */
    public String getRaiseType() {
        return raiseType;
    }
    
    /**
     * 设置 raiseType
     * @param raiseType raiseType
     */
    public void setRaiseType(String raiseType) {
        this.raiseType = raiseType;
    }
    
    /**
     * 获取 investType
     * @return investType.
     */
    public String getInvestType() {
        return investType;
    }
    
    /**
     * 设置 investType
     * @param investType investType
     */
    public void setInvestType(String investType) {
        this.investType = investType;
    }
    
    /**
     * 获取 sceneId
     * @return sceneId.
     */
    public String getSceneId() {
        return sceneId;
    }
    
    /**
     * 设置 sceneId
     * @param sceneId sceneId
     */
    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
    }
    
    /**
     * 获取 analysisname
     * @return analysisname.
     */
    public String getAnalysisname() {
        return analysisname;
    }
    
    /**
     * 设置 analysisname
     * @param analysisname analysisname
     */
    public void setAnalysisname(String analysisname) {
        this.analysisname = analysisname;
    }
    
    /**
     * 获取 null_remark
     * @return null_remark.
     */
    public String getNull_remark() {
        return null_remark;
    }
    
    /**
     * 设置 null_remark
     * @param null_remark null_remark
     */
    public void setNull_remark(String null_remark) {
        this.null_remark = null_remark;
    }
    
    /**
     * 获取 pageModel
     * @return pageModel.
     */
    public String getPageModel() {
        return pageModel;
    }

    
    /**
     * 设置 pageModel
     * @param pageModel pageModel
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    
    /**
     * 获取 operateType
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }

    
    /**
     * 设置 operateType
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    
    /**
     * 获取 elementId
     * @return elementId.
     */
    public String getElementId() {
        return elementId;
    }

    
    /**
     * 设置 elementId
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    
    /**
     * 获取 pageId
     * @return pageId.
     */
    public String getPageId() {
        return pageId;
    }

    
    /**
     * 设置 pageId
     * @param pageId pageId
     */
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    
    /**
     * 获取 baseDate
     * @return baseDate.
     */
    public Date getBaseDate() {
        return baseDate;
    }

    
    /**
     * 设置 baseDate
     * @param baseDate baseDate
     */
    public void setBaseDate(Date baseDate) {
        this.baseDate = baseDate;
    }

    
    /**
     * 获取 analysisVdate
     * @return analysisVdate.
     */
    public String getAnalysisVdate() {
        return analysisVdate;
    }

    
    /**
     * 设置 analysisVdate
     * @param analysisVdate analysisVdate
     */
    public void setAnalysisVdate(String analysisVdate) {
        this.analysisVdate = analysisVdate;
    }

    
    /**
     * 获取 analysisMdate
     * @return analysisMdate.
     */
    public String getAnalysisMdate() {
        return analysisMdate;
    }

    
    /**
     * 设置 analysisMdate
     * @param analysisMdate analysisMdate
     */
    public void setAnalysisMdate(String analysisMdate) {
        this.analysisMdate = analysisMdate;
    }

    
    /**
     * 获取 testDate
     * @return testDate.
     */
    public Date getTestDate() {
        return testDate;
    }

    
    /**
     * 设置 testDate
     * @param testDate testDate
     */
    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    
    /**
     * 获取 testId
     * @return testId.
     */
    @PrimaryKey(value=Field.PK_1)
    public String getTestId() {
        return testId;
    }

    
    /**
     * 设置 testId
     * @param testId testId
     */
    public void setTestId(String testId) {
        this.testId = testId;
    }

    
    /**
     * 获取 testName
     * @return testName.
     */
    public String getTestName() {
        return testName;
    }

    
    /**
     * 设置 testName
     * @param testName testName
     */
    public void setTestName(String testName) {
        this.testName = testName;
    }

    
    /**
     * 获取 remark
     * @return remark.
     */
    public String getRemark() {
        return remark;
    }

    
    /**
     * 设置 remark
     * @param remark remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 得到是否节假日
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public String getIsHoliday() {
        return isHoliday;
    }

    /**
     * 设置是否节假日
     * <功能详细描述>
     * @param isHoliday
     * @see
     * @since 1.0
     */
    public void setIsHoliday(String isHoliday) {
        this.isHoliday = isHoliday;
    }

    /**
     * 得到日历代码
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public String getCalendarId() {
        return calendarId;
    }

    /**
     * 设置日历代码
     * <功能详细描述>
     * @param calendarId
     * @see
     * @since 1.0
     */
    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }

    
}
