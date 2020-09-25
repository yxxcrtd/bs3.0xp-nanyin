/*
 * 文件名：RskPortfolioIndicatorDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.risk.stresstestmanage.flowrisksstresstest.dto;

import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

/**
 * 
 * 组合关联指标基本信息
 * @author 裴家俊
 * @version 1.0 2018年10月23日
 * @see FlowRskPortfolioIndicatorDto
 * @since 1.0
 */
public class FlowRskPortfolioIndicatorDto  extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 监督指标代码
     */
    private String indId;
    /**
     * 组合层次
     */
    private String portfolioLevel;
    /**
     * 有效开始时间
     */
    private Date startDate;
    /**
     * 有效结束时间
     */
    private Date endDate;
    
    /**
     * 组合名称
     */
    private String portfolioName;
    /**
     * 指标名称
     */
    private String indName;
    /**
     * 组合成分
     */
    private String portfolioMix;
    
	public String getPortfolioId() {
		return portfolioId;
	}
	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}
	public String getIndId() {
		return indId;
	}
	public void setIndId(String indId) {
		this.indId = indId;
	}
	public String getPortfolioLevel() {
		return portfolioLevel;
	}
	public void setPortfolioLevel(String portfolioLevel) {
		this.portfolioLevel = portfolioLevel;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getPortfolioName() {
		return portfolioName;
	}
	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}
	public String getIndName() {
		return indName;
	}
	public void setIndName(String indName) {
		this.indName = indName;
	}
	public String getPortfolioMix() {
		return portfolioMix;
	}
	public void setPortfolioMix(String portfolioMix) {
		this.portfolioMix = portfolioMix;
	}
   
    
}
