/*
 * 文件名：RiskSelectDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2019年1月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.common.risk.dto;

import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

/**
 * 风险失算结果实体
 * @author fanshuanghu
 * @version 1.0 2019年1月17日
 * @see RiskSelectDto
 * @since 1.0
 */
public class RiskSelectDto extends BaseEntity{
    /*
     * 组合(包)代码
     */
    private String portfolioId;
    /*
     * 指标编号
     */
    private String indId;
    /*
     * 指标名称
     */
    private String indName;
    /*
     * 监督日期
     */
    private Date supDate;
    /*
     * 监督值
     */
    private String supVal;
    /*
     * 监督状态
     */
    private String supStat;
    
    /*
     * 指标类型
     */
    private String indType;
    
    /*
     * 预警条件，记录当时比较的预警值，防止该指标以后发生变化
     */
    private String warnCond;
    /*
     * 违规条件，记录当时比较的违规值，防止该指标以后发生变化
     */
    private String foulCond;
    /*
     * 结论
     */
    private String conclusion;
    /*
     * 总结论
     */
    private String totalConclu;
    
    /**
     * 获取 totalConclu
     * @return totalConclu.
     */
    public String getTotalConclu() {
        return totalConclu;
    }
    
    /**
     * 设置 totalConclu
     * @param totalConclu totalConclu
     */
    public void setTotalConclu(String totalConclu) {
        this.totalConclu = totalConclu;
    }
    public String getPortfolioId() {
        return portfolioId;
    }
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }
    public String getIndId() {
        return indId;
    }
    public void setIndId(String indId) {
        this.indId = indId;
    }
    public String getIndName() {
        return indName;
    }
    public void setIndName(String indName) {
        this.indName = indName;
    }
    public Date getSupDate() {
        return supDate;
    }
    public void setSupDate(Date supDate) {
        this.supDate = supDate;
    }
    public String getSupVal() {
        return supVal;
    }
    public void setSupVal(String supVal) {
        this.supVal = supVal;
    }
    public String getSupStat() {
        return supStat;
    }
    public void setSupStat(String supStat) {
        this.supStat = supStat;
    }
    public String getIndType() {
        return indType;
    }
    public void setIndType(String indType) {
        this.indType = indType;
    }
    public String getWarnCond() {
        return warnCond;
    }
    public void setWarnCond(String warnCond) {
        this.warnCond = warnCond;
    }
    public String getFoulCond() {
        return foulCond;
    }
    public void setFoulCond(String foulCond) {
        this.foulCond = foulCond;
    }
    public String getConclusion() {
        return conclusion;
    }
    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    //--------------add by jjpei 20200116 新风险字段-----------------
    /*
     * 组合(包)代码
     */
    private String portfolioCode;
    /*
     * 组合(包)名称
     */
    private String portfolioName;
    /*
     * 指标代码
     */
    private String kriCode;
    /*
     * 趋势
     */
    private String kriTrend;
    /*
     * 指标名称
     */
    private String kriName;
    /*
     * 原值
     */
    private String oldValue;
    /*
     * 监督值
     */
    private String kriValue;
    /*
     * 监督状态
     */
    private String kriStatus;

    /*
     * 联合监督状态
     */
    private String uniteStatus;

    /*
     * 指标类型
     */
    private String kriType;

    /*
     * 预警条件，记录当时比较的预警值，防止该指标以后发生变化
     */
    private String warningLine;
    /*
     * 审批条件，记录当时比较的审批值，防止该指标以后发生变化
     */
    private String approveLine;
    /*
     * 违规条件，记录当时比较的违规值，防止该指标以后发生变化
     */
    private String forbiddenLn;

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public String getKriTrend() {
        return kriTrend;
    }

    public void setKriTrend(String kriTrend) {
        this.kriTrend = kriTrend;
    }

    public String getKriName() {
        return kriName;
    }

    public void setKriName(String kriName) {
        this.kriName = kriName;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getKriValue() {
        return kriValue;
    }

    public void setKriValue(String kriValue) {
        this.kriValue = kriValue;
    }

    public String getKriStatus() {
        return kriStatus;
    }

    public void setKriStatus(String kriStatus) {
        this.kriStatus = kriStatus;
    }

    public String getKriType() {
        return kriType;
    }

    public void setKriType(String kriType) {
        this.kriType = kriType;
    }

    public String getWarningLine() {
        return warningLine;
    }

    public void setWarningLine(String warningLine) {
        this.warningLine = warningLine;
    }

    public String getApproveLine() {
        return approveLine;
    }

    public void setApproveLine(String approveLine) {
        this.approveLine = approveLine;
    }

    public String getForbiddenLn() {
        return forbiddenLn;
    }

    public void setForbiddenLn(String forbiddenLn) {
        this.forbiddenLn = forbiddenLn;
    }

    public String getUniteStatus() {
        return uniteStatus;
    }

    public void setUniteStatus(String uniteStatus) {
        this.uniteStatus = uniteStatus;
    }

    public String getPortfolioCode() {
        return portfolioCode;
    }

    public void setPortfolioCode(String portfolioCode) {
        this.portfolioCode = portfolioCode;
    }

    public String getKriCode() {
        return kriCode;
    }

    public void setKriCode(String kriCode) {
        this.kriCode = kriCode;
    }
}
