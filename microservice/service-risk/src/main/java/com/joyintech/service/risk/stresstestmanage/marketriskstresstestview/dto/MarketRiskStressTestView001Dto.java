/*
 * 文件名：FlowRiskFactroRManageDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2019年8月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.stresstestmanage.marketriskstresstestview.dto;

import com.joyintech.framework.base.BaseEntity;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author lifangchen
 * @version 1.0 2019年8月21日
 * @see MarketRiskStressTestView001Dto
 * @since 1.0
 */
public class MarketRiskStressTestView001Dto extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String tmplId;//压力测试模板代码
	private String tmplName;//压力测试模板名称
	private String sId;//情景编号
	private String sName;//情景名称
	private String riskType;//适用风险分类
    private String testId;//压力测试代码
    private String testName;//压力测试名称
    private String analysisVDate;//分析开始时间
    private String analysisMDate;//分析结束时间
    private String baseDate;//基准日期
    private String testDate;//执行日期
    private String startTime;//执行开始时间
    private String finishedTime;//执行完成时间
    private String exeStatus;//执行状态
    private String remark;//备注
    /**
     * 适用产品（开放类型）
     */
    private String openMode;
    
    
    
    
	public String getsId() {
		return sId;
	}
	public void setsId(String sId) {
		this.sId = sId;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	/**
	 * @return openMode
	 */
	public String getOpenMode() {
		return openMode;
	}
	/**
	 * @param openMode 要设置的 openMode
	 */
	public void setOpenMode(String openMode) {
		this.openMode = openMode;
	}
	/**
	 * @return riskType
	 */
	public String getRiskType() {
		return riskType;
	}
	/**
	 * @param riskType 要设置的 riskType
	 */
	public void setRiskType(String riskType) {
		this.riskType = riskType;
	}
	public String getTmplId() {
		return tmplId;
	}
	public void setTmplId(String tmplId) {
		this.tmplId = tmplId;
	}
	
	public String getTmplName() {
		return tmplName;
	}
	public void setTmplName(String tmplName) {
		this.tmplName = tmplName;
	}
	public String getTestId() {
		return testId;
	}
	public void setTestId(String testId) {
		this.testId = testId;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public String getAnalysisVDate() {
		return analysisVDate;
	}
	public void setAnalysisVDate(String analysisVDate) {
		this.analysisVDate = analysisVDate;
	}
	public String getAnalysisMDate() {
		return analysisMDate;
	}
	public void setAnalysisMDate(String analysisMDate) {
		this.analysisMDate = analysisMDate;
	}
	public String getBaseDate() {
		return baseDate;
	}
	public void setBaseDate(String baseDate) {
		this.baseDate = baseDate;
	}
	public String getTestDate() {
		return testDate;
	}
	public void setTestDate(String testDate) {
		this.testDate = testDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getFinishedTime() {
		return finishedTime;
	}
	public void setFinishedTime(String finishedTime) {
		this.finishedTime = finishedTime;
	}
	public String getExeStatus() {
		//return SysDatadictUtil.getNameFromDataDict(DictCode.压力测试_执行状态, this.exeStatus);
		return exeStatus;
	}
	public void setExeStatus(String exeStatus) {
		this.exeStatus = exeStatus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
    
    
}
