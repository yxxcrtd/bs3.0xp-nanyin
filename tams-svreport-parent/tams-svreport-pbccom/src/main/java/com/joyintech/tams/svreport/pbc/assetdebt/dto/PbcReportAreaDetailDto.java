package com.joyintech.tams.svreport.pbc.assetdebt.dto;

import java.math.BigDecimal;

/**
 * 描述:PBC_REPORT_AREA_DETAIL表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-03-04
 */
public class PbcReportAreaDetailDto {
    /**
     * 明细统计uuid
     */
    private String detailuuid;

    /**
     * 报表类型 AREADEBT-除回购和拆借外贷款分行业、企业规模统计
     */
    private String reporttype;

    /**
     * 分组类型 AREA-地区
     */
    private String grouptype;

    /**
     * 属性代码
     */
    private String propertycode;

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 主表关联id
     */
    private String reportuuid;

    /**
     * 明细统计uuid
     * @return DETAILUUID 明细统计uuid
     */
    public String getDetailuuid() {
        return detailuuid;
    }

    /**
     * 明细统计uuid
     * @param detailuuid 明细统计uuid
     */
    public void setDetailuuid(String detailuuid) {
        this.detailuuid = detailuuid == null ? null : detailuuid.trim();
    }

    /**
     * 报表类型 AREADEBT-除回购和拆借外贷款分行业、企业规模统计
     * @return REPORTTYPE 报表类型 AREADEBT-除回购和拆借外贷款分行业、企业规模统计
     */
    public String getReporttype() {
        return reporttype;
    }

    /**
     * 报表类型 AREADEBT-除回购和拆借外贷款分行业、企业规模统计
     * @param reporttype 报表类型 AREADEBT-除回购和拆借外贷款分行业、企业规模统计
     */
    public void setReporttype(String reporttype) {
        this.reporttype = reporttype == null ? null : reporttype.trim();
    }

    /**
     * 分组类型 AREA-地区
     * @return GROUPTYPE 分组类型 AREA-地区
     */
    public String getGrouptype() {
        return grouptype;
    }

    /**
     * 分组类型 AREA-地区
     * @param grouptype 分组类型 AREA-地区
     */
    public void setGrouptype(String grouptype) {
        this.grouptype = grouptype == null ? null : grouptype.trim();
    }

    /**
     * 属性代码
     * @return PROPERTYCODE 属性代码
     */
    public String getPropertycode() {
        return propertycode;
    }

    /**
     * 属性代码
     * @param propertycode 属性代码
     */
    public void setPropertycode(String propertycode) {
        this.propertycode = propertycode == null ? null : propertycode.trim();
    }

    /**
     * 余额
     * @return BALANCE 余额
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * 余额
     * @param balance 余额
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * 主表关联id
     * @return REPORTUUID 主表关联id
     */
    public String getReportuuid() {
        return reportuuid;
    }

    /**
     * 主表关联id
     * @param reportuuid 主表关联id
     */
    public void setReportuuid(String reportuuid) {
        this.reportuuid = reportuuid == null ? null : reportuuid.trim();
    }
}