package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class SttStressTest extends BaseEntity {
    /**
     * 模板代码
     */
    private String testId;
    
    /**
     * 模板名称
     */
    private String testName;

    /**
     * 模板代码
     */
    private String tmplId;
    private String remark;
    
    /**
     * 机构
     */
    private String institutionCode;
     
    /**
     * 获取 institutionCode
     * @return institutionCode.
     */
    public String getInstitutionCode() {
        return institutionCode;
    }

    
    /**
     * 设置 institutionCode
     * @param institutionCode institutionCode
     */
    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode;
    }

    /**
	 * @return remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark 要设置的 remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}


	/**
     * 分析开始日期
     */
    private Date analysisVdate;

    /**
     * 分析结束日期
     */
    private Date analysisMdate;

    /**
     * 基准日期
     */
    private Date baseDate;

    /**
     * 执行日期
     */
    private Date testDate;
    
    /**
     * 执行状态
     * add by wangkang 20190906
     */
    private String exeStatus;

private String riskFacHandling;//风险因子的异常处理
    
    
    /**
	 * @return the riskFacHandling
	 */
	public String getRiskFacHandling() {
		return riskFacHandling;
	}


	/**
	 * @param riskFacHandling the riskFacHandling to set
	 */
	public void setRiskFacHandling(String riskFacHandling) {
		this.riskFacHandling = riskFacHandling;
	}
    /**
     * 取得 模板代码
     * @return 模板代码
     */
    public String getTestId() {
        return testId;
    }

    /**
     * 设置 模板代码
     * @param testId 模板代码
     */
    public void setTestId(String testId) {
        this.testId = testId == null ? null : testId.trim();
    }

    /**
     * 取得 模板代码
     * @return 模板代码
     */
    public String getTmplId() {
        return tmplId;
    }

    /**
     * 设置 模板代码
     * @param tmplId 模板代码
     */
    public void setTmplId(String tmplId) {
        this.tmplId = tmplId == null ? null : tmplId.trim();
    }

    /**
     * 取得 分析开始日期
     * @return 分析开始日期
     */
    public Date getAnalysisVdate() {
        return analysisVdate;
    }

    /**
     * 设置 分析开始日期
     * @param analysisVdate 分析开始日期
     */
    public void setAnalysisVdate(Date analysisVdate) {
        this.analysisVdate = analysisVdate;
    }

    /**
     * 取得 分析结束日期
     * @return 分析结束日期
     */
    public Date getAnalysisMdate() {
        return analysisMdate;
    }

    /**
     * 设置 分析结束日期
     * @param analysisMdate 分析结束日期
     */
    public void setAnalysisMdate(Date analysisMdate) {
        this.analysisMdate = analysisMdate;
    }

    /**
     * 取得 基准日期
     * @return 基准日期
     */
    public Date getBaseDate() {
        return baseDate;
    }

    /**
     * 设置 基准日期
     * @param baseDate 基准日期
     */
    public void setBaseDate(Date baseDate) {
        this.baseDate = baseDate;
    }

    /**
     * 取得 执行日期
     * @return 执行日期
     */
    public Date getTestDate() {
        return testDate;
    }

    /**
     * 设置 执行日期
     * @param testDate 执行日期
     */
    public void setTestDate(Date testDate) {
        this.testDate = testDate;
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
     * 获取 exeStatus
     * @return exeStatus.
     */
    public String getExeStatus() {
        return exeStatus;
    }

    
    /**
     * 设置 exeStatus
     * @param exeStatus exeStatus
     */
    public void setExeStatus(String exeStatus) {
        this.exeStatus = exeStatus;
    }
    
}