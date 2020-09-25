/*
 * 文件名：FinProductRateQuotationDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.messageadjust.finproductratequotation.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.FinProduct;

/**
 * 信息调整管理_产品费率报价管理Dto
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年10月11日
 * @see FinProductRateQuotationDto
 * @since 1.0
 */
public class FinProductRateQuotationDto extends FinProduct{
    
    /**
     * 费用名称
     */
    private String feeName;
    
    /**
     * 费用品种
     */
    private String feeType;
    
    /**
     * 费率
     */
    private BigDecimal yield;
    
    /**
     * 报价日
     */
    private Date relaeseDate;
    
    /**
     * 生效日
     */
    private Date effDate;
    
    /**
     * 
     */
    private Date effDateOld;
    
    /**
     * 现金流代码
     */
    private String cashId;
    
    /**
     * 现金流代码
     */
    private String cashIdStr;
    
    /**
     * 是否初始利率
     */
    private String isInitial;
    
    /**
     * 操作类型
     */
    private String operateType;
    
    /**
     * 
     */
    private String elementId;
    
    /**
     * 
     */
    private List<FinProductRateQuotationDto> selectedItem;
    
    /**
     * 产品款型
     */
    private String prodStyle;
    
    /**
     * 获取 feeName
     * @return feeName.
     */
    public String getFeeName() {
        return feeName;
    }

    
    /**
     * 设置 feeName
     * @param feeName feeName
     */
    public void setFeeName(String feeName) {
        this.feeName = feeName;
    }

    
    /**
     * 获取 feeType
     * @return feeType.
     */
    public String getFeeType() {
        return feeType;
    }

    
    /**
     * 设置 feeType
     * @param feeType feeType
     */
    public void setFeeType(String feeType) {
        this.feeType = feeType;
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
     * 获取 relaeseDate
     * @return relaeseDate.
     */
    public Date getRelaeseDate() {
        return relaeseDate;
    }

    
    /**
     * 设置 relaeseDate
     * @param relaeseDate relaeseDate
     */
    public void setRelaeseDate(Date relaeseDate) {
        this.relaeseDate = relaeseDate;
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
     * 获取 cashId
     * @return cashId.
     */
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
     * 获取 isInitial
     * @return isInitial.
     */
    public String getIsInitial() {
        return isInitial;
    }

    
    /**
     * 设置 isInitial
     * @param isInitial isInitial
     */
    public void setIsInitial(String isInitial) {
        this.isInitial = isInitial;
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
     * 获取 selectItem
     * @return selectItem.
     */
    public List<FinProductRateQuotationDto> getSelectedItem() {
        return selectedItem;
    }


    
    /**
     * 设置 selectItem
     * @param selectItem selectItem
     */
    public void setSelectedItem(List<FinProductRateQuotationDto> selectedItem) {
        this.selectedItem = selectedItem;
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


    
    /**
     * 获取 cashIdStr
     * @return cashIdStr.
     */
    public String getCashIdStr() {
        return cashIdStr;
    }


    
    /**
     * 设置 cashIdStr
     * @param cashIdStr cashIdStr
     */
    public void setCashIdStr(String cashIdStr) {
        this.cashIdStr = cashIdStr;
    }


    
    /**
     * 获取 prodStyle
     * @return prodStyle.
     */
    public String getProdStyle() {
        return prodStyle;
    }


    
    /**
     * 设置 prodStyle
     * @param prodStyle prodStyle
     */
    public void setProdStyle(String prodStyle) {
        this.prodStyle = prodStyle;
    }
    
    
}
