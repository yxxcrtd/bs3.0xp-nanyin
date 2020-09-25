/*
 * 文件名：FinProductModule002Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年9月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.productelement.module.dto;


import com.joyintech.tams.common.base.entity.FinProduct;

/**
 * 产品模板基本信息
 * 
 * @author 何阳阳
 * @version 1.0 2018年9月17日
 * @see FinProductModuleInfoDto
 * @since 1.0
 */
public class FinProductModuleInfoDto extends FinProduct {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    /**
     * 金融产品代码
     */
    private String finprodId;
    /**
     * 模板代码
     */
    private String templateId;

    /**
     * 模板名称
     */
    private String templateName;

    /**
     * 处理模式
     */
    private String dealMode;

    /**
     * 收益类型
     */
    private String profitType;

    /**
     * 利率类型
     */
    private String rateType;

    /**
     * 是否周期型
     */
    private String isCycle;

    /**
     * 是否分层
     */
    private String isLay;

    /**
     * 分层类型
     */
    private String layType;

    /**
     * 产品收益标识
     */
    private String profitFlag;

    /**
     * 发行方式
     */
    private String issueMode;

    /**
     * 分行
     */
    private String branchBank;

    /**
     * 封闭期限
     */
    private String closedPeriod;
    
    /**
     * 交易日历
     */
    private String calendarId;
    
    /**
     * 计息基础
     */
    private String basis;
    
    /**
     * 币种
     */
    private String ccy;
    
    /**
     * 申赎确认天数
     */
    private String purRedDate;
    
    /**
     * 投资性质
     */
    private String investType;
    
    /**
     * 模板内容
     */
    private String templateContent;
    
    /**
     * 销售模式
     */
    private String saleMode;
    
    public String getFinprodId() {
        return finprodId;
    }

    
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getTemplateContent() {
		return templateContent;
	}


	public void setTemplateContent(String templateContent) {
		this.templateContent = templateContent;
	}


    public String getTemplateId() {
		return templateId;
	}


	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}


	public String getTemplateName() {
		return templateName;
	}


	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}


	/**
     * 获取 dealMode
     * 
     * @return dealMode.
     */
    public String getDealMode() {
        return dealMode;
    }

    /**
     * 设置dealMode
     * 
     * @param dealMode dealMode
     */
    public void setDealMode(String dealMode) {
        this.dealMode = dealMode;
    }

    /**
     * 获取 profitType
     * 
     * @return profitType.
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 设置 profitType
     * 
     * @param profitType profitType
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

    /**
     * 获取 rateType
     * 
     * @return rateType.
     */
    public String getRateType() {
        return rateType;
    }

    /**
     * 设置 rateType
     * 
     * @param rateType rateType
     */
    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    /**
     * 获取 isCycle
     * 
     * @return isCycle.
     */
    public String getIsCycle() {
        return isCycle;
    }

    /**
     * 设置 isCycle
     * 
     * @param isCycle isCycle
     */
    public void setIsCycle(String isCycle) {
        this.isCycle = isCycle;
    }

    /**
     * 获取 isLay
     * 
     * @return isLay.
     */
    public String getIsLay() {
        return isLay;
    }

    /**
     * 设置 isLay
     * 
     * @param isLay isLay
     */
    public void setIsLay(String isLay) {
        this.isLay = isLay;
    }

    /**
     * 获取 layType
     * 
     * @return layType.
     */
    public String getLayType() {
        return layType;
    }

    /**
     * 设置 layType
     * 
     * @param layType layType
     */
    public void setLayType(String layType) {
        this.layType = layType;
    }

    public String getProfitFlag() {
		return profitFlag;
	}

	public void setProfitFlag(String profitFlag) {
		this.profitFlag = profitFlag;
	}


	/**
     * 获取 issueMode
     * 
     * @return issueMode.
     */
    public String getIssueMode() {
        return issueMode;
    }

    /**
     * 设置 issueMode
     * 
     * @param issueMode issueMode
     */
    public void setIssueMode(String issueMode) {
        this.issueMode = issueMode;
    }

    /**
     * 获取 branchBank
     * 
     * @return branchBank.
     */
    public String getBranchBank() {
        return branchBank;
    }

    /**
     * 设置 branchBank
     * 
     * @param branchBank branchBank
     */
    public void setBranchBank(String branchBank) {
        this.branchBank = branchBank;
    }

    /**
     * 获取 closedPeriod
     * 
     * @return closedPeriod.
     */
    public String getClosedPeriod() {
        return closedPeriod;
    }

    /**
     * 设置 closedPeriod
     * 
     * @param closedPeriod closedPeriod
     */
    public void setClosedPeriod(String closedPeriod) {
        this.closedPeriod = closedPeriod;
    }

    
    /**
     * 获取 calendarId
     * @return calendarId.
     */
    public String getCalendarId() {
        return calendarId;
    }

    
    /**
     * 设置 calendarId
     * @param calendarId calendarId
     */
    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }

    
    /**
     * 获取 basis
     * @return basis.
     */
    public String getBasis() {
        return basis;
    }

    
    /**
     * 设置 basis
     * @param basis basis
     */
    public void setBasis(String basis) {
        this.basis = basis;
    }

    
    /**
     * 获取 ccy
     * @return ccy.
     */
    public String getCcy() {
        return ccy;
    }

    
    /**
     * 设置 ccy
     * @param ccy ccy
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    
    /**
     * 获取 purRedDate
     * @return purRedDate.
     */
    public String getPurRedDate() {
        return purRedDate;
    }

    
    /**
     * 设置 purRedDate
     * @param purRedDate purRedDate
     */
    public void setPurRedDate(String purRedDate) {
        this.purRedDate = purRedDate;
    }

    
    /**
     * 获取 investType
     * @return investType.
     */
    public String getInvestType() {
        return investType;
    }

    
    /**
     * 设置 investType
     * @param investType investType
     */
    public void setInvestType(String investType) {
        this.investType = investType;
    }

    
    /**
     * 获取 saleMode
     * @return saleMode.
     */
    public String getSaleMode() {
        return saleMode;
    }

    
    /**
     * 设置 saleMode
     * @param saleMode saleMode
     */
    public void setSaleMode(String saleMode) {
        this.saleMode = saleMode;
    }
}
