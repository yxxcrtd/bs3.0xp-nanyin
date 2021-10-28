package com.joyintech.tams.cfets.etrib.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * 风险试算结果DTO
 * @author 裴家俊
 * @version 1.0 2018年11月30日
 * @see CfetsEtribdoRiskcalReturn
 * @since 1.0
 */
public class CfetsEtribdoRiskcalReturn{

	@ApiModelProperty(name="riskMathFlag",value="02：已点击风险试算",example="02")
	private String riskMathFlag;
	@ApiModelProperty(name="riskTrialSpan",value="运算结果",example="02")
	private String riskTrialSpan;
	@ApiModelProperty(name="riskcalReturnList",value="风险运算结果",example="02")
	List<CfetsEtribdoRiskcalReturn> riskcalReturnList;

	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = 1L;
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
	
	/*
	 * 组合层次
	 */
	private String portfolioLevel;
	
	/**
     * 质押式回购多组合
     */
    private String index;

	/**
	 * 表达式
	 */
	private String expr;


	/**
	 * 表达式(可投金额试算使用新增)
	 * by nixukuan 20191012
	 */
	private String investedAmountExpr;
	
    /**
     * 获取 index
     * @return index.
     */
    public String getIndex() {
        return index;
    }

    
    /**
     * 设置 index
     * @param index index
     */
    public void setIndex(String index) {
        this.index = index;
    }

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
	public String getIndTypeStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.指标类型, this.getIndType());
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
	
	public String getPortfolioLevel() {
		return portfolioLevel;
	}

	public void setPortfolioLevel(String portfolioLevel) {
		this.portfolioLevel = portfolioLevel;
	}

	public String getSupStatStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.监督状态, this.getSupStat());
	}

	public String getExpr() {
		return expr;
	}

	public void setExpr(String expr) {
		this.expr = expr;
	}


	public String getInvestedAmountExpr() {
		return investedAmountExpr;
	}

	public void setInvestedAmountExpr(String investedAmountExpr) {
		this.investedAmountExpr = investedAmountExpr;
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
	
	/*
	 *资产代码
	 */
	private String assetCode;
	
	/*
	 * 资产名称
	 */
	private String assetName;

	/*
	 * 指标计算表达式
	 */
	private String kriExpr;


	public String getRiskMathFlag() {
		return riskMathFlag;
	}

	public void setRiskMathFlag(String riskMathFlag) {
		this.riskMathFlag = riskMathFlag;
	}

	public String getRiskTrialSpan() {
		return riskTrialSpan;
	}

	public void setRiskTrialSpan(String riskTrialSpan) {
		this.riskTrialSpan = riskTrialSpan;
	}
	public List<CfetsEtribdoRiskcalReturn> getRiskcalReturnList() {
		return riskcalReturnList;
	}

	public void setRiskcalReturnList(List<CfetsEtribdoRiskcalReturn> riskcalReturnList) {
		this.riskcalReturnList = riskcalReturnList;
	}
}
