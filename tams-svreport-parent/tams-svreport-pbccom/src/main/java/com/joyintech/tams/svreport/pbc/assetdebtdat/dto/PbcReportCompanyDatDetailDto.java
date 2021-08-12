package com.joyintech.tams.svreport.pbc.assetdebtdat.dto;

import java.math.BigDecimal;

/**
 * 描述:PBC_REPORT_COMPANY_DETAIL_DAT表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-03-04
 */
public class PbcReportCompanyDatDetailDto {
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
     * 大型企业
     */
    private BigDecimal largeCompanyAmtPrd;

    /**
     * 中型企业
     */
    private BigDecimal mediumCompanyAmtPrd;

    /**
     * 小型企业
     */
    private BigDecimal smallCompanyAmtPrd;

    /**
     * 微型企业
     */
    private BigDecimal microCompanyAmtPrd;

    /**
     * 其他（住户、广义政府及境外）
     */
    private BigDecimal othAmtPrd;

    /**
     * 合计
     */
    private BigDecimal sumAmtPrd;

    /**
     * 大型企业
     */
    private Integer largeCompanyAmtDiff=0;

    /**
     * 中型企业
     */
    private Integer mediumCompanyAmtDiff=0;

    /**
     * 小型企业
     */
    private Integer smallCompanyAmtDiff=0;

    /**
     * 微型企业
     */
    private Integer microCompanyAmtDiff=0;

    /**
     * 其他（住户、广义政府及境外）
     */
    private Integer othAmtDiff=0;

    /**
     * 合计
     */
    private Integer sumAmtDiff=0;

    /**
     * 金融机构编码
     */
    private String finInstitutionCode;
    /**
     * 产品品种
     */
    private String prodVariety;

    /**
     * 期数
     */
    private String termid;

    /**
     * 发行机构代码
     */
    private String issuePartyId;

    public String getFinInstitutionCode() {
        return finInstitutionCode;
    }

    public void setFinInstitutionCode(String finInstitutionCode) {
        this.finInstitutionCode = finInstitutionCode;
    }

    public String getProdVariety() {
        return prodVariety;
    }

    public void setProdVariety(String prodVariety) {
        this.prodVariety = prodVariety;
    }

    public String getTermid() {
        return termid;
    }

    public void setTermid(String termid) {
        this.termid = termid;
    }

    public String getIssuePartyId() {
        return issuePartyId;
    }

    public void setIssuePartyId(String issuePartyId) {
        this.issuePartyId = issuePartyId;
    }

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


    public BigDecimal getLargeCompanyAmtPrd() {
        return largeCompanyAmtPrd;
    }

    public PbcReportCompanyDatDetailDto setLargeCompanyAmtPrd(BigDecimal largeCompanyAmtPrd) {
        this.largeCompanyAmtPrd = largeCompanyAmtPrd;
        return this;
    }

    public BigDecimal getMediumCompanyAmtPrd() {
        return mediumCompanyAmtPrd;
    }

    public PbcReportCompanyDatDetailDto setMediumCompanyAmtPrd(BigDecimal mediumCompanyAmtPrd) {
        this.mediumCompanyAmtPrd = mediumCompanyAmtPrd;
        return this;
    }

    public BigDecimal getSmallCompanyAmtPrd() {
        return smallCompanyAmtPrd;
    }

    public PbcReportCompanyDatDetailDto setSmallCompanyAmtPrd(BigDecimal smallCompanyAmtPrd) {
        this.smallCompanyAmtPrd = smallCompanyAmtPrd;
        return this;
    }

    public BigDecimal getMicroCompanyAmtPrd() {
        return microCompanyAmtPrd;
    }

    public PbcReportCompanyDatDetailDto setMicroCompanyAmtPrd(BigDecimal microCompanyAmtPrd) {
        this.microCompanyAmtPrd = microCompanyAmtPrd;
        return this;
    }

    public BigDecimal getOthAmtPrd() {
        return othAmtPrd;
    }

    public PbcReportCompanyDatDetailDto setOthAmtPrd(BigDecimal othAmtPrd) {
        this.othAmtPrd = othAmtPrd;
        return this;
    }

    public BigDecimal getSumAmtPrd() {
        return sumAmtPrd;
    }

    public PbcReportCompanyDatDetailDto setSumAmtPrd(BigDecimal sumAmtPrd) {
        this.sumAmtPrd = sumAmtPrd;
        return this;
    }

    public Integer getLargeCompanyAmtDiff() {
        return largeCompanyAmtDiff;
    }

    public PbcReportCompanyDatDetailDto setLargeCompanyAmtDiff(Integer largeCompanyAmtDiff) {
        this.largeCompanyAmtDiff = largeCompanyAmtDiff;
        return this;
    }

    public Integer getMediumCompanyAmtDiff() {
        return mediumCompanyAmtDiff;
    }

    public PbcReportCompanyDatDetailDto setMediumCompanyAmtDiff(Integer mediumCompanyAmtDiff) {
        this.mediumCompanyAmtDiff = mediumCompanyAmtDiff;
        return this;
    }

    public Integer getSmallCompanyAmtDiff() {
        return smallCompanyAmtDiff;
    }

    public PbcReportCompanyDatDetailDto setSmallCompanyAmtDiff(Integer smallCompanyAmtDiff) {
        this.smallCompanyAmtDiff = smallCompanyAmtDiff;
        return this;
    }

    public Integer getMicroCompanyAmtDiff() {
        return microCompanyAmtDiff;
    }

    public PbcReportCompanyDatDetailDto setMicroCompanyAmtDiff(Integer microCompanyAmtDiff) {
        this.microCompanyAmtDiff = microCompanyAmtDiff;
        return this;
    }

    public Integer getOthAmtDiff() {
        return othAmtDiff;
    }

    public PbcReportCompanyDatDetailDto setOthAmtDiff(Integer othAmtDiff) {
        this.othAmtDiff = othAmtDiff;
        return this;
    }

    public Integer getSumAmtDiff() {
        return sumAmtDiff;
    }

    public PbcReportCompanyDatDetailDto setSumAmtDiff(Integer sumAmtDiff) {
        this.sumAmtDiff = sumAmtDiff;
        return this;
    }
}