/*
 * 文件名：EtrMoneyCalculationDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：nixiukuan
 * 修改时间：2019年9月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.entrust.etrmoneycalculation.dto;

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;

/**
 *  可投金额试算
 * 〈功能详细描述〉
 * @author nixiukuan
 * @version 1.0 2019年9月26日
 * @see EtrMoneyCalculationDto
 * @since 1.0
 */

public class EtrMoneyCalculationDto extends BaseEntity {

	/**
     * 组合代码
     */
    private String portfolioId;
    
   	/**
	 * 资产类型
	 */
	private String finprodType;
	
	/**
	 * 资产代码
	 */
	private String finprodId;
	
	/**
	 * 债券一级分类
	 */
	private String bondOneType;
	/**
	 * 债券二级分类
	 */
	private String bondTwoType;
	/**
	 * 发行人
	 */
	private String publisher;
	/**
	 * 债项评级
	 */
	private String bondRating;
	/**
	 * 主体评级
	 */
	private String bondSubjectRating;
	/**
	 * 可投金额
	 */
	private String haveMoney;
	/**
	 * 基金类型
	 */
	private String fundType;
	/**
	 * 基金二级类型
	 */
	private String fundTwoType;
	/**
	 * 管理人
	 */
	private String manager;
	/**
	 * 交易场所
	 */
	private String tradingPlace;
	/**
	 * 上市板
	 */
	private String board;
	/**
	 * 行业
	 */
	private String repIndustry;


	//指标名称
	private String targetName;
	//关联组合/组合包
	private String portfoliopackage;
	//已投金额
	private String useMoney;
	//剩余可投金额
	private String surplusMoney;
	//可投总金额
	private String sumMoney;

	public String getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getFinprodType() {
		return finprodType;
	}

	public void setFinprodType(String finprodType) {
		this.finprodType = finprodType;
	}

	public String getFinprodId() {
		return finprodId;
	}

	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}

	public String getBondOneType() {
		return bondOneType;
	}

	public void setBondOneType(String bondOneType) {
		this.bondOneType = bondOneType;
	}

	public String getBondTwoType() {
		return bondTwoType;
	}

	public void setBondTwoType(String bondTwoType) {
		this.bondTwoType = bondTwoType;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getBondRating() {
		return bondRating;
	}

	public void setBondRating(String bondRating) {
		this.bondRating = bondRating;
	}

	public String getBondSubjectRating() {
		return bondSubjectRating;
	}

	public void setBondSubjectRating(String bondSubjectRating) {
		this.bondSubjectRating = bondSubjectRating;
	}

	public String getHaveMoney() {
		return haveMoney;
	}

	public void setHaveMoney(String haveMoney) {
		this.haveMoney = haveMoney;
	}

	public String getFundType() {
		return fundType;
	}

	public void setFundType(String fundType) {
		this.fundType = fundType;
	}

	public String getFundTwoType() {
		return fundTwoType;
	}

	public void setFundTwoType(String fundTwoType) {
		this.fundTwoType = fundTwoType;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getTradingPlace() {
		return tradingPlace;
	}

	public void setTradingPlace(String tradingPlace) {
		this.tradingPlace = tradingPlace;
	}

	public String getBoard() {
		return board;
	}

	public void setBoard(String board) {
		this.board = board;
	}

	public String getRepIndustry() {
		return repIndustry;
	}

	public void setRepIndustry(String repIndustry) {
		this.repIndustry = repIndustry;
	}


	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public String getPortfoliopackage() {
		return portfoliopackage;
	}

	public void setPortfoliopackage(String portfoliopackage) {
		this.portfoliopackage = portfoliopackage;
	}

	public String getUseMoney() {
		return useMoney;
	}

	public void setUseMoney(String useMoney) {
		this.useMoney = useMoney;
	}

	public String getSurplusMoney() {
		return surplusMoney;
	}

	public void setSurplusMoney(String surplusMoney) {
		this.surplusMoney = surplusMoney;
	}

	public String getSumMoney() {
		return sumMoney;
	}

	public void setSumMoney(String sumMoney) {
		this.sumMoney = sumMoney;
	}
}
