/*
 * 文件名：PlanningStrategyPoolDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：niuqianyu
 * 修改时间：2019年12月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.productplanning.planningstrategypool.dto;

import java.math.BigDecimal;
import java.util.List;

import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.grid.GridRowDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.PtlInvStrategy;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author niuqianyu
 * @version 1.0 2019年12月16日
 * @see PlanningStrategyPoolDto
 * @since 1.0
 */
public class PlanningStrategyPoolDto extends PtlInvStrategy implements ITableRow, GridRowDto {

	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = 1L;

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
		return this.rowId;
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

}
