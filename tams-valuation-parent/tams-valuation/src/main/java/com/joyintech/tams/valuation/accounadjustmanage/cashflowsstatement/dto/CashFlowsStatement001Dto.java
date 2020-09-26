/*
 * 文件名：CashFlowsStatement001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：QuJianZhong
 * 修改时间：2018年10月22日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.cashflowsstatement.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.valuation.accounadjustmanage.cashflowsstatement.validator.CashFlowsStatement001DtoValidator;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 现金流量表数据
 * 
 * @author qujianzhong
 * @version 1.0 2018年10月22日
 * @see CashFlowsStatement001Dto
 * @since 1.0
 */
@ValidLogic(targetClass = CashFlowsStatement001DtoValidator.class)
public class CashFlowsStatement001Dto extends BaseEntity implements ITableRow {

	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = -2654564364464641L;

	/**
	 * 排序号
	 */
	private int sortNum;

	/**
	 * 项目
	 */
	private String project;

	/**
	 * 报表维度
	 */
	@NotBlank(message = "报表维度不能为空！")
	private String reportDimen;

	/**
	 * 账套组
	 */
	private String booksetGroup;
	/**
	 * 资产.项目名
	 */
	private String itemName;

	/**
	 * 本期金额
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal thisTermAmt;

	/**
	 * 上期金额
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal lastTermAmt;

	/**
	 * 账套
	 */
	//@NotEmpty(fieldName = "CashFlowsStatement001.booksetId")
	private String booksetId;

	/**
	 * 报表期限类型
	 */
	@NotEmpty(fieldName = "CashFlowsStatement001.reportTermType")
	private String reportTermType;

	/**
	 * 日期
	 */
	@NotEmpty(fieldName = "CashFlowsStatement001.valDate")
	private String reportDate;
	
	/**
     * 上期维度
     */
    @NotEmpty(fieldName = "CashFlowsStatement001.lastDimen")
    private String lastDimen;

	/**
	 * 本期开始日
	 */
	private String valueDateStart;
	/**
	 * 本期结束日
	 */
	private String valueDateEnd;

	/**
	 * 期限明细
	 */
	private String reportTerm;

	/**
	 * 获取 valueDateStart
	 * 
	 * @return valueDateStart.
	 */
	public String getValueDateStart() {
		return valueDateStart;
	}

	/**
	 * 设置 valueDateStart
	 * 
	 * @param valueDateStart valueDateStart
	 */
	public void setValueDateStart(String valueDateStart) {
		this.valueDateStart = valueDateStart;
	}

	/**
	 * 获取 valueDateEnd
	 * 
	 * @return valueDateEnd.
	 */
	public String getValueDateEnd() {
		return valueDateEnd;
	}

	/**
	 * 设置 valueDateEnd
	 * 
	 * @param valueDateEnd valueDateEnd
	 */
	public void setValueDateEnd(String valueDateEnd) {
		this.valueDateEnd = valueDateEnd;
	}

	/**
	 * 获取 sortNum
	 * 
	 * @return sortNum.
	 */
	public int getSortNum() {
		return sortNum;
	}

	/**
	 * 设置 sortNum
	 * 
	 * @param sortNum sortNum
	 */
	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}

	/**
	 * 获取 reportTermType
	 * 
	 * @return reportTermType.
	 */
	public String getReportTermType() {
		return reportTermType;
	}

	/**
	 * 设置 reportTermType
	 * 
	 * @param reportTermType reportTermType
	 */
	public void setReportTermType(String reportTermType) {
		this.reportTermType = reportTermType;
	}

	/**
	 * 获取 booksetId
	 * 
	 * @return booksetId.
	 */
	public String getBooksetId() {
		return booksetId;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
	
    /**
     * 获取 lastDimen
     * @return lastDimen.
     */
    public String getLastDimen() {
        return lastDimen;
    }

    
    /**
     * 设置 lastDimen
     * @param lastDimen lastDimen
     */
    public void setLastDimen(String lastDimen) {
        this.lastDimen = lastDimen;
    }

    /**
	 * 设置 booksetId
	 * 
	 * @param booksetId booksetId
	 */
	public void setBooksetId(String booksetId) {
		this.booksetId = booksetId;
	}

	/**
	 * 获取 project
	 * 
	 * @return project.
	 */
	public String getProject() {
		return project;
	}

	/**
	 * 设置 project
	 * 
	 * @param project project
	 */
	public void setProject(String project) {
		this.project = project;
	}

	/**
	 * 获取 thisTermAmt
	 * 
	 * @return thisTermAmt.
	 */
	public BigDecimal getThisTermAmt() {
		return thisTermAmt;
	}

	/**
	 * 设置 thisTermAmt
	 * 
	 * @param thisTermAmt thisTermAmt
	 */
	public void setThisTermAmt(BigDecimal thisTermAmt) {
		this.thisTermAmt = thisTermAmt;
	}

	/**
	 * 获取 lastTermAmt
	 * 
	 * @return lastTermAmt.
	 */
	public BigDecimal getLastTermAmt() {
		return lastTermAmt;
	}

	/**
	 * 设置 lastTermAmt
	 * 
	 * @param lastTermAmt lastTermAmt
	 */
	public void setLastTermAmt(BigDecimal lastTermAmt) {
		this.lastTermAmt = lastTermAmt;
	}

	public String getReportTerm() {
		return reportTerm;
	}

	public void setReportTerm(String reportTerm) {
		this.reportTerm = reportTerm;
	}

	public String getReportDimen() {
		return reportDimen;
	}

	public void setReportDimen(String reportDimen) {
		this.reportDimen = reportDimen;
	}

	public String getBooksetGroup() {
		return booksetGroup;
	}

	public void setBooksetGroup(String booksetGroup) {
		this.booksetGroup = booksetGroup;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
}
