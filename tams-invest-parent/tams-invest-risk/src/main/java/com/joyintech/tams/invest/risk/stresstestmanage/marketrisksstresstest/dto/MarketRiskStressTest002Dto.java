/*
 * 文件名：FlowRiskStressTest001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2019年8月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.stresstestmanage.marketrisksstresstest.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangkang
 * @version 1.0 2019年8月13日
 * @see MarketRiskStressTest002Dto
 * @since 1.0
 */
@PageId("MarketRiskStressTest002")
public class MarketRiskStressTest002Dto extends BaseEntity implements ProcessEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 932593004441017450L;
	
	/**
	 * 所选产品代码
	 */
	private String prodIds;
	/**
	 * 开始日期
	 */
	private Date startDate;
	/**
	 * 结束日期
	 */
	private Date endDate;
	/**
	 * 基准日期
	 */
	private Date baseDate;
	
	/**
	 * 情景代码
	 */
	private String sceneId;
	/**
	 * 产品代码
	 */
	private String prodId;
	/**
	 * 产品名称
	 */
	private String prodName;
	/**
	 * 持仓
	 */
	private BigDecimal ccsl;
	/**
	 * 因子代码
	 */
	private String factorId;
	/**
	 * 因子名称
	 */
	private String ftrName;
	/**
	 * 因子值
	 */
	private String ftrValueStruct;
	
	/**
	 * 产品代码
	 * @return
	 */
	public String getProdIds() {
		return prodIds;
	}
	/**
	 * 产品代码
	 * @param prodIds
	 */
	public void setProdIds(String prodIds) {
		this.prodIds = prodIds;
	}
	/**
	 * 开始日期
	 * @return
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * 开始日期
	 * @param startDate
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * 结束日期
	 * @return
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * 结束日期
	 * @param endDate
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * 基准日期
	 * @return
	 */
	public Date getBaseDate() {
		return baseDate;
	}
	/**
	 * 基准日期
	 * @param baseDate
	 */
	public void setBaseDate(Date baseDate) {
		this.baseDate = baseDate;
	}
	/**
	 * 情景代码
	 * @return
	 */
	public String getSceneId() {
		return sceneId;
	}
	/**
	 * 情景代码
	 * @param sceneId
	 */
	public void setSceneId(String sceneId) {
		this.sceneId = sceneId;
	}
	/**
	 * 产品代码
	 * @return
	 */
	public String getProdId() {
		return prodId;
	}
	/**
	 * 产品代码
	 * @param prodId
	 */
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	/**
	 * 产品名称
	 * @return
	 */
	public String getProdName() {
		return prodName;
	}
	/**
	 * 产品名称
	 * @param prodName
	 */
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	/**
	 * 持仓
	 * @return
	 */
	public BigDecimal getCcsl() {
		return ccsl;
	}
	/**
	 * 持仓
	 * @param ccsl
	 */
	public void setCcsl(BigDecimal ccsl) {
		this.ccsl = ccsl;
	}
	/**
	 * 因子代码
	 * @return
	 */
	public String getFactorId() {
		return factorId;
	}
	/**
	 * 因子代码
	 * @param factorId
	 */
	public void setFactorId(String factorId) {
		this.factorId = factorId;
	}
	/**
	 * 因子名称
	 * @return
	 */
	public String getFtrName() {
		return ftrName;
	}
	/**
	 * 因子名称
	 * @param ftrName
	 */
	public void setFtrName(String ftrName) {
		this.ftrName = ftrName;
	}
	/**
	 * 因子值
	 * @return
	 */
	public String getFtrValueStruct() {
		return ftrValueStruct;
	}
	/**
	 * 因子值
	 * @param ftrValueStruct
	 */
	public void setFtrValueStruct(String ftrValueStruct) {
		this.ftrValueStruct = ftrValueStruct;
	}
	
	public String getFacStatus(){
//		if(!StringUtil.isEmpty(getFtrValueStruct())){
//			if(StringUtil.equals("0", getFtrValueStruct())){
//				return "未准备";
//			}
//			
//		}
		return "未准备";
	}
    
}
