/*
 * 文件名：MarketRiskSceneManage003Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：汪海
 * 修改时间：2019年9月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.stresstestmanage.marketriskscenemanage.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.systemtools.util.SysDatadictUtil;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author 汪海
 * @version 1.0 2019年9月20日
 * @see MarketRiskSceneManage003Dto
 * @since 1.0
 */
@PageId("MarketRiskSceneManage003")
public class MarketRiskSceneManage003Dto extends BaseEntity {

	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = -8302699439702808141L;
	
	/**
	 * 情景id
	 */
	private String sceneId;
	
	/**
	 * 因子id
	 */
	public String ftrId;
	
	/**
	 * 因子名称
	 */
	private String ftrName;
	
	/**
	 * 因子值结构
	 */
	private String ftrValueStruct;
	
	/**
	 * 因子类别
	 */
	private String ftrTypeCode;
	
	/**
	 * 因子类别
	 */
	private String ftrType2Code;
	
	/**
	 * 起始日期
	 */
	private String startDate;
	
	/**
	 * 结束日期
	 */
	private String endDate;
	
	/**
	 * 月数
	 */
	private String months;
	
	/**
	 * 天数
	 */
	private String days;
	
	/**
	 * 变化值
	 */
	private String changeValue;
	
	/**
	 * 资产大类
	 */
	private String assetType1;
	
	/**
	 * 资产一级分类
	 */
	private String assetType2;
	
	/**
	 * 资产二级分类
	 */
	private String assetType3;
	
	/**
	 * 资产分类名称
	 */
	private String assetTypeName;
	
	/**
	 * 债券评级代码
	 */
	private String gradeCode;
	
	/**
	 * 是否是混合因子
	 */
	private boolean mixFtr;
	
	/**
	 * 关联的因子组id
	 */
	private String groupId;
	
	/**
	 * 情景因子id
	 */
	private String sceneFtrId;
	
	/**
	 * 资产了下和评级
	 */
	private String assetTypeAndGrade;

	/**
	 * 获取 sceneId
	 * @return sceneId.
	 */
	public String getSceneId() {
		return sceneId;
	}

	/**
	 * 设置 sceneId
	 * @param sceneId sceneId
	 */
	public void setSceneId(String sceneId) {
		this.sceneId = sceneId;
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
	 * 获取 ftrTypeCode
	 * @return ftrTypeCode.
	 */
	public String getFtrTypeCode() {
		return ftrTypeCode;
	}
	
	/**
	 * 获取 factorTypeName
	 * @return factorTypeName.
	 */
	public String getFtrTypeName(){
		return SysDatadictUtil.getNameFromDataDict(DictCode.风险因子管理_市场风险因子分类, ftrTypeCode);
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
	 * 获取 ftrType2Value
	 * @return ftrType2Value.
	 */
	public String getFtrType2Name() {
		return SysDatadictUtil.getNameFromDataDict(DictCode.风险因子管理_二级分类, ftrType2Code);
	}

	/**
	 * 设置 ftrType2Code
	 * @param ftrType2Code ftrType2Code
	 */
	public void setFtrType2Code(String ftrType2Code) {
		this.ftrType2Code = ftrType2Code;
	}

	/**
	 * 获取 startDate
	 * @return startDate.
	 */
	public String getStartDate() {
		return startDate==null?null:startDate.substring(0, 10);
	}

	/**
	 * 设置 startDate
	 * @param startDate startDate
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * 获取 endDate
	 * @return endDate.
	 */
	public String getEndDate() {
		return endDate==null?null:endDate.substring(0, 10);
	}

	/**
	 * 设置 endDate
	 * @param endDate endDate
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * 获取 months
	 * @return months.
	 */
	public String getMonths() {
		return months;
	}

	/**
	 * 设置 months
	 * @param months months
	 */
	public void setMonths(String months) {
		this.months = months;
	}

	/**
	 * 获取 days
	 * @return days.
	 */
	public String getDays() {
		return days;
	}

	/**
	 * 设置 days
	 * @param days days
	 */
	public void setDays(String days) {
		this.days = days;
	}

	/**
	 * 获取 changeValue
	 * @return changeValue.
	 */
	public String getChangeValue() {
		return changeValue;
	}

	/**
	 * 设置 changeValue
	 * @param changeValue changeValue
	 */
	public void setChangeValue(String changeValue) {
		this.changeValue = changeValue;
	}
	
	/**
	 * 获取 assetType1
	 * @return assetType1.
	 */
	public String getAssetType1() {
		return assetType1;
	}

	/**
	 * 设置 assetType1
	 * @param assetType1 assetType1
	 */
	public void setAssetType1(String assetType1) {
		this.assetType1 = assetType1;
	}

	/**
	 * 获取 assetType2
	 * @return assetType2.
	 */
	public String getAssetType2() {
		return assetType2;
	}

	/**
	 * 设置 assetType2
	 * @param assetType2 assetType2
	 */
	public void setAssetType2(String assetType2) {
		this.assetType2 = assetType2;
	}

	/**
	 * 获取 assetType3
	 * @return assetType3.
	 */
	public String getAssetType3() {
		return assetType3;
	}

	/**
	 * 设置 assetType3
	 * @param assetType3 assetType3
	 */
	public void setAssetType3(String assetType3) {
		this.assetType3 = assetType3;
	}

	/**
	 * 获取 assetTypeName
	 * @return assetTypeName.
	 */
	public String getAssetTypeName() {
		return assetTypeName;
	}
	
	
	/**
	 * 设置 assetTypeAndGrade
	 * @param assetTypeAndGrade assetTypeAndGrade
	 */
	public void setAssetTypeAndGrade(String assetTypeAndGrade) {
//		this.assetTypeAndGrade = assetTypeAndGrade;		//不需要保存该属性
	}

	//获取资产类型名称和评级
	public String getAssetTypeAndGrade(){
		if("Mrk0201".equals(ftrType2Code) ){		//汇率类-汇率行情，直接关联 外汇即期、外汇远期、外汇掉期
			return "外汇即期，外汇远期，外汇掉期";
		}if("Mrk0102".equals(ftrType2Code)){		//利率类-基准利率类，直接关联 利率互换
			return "利率互换";
		}else{								//其他类型
			if(StringUtil.isNotEmpty(assetTypeName) && StringUtil.isNotEmpty(gradeCode)){
				return assetTypeName+"（"+gradeCode+"）";
			}else{
				return assetTypeName;
			}
		}
	}

	/**
	 * 设置 assetTypeName
	 * @param assetTypeName assetTypeName
	 */
	public void setAssetTypeName(String assetTypeName) {
		this.assetTypeName = assetTypeName;
	}

	/**
	 * 获取 gradeCode
	 * @return gradeCode.
	 */
	public String getGradeCode() {
		return gradeCode;
	}

	/**
	 * 设置 gradeCode
	 * @param gradeCode gradeCode
	 */
	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}

	/**
	 * 获取 mixFtr
	 * @return mixFtr.
	 */
	public boolean isMixFtr() {
		return mixFtr;
	}

	/**
	 * 设置 mixFtr
	 * @param mixFtr mixFtr
	 */
	public void setMixFtr(boolean mixFtr) {
		this.mixFtr = mixFtr;
	}

	/**
	 * 获取 groupId
	 * @return groupId.
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * 设置 groupId
	 * @param groupId groupId
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/**
	 * 获取 sceneFtrId
	 * @return sceneFtrId.
	 */
	public String getSceneFtrId() {
		return sceneFtrId;
	}

	/**
	 * 设置 sceneFtrId
	 * @param sceneFtrId sceneFtrId
	 */
	public void setSceneFtrId(String sceneFtrId) {
		this.sceneFtrId = sceneFtrId;
	}

	/**
	 * 返回id和资产类型拼接后的值
	 * <功能详细描述>
	 * @see
	 * @since 1.0
	 */
	public String getFtrIdAndType(){
		return ftrId + "," + assetType1 + "," + assetType2 + "," + assetType3;
	}
	
}
