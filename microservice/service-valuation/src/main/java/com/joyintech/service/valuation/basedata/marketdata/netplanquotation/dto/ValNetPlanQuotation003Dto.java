/*
 * 文件名：ValNetPlanQuotation003Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.basedata.marketdata.netplanquotation.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

@PageId("ValNetPlanQuotation003")
public class ValNetPlanQuotation003Dto extends BaseEntity implements ProcessEntity{
	/**
     * <意义，目的和功能，以及可能被用到的地方>
     */
	private static final long serialVersionUID = 1L;
	
	/**
     * 资产代码
     */
    @PrimaryKey(value=Field.PK_1)
	@NotBlank(message = "资产代码不能为空！")
    private String finprodId;
	
	 /**
     * 资产名称
     */
    private String finprodAbbr;
    
    
    /**
     * 估值日期
     */
    @PrimaryKey(value=Field.PK_1)
	@NotBlank(message = "估值日期不能为空！")
    private String valueDate;
    
    /**
     * 资产净值(估值)
     */
    private BigDecimal valueAsset;
    
    /**
     * 金融产品类型
     */
    private String finprodType;
    
    /**
     * 公布日期
     */
    private Date publishDate;
    
    /**
     * 录入方式
     */
    private String inputType;
    
    /**
     * 处理操作
     */
    private String operateType;

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
	 * 获取 valueDate
	 * @return valueDate.
	 */
	public String getValueDate() {
	 return DateUtil.format(DateUtil.parse(valueDate),"yyyy-MM-dd");
	}

	/**
	 * 设置 valueDate
	 * @param valueDate valueDate
	 */
	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
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

	/**
	 * 获取 finprodType
	 * @return finprodType.
	 */
	public String getFinprodType() {
		return finprodType;
	}

	/**
	 * 设置 finprodType
	 * @param finprodType finprodType
	 */
	public void setFinprodType(String finprodType) {
		this.finprodType = finprodType;
	}

	/**
	 * 获取 publishDate
	 * @return publishDate.
	 */
	public Date getPublishDate() {
		return publishDate;
	}

	/**
	 * 设置 publishDate
	 * @param publishDate publishDate
	 */
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	/**
	 * 获取 inputType
	 * @return inputType.
	 */
	public String getInputType() {
		return this.inputType;
	}

	/**
	 * 设置 inputType
	 * @param inputType inputType
	 */
	public void setInputType(String inputType) {
		this.inputType = inputType;
	}

	/**
	 * 获取 operateType
	 * @return operateType.
	 */
	public String getOperateType() {
		return operateType;
	}

	/**
	 * 设置 operateType
	 * @param operateType operateType
	 */
	public void setOperateType(String operateType) {
		this.operateType = operateType;
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
    
	public String getValueDateFmt(){
        if(StringUtil.isNotEmpty(valueDate)){
            return DateUtil.format(DateUtil.parse(valueDate),"yyyy-MM-dd");
        }       
        return "";
    }
}
