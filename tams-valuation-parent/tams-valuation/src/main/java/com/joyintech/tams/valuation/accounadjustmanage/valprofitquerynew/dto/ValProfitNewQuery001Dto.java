/*
 * 文件名：ValProfitQuery001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.valprofitquerynew.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.valuation.accounadjustmanage.valprofitquerynew.validator.ValProfitNewQuery001DtoValidator;

/**
 * 利润表数据
 * 
 * @author wanghb
 * @version 1.0 2018年9月18日
 * @see ValProfitNewQuery001Dto
 * @since 1.0
 */
@ValidLogic(targetClass = ValProfitNewQuery001DtoValidator.class)
public class ValProfitNewQuery001Dto extends BaseEntity implements ITableRow {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -2654564364464641L;

    /**
     * 排序号
     */
    private int sortNum;


    /**
     * 类目编号
     */
    private String itemNo;
    

    /**
     * 项目
     */
    private String project;

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
    private String booksetId;

    /**
     * 报表期限类型
     */
    @NotEmpty(fieldName = "ValProfitQuery001.reportTermType")
    private String reportTermType;

    /**
     * 日期
     */
    @NotEmpty(fieldName = "ValProfitQuery001.valDate")
    private String reportDate;

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
     * 上期维度
     */
    @NotEmpty(fieldName = "ValProfitQuery001.lastDimen")
    private String lastDimen;
    
    /**
     * 报表维度
     */
    @NotEmpty(fieldName = "ValProfitQuery001.reportDimen")
    private String reportDimen;
    
   /**
    * 元素层级
    */
    private String itemLevel;
    
    /**
     * 元素类型
     */
    private String itemType;

    /**
     * 账套组
     */
    private String booksetGroup;
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
     * @return sortNum.
     */
    public int getSortNum() {
        return sortNum;
    }

    
    /**
     * 设置 sortNum
     * @param sortNum sortNum
     */
    public void setSortNum(int sortNum) {
        this.sortNum = sortNum;
    }

    
    /**
     * 获取 itemNo
     * @return itemNo.
     */
    public String getItemNo() {
        return itemNo;
    }

    
    /**
     * 设置 itemNo
     * @param itemNo itemNo
     */
    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
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
        return (thisTermAmt == null ? BigDecimal.ZERO : thisTermAmt);
    }

    /**
     * 设置 thisTermAmt
     * 
     * @param thisTermAmt thisTermAmt
     */
    public void setThisTermAmt(BigDecimal thisTermAmt) {
        this.thisTermAmt = (thisTermAmt == null ? BigDecimal.ZERO : thisTermAmt);
    }

    /**
     * 获取 lastTermAmt
     * 
     * @return lastTermAmt.
     */
    public BigDecimal getLastTermAmt() {
        return (lastTermAmt == null ? BigDecimal.ZERO : lastTermAmt);
    }

    /**
     * 设置 lastTermAmt
     * 
     * @param lastTermAmt lastTermAmt
     */
    public void setLastTermAmt(BigDecimal lastTermAmt) {
        this.lastTermAmt = (lastTermAmt == null ? BigDecimal.ZERO : lastTermAmt);
    }

    public String getReportTerm() {
        return reportTerm;
    }

    public void setReportTerm(String reportTerm) {
        this.reportTerm = reportTerm;
    }

    /**
     * 获取 上期维度
     * 
     * @return 上期维度.
     */
    public String getLastDimen() {
        return lastDimen;
    }

    /**
     * 设置 上期维度
     * 
     * @param lastDimen 上期维度
     */
    public void setLastDimen(String lastDimen) {
        this.lastDimen = lastDimen;
    }

	public String getReportDimen() {
		return reportDimen;
	}

	public void setReportDimen(String reportDimen) {
		this.reportDimen = reportDimen;
	}

    
    /**
     * 获取 itemLevel
     * @return itemLevel.
     */
    public String getItemLevel() {
        return itemLevel;
    }

    
    /**
     * 设置 itemLevel
     * @param itemLevel itemLevel
     */
    public void setItemLevel(String itemLevel) {
        this.itemLevel = itemLevel;
    }

    
    /**
     * 获取 itemType
     * @return itemType.
     */
    public String getItemType() {
        return itemType;
    }

    
    /**
     * 设置 itemType
     * @param itemType itemType
     */
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getBooksetGroup() {
        return booksetGroup;
    }

    public void setBooksetGroup(String booksetGroup) {
        this.booksetGroup = booksetGroup;
    }

}
