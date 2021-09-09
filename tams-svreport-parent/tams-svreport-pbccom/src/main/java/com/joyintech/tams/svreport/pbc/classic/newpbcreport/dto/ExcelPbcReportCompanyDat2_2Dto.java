package com.joyintech.tams.svreport.pbc.classic.newpbcreport.dto;

import com.joyintech.tams.svreport.pbc.common.annotation.ExcelCell;
import com.joyintech.tams.svreport.pbc.common.annotation.ReportGroup;
import com.joyintech.tams.svreport.pbc.common.constants.PbcMonthReportGroupConstants;

import java.math.BigDecimal;

/**
 * 描述:PBC_REPORT_COMPANY_DETAIL_DAT表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-03-04
 */
@ReportGroup(group = PbcMonthReportGroupConstants.PBC_REPORT_2_2_1)
public class ExcelPbcReportCompanyDat2_2Dto {

    /**
     * 报表类型 COMPANYBOND-企业债券分行业、企业规模统计;COMPANYDEBT-除回购和拆借外贷款分行业、企业规模统计
     */
    @ExcelCell(isReportType = true)
    private String reporttype;

    /**
     * 分组类型 企业-COMPANY
     */
    @ExcelCell(isGroupType = true)
    private String grouptype;

    /**
     * 属性代码
     */
    @ExcelCell(isPropertyCode = true)
    private String propertycode;

    /**
     * 大型企业
     */
    @ExcelCell(columnNumber = 3)
    private BigDecimal largeCompanyAmt;

    /**
     * 中型企业
     */
    @ExcelCell(columnNumber = 4)
    private BigDecimal mediumCompanyAmt;

    /**
     * 小型企业
     */
    @ExcelCell(columnNumber = 5)
    private BigDecimal smallCompanyAmt;

    /**
     * 微型企业
     */
    @ExcelCell(columnNumber = 6)
    private BigDecimal microCompanyAmt;

    /**
     * 其他（住户、广义政府及境外）
     */
    @ExcelCell(columnNumber = 7)
    private BigDecimal othAmt;

    /**
     * 合计
     */
    @ExcelCell(columnNumber = 8)
    private BigDecimal sumAmt;

    public String getReporttype() {
        return reporttype;
    }

    public void setReporttype(String reporttype) {
        this.reporttype = reporttype;
    }

    public String getGrouptype() {
        return grouptype;
    }

    public void setGrouptype(String grouptype) {
        this.grouptype = grouptype;
    }

    public String getPropertycode() {
        return propertycode;
    }

    public void setPropertycode(String propertycode) {
        this.propertycode = propertycode;
    }

    public BigDecimal getLargeCompanyAmt() {
        return largeCompanyAmt;
    }

    public void setLargeCompanyAmt(BigDecimal largeCompanyAmt) {
        this.largeCompanyAmt = largeCompanyAmt;
    }

    public BigDecimal getMediumCompanyAmt() {
        return mediumCompanyAmt;
    }

    public void setMediumCompanyAmt(BigDecimal mediumCompanyAmt) {
        this.mediumCompanyAmt = mediumCompanyAmt;
    }

    public BigDecimal getSmallCompanyAmt() {
        return smallCompanyAmt;
    }

    public void setSmallCompanyAmt(BigDecimal smallCompanyAmt) {
        this.smallCompanyAmt = smallCompanyAmt;
    }

    public BigDecimal getMicroCompanyAmt() {
        return microCompanyAmt;
    }

    public void setMicroCompanyAmt(BigDecimal microCompanyAmt) {
        this.microCompanyAmt = microCompanyAmt;
    }

    public BigDecimal getOthAmt() {
        return othAmt;
    }

    public void setOthAmt(BigDecimal othAmt) {
        this.othAmt = othAmt;
    }

    public BigDecimal getSumAmt() {
        return sumAmt;
    }

    public void setSumAmt(BigDecimal sumAmt) {
        this.sumAmt = sumAmt;
    }
}