/*
 * 文件名：MarketRiskSceneFactorTypeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：汪海
 * 修改时间：2019年9月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.stresstestmanage.marketriskscenemanage.dto;

import com.joyintech.framework.base.BaseEntity;

/**
 * 用于显示因子二级分类，同时新增情景因子时用到该dto
 * 〈功能详细描述〉
 * @author 汪海
 * @version 1.0 2019年9月19日
 * @see MarketRiskSceneFactorTypeDto
 * @since 1.0
 */
public class MarketRiskSceneFactorTypeDto extends BaseEntity {
	
	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = 9092519562516952617L;


	/**
	 * 因子分类
	 */
	private String ftrTypeCode;
	
	/**
	 * 因子二级分类
	 */
	private String ftrType2Code;
	
	/**
	 * 说明
	 */
	private String remark;
	
	/**
	 * 是否选中
	 */
	private boolean ftrChecked;
	
	/**
	 * 因子id
	 */
	private String ftrId;
	
	/**
	 * 因子名称
	 */
	private String ftrName;
	
	/**
	 * 因子值结构
	 */
	private String ftrValueStruct;
	
	/**
	 * 资产类型
	 */
	private String assetType;

	/**
	 * 获取 ftrTypeCode
	 * @return ftrTypeCode.
	 */
	public String getFtrTypeCode() {
		return ftrTypeCode;
	}

	/**
	 * 设置 ftrTypeCode
	 * @param ftrTypeCode ftrTypeCode
	 */
	public void setFtrTypeCode(String ftrTypeCode) {
		this.ftrTypeCode = ftrTypeCode;
	}

	/**
	 * 获取 ftrType2Code
	 * @return ftrType2Code.
	 */
	public String getFtrType2Code() {
		return ftrType2Code;
	}

	/**
	 * 设置 ftrType2Code
	 * @param ftrType2Code ftrType2Code
	 */
	public void setFtrType2Code(String ftrType2Code) {
		this.ftrType2Code = ftrType2Code;
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
	 * 获取 ftrChecked
	 * @return ftrChecked.
	 */
	public boolean isFtrChecked() {
		return ftrChecked;
	}

	/**
	 * 设置 ftrChecked
	 * @param ftrChecked ftrChecked
	 */
	public void setFtrChecked(boolean ftrChecked) {
		this.ftrChecked = ftrChecked;
	}

	/**
	 * 获取 ftrId
	 * @return ftrId.
	 */
	public String getFtrId() {
		return ftrId;
	}

	/**
	 * 设置 ftrId
	 * @param ftrId ftrId
	 */
	public void setFtrId(String ftrId) {
		this.ftrId = ftrId;
	}

	/**
	 * 获取 ftrName
	 * @return ftrName.
	 */
	public String getFtrName() {
		return ftrName;
	}

	/**
	 * 设置 ftrName
	 * @param ftrName ftrName
	 */
	public void setFtrName(String ftrName) {
		this.ftrName = ftrName;
	}

	/**
	 * 获取 ftrValueStruct
	 * @return ftrValueStruct.
	 */
	public String getFtrValueStruct() {
		return ftrValueStruct;
	}

	/**
	 * 设置 ftrValueStruct
	 * @param ftrValueStruct ftrValueStruct
	 */
	public void setFtrValueStruct(String ftrValueStruct) {
		this.ftrValueStruct = ftrValueStruct;
	}

	/**
	 * 获取 assetType
	 * @return assetType.
	 */
	public String getAssetType() {
		return assetType;
	}

	/**
	 * 设置 assetType
	 * @param assetType assetType
	 */
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	
}
