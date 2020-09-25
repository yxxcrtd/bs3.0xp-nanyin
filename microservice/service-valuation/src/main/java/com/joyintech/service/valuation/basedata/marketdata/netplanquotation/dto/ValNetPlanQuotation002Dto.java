/*
 * 文件名：ValNetPlanQuotation002Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.basedata.marketdata.netplanquotation.dto;

import java.math.BigDecimal;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

public class ValNetPlanQuotation002Dto extends BaseEntity {
	
	/**
     * <意义，目的和功能，以及可能被用到的地方>
     */
	private static final long serialVersionUID = 1L;
	 /**
     * 资产代码
     */
    private String finprodId;
    
    /**
     * 资产名称
     */
    private String finprodAbbr;
    
    /**
     * 行情日期
     */
    private String valueDate;
    
    /**
     * 数据来源
     */
    private String valueSource;   
   
  
    /**
     * 资产净值
     */
    private BigDecimal valueAsset;

	/**
	 * 获取 finprodId
	 * @return finprodId.
	 */
	public String getFinprodId() {
		return finprodId;
	}

	/**
	 * 设置 finprodId
	 * @param finprodId finprodId
	 */
	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}

	/**
	 * 获取 finprodAbbr
	 * @return finprodAbbr.
	 */
	public String getFinprodAbbr() {
		return finprodAbbr;
	}

	/**
	 * 设置 finprodAbbr
	 * @param finprodAbbr finprodAbbr
	 */
	public void setFinprodAbbr(String finprodAbbr) {
		this.finprodAbbr = finprodAbbr;
	}


	/**
	 * 获取 valueDate
	 * @return valueDate.
	 */
	public String getValueDate() {
		return valueDate;
	}

	/**
	 * 设置 valueDate
	 * @param valueDate valueDate
	 */
	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}

	/**
	 * 获取 valueSource
	 * @return valueSource.
	 */
	public String getValueSource() {
		return valueSource;
	}

	/**
	 * 设置 valueSource
	 * @param valueSource valueSource
	 */
	public void setValueSource(String valueSource) {
		this.valueSource = valueSource;
	}

	/**
	 * 获取 valueAsset
	 * @return valueAsset.
	 */
	public BigDecimal getValueAsset() {
		return valueAsset;
	}

	/**
	 * 设置 valueAsset
	 * @param valueAsset valueAsset
	 */
	public void setValueAsset(BigDecimal valueAsset) {
		this.valueAsset = valueAsset;
	}
  
	public String getValueDateFmt(){
    	if(StringUtil.isNotEmpty(valueDate)){
    		return DateUtil.format(DateUtil.parse(valueDate),"yyyy-MM-dd");
    	}   	
    	return "";
    }
    
}
