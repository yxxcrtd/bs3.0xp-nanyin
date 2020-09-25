/*
 * 文件名：MarketRiskSceneFactorTypeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：汪海
 * 修改时间：2019年9月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.stresstestmanage.marketriskscenemanage.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.systemtools.util.SysDatadictUtil;

/**
 * 〈一句话功能简述〉
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
	private static final long serialVersionUID = -9117284420849157554L;

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
	 * 获取 ftrTypeCode
	 * @return ftrTypeCode.
	 */
	public String getFtrTypeCode() {
		return ftrTypeCode;
	}
	
	/**
	 * 获取 ftrTypeCode字典值
	 * @return ftrTypeCode字典值.
	 */
	public String getFtrTypeValue() {
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
	 * 获取 ftrType2Code字典值
	 * @return ftrType2Code字典值.
	 */
	public String getFtrType2Value() {
		return SysDatadictUtil.getNameFromDataDict(DictCode.因子二级分类, ftrType2Code);
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
		if(ftrType2Code.equals("Mrk0101")){
			return "债券收益率曲线，如：中债国债收益率曲线";
		}
		else if(ftrType2Code.equals("Mrk0102")){
			return "各基准利率行情，如：FR007";
		}
		else if(ftrType2Code.equals("Mrk0201")){
			return "各货币对的汇率行情，如：USC/CNY的汇率";
		}
		else if(ftrType2Code.equals("Mrk0301")){
			return "各股市指数，如：沪深300指数";
		}else if(ftrType2Code.equals("Mrk0302")){
			return "中证转债指数、中证货币型基金指数、中证普通混合型基金指数";
		}else{
			return remark;
		}
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
	
}
