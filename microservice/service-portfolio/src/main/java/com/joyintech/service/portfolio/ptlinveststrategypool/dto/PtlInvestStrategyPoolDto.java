/*
 * 文件名：PtlInvPortfolioStrategyDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：niuqianyu
 * 修改时间：2019年7月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.portfolio.ptlinveststrategypool.dto;


import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.grid.GridRowDto;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.PtlInvStrategy;

public class PtlInvestStrategyPoolDto extends PtlInvStrategy  implements ITableRow, GridRowDto {
	/**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -2785845093600767995L;
    /**
	 * 操作类型
	 */
	private String IsNew;
	/**
	 * 资产二级分类
	 */
	private String type2;
	/**
	 * 资产三级分类
	 */
	private String type3;
	/**
	 * 资产四级分类
	 */
	private String type4;
	/**
	 * 资产五级分类
	 */
	private String type5;
	/**
	 * 比例（%）
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal ratio;
	/**
	 * 树形结构id
	 */
	private String assetTypeId;
	/**
	 * 树形结构id
	 */
	private String pAssetTypeId;
	/**
     * 行ID
     */
    private String rowId;
    /**
     * 名称
     */
    private String name;
    /**
     * 是否有子节点
     */
    private String hasChildren;
    /**
     * 是否最终节点判断
     */
    private String fiterFlag;
    /**
	 * 投资策略组合详情列表
	 */
	private List<PtlInvStrategyDetailDto> ptlInvestStrategyPoolDetailList;
	/**
	 * 指标
	 */
	private String evalIndex;

    /**
     * 批次号
     */
	private String batchId;
	
    /**
     * 获取 batchId
     * @return batchId.
     */
    public String getBatchId() {
        return batchId;
    }
    
    /**
     * 设置 batchId
     * @param batchId batchId
     */
    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }
    public String getType2() {
		return type2;
	}
	public void setType2(String type2) {
		this.type2 = type2;
	}
	public String getType3() {
		return type3;
	}
	public void setType3(String type3) {
		this.type3 = type3;
	}
	public String getType4() {
		return type4;
	}
	public void setType4(String type4) {
		this.type4 = type4;
	}
	public String getType5() {
		return type5;
	}
	public void setType5(String type5) {
		this.type5 = type5;
	}
	public BigDecimal getRatio() {
		return ratio;
	}
	public void setRatio(BigDecimal ratio) {
		this.ratio = ratio;
	}
	public String getAssetTypeId() {
		return assetTypeId;
	}
	public void setAssetTypeId(String assetTypeId) {
		this.assetTypeId = assetTypeId;
	}
	public String getpAssetTypeId() {
		return pAssetTypeId;
	}
	public void setpAssetTypeId(String pAssetTypeId) {
		this.pAssetTypeId = pAssetTypeId;
	}
	@Override
	public String getRowId() {
		return this.rowId;
	}
	@Override
	public void setRowId(String rowId) {
		this.rowId = rowId;
		
	}
	@Override
	public int getOrder() {
		return 0;
	}
	@Override
	public void setOrder(int order) {
		
	}
	public String getId() {
        return this.getAssetTypeId();

	}
	public void setId(String id) {
		setAssetTypeId(id);
		
	}
	public String getParentId() {
		return this.getpAssetTypeId();
	}
	public void setParentId(String parentId) {
		setpAssetTypeId(parentId);
	}
	public String getName() {
        return name;
	}
	public void setName(String name) {
        this.name = name;
	}
	public boolean isLeaf() {
		return StringUtil.equals(com.joyintech.framework.constant.DictCodeList.是否_是.getValue(), hasChildren);
	}
	public void setLeaf(boolean leaf) {
	}
	
	public String getHasChildren() {
		return hasChildren;
	}
	public void setHasChildren(String hasChildren) {
		this.hasChildren = hasChildren;
	}
	public String getFiterFlag() {
		return fiterFlag;
	}
	public void setFiterFlag(String fiterFlag) {
		this.fiterFlag = fiterFlag;
	}
	public List<PtlInvStrategyDetailDto> getPtlInvestStrategyPoolDetailList() {
		return ptlInvestStrategyPoolDetailList;
	}
	public void setPtlInvestStrategyPoolDetailList(List<PtlInvStrategyDetailDto> ptlInvestStrategyPoolDetailList) {
		this.ptlInvestStrategyPoolDetailList = ptlInvestStrategyPoolDetailList;
	}
	public String getEvalIndex() {
		return evalIndex;
	}
	public void setEvalIndex(String evalIndex) {
		this.evalIndex = evalIndex;
	}
	public String getIsNew() {
		return IsNew;
	}
	public void setIsNew(String isNew) {
		IsNew = isNew;
	}
	
	
}
