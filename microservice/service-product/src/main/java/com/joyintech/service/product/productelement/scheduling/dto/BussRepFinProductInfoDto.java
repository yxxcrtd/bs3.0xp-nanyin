/*
 * 文件名：BussRepFinProductInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年9月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.productelement.scheduling.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.tams.common.base.entity.RepProdDeclare;

/**
 * 产品中债报备信息
 * @author 张亮
 * @version 1.0 2018年9月21日
 * @see BussRepFinProductInfoDto
 * @since 1.0
 */
public class BussRepFinProductInfoDto extends RepProdDeclare {

	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = -3615036090702766860L;

	/**
	 * 平均预计客户年收益率
	 */
	private BigDecimal repExpAvgYield;
	
	/**
	 * 收益率分档情况说明
	 */
	private String repYieldComment;
	
	/**
	 * 规律开放周期
	 */
	private String repOpenCircle;
	
	/**
	 * 其他规律开放周期
	 */
	private Integer repOpenCircleDays;
	
	/**
	 * 无规律开放说明
	 */
	private String repRuleComment;
	
	/**
	 * 首次开放周期起始日
	 */
	private Date repFirstOpenDay;
	
	/**
	 * 平均开放次数（年化）
	 */
	private BigDecimal repAvgOpenCount;
	
	/**
	 * 开放期业务
	 */
	private String repOpenBuss;
	
	/**
	 * 开放期业务说明
	 */
	private String repOpenBussRemark;
	
	/**
	 * 开放类型
	 */
	private String openType;
	
	/**
	 * 开放模式
	 */
	private String openMode;
	
	/**
	 * 画面来源
	 */
	private String pageFrom;

	private String isMerger;
	
	public BigDecimal getRepExpAvgYield() {
		return repExpAvgYield;
	}

	public void setRepExpAvgYield(BigDecimal repExpAvgYield) {
		this.repExpAvgYield = repExpAvgYield;
	}

	public String getRepYieldComment() {
		return repYieldComment;
	}

	public void setRepYieldComment(String repYieldComment) {
		this.repYieldComment = repYieldComment;
	}

	public String getRepOpenCircle() {
		return repOpenCircle;
	}

	public void setRepOpenCircle(String repOpenCircle) {
		this.repOpenCircle = repOpenCircle;
	}

	public Integer getRepOpenCircleDays() {
		return repOpenCircleDays;
	}

	public void setRepOpenCircleDays(Integer repOpenCircleDays) {
		this.repOpenCircleDays = repOpenCircleDays;
	}

	public String getRepRuleComment() {
		return repRuleComment;
	}

	public void setRepRuleComment(String repRuleComment) {
		this.repRuleComment = repRuleComment;
	}

	public Date getRepFirstOpenDay() {
		return repFirstOpenDay;
	}

	public void setRepFirstOpenDay(Date repFirstOpenDay) {
		this.repFirstOpenDay = repFirstOpenDay;
	}

	public BigDecimal getRepAvgOpenCount() {
		return repAvgOpenCount;
	}

	public void setRepAvgOpenCount(BigDecimal repAvgOpenCount) {
		this.repAvgOpenCount = repAvgOpenCount;
	}

	public String getRepOpenBuss() {
		return repOpenBuss;
	}

	public void setRepOpenBuss(String repOpenBuss) {
		this.repOpenBuss = repOpenBuss;
	}

	public String getRepOpenBussRemark() {
		return repOpenBussRemark;
	}
	
	public String getOpenType() {
		return openType;
	}

	public void setOpenType(String openType) {
		this.openType = openType;
	}

	public String getOpenMode() {
		return openMode;
	}

	public void setOpenMode(String openMode) {
		this.openMode = openMode;
	}

	public String getPageFrom() {
		return pageFrom;
	}

	public void setPageFrom(String pageFrom) {
		this.pageFrom = pageFrom;
	}

	public void setRepOpenBussRemark(String repOpenBussRemark) {
		this.repOpenBussRemark = repOpenBussRemark;
	}

	public String getIsMerger() {
		return isMerger;
	}

	public void setIsMerger(String isMerger) {
		this.isMerger = isMerger;
	}
	
}
