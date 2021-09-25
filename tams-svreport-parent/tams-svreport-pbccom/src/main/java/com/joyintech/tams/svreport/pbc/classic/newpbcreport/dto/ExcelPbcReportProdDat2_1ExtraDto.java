package com.joyintech.tams.svreport.pbc.classic.newpbcreport.dto;

import com.joyintech.tams.svreport.pbc.common.annotation.ExcelCell;
import com.joyintech.tams.svreport.pbc.common.annotation.ReportGroup;
import com.joyintech.tams.svreport.pbc.common.constants.PbcMonthReportGroupConstants;

import java.math.BigDecimal;

/**
 * 描述:报表表1-1资管产品募集余额 的实体类
 * @author:  Jason.Jin
 * @创建时间: 2021年8月3日10:14:50
 */
@ReportGroup(group = PbcMonthReportGroupConstants.PBC_REPORT_2_1_3)
public class ExcelPbcReportProdDat2_1ExtraDto {
    /**
     * 属性代码
     */
    @ExcelCell(isPropertyCode = true)
    private String propertycode;

    /**
     * 报表类型：MJYE-募集余额报表,MJDF-募集兑付报表,ZSQK-只数情况报表,MJYEFDQ-募集余额报表分地区报表,TQJYQDF-提前及延期兑付报表,DQWDF-到期未兑付报表
     */
    @ExcelCell(isReportType = true)
    private String reporttype;

    /**
     * 分组类型：DQMJ-当期募集,DQDF-当期兑付,QMCX-期末存续,DQXF-当期新发,DQZZ-当期终止,TQDF-提前兑付,YQDF-延期兑付,DQWDF-到期未兑付
     */
    @ExcelCell(isGroupType = true)
    private String grouptype;

    /**
     * 合计		
     */
    @ExcelCell(columnNumber = 3)
    private BigDecimal total;

    public String getPropertycode() {
        return propertycode;
    }

    public void setPropertycode(String propertycode) {
        this.propertycode = propertycode;
    }

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

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}