package com.joyintech.service.product.messageadjust.supplement.dto;

import java.math.BigDecimal;

import com.joyintech.tams.common.base.entity.FinFloatAccrualSche;

public class FinSupplementPlanChgDto extends FinFloatAccrualSche {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 行号
	 */
	private BigDecimal rn;
	
	/**
	 * 金融产品代码
	 */
	private String finprodId;
	
	/**
	 * 市场代码
	 */
	private String finprodMarketId;
	
	/**
	 * 金融产品简称
	 */
	private String finprodAbbr;
	
	/**
	 * 现金流代码
	 */
	private String cashId;
	
	/**
	 * 回补状态
	 */
	private String supplementStatus;
	
	/**
	 * 是否分层
	 */
	private String isLay;
	
	/**
	 * 产品款型
	 */
	private String prodStyle;

	
	/**
     * 分层子产品代码
     */
    private String layFinprodId;

    
    /**
     * 计划是否有效
     */
    private String  isAvailable;

   


    
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
     * 获取 finprodMarketId
     * @return finprodMarketId.
     */
    public String getFinprodMarketId() {
        return finprodMarketId;
    }


    
    /**
     * 设置 finprodMarketId
     * @param finprodMarketId finprodMarketId
     */
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
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
     * 获取 supplementStatus
     * @return supplementStatus.
     */
    public String getSupplementStatus() {
        return supplementStatus;
    }


    
    /**
     * 设置 supplementStatus
     * @param supplementStatus supplementStatus
     */
    public void setSupplementStatus(String supplementStatus) {
        this.supplementStatus = supplementStatus;
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


    
    /**
     * 获取 layFinprodId
     * @return layFinprodId.
     */
    public String getLayFinprodId() {
        return layFinprodId;
    }


    
    /**
     * 设置 layFinprodId
     * @param layFinprodId layFinprodId
     */
    public void setLayFinprodId(String layFinprodId) {
        this.layFinprodId = layFinprodId;
    }



    /**
     * 获取 isAvailable
     * @return isAvailable.
     */
    public String getIsAvailable() {
        return isAvailable;
    }



    /**
     * 设置 isAvailable
     * @param isAvailable isAvailable
     */
    public void setIsAvailable(String isAvailable) {
        this.isAvailable = isAvailable;
    }



    /**
     * 获取 rn
     * @return rn.
     */
    public BigDecimal getRn() {
        return rn;
    }



    /**
     * 设置 rn
     * @param rn rn
     */
    public void setRn(BigDecimal rn) {
        this.rn = rn;
    }
    


}
