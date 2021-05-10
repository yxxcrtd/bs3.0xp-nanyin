/*
 * 文件名：AssetLiabilityStatementQuery001SearchDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.valbatchexport.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.joyintech.framework.select.SuggestDto;

/**
 * 三表批量导出查询条件
 * 
 * @author huanghao
 * @version 1.0 2020年5月31日
 * @see ValBatchExport001SearchDto
 * @since 1.0
 */
public class ValBatchExport001SearchDto {

	/**
	 * 账套组/账套
	 */
	@NotBlank(message = "报表维度不能为空！")
	private String batchBooksetType;

	/**
	 * 账套代码
	 */
	private String[] booksetId;

	/**
	 * 报表期限类型
	 */
	@NotBlank(message = "报表期限类型不能为空！")
	private String reportTermType;

	/**
	 * 上期维度
	 */
	private String lastDimen;

	/**
	 * 报表类型
	 */
	@NotBlank(message = "报表类型不能为空！")
	private String reportType;

	/**
	 * 报表时间
	 */
	@NotBlank(message = "报表时间不能为空！")
	private String reportDate;

	/**
	 * 报表维度
	 */
	private String reportDimen;

	/**
	 * 期限明细
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

	/**
	 * 表格类型
	 */
	private List<String> itemTypes;

	/**
	 * 获取 booksetId
	 * 
	 * @return booksetId.
	 */
	public String[] getBooksetId() {
		return booksetId;
	}

	/**
	 * 设置 booksetId
	 * 
	 * @param booksetId
	 *            booksetId
	 */
	public void setBooksetId(String[] booksetId) {
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

	/**
	 * 获取 itemTypes
	 * 
	 * @return itemTypes.
	 */
	public List<String> getItemTypes() {
		return itemTypes;
	}

	/**
	 * 设置 itemTypes
	 * 
	 * @param itemTypes
	 *            itemTypes
	 */
	public void setItemTypes(List<String> itemTypes) {
		this.itemTypes = itemTypes;
	}

	/**
	 * 获取 reportType
	 * 
	 * @return reportType.
	 */
	public String getReportType() {
		return reportType;
	}

	/**
	 * 设置 reportType
	 * 
	 * @param reportType
	 *            reportType
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	/**
	 * 获取 lastDimen
	 * 
	 * @return lastDimen.
	 */
	public String getLastDimen() {
		return lastDimen;
	}

	/**
	 * 设置 lastDimen
	 * 
	 * @param lastDimen
	 *            lastDimen
	 */
	public void setLastDimen(String lastDimen) {
		this.lastDimen = lastDimen;
	}

	/**
	 * 获取 batchBooksetType
	 * 
	 * @return batchBooksetType.
	 */
	public String getBatchBooksetType() {
		return batchBooksetType;
	}

	/**
	 * 设置 batchBooksetType
	 * 
	 * @param batchBooksetType
	 *            batchBooksetType
	 */
	public void setBatchBooksetType(String batchBooksetType) {
		this.batchBooksetType = batchBooksetType;
	}

}
