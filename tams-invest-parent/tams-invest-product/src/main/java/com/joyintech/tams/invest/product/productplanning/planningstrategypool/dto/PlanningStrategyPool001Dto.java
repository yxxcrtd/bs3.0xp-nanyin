/*
 * 文件名：PlanningStrategyPool001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：niuqianyu
 * 修改时间：2019年12月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productplanning.planningstrategypool.dto;

import java.math.BigDecimal;
import java.util.List;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.grid.GridRowDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.PtlInvStrategy;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.product.productplanning.planningstrategypool.validator.PlanningStrategyPool001Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 规则策略池维护
 * 
 * @author niuqianyu
 * @version 1.0 2019年12月13日
 * @see PlanningStrategyPool001Dto
 * @since 1.0
 */
@ValidLogic(targetClass = PlanningStrategyPool001Validator.class, groups = { ValidateGroupEdit.class,
		ValidateGroupDelete.class })
public class PlanningStrategyPool001Dto extends PtlInvStrategy implements ITableRow, ProcessEntity, GridRowDto {

	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 策略代码
	 */
	@PrimaryKey(PrimaryKey.Field.PK_1)
	private String strategyId;

	/**
	 * 策略名称
	 */
	@NotEmpty(fieldName = "PlanningStrategyPool001.strategyName", groups = { ValidateGroupEdit.class })
	private String strategyName;

	/**
	 * 策略描述
	 */
	@NotEmpty(fieldName = "PlanningStrategyPool002.strategyDesc", groups = { ValidateGroupEdit.class })
	private String strategyDesc;

	/**
	 * 策略类型
	 */
	@NotEmpty(fieldName = "PlanningStrategyPool002.strategyType", groups = { ValidateGroupEdit.class })
	private String strategyType;

	/**
	 * 使用状态
	 */
	@NotEmpty(fieldName = "PlanningStrategyPool001.useStatus", groups = { ValidateGroupEdit.class })
	private String useStatus;

	/**
	 * 比例
	 */
	private BigDecimal ratio;

	/**
	 * 资产大类
	 */
	private String type2;

	/**
	 * 批次号
	 */
	private String batchId;

	/**
	 * 策略明细
	 */
	private List<PlanningStrategyPoolDetailDto> planningStrategyPoolDetailList;

	/**
	 * 树形结构id
	 */
	private String assetTypeId;

	/**
	 * 树形结构id
	 */
	private String pAssetTypeId;

	/**
	 * 是否最终节点判断
	 */
	private String fiterFlag;

	/**
	 * 是否有子节点
	 */
	private String hasChildren;

	/**
	 * 行ID
	 */
	private String rowId;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 操作类型
	 */
	private String operateType;

	private String pageModel;

	/**
	 * 获取 pageModel
	 * 
	 * @return pageModel.
	 */
	public String getPageModel() {
		return pageModel;
	}

	/**
	 * 设置 pageModel
	 * 
	 * @param pageModel
	 *            pageModel
	 */
	public void setPageModel(String pageModel) {
		this.pageModel = pageModel;
	}

	/**
	 * 获取 operateType
	 * 
	 * @return operateType.
	 */
	public String getOperateType() {
		return operateType;
	}

	/**
	 * 设置 operateType
	 * 
	 * @param operateType
	 *            operateType
	 */
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	/**
	 * 获取 ratio
	 * 
	 * @return ratio.
	 */
	public BigDecimal getRatio() {
		return ratio;
	}

	/**
	 * 设置 ratio
	 * 
	 * @param ratio
	 *            ratio
	 */
	public void setRatio(BigDecimal ratio) {
		this.ratio = ratio;
	}

	/**
	 * 获取 type2
	 * 
	 * @return type2.
	 */
	public String getType2() {
		return type2;
	}

	/**
	 * 设置 type2
	 * 
	 * @param type2
	 *            type2
	 */
	public void setType2(String type2) {
		this.type2 = type2;
	}

	/**
	 * 获取 batchId
	 * 
	 * @return batchId.
	 */
	public String getBatchId() {
		return batchId;
	}

	/**
	 * 设置 batchId
	 * 
	 * @param batchId
	 *            batchId
	 */
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	/**
	 * 获取 planningStrategyPoolDetailList
	 * 
	 * @return planningStrategyPoolDetailList.
	 */
	public List<PlanningStrategyPoolDetailDto> getPlanningStrategyPoolDetailList() {
		return planningStrategyPoolDetailList;
	}

	/**
	 * 设置 planningStrategyPoolDetailList
	 * 
	 * @param planningStrategyPoolDetailList
	 *            planningStrategyPoolDetailList
	 */
	public void setPlanningStrategyPoolDetailList(List<PlanningStrategyPoolDetailDto> planningStrategyPoolDetailList) {
		this.planningStrategyPoolDetailList = planningStrategyPoolDetailList;
	}

	/**
	 * 获取 assetTypeId
	 * 
	 * @return assetTypeId.
	 */
	public String getAssetTypeId() {
		return assetTypeId;
	}

	/**
	 * 设置 assetTypeId
	 * 
	 * @param assetTypeId
	 *            assetTypeId
	 */
	public void setAssetTypeId(String assetTypeId) {
		this.assetTypeId = assetTypeId;
	}

	/**
	 * 获取 pAssetTypeId
	 * 
	 * @return pAssetTypeId.
	 */
	public String getpAssetTypeId() {
		return pAssetTypeId;
	}

	/**
	 * 设置 pAssetTypeId
	 * 
	 * @param pAssetTypeId
	 *            pAssetTypeId
	 */
	public void setpAssetTypeId(String pAssetTypeId) {
		this.pAssetTypeId = pAssetTypeId;
	}

	/**
	 * 获取 fiterFlag
	 * 
	 * @return fiterFlag.
	 */
	public String getFiterFlag() {
		return fiterFlag;
	}

	/**
	 * 设置 fiterFlag
	 * 
	 * @param fiterFlag
	 *            fiterFlag
	 */
	public void setFiterFlag(String fiterFlag) {
		this.fiterFlag = fiterFlag;
	}

	/**
	 * 获取 hasChildren
	 * 
	 * @return hasChildren.
	 */
	public String getHasChildren() {
		return hasChildren;
	}

	/**
	 * 设置 hasChildren
	 * 
	 * @param hasChildren
	 *            hasChildren
	 */
	public void setHasChildren(String hasChildren) {
		this.hasChildren = hasChildren;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getRowId() {
		return rowId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getOrder() {
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setOrder(int order) {

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getId() {
		return this.getAssetTypeId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setId(String id) {
		setAssetTypeId(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getParentId() {
		return this.getpAssetTypeId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setParentId(String parentId) {
		setpAssetTypeId(parentId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isLeaf() {
		return StringUtil.equals(com.joyintech.framework.constant.DictCodeList.是否_是.getValue(), hasChildren);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setLeaf(boolean leaf) {

	}

	/**
	 * 获取 strategyId
	 * 
	 * @return strategyId.
	 */
	public String getStrategyId() {
		return strategyId;
	}

	/**
	 * 设置 strategyId
	 * 
	 * @param strategyId
	 *            strategyId
	 */
	public void setStrategyId(String strategyId) {
		this.strategyId = strategyId;
	}

	/**
	 * 获取 strategyName
	 * 
	 * @return strategyName.
	 */
	public String getStrategyName() {
		return strategyName;
	}

	/**
	 * 设置 strategyName
	 * 
	 * @param strategyName
	 *            strategyName
	 */
	public void setStrategyName(String strategyName) {
		this.strategyName = strategyName;
	}

	/**
	 * 获取 strategyDesc
	 * 
	 * @return strategyDesc.
	 */
	public String getStrategyDesc() {
		return strategyDesc;
	}

	/**
	 * 设置 strategyDesc
	 * 
	 * @param strategyDesc
	 *            strategyDesc
	 */
	public void setStrategyDesc(String strategyDesc) {
		this.strategyDesc = strategyDesc;
	}

	/**
	 * 获取 strategyType
	 * 
	 * @return strategyType.
	 */
	public String getStrategyType() {
		return strategyType;
	}

	/**
	 * 设置 strategyType
	 * 
	 * @param strategyType
	 *            strategyType
	 */
	public void setStrategyType(String strategyType) {
		this.strategyType = strategyType;
	}

	/**
	 * 获取 useStatus
	 * 
	 * @return useStatus.
	 */
	public String getUseStatus() {
		return useStatus;
	}

	/**
	 * 设置 useStatus
	 * 
	 * @param useStatus
	 *            useStatus
	 */
	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	public String getUseStatusStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.使用状态, this.useStatus);
	}

	public String getType2Str() {
		String type2Str = StringUtil.EMPTY;
		List<String> list = StringUtil.splitToList(GlobalConstant.COMMA, this.getType2());
		if (!StringUtil.isEmptyList(list)) {
			for (String type : list) {
				type2Str = type2Str + SysDataUtil.getNameFromDataDict(DictCode.资产大类, type) + GlobalConstant.COMMA;
			}
			type2Str = type2Str.substring(0, type2Str.lastIndexOf(GlobalConstant.COMMA));
		}
		return type2Str;
	}

}
