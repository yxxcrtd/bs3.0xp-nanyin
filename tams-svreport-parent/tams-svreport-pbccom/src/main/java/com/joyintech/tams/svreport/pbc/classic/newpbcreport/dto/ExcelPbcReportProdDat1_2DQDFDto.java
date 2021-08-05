package com.joyintech.tams.svreport.pbc.classic.newpbcreport.dto;

import com.joyintech.tams.svreport.pbc.common.annotation.ExcelCell;
import com.joyintech.tams.svreport.pbc.common.annotation.ReportGroup;
import com.joyintech.tams.svreport.pbc.common.constants.PbcMonthReportGroupConstants;

import java.math.BigDecimal;

/**
 * 描述:报表1-2募集及兑付发生额 当期兑付的实体类
 * @author:  Jason.Jin
 * @创建时间: 2021年8月3日10:14:50
 */
@ReportGroup(group = PbcMonthReportGroupConstants.PBC_REPORT_1_2_2)
public class ExcelPbcReportProdDat1_2DQDFDto {
    /**
     * 属性代码
     */
    @ExcelCell(isPropertyCode = true)
    private String propertyCode;

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
     * 人民币
     */
    @ExcelCell(columnNumber = 3)
    private BigDecimal cny;

    /**
     * 美元折人民币
     */
    @ExcelCell(columnNumber = 4)
    private BigDecimal usdToCny;

    /**
     * 欧元折人民币
     */
    @ExcelCell(columnNumber = 5)
    private BigDecimal eurToCny;

    /**
     * 英镑折人民币
     */
    @ExcelCell(columnNumber = 6)
    private BigDecimal gbpToCny;

    /**
     * 日元折人民币
     */
    @ExcelCell(columnNumber = 7)
    private BigDecimal jpyToCny;

    /**
     * 其他币种(外币)折人民币
     */
    @ExcelCell(columnNumber = 8)
    private BigDecimal othToCny;

    /**
     * 合计		
     */
    @ExcelCell(columnNumber = 9)
    private BigDecimal total;

    public String getPropertyCode() {
        return propertyCode;
    }

    public void setPropertyCode(String propertyCode) {
        this.propertyCode = propertyCode;
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

    public BigDecimal getCny() {
        return cny;
    }

    public void setCny(BigDecimal cny) {
        this.cny = cny;
    }

    public BigDecimal getUsdToCny() {
        return usdToCny;
    }

    public void setUsdToCny(BigDecimal usdToCny) {
        this.usdToCny = usdToCny;
    }

    public BigDecimal getEurToCny() {
        return eurToCny;
    }

    public void setEurToCny(BigDecimal eurToCny) {
        this.eurToCny = eurToCny;
    }

    public BigDecimal getGbpToCny() {
        return gbpToCny;
    }

    public void setGbpToCny(BigDecimal gbpToCny) {
        this.gbpToCny = gbpToCny;
    }

    public BigDecimal getJpyToCny() {
        return jpyToCny;
    }

    public void setJpyToCny(BigDecimal jpyToCny) {
        this.jpyToCny = jpyToCny;
    }

    public BigDecimal getOthToCny() {
        return othToCny;
    }

    public void setOthToCny(BigDecimal othToCny) {
        this.othToCny = othToCny;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}