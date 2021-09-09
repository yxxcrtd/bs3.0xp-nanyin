package com.joyintech.tams.svreport.pbc.classic.newpbcreport.dto;

import com.joyintech.tams.svreport.pbc.common.annotation.ExcelCell;
import com.joyintech.tams.svreport.pbc.common.annotation.ReportGroup;
import com.joyintech.tams.svreport.pbc.common.constants.PbcMonthReportGroupConstants;

import java.math.BigDecimal;

/**
 * 描述:PBC_REPORT_AREA_DETAIL_DAT表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-03-04
 */
@ReportGroup(group = PbcMonthReportGroupConstants.PBC_REPORT_2_3_1)
public class ExcelPbcReportAreaDat2_3Dto {

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
     * 余额
     */
    @ExcelCell(columnNumber = 3)
    private BigDecimal balance;

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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}