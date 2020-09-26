package com.joyintech.tams.svreport.pbc.assetdebt.dto;

import java.math.BigDecimal;

/**
 * 描述:PBC_REPORT_COMPANY_DETAIL表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-03-04
 */
public class PbcReportCompanyDetailDto {
    /**
     * 明细统计uuid
     */
    private String detailuuid;

    /**
     * 报表类型 COMPANYBOND-企业债券分行业、企业规模统计;COMPANYDEBT-除回购和拆借外贷款分行业、企业规模统计
     */
    private String reporttype;

    /**
     * 分组类型 企业-COMPANY
     */
    private String grouptype;

    /**
     * 属性代码
     */
    private String propertycode;

    /**
     * 大型企业
     */
    private BigDecimal largeCompanyAmt;

    /**
     * 中型企业
     */
    private BigDecimal mediumCompanyAmt;

    /**
     * 小型企业
     */
    private BigDecimal smallCompanyAmt;

    /**
     * 微型企业
     */
    private BigDecimal microCompanyAmt;

    /**
     * 其他（住户、广义政府及境外）
     */
    private BigDecimal othAmt;

    /**
     * 合计
     */
    private BigDecimal sumAmt;

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
     * 报表类型 COMPANYBOND-企业债券分行业、企业规模统计;COMPANYDEBT-除回购和拆借外贷款分行业、企业规模统计
     * @return REPORTTYPE 报表类型 COMPANYBOND-企业债券分行业、企业规模统计;COMPANYDEBT-除回购和拆借外贷款分行业、企业规模统计
     */
    public String getReporttype() {
        return reporttype;
    }

    /**
     * 报表类型 COMPANYBOND-企业债券分行业、企业规模统计;COMPANYDEBT-除回购和拆借外贷款分行业、企业规模统计
     * @param reporttype 报表类型 COMPANYBOND-企业债券分行业、企业规模统计;COMPANYDEBT-除回购和拆借外贷款分行业、企业规模统计
     */
    public void setReporttype(String reporttype) {
        this.reporttype = reporttype == null ? null : reporttype.trim();
    }

    /**
     * 分组类型 企业-COMPANY
     * @return GROUPTYPE 分组类型 企业-COMPANY
     */
    public String getGrouptype() {
        return grouptype;
    }

    /**
     * 分组类型 企业-COMPANY
     * @param grouptype 分组类型 企业-COMPANY
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
     * 大型企业
     * @return LARGE_COMPANY_AMT 大型企业
     */
    public BigDecimal getLargeCompanyAmt() {
        return largeCompanyAmt;
    }

    /**
     * 大型企业
     * @param largeCompanyAmt 大型企业
     */
    public void setLargeCompanyAmt(BigDecimal largeCompanyAmt) {
        this.largeCompanyAmt = largeCompanyAmt;
    }

    /**
     * 中型企业
     * @return MEDIUM_COMPANY_AMT 中型企业
     */
    public BigDecimal getMediumCompanyAmt() {
        return mediumCompanyAmt;
    }

    /**
     * 中型企业
     * @param mediumCompanyAmt 中型企业
     */
    public void setMediumCompanyAmt(BigDecimal mediumCompanyAmt) {
        this.mediumCompanyAmt = mediumCompanyAmt;
    }

    /**
     * 小型企业
     * @return SMALL_COMPANY_AMT 小型企业
     */
    public BigDecimal getSmallCompanyAmt() {
        return smallCompanyAmt;
    }

    /**
     * 小型企业
     * @param smallCompanyAmt 小型企业
     */
    public void setSmallCompanyAmt(BigDecimal smallCompanyAmt) {
        this.smallCompanyAmt = smallCompanyAmt;
    }

    /**
     * 微型企业
     * @return MICRO_COMPANY_AMT 微型企业
     */
    public BigDecimal getMicroCompanyAmt() {
        return microCompanyAmt;
    }

    /**
     * 微型企业
     * @param microCompanyAmt 微型企业
     */
    public void setMicroCompanyAmt(BigDecimal microCompanyAmt) {
        this.microCompanyAmt = microCompanyAmt;
    }

    /**
     * 其他（住户、广义政府及境外）
     * @return OTH_AMT 其他（住户、广义政府及境外）
     */
    public BigDecimal getOthAmt() {
        return othAmt;
    }

    /**
     * 其他（住户、广义政府及境外）
     * @param othAmt 其他（住户、广义政府及境外）
     */
    public void setOthAmt(BigDecimal othAmt) {
        this.othAmt = othAmt;
    }

    /**
     * 合计
     * @return SUM_AMT 合计
     */
    public BigDecimal getSumAmt() {
        return sumAmt;
    }

    /**
     * 合计
     * @param sumAmt 合计
     */
    public void setSumAmt(BigDecimal sumAmt) {
        this.sumAmt = sumAmt;
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