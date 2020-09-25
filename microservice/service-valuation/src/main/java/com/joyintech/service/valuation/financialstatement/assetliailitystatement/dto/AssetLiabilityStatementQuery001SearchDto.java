/*
 * 文件名：AssetLiabilityStatementQuery001SearchDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.financialstatement.assetliailitystatement.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.joyintech.framework.select.SuggestDto;

/**
 * 资产负债表查询条件
 * @author zhangwei
 * @version 1.0 2018年9月18日
 * @see AssetLiabilityStatementQuery001SearchDto
 * @since 1.0
 */
public class AssetLiabilityStatementQuery001SearchDto {

	/**
	 * 账套代码
	 */
	private String booksetId;

	/**
	 * 账套组
	 */
	private String booksetGroup;

	/**
	 * 报表期限类型
	 */
	@NotBlank(message="报表期限类型不能为空！")
	private String reportTermType;

	/**
	 * 报表时间
	 */
	@NotBlank(message="报表时间不能为空！")
	private String reportDate;

	/**
	 * 报表维度
	 */
	@NotBlank(message="报表维度不能为空！")
	private String reportDimen;

	/**
	 * 	期限明细
	 */
	private String reportTerm;

	/**
	 * 余额日期
	 */
	private Date balanceDate;

	/**
	 * 科目代码列表
	 */
	private List<String> subjectNoList;

	/**
	 * 账套列表
	 */
	private List<SuggestDto> accountList;

	/**
	 * 项目
	 */
	private String itemName;

	/**
	 * 期末余额
	 */
	private BigDecimal endingBalance = BigDecimal.ZERO;

	/**
	 * 年初余额
	 */
	private BigDecimal initialBalance = BigDecimal.ZERO;

	/**
	 * 表格ID
	 */
	private String tableId;

	public String getBooksetId() {
		return booksetId;
	}

	public void setBooksetId(String booksetId) {
		this.booksetId = booksetId;
	}

	public String getReportTermType() {
		return reportTermType;
	}

	public void setReportTermType(String reportTermType) {
		this.reportTermType = reportTermType;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getReportTerm() {
		return reportTerm;
	}

	public void setReportTerm(String reportTerm) {
		this.reportTerm = reportTerm;
	}

	public Date getBalanceDate() {
		return balanceDate;
	}

	public void setBalanceDate(Date balanceDate) {
		this.balanceDate = balanceDate;
	}

	public List<String> getSubjectNoList() {
		return subjectNoList;
	}

	public void setSubjectNoList(List<String> subjectNoList) {
		this.subjectNoList = subjectNoList;
	}

	public String getReportDimen() {
		return reportDimen;
	}

	public void setReportDimen(String reportDimen) {
		this.reportDimen = reportDimen;
	}

	public List<SuggestDto> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<SuggestDto> accountList) {
		this.accountList = accountList;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public BigDecimal getEndingBalance() {
		return endingBalance;
	}

	public void setEndingBalance(BigDecimal endingBalance) {
		this.endingBalance = endingBalance;
	}

	public BigDecimal getInitialBalance() {
		return initialBalance;
	}

	public void setInitialBalance(BigDecimal initialBalance) {
		this.initialBalance = initialBalance;
	}

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public String getBooksetGroup() {
		return booksetGroup;
	}


	public void setBooksetGroup(String booksetGroup) {
		this.booksetGroup = booksetGroup;
	}


}
