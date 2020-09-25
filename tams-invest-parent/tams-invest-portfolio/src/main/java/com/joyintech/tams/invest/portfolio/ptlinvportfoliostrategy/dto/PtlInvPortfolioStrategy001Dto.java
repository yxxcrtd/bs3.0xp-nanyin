/*
 * 文件名：PtlInvPortfolioStrategy001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：niuqianyu
 * 修改时间：2019年7月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.portfolio.ptlinvportfoliostrategy.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.PtlIncomeIndexFloat;
import com.joyintech.tams.common.base.entity.PtlInvStrategy;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.portfolio.ptlinvportfoliostrategy.validator.PtlInvPortfolioStrategy001Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

@ValidLogic(targetClass = PtlInvPortfolioStrategy001Validator.class, groups = { ValidateGroupEdit.class,
		ValidateGroupDelete.class })
public class PtlInvPortfolioStrategy001Dto extends PtlInvStrategy implements ProcessEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 组合策略代码
	 */
	private String ptlStgyId;

	/**
	 * 组合代码
	 */
	@PrimaryKey(value = Field.PK_1)
	@NotEmpty(fieldName = "PtlInvPortfolioStrategy001.portfolioId", groups = { ValidateGroupEdit.class })
	private String portfolioId;
	/**
	 * 组合名称
	 */
	@NotEmpty(fieldName = "PtlInvPortfolioStrategy001.portfolioName", groups = { ValidateGroupEdit.class })
	private String portfolioName;

	/**
	 * 策略类型
	 */
	@NotEmpty(fieldName = "PtlInvPortfolioStrategy001.strategyType", groups = { ValidateGroupEdit.class })
	private String strategyType;

	/**
	 * 策略代码
	 */
	@PrimaryKey(value = Field.PK_2)
	@NotEmpty(fieldName = "PtlInvPortfolioStrategy001.strategyId", groups = { ValidateGroupEdit.class })
	private String strategyId;

	/**
	 * 策略名称
	 */
	@NotEmpty(fieldName = "PtlInvPortfolioStrategy001.strategyName", groups = { ValidateGroupEdit.class })
	private String strategyName;

	/**
	 * 策略描述
	 */
	@NotEmpty(fieldName = "PtlInvPortfolioStrategy001.strategyDesc", groups = { ValidateGroupEdit.class })
	private String strategyDesc;

	/**
	 * 策略生效日
	 */
	@PrimaryKey(value = Field.PK_3)
	@NotEmpty(fieldName = "PtlInvPortfolioStrategy001.effDate", groups = { ValidateGroupEdit.class })
	private String effDate;

	/**
	 * 收益目标类型
	 */
//	@NotEmpty(fieldName = "PtlInvPortfolioStrategy001.incomeType", groups = { ValidateGroupEdit.class })
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

	public String getStrategyType() {
		return strategyType;
	}

	public void setStrategyType(String strategyType) {
		this.strategyType = strategyType;
	}

	public String getStrategyId() {
		return strategyId;
	}

	public void setStrategyId(String strategyId) {
		this.strategyId = strategyId;
	}

	public String getStrategyName() {
		return strategyName;
	}

	public void setStrategyName(String strategyName) {
		this.strategyName = strategyName;
	}

	public String getStrategyDesc() {
		return strategyDesc;
	}

	public void setStrategyDesc(String strategyDesc) {
		this.strategyDesc = strategyDesc;
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

	public String getStrategyTypeStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.投资策略类型, this.strategyType);
	}

	public String getIncomeTypeStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.评价指标收益目标类型, this.incomeType);
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
