package com.joyintech.tams.invest.risk.creditmanagement.cemonitor.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.tams.common.base.entity.CreditLines;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.grid.GridRowDto;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.StringUtil;

/**
 * @description: CreditLinesDealDto
 * @author: 裴家俊
 * @create: 2019-01-28 
 **/
@PageId("CeMonitorReport001")
public class CreditLinesDealDto extends CreditLines implements GridRowDto{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 /**
     * 组合机构代码
     */
    private String insitituteCode;
    /**
     * 机构名称/资产名称/系列名称
     */
    private String customerName;
    
    /**
     * 组合机构代码
     */
    private String creditQuoteTypeValue;
    /**
     * 已使用金额(直接投资)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal usedAmt;
    /**
     * 已使用金额(委托投资)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal usedEnstustAmt;
    /**
     * 已使用金额(合计)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal usedAmtTotal;
    /**
     * 剩余金额(亿元)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal remainAmt;
    
    /**
     * 是否有子节点
     */
    private String hasChildren;

    /**
     * 行ID
     */
    private String rowId;

    /**
     * 父节点
     */
    private String parentInfo;
    /**
     * 子节点
     */
    private String childInfo;
    
	public String getParentInfo() {
		return parentInfo;
	}
	public void setParentInfo(String parentInfo) {
		this.parentInfo = parentInfo;
	}
	public String getChildInfo() {
		return childInfo;
	}
	public void setChildInfo(String childInfo) {
		this.childInfo = childInfo;
	}
	public String getInsitituteCode() {
		return insitituteCode;
	}
	public void setInsitituteCode(String insitituteCode) {
		this.insitituteCode = insitituteCode;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public BigDecimal getUsedAmt() {
		return usedAmt;
	}
	public void setUsedAmt(BigDecimal usedAmt) {
		this.usedAmt = usedAmt;
	}
	public BigDecimal getUsedEnstustAmt() {
		return usedEnstustAmt;
	}
	public void setUsedEnstustAmt(BigDecimal usedEnstustAmt) {
		this.usedEnstustAmt = usedEnstustAmt;
	}
	public BigDecimal getUsedAmtTotal() {
		return usedAmtTotal;
	}
	public void setUsedAmtTotal(BigDecimal usedAmtTotal) {
		this.usedAmtTotal = usedAmtTotal;
	}
	public BigDecimal getRemainAmt() {
		return remainAmt;
	}
	public void setRemainAmt(BigDecimal remainAmt) {
		this.remainAmt = remainAmt;
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
	@Override
	public String getId() {
		return this.getCustomerId();
	}
	@Override
	public void setId(String id) {
		this.setCustomerId(id);
	}
	@Override
	public String getParentId() {
		return this.getParentInfo();
	}
	@Override
	public void setParentId(String parentId) {
		this.setParentInfo(parentId);
	}
	@Override
	public String getName() {
		return getCustomerName();
	}
	@Override
	public void setName(String name) {
		this.setCustomerName(name);
	}
	@Override
	public boolean isLeaf() {
		return StringUtil.equals(DictCodeListExt1.是或否_是.getValue(), hasChildren);
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
	public String getCreditQuoteTypeValue() {
		return creditQuoteTypeValue;
	}
	public void setCreditQuoteTypeValue(String creditQuoteTypeValue) {
		this.creditQuoteTypeValue = creditQuoteTypeValue;
	}
    
}
