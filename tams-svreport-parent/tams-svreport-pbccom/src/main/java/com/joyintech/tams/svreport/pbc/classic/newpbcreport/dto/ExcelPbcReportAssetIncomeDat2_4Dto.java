package com.joyintech.tams.svreport.pbc.classic.newpbcreport.dto;

import com.joyintech.tams.svreport.pbc.common.annotation.ExcelCell;
import com.joyintech.tams.svreport.pbc.common.annotation.ReportGroup;
import com.joyintech.tams.svreport.pbc.common.constants.PbcMonthReportGroupConstants;

import java.math.BigDecimal;

/**
 * 描述:PBC_REPORT_ASSET_INCOME_DETAIL_DAT表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-03-04
 */
@ReportGroup(group = PbcMonthReportGroupConstants.PBC_REPORT_2_4_1)
public class ExcelPbcReportAssetIncomeDat2_4Dto {


    /**
     * 报表类型：:XFCP 新发产品分合同期限募集金额统计报表;CXQCP 存续产品分合同期限募集余额统计模板
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
     * 贷款收益权	
     */
    @ExcelCell(columnNumber = 3)
    private BigDecimal loanIncomeRight;

    /**
     * 债务证券收益权
     */
    @ExcelCell(columnNumber = 4)
    private BigDecimal debtSecuritiesIncomeRights;

    /**
     * 应收账款收益权
     */
    @ExcelCell(columnNumber = 5)
    private BigDecimal receivableIncomeRights;

    /**
     * 其他债权收益权
     */
    @ExcelCell(columnNumber = 6)
    private BigDecimal otherCreditorRights;

    /**
     * 股票收益权
     */
    @ExcelCell(columnNumber = 7)
    private BigDecimal stockIncomeRight;

    /**
     * 其他股权收益权
     */
    @ExcelCell(columnNumber = 8)
    private BigDecimal otherEquityIncomeRights;

    /**
     * 物权资产收益权
     */
    @ExcelCell(columnNumber = 9)
    private BigDecimal propertyRights;

    /**
     * 总计
     */
    @ExcelCell(columnNumber = 10)
    private BigDecimal total;

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

    public BigDecimal getLoanIncomeRight() {
        return loanIncomeRight;
    }

    public void setLoanIncomeRight(BigDecimal loanIncomeRight) {
        this.loanIncomeRight = loanIncomeRight;
    }

    public BigDecimal getDebtSecuritiesIncomeRights() {
        return debtSecuritiesIncomeRights;
    }

    public void setDebtSecuritiesIncomeRights(BigDecimal debtSecuritiesIncomeRights) {
        this.debtSecuritiesIncomeRights = debtSecuritiesIncomeRights;
    }

    public BigDecimal getReceivableIncomeRights() {
        return receivableIncomeRights;
    }

    public void setReceivableIncomeRights(BigDecimal receivableIncomeRights) {
        this.receivableIncomeRights = receivableIncomeRights;
    }

    public BigDecimal getOtherCreditorRights() {
        return otherCreditorRights;
    }

    public void setOtherCreditorRights(BigDecimal otherCreditorRights) {
        this.otherCreditorRights = otherCreditorRights;
    }

    public BigDecimal getStockIncomeRight() {
        return stockIncomeRight;
    }

    public void setStockIncomeRight(BigDecimal stockIncomeRight) {
        this.stockIncomeRight = stockIncomeRight;
    }

    public BigDecimal getOtherEquityIncomeRights() {
        return otherEquityIncomeRights;
    }

    public void setOtherEquityIncomeRights(BigDecimal otherEquityIncomeRights) {
        this.otherEquityIncomeRights = otherEquityIncomeRights;
    }

    public BigDecimal getPropertyRights() {
        return propertyRights;
    }

    public void setPropertyRights(BigDecimal propertyRights) {
        this.propertyRights = propertyRights;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}