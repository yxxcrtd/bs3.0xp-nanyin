/*
 * 文件名：TargetFinProduct.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2019年2月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.batch.finproduct.entity;

import java.util.Date;
import java.util.List;

/**
 * 业绩回补计算规则 - 实体类
 * 
 * @author dongjun
 * @version 1.0 2019年2月19日
 */
public class TargetFinClawbackCalcRule {

	/**
	 * 现金流代码
	 */
	private String cashId;

	/**
	 * 生效日期
	 */
	private Date effDate;

	/**
	 * 回补方式
	 */
	private String cawlbackType;

	/**
	 * 回补规则
	 */
	private String cawlbackRule;

	/**
	 * 计算基础
	 */
	private String basis;

	/**
	 * 生效规则
	 */
	private String actRule;

	/**
	 * 现金流一级类型
	 */
	private String cashType1;

	/**
	 * 现金流二级类型
	 */
	private String cashType2;

	/**
	 * 金融产品代码
	 */
	private String finprodId;

	/**
	 * 金融产品类型
	 */
	private String finprodType;

	/**
	 * 分支序号
	 */
	private Long branch;

	/**
	 * 业绩报酬规则 对应的 业绩报酬规则明细
	 */
	private List<TargetPerfRewardCalRuleDetail> targetPerfRewardCalRuleDetailList;

	public String getCashId() {
		return cashId;
	}

	public void setCashId(String cashId) {
		this.cashId = cashId;
	}

	public Date getEffDate() {
		return effDate;
	}

	public void setEffDate(Date effDate) {
		this.effDate = effDate;
	}

	public String getCawlbackType() {
		return cawlbackType;
	}

	public void setCawlbackType(String cawlbackType) {
		this.cawlbackType = cawlbackType;
	}

	public String getCawlbackRule() {
		return cawlbackRule;
	}

	public void setCawlbackRule(String cawlbackRule) {
		this.cawlbackRule = cawlbackRule;
	}

	public String getBasis() {
		return basis;
	}

	public void setBasis(String basis) {
		this.basis = basis;
	}

	public String getActRule() {
		return actRule;
	}

	public void setActRule(String actRule) {
		this.actRule = actRule;
	}

	public String getCashType1() {
		return cashType1;
	}

	public void setCashType1(String cashType1) {
		this.cashType1 = cashType1;
	}

	public String getCashType2() {
		return cashType2;
	}

	public void setCashType2(String cashType2) {
		this.cashType2 = cashType2;
	}

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

	public Long getBranch() {
		return branch;
	}

	public void setBranch(Long branch) {
		this.branch = branch;
	}

	public List<TargetPerfRewardCalRuleDetail> getTargetPerfRewardCalRuleDetailList() {
		return targetPerfRewardCalRuleDetailList;
	}

	public void setTargetPerfRewardCalRuleDetailList(
			List<TargetPerfRewardCalRuleDetail> targetPerfRewardCalRuleDetailList) {
		this.targetPerfRewardCalRuleDetailList = targetPerfRewardCalRuleDetailList;
	}

}
