/*
 * 文件名：ValNetValueConfirm004Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：hechuan
 * 修改时间：2019年9月23日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.validator.ValNetValueConfirm004Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 净值确认下发修改传参实体类
 * @author hechuan
 * @version 1.0 2019年10月9日
 * @see ValNetValueConfirm004Dto
 * @since 1.0
 */
@PageId("ValNetValueConfirm004")
@ValidLogic(targetClass = ValNetValueConfirm004Validator.class)
public class ValNetValueConfirm004Dto extends BaseEntity implements ProcessEntity {
    
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 4906727767758397076L;

	/**
     * 操作类型
     */
    private String operateType;
    
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String pageModel;

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String elementId;
    
    /**
     * 净值下发确认勾选的数据json字符串
     */
    private String data;
    
    /**
     * 单位净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal netUnitValue;
    
    /**
     * 修改后单位净值
     */
    @NotEmpty(fieldName = "修改后单位净值")
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal netUnitValueEdit;

    /**
     * 修改后总净值
     */
    @NotEmpty(fieldName = "修改后总净值")
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal assetValueEdit;
    
    /**
     * 理财份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal capiTal;
    
    /**
     * 产品代码
     */
    @PrimaryKey(value=Field.PK_1)
    private String finprodId;
    
    /**
     * 估值日期                 
     */
    @PrimaryKey(value=Field.PK_2)
    private String valDate;
    
    /**
     * 估值表余额表标志：01：估值表；02：余额表                         
     */
    private String valBalFlagCode;
    
    /**
     * 产品类型
     */
    private String  profitType;
    
    /**
     * 产品类型
     */
    private String  profitTypeCode;
    
    
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
     * 获取 pageModel
     * @return pageModel.
     */
    public String getPageModel() {
        return pageModel;
    }

    /**
     * 设置 pageModel
     * @param pageModel pageModel
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    /**
     * 获取 elementId
     * @return elementId.
     */
    public String getElementId() {
        return elementId;
    }

    /**
     * 设置 elementId
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

	/**
     * 获取 data
     * @return data.
     */
	public String getData() {
		return data;
	}

	/**
     * 设置 data
     * @param data data
     */
	public void setData(String data) {
		this.data = data;
	}

	/**
     * 获取 netUnitValue
     * @return netUnitValue.
     */
	public BigDecimal getNetUnitValue() {
		return netUnitValue;
	}

	/**
     * 设置 netUnitValue
     * @param netUnitValue netUnitValue
     */
	public void setNetUnitValue(BigDecimal netUnitValue) {
		this.netUnitValue = netUnitValue;
	}

	/**
     * 获取 netUnitValueEdit
     * @return netUnitValueEdit.
     */
	public BigDecimal getNetUnitValueEdit() {
		return netUnitValueEdit;
	}

	/**
     * 设置 netUnitValueEdit
     * @param netUnitValueEdit netUnitValueEdit
     */
	public void setNetUnitValueEdit(BigDecimal netUnitValueEdit) {
		this.netUnitValueEdit = netUnitValueEdit;
	}

	/**
     * 获取 assetValueEdit
     * @return assetValueEdit.
     */
	public BigDecimal getAssetValueEdit() {
		return assetValueEdit;
	}

	/**
     * 设置 assetValueEdit
     * @param assetValueEdit assetValueEdit
     */
	public void setAssetValueEdit(BigDecimal assetValueEdit) {
		this.assetValueEdit = assetValueEdit;
	}

	/**
     * 获取 capiTal
     * @return capiTal.
     */
	public BigDecimal getCapiTal() {
		return capiTal;
	}

	/**
     * 设置 capiTal
     * @param capiTal capiTal
     */
	public void setCapiTal(BigDecimal capiTal) {
		this.capiTal = capiTal;
	}

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
     * 获取 valDate
     * @return valDate.
     */
	public String getValDate() {
		return valDate;
	}

	/**
     * 设置 valDate
     * @param valDate valDate
     */
	public void setValDate(String valDate) {
		this.valDate = valDate;
	}
	
	/**
     * 获取 valBalFlagCode
     * @return valBalFlagCode.
     */
    public String getValBalFlagCode() {
        return valBalFlagCode;
    }
    
    /**
     * 设置 valBalFlagCode
     * @param valBalFlagCode valBalFlagCode
     */
    public void setValBalFlagCode(String valBalFlagCode) {
        this.valBalFlagCode = valBalFlagCode;
    }
    
    /**
     * 获取 profitType
     * @return profitType.
     */
	public String getProfitType() {
		return profitType;
	}

	/**
     * 设置 profitType
     * @param profitType profitType
     */
	public void setProfitType(String profitType) {
		this.profitType = profitType;
	}

	/**
     * 获取 profitTypeCode
     * @return profitTypeCode.
     */
	public String getProfitTypeCode() {
		return profitTypeCode;
	}

	/**
     * 设置 profitTypeCode
     * @param profitTypeCode profitTypeCode
     */
	public void setProfitTypeCode(String profitTypeCode) {
		this.profitTypeCode = profitTypeCode;
	}
    
}
