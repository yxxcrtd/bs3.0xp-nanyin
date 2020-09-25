/*
 * 文件名：PtlInvPortfolioStrategyDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：niuqianyu
 * 修改时间：2019年7月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.portfolio.ptlinvportfoliostrategy.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.tams.common.base.entity.PtlIncomeIndexFloat;
import com.joyintech.tams.common.base.entity.PtlInvStrategy;

@PageId("PtlInvPortfolioStrategy002")
public class PtlInvPortfolioStrategyDto extends PtlInvStrategy {

	private static final long serialVersionUID = 1L;

	/**
	 * 组合策略代码
	 */
	private String ptlStgyId;

	/**
	 * 组合代码
	 */
	private String portfolioId;
	/**
	 * 组合名称
	 */
	private String portfolioName;
	/**
	 * 策略生效日
	 */
	private String effDate;

	/**
	 * 收益目标类型
	 */
	private String incomeType;

	/**
	 * 收益目标-固定
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal incomeTarget;

	/**
	 * 最高收益目标
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal maxIncomeTarget;

	/**
	 * 最低收益目标
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal minIncomeTarget;

	/**
	 * 指标代码
	 */
	private String indexId;

	private List<PtlIncomeIndexFloat> ptlIncomeIndexFloatTable;
    
    private List<PtlIncomeIndexDto> ptlIncomeIndexList;

	/**
	 * 操作类型
	 */
	private String operateType;

	private String pageModel;
	
	/**
     * 表格显示标志
     */
    private String tableShowFlag;
    
    /**
     * 评价指标
     */
    private String evalIndex;
    
    /**
     * 获取 evalIndex
     * @return evalIndex.
     */
    public String getEvalIndex() {
        return evalIndex;
    }

    
    /**
     * 设置 evalIndex
     * @param evalIndex evalIndex
     */
    public void setEvalIndex(String evalIndex) {
        this.evalIndex = evalIndex;
    }

    /**
     * 获取 tableShowFlag
     * @return tableShowFlag.
     */
    public String getTableShowFlag() {
        return tableShowFlag;
    }

    /**
     * 设置 tableShowFlag
     * @param tableShowFlag tableShowFlag
     */
    public void setTableShowFlag(String tableShowFlag) {
        this.tableShowFlag = tableShowFlag;
    }

	public String getPtlStgyId() {
		return ptlStgyId;
	}

	public void setPtlStgyId(String ptlStgyId) {
		this.ptlStgyId = ptlStgyId;
	}

	public String getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getPortfolioName() {
		return portfolioName;
	}

	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}

	public String getEffDate() {
		return effDate;
	}

	public void setEffDate(String effDate) {
		this.effDate = effDate;
	}

	public String getIncomeType() {
		return incomeType;
	}

	public void setIncomeType(String incomeType) {
		this.incomeType = incomeType;
	}

	public BigDecimal getIncomeTarget() {
		return incomeTarget;
	}

	public void setIncomeTarget(BigDecimal incomeTarget) {
		this.incomeTarget = incomeTarget;
	}

	public BigDecimal getMaxIncomeTarget() {
		return maxIncomeTarget;
	}

	public void setMaxIncomeTarget(BigDecimal maxIncomeTarget) {
		this.maxIncomeTarget = maxIncomeTarget;
	}

	public BigDecimal getMinIncomeTarget() {
		return minIncomeTarget;
	}

	public void setMinIncomeTarget(BigDecimal minIncomeTarget) {
		this.minIncomeTarget = minIncomeTarget;
	}

	public String getIndexId() {
		return indexId;
	}

	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}

	public List<PtlIncomeIndexFloat> getPtlIncomeIndexFloatTable() {
		return ptlIncomeIndexFloatTable;
	}

	public void setPtlIncomeIndexFloatTable(List<PtlIncomeIndexFloat> ptlIncomeIndexFloatTable) {
		this.ptlIncomeIndexFloatTable = ptlIncomeIndexFloatTable;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getPageModel() {
		return pageModel;
	}

	public void setPageModel(String pageModel) {
		this.pageModel = pageModel;
	}
    
    /**
     * 获取 ptlIncomeIndexList
     * @return ptlIncomeIndexList.
     */
    public List<PtlIncomeIndexDto> getPtlIncomeIndexList() {
        return ptlIncomeIndexList;
    }

    /**
     * 设置 ptlIncomeIndexList
     * @param ptlIncomeIndexList ptlIncomeIndexList
     */
    public void setPtlIncomeIndexList(List<PtlIncomeIndexDto> ptlIncomeIndexList) {
        this.ptlIncomeIndexList = ptlIncomeIndexList;
    }

}
