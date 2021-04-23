/*
 * 文件名：BondQuoteImportPreviewDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Jiaoxujin
 * 修改时间：2020年5月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.bankuplink.bond.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 现卷报价导入预览对象
 * 
 * @author Jiaoxujin
 * @version 1.0 2020年5月23日
 * @see BondQuoteImportPreviewDto
 * @since 1.0
 */
public class BondQuoteImportPreviewDto extends CfetsBondDealEntrustOrderDto  implements ProcessEntity{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -3775754130971918093L;

    /**
     * 错误信息
     */
    private String validationErrorMsg;

    /**
     * 预警信息
     */
    private String validationWarnMsg;

    /**
     * 有错误的字段名称
     */
    private List<String> inValidFieldNames;
    
    /**
     * 应计利息
     */
    private BigDecimal unitInt;
    
    /**
     * 全价
     */
    private BigDecimal unitFprice;
    
    /**
     * 净价总额
     */
    private BigDecimal cpriceAmt;
    
    /**
     * 应计利息总额
     */
    private BigDecimal intAmt;
    
    /**
     * 全价总额
     */
    private BigDecimal fpriceAmt;
    
    
	/**
	 * 操作类型
	 */
	private String operateType;

	/**
	 * 
	 */
	private String pageModel;
	
    /**
     * 每百元金额(元)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal parValue;
    
    /**
     * 本金额(元)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal parValueAmt;
    
    private List<BondQuoteImportPreviewDto> bondQuoteImportPreviewDtoList;
    
    private List<String> counterIdList;

    /**
     * 获取 validationErrorMsg
     * 
     * @return validationErrorMsg.
     */
    public String getValidationErrorMsg() {
        return validationErrorMsg;
    }

    /**
     * 设置 validationErrorMsg
     * 
     * @param validationErrorMsg validationErrorMsg
     */
    public void setValidationErrorMsg(String validationErrorMsg) {
        this.validationErrorMsg = validationErrorMsg;
    }

    /**
     * 获取 validationWarnMsg
     * 
     * @return validationWarnMsg.
     */
    public String getValidationWarnMsg() {
        return validationWarnMsg;
    }

    /**
     * 设置 validationWarnMsg
     * 
     * @param validationWarnMsg validationWarnMsg
     */
    public void setValidationWarnMsg(String validationWarnMsg) {
        this.validationWarnMsg = validationWarnMsg;
    }

    /**
     * 获取 inValidFieldName
     * @return inValidFieldName.
     */
    public List<String> getInValidFieldNames() {
        return inValidFieldNames;
    }

    /**
     * 设置 inValidFieldName
     * @param inValidFieldName inValidFieldName
     */
    public void setInValidFieldNames(List<String> inValidFieldNames) {
        this.inValidFieldNames = inValidFieldNames;
    }

    public void addValidFieldName(String invalidFieldName) {
        if (this.inValidFieldNames == null) {
            this.inValidFieldNames = new ArrayList<>();
        }
        this.inValidFieldNames.add(invalidFieldName);
    }

	public BigDecimal getUnitInt() {
		return unitInt;
	}

	public void setUnitInt(BigDecimal unitInt) {
		this.unitInt = unitInt;
	}

	public BigDecimal getUnitFprice() {
		return unitFprice;
	}

	public void setUnitFprice(BigDecimal unitFprice) {
		this.unitFprice = unitFprice;
	}

	public BigDecimal getCpriceAmt() {
		return cpriceAmt;
	}

	public void setCpriceAmt(BigDecimal cpriceAmt) {
		this.cpriceAmt = cpriceAmt;
	}

	public BigDecimal getIntAmt() {
		return intAmt;
	}

	public void setIntAmt(BigDecimal intAmt) {
		this.intAmt = intAmt;
	}

	public BigDecimal getFpriceAmt() {
		return fpriceAmt;
	}

	public void setFpriceAmt(BigDecimal fpriceAmt) {
		this.fpriceAmt = fpriceAmt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getPageModel() {
		return pageModel;
	}

	public void setPageModel(String pageModel) {
		this.pageModel = pageModel;
	}

	public BigDecimal getParValue() {
		return parValue;
	}

	public void setParValue(BigDecimal parValue) {
		this.parValue = parValue;
	}

	public BigDecimal getParValueAmt() {
		return parValueAmt;
	}

	public void setParValueAmt(BigDecimal parValueAmt) {
		this.parValueAmt = parValueAmt;
	}

	public List<BondQuoteImportPreviewDto> getBondQuoteImportPreviewDtoList() {
		return bondQuoteImportPreviewDtoList;
	}

	public void setBondQuoteImportPreviewDtoList(List<BondQuoteImportPreviewDto> bondQuoteImportPreviewDtoList) {
		this.bondQuoteImportPreviewDtoList = bondQuoteImportPreviewDtoList;
	}

	public List<String> getCounterIdList() {
		return counterIdList;
	}

	public void setCounterIdList(List<String> counterIdList) {
		this.counterIdList = counterIdList;
	}
	
	
	
	
}
