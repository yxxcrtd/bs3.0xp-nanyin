/*
 * 文件名：FinProductQuotation002Dto.java
 * add：wangkang
 * 修改时间：2018年10月9日
 */

package com.joyintech.tams.invest.product.messageadjust.finproductquotation.dto;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.invest.product.messageadjust.finproductquotation.validator.FinProductQuotation002Validator;
import com.joyintech.workflow.engine.dto.ProcessEntity;
/**
 * 报价新建
 * 
 * @author zhangliang
 * @version 1.0 2018年11月28日
 * @see FinProductQuotation002Dto
 * @since 1.0
 */
@PageId("FinProductQuotation002")
@ValidLogic(targetClass = FinProductQuotation002Validator.class)
public class FinProductQuotation002Dto extends FinProduct implements ProcessEntity{

	/**
	 * 
	 */
    private static final long serialVersionUID = -3364633720300166637L;
	/**
	 * 销售部门
	 */
    private String saleDepartment;
	/**
	 * 生效日
	 */
    private Date effDate;
    /**
     * 修改之前的生效日
     */
    private Date effDateOld;
	/**
	 * 报价日
	 */
    private Date releaseDate;
	/**
	 * 收益率/单位净值/万份收益
	 */
    private BigDecimal yield;
	/**
	 * 七日年化收益率
	 */
    private BigDecimal day7YearYield;
	/**
	 * 年化收益率
	 */
    private BigDecimal yearYield;
	/**
	 * 累计净值
	 */
    private BigDecimal totalValue;
	/**
	 * 报价来源
	 */
    private String inputType;
	/**
	 * 现金流代码
	 */
    private String cashId;
	
	/**
	 * 操作类型
	 */
    private String operateType;
	/**
	 * 元素Id
	 */
    private String elementId;
	/**
	 * 是否分层
	 */
    private String isLay;
	/**
	 * 客户等级
	 */
    private String customLevel;
    /**
     * 单位净值/万份收益
     */
    private BigDecimal valuation;
	/**
	 * 
	 */
    private String pageModel;
    
    /**
     * 对象list
     */
    @Valid
    private List<FinProductQuotation003Dto> layTypeInfoList;
    
	/**
	 * 
	 * {@inheritDoc}
	 */
    @PrimaryKey(value=Field.PK_1)
    public String getFinprodId() {
        return super.getFinprodId();
    }
	/**
     * 获取 saleDepartment
     * @return saleDepartment.
     */
    public String getSaleDepartment() {
        return saleDepartment;
    }
    /**
     * 设置 saleDepartment
     * @param saleDepartment saleDepartment
     */
    public void setSaleDepartment(String saleDepartment) {
        this.saleDepartment = saleDepartment;
    }
    
    @PrimaryKey(value=Field.PK_2)
    public String getEffDateStr(){
        return DateUtil.format(this.getEffDate());
    }
    
    /**
     * 获取 effDate
     * @return effDate.
     */
    public Date getEffDate() {
        return effDate;
    }
    /**
     * 设置 effDate
     * @param effDate effDate
     */
    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }
    /**
     * 获取 releaseDate
     * @return releaseDate.
     */
    public Date getReleaseDate() {
        return releaseDate;
    }
    
    public String getReleaseDateStr(){
        return DateUtil.format(this.getReleaseDate());
    }
    /**
     * 设置 releaseDate
     * @param releaseDate releaseDate
     */
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
    /**
     * 获取 yield
     * @return yield.
     */
    public BigDecimal getYield() {
        return yield;
    }
    /**
     * 设置 yield
     * @param yield yield
     */
    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }
    /**
     * 获取 day7YearYield
     * @return day7YearYield.
     */
    public BigDecimal getDay7YearYield() {
        return day7YearYield;
    }
    /**
     * 设置 day7YearYield
     * @param day7YearYield day7YearYield
     */
    public void setDay7YearYield(BigDecimal day7YearYield) {
        this.day7YearYield = day7YearYield;
    }
    /**
     * 获取 yearYield
     * @return yearYield.
     */
    public BigDecimal getYearYield() {
        return yearYield;
    }
    /**
     * 设置 yearYield
     * @param yearYield yearYield
     */
    public void setYearYield(BigDecimal yearYield) {
        this.yearYield = yearYield;
    }
    /**
     * 获取 totalValue
     * @return totalValue.
     */
    public BigDecimal getTotalValue() {
        return totalValue;
    }
    /**
     * 设置 totalValue
     * @param totalValue totalValue
     */
    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }
    /**
     * 获取 inputType
     * @return inputType.
     */
    public String getInputType() {
        return inputType;
    }
    /**
     * 设置 inputType
     * @param inputType inputType
     */
    public void setInputType(String inputType) {
        this.inputType = inputType;
    }
    /**
     * 获取 cashId
     * @return cashId.
     */
    @PrimaryKey(value=Field.PK_3)
    public String getCashId() {
        return cashId;
    }
    /**
     * 设置 cashId
     * @param cashId cashId
     */
    public void setCashId(String cashId) {
        this.cashId = cashId;
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
     * 获取 isLay
     * @return isLay.
     */
    public String getIsLay() {
        return isLay;
    }
    /**
     * 设置 isLay
     * @param isLay isLay
     */
    public void setIsLay(String isLay) {
        this.isLay = isLay;
    }
    /**
     * 获取 customLevel
     * @return customLevel.
     */
    public String getCustomLevel() {
        return customLevel;
    }
    /**
     * 设置 customLevel
     * @param customLevel customLevel
     */
    public void setCustomLevel(String customLevel) {
        this.customLevel = customLevel;
    }
    /**
     * 获取 valuation
     * @return valuation.
     */
    public BigDecimal getValuation() {
        return valuation;
    }
    /**
     * 设置 valuation
     * @param valuation valuation
     */
    public void setValuation(BigDecimal valuation) {
        this.valuation = valuation;
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
     * 获取 layTypeInfoList
     * @return layTypeInfoList.
     */
    public List<FinProductQuotation003Dto> getLayTypeInfoList() {
        return layTypeInfoList;
    }
    
    /**
     * 设置 layTypeInfoList
     * @param layTypeInfoList layTypeInfoList
     */
    public void setLayTypeInfoList(List<FinProductQuotation003Dto> layTypeInfoList) {
        this.layTypeInfoList = layTypeInfoList;
    }
    
    /**
     * 获取 effDateOld
     * @return effDateOld.
     */
    public Date getEffDateOld() {
        return effDateOld;
    }
    
    /**
     * 设置 effDateOld
     * @param effDateOld effDateOld
     */
    public void setEffDateOld(Date effDateOld) {
        this.effDateOld = effDateOld;
    }
	
    
}
