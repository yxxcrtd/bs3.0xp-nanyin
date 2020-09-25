/*
 * 文件名：CreditLinesDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.risk.creditmanagement.ceorg.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.tams.common.base.entity.CreditMstInfo;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.framework.grid.GridRowDto;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.StringUtil;

/**
 * 
 * 机构授信实体
 * @author 裴家俊
 * @version 1.0 2018年10月23日
 * @see CreditMstInfoDto
 * @since 1.0
 */
public class CreditMstInfoDto  extends CreditMstInfo implements GridRowDto {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 是否有子节点
     */
    private String hasChildren;

    /**
     * 行ID
     */
    private String rowId;
    
    /**
     * 父ID
     */
    private String upperCreditQuoteTypeCode;
    
    private String creditMstId;
    
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal creditAmt;
    
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal creditAmtInit;
    
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


	@Override
	public String getId() {
		return super.getCreditQuoteTypeCode();
	}


	@Override
	public void setId(String id) {
		super.setCreditQuoteTypeCode(id);
	}


	@Override
	public String getParentId() {
		return this.upperCreditQuoteTypeCode;
	}


	@Override
	public void setParentId(String parentId) {
		this.setUpperCreditQuoteTypeCode(parentId);
	}


	@Override
	public String getName() {
		return super.getCreditQuoteTypeValue();
	}


	@Override
	public void setName(String name) {
		this.setCreditQuoteTypeValue(name);
	}


	@Override
	public boolean isLeaf() {
		return StringUtil.equals(DictCodeList.是否_是.getValue(), hasChildren);
	}


	@Override
	public void setLeaf(boolean leaf) {
		
	}


	public String getHasChildren() {
		return hasChildren;
	}


	public void setHasChildren(String hasChildren) {
		this.hasChildren = hasChildren;
	}


	public String getUpperCreditQuoteTypeCode() {
		return upperCreditQuoteTypeCode;
	}


	public void setUpperCreditQuoteTypeCode(String upperCreditQuoteTypeCode) {
		this.upperCreditQuoteTypeCode = upperCreditQuoteTypeCode;
	}


	public String getCreditMstId() {
		return creditMstId;
	}


	public void setCreditMstId(String creditMstId) {
		this.creditMstId = creditMstId;
	}


	public BigDecimal getCreditAmt() {
		return creditAmt;
	}


	public void setCreditAmt(BigDecimal creditAmt) {
		this.creditAmt = creditAmt;
	}


	public BigDecimal getCreditAmtInit() {
		return creditAmtInit;
	}


	public void setCreditAmtInit(BigDecimal creditAmtInit) {
		this.creditAmtInit = creditAmtInit;
	}


}
