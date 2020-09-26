/*
 * 文件名：StockCostDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangzhao
 * 修改时间：2019年11月5日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.secdealaccounts.dto;

import java.math.BigDecimal;

import com.joyintech.tams.common.base.entity.BokDetailId;

/**
 * 
 * 股票成本DTO
 * 
 * @author zhangzhao
 * @version 1.0 2019年11月5日
 * @see StockCostDto
 * @since 1.0
 */
public class StockCostDto {
	
	/**
	 * 金融产品代码
	 */
	private String finprodId;
	
	/**
	 * 金融产品类型
	 */
	private String finprodType;
	
	/**
	 * 核算分类
	 */
	private String type2;
    
    /**
     * 成本
     */
    private BigDecimal cost;

    /**
     * 数量
     */
    private BigDecimal Num;

    /**
     * 账务明细代码
     */
    private BokDetailId bokDetailId;
    
    

	public String getFinprodId() {
		return finprodId;
	}

	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}

	public String getFinprodType() {
		return finprodType;
	}

	public void setFinprodType(String finprodType) {
		this.finprodType = finprodType;
	}

	public String getType2() {
		return type2;
	}

	public void setType2(String type2) {
		this.type2 = type2;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public BigDecimal getNum() {
		return Num;
	}

	public void setNum(BigDecimal Num) {
		this.Num = Num;
	}

	public BokDetailId getBokDetailId() {
		return bokDetailId;
	}

	public void setBokDetailId(BokDetailId bokDetailId) {
		this.bokDetailId = bokDetailId;
	}
    
}
