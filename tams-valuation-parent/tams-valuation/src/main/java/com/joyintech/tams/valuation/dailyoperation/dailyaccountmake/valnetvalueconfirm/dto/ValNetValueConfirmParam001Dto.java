/*
 * 文件名：ValNetValueConfirmParam001Dto.java
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
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.validator.ValNetValueConfirmParam001Validator;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 净值确认下发修改传参实体类
 * @author hechuan
 * @version 1.0 2019年10月9日
 * @see ValNetValueConfirmParam001Dto
 * @since 1.0
 */
@ValidLogic(targetClass = ValNetValueConfirmParam001Validator.class)
public class ValNetValueConfirmParam001Dto extends BaseEntity implements ProcessEntity {
    
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -701296730056782555L;

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
     * 确认状态                         
     */
    private String confirmStatusCode;
    
    /**
     * 净值下发确认勾选的数据json字符串
     */
    private String data;
    
    /**
     * 理财份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal capiTal;

    
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
     * 获取 confirmStatusCode
     * @return confirmStatusCode.
     */
	public String getConfirmStatusCode() {
		return confirmStatusCode;
	}

	/**
     * 设置 confirmStatusCode
     * @param confirmStatusCode confirmStatusCode
     */
	public void setConfirmStatusCode(String confirmStatusCode) {
		this.confirmStatusCode = confirmStatusCode;
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
	
    
}
