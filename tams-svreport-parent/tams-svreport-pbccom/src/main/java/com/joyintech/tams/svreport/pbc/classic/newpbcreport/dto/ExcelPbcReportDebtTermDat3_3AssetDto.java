package com.joyintech.tams.svreport.pbc.classic.newpbcreport.dto;

import com.joyintech.tams.svreport.pbc.common.annotation.ExcelCell;
import com.joyintech.tams.svreport.pbc.common.annotation.ReportGroup;
import com.joyintech.tams.svreport.pbc.common.constants.PbcMonthReportGroupConstants;

import java.math.BigDecimal;

/**
 * 描述:PBC_REPORT_BALANCETERM_DAT表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-03-04
 */
@ReportGroup(group = PbcMonthReportGroupConstants.PBC_REPORT_3_3_1)
public class ExcelPbcReportDebtTermDat3_3AssetDto {

    /**
     * 报表类型 DEBTTERM-资管产品资产负债剩余期限统计
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
     * 债务工具
     */
    @ExcelCell(columnNumber = 2)
    private BigDecimal debtInstrument;

    /**
     * 股权
     */
    @ExcelCell(columnNumber = 3)
    private BigDecimal stockRight;

    /**
     * 特定目的载体份额
     */
    @ExcelCell(columnNumber = 4)
    private BigDecimal specialShare;

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

    public BigDecimal getDebtInstrument() {
        return debtInstrument;
    }

    public void setDebtInstrument(BigDecimal debtInstrument) {
        this.debtInstrument = debtInstrument;
    }

    public BigDecimal getStockRight() {
        return stockRight;
    }

    public void setStockRight(BigDecimal stockRight) {
        this.stockRight = stockRight;
    }

    public BigDecimal getSpecialShare() {
        return specialShare;
    }

    public void setSpecialShare(BigDecimal specialShare) {
        this.specialShare = specialShare;
    }

}