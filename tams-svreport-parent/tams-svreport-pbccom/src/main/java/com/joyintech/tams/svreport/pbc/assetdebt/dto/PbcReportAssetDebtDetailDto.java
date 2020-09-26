package com.joyintech.tams.svreport.pbc.assetdebt.dto;

import java.math.BigDecimal;

/**
 * 描述:PBC_REPORT_ASSETDEBT_DETAIL表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-03-04
 */
public class PbcReportAssetDebtDetailDto {
	 /**
     * 明细统计uuid
     */
    private String detailuuid;

    /**
     * 报表类型 PRODDEBT-资管产品资产负债统计
     */
    private String reporttype;

    /**
     * 分组类型 资产-ASSET，负债-DEBT
     */
    private String grouptype;

    /**
     * 属性代码
     */
    private String propertycode;

    /**
     * 人民币
     */
    private BigDecimal cny;

    /**
     * 人民币
     */
    private String cnyString;

    /**
     * 美元折人民币
     */
    private BigDecimal usdToCny;

    /**
     * 美元折人民币
     */
    private String usdToCnyString;

    /**
     * 欧元折人民币
     */
    private BigDecimal eurToCny;

    /**
     * 欧元折人民币
     */
    private String eurToCnyString;

    /**
     * 英镑折人民币
     */
    private BigDecimal gbpToCny;

    /**
     * 英镑折人民币
     */
    private String gbpToCnyString;

    /**
     * 日元折人民币
     */
    private BigDecimal jpyToCny;

    /**
     * 日元折人民币
     */
    private String jpyToCnyString;

    /**
     * 其他币种折人名币
     */
    private BigDecimal othToCny;

    /**
     * 其他币种折人名币
     */
    private String othToCnyString;

    /**
     * 合计
     */
    private BigDecimal total;

    /**
     * 合计
     */
    private String totalString;

    /**
     * 主表关联id
     */
    private String reportuuid;
    
    /**
     * 币种金额信息，多币种存值，人民币也存值，格式：CNY,原币金额,折人民币金额;USD,原币金额,折人民币金额
     */
    private String ccyAmount;

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
     * 报表类型 PRODDEBT-资管产品资产负债统计
     * @return REPORTTYPE 报表类型 PRODDEBT-资管产品资产负债统计
     */
    public String getReporttype() {
        return reporttype;
    }

    /**
     * 报表类型 PRODDEBT-资管产品资产负债统计
     * @param reporttype 报表类型 PRODDEBT-资管产品资产负债统计
     */
    public void setReporttype(String reporttype) {
        this.reporttype = reporttype == null ? null : reporttype.trim();
    }

    /**
     * 分组类型 资产-ASSET，负债-DEBT
     * @return GROUPTYPE 分组类型 资产-ASSET，负债-DEBT
     */
    public String getGrouptype() {
        return grouptype;
    }

    /**
     * 分组类型 资产-ASSET，负债-DEBT
     * @param grouptype 分组类型 资产-ASSET，负债-DEBT
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
     * 人民币
     * @return CNY 人民币
     */
    public BigDecimal getCny() {
        return cny;
    }

    /**
     * 人民币
     * @param cny 人民币
     */
    public void setCny(BigDecimal cny) {
        this.cny = cny;
    }

    /**
     * 美元折人民币
     * @return USD_TO_CNY 美元折人民币
     */
    public BigDecimal getUsdToCny() {
        return usdToCny;
    }

    /**
     * 美元折人民币
     * @param usdToCny 美元折人民币
     */
    public void setUsdToCny(BigDecimal usdToCny) {
        this.usdToCny = usdToCny;
    }

    /**
     * 欧元折人民币
     * @return EUR_TO_CNY 欧元折人民币
     */
    public BigDecimal getEurToCny() {
        return eurToCny;
    }

    /**
     * 欧元折人民币
     * @param eurToCny 欧元折人民币
     */
    public void setEurToCny(BigDecimal eurToCny) {
        this.eurToCny = eurToCny;
    }

    /**
     * 英镑折人民币
     * @return GBP_TO_CNY 英镑折人民币
     */
    public BigDecimal getGbpToCny() {
        return gbpToCny;
    }

    /**
     * 英镑折人民币
     * @param gbpToCny 英镑折人民币
     */
    public void setGbpToCny(BigDecimal gbpToCny) {
        this.gbpToCny = gbpToCny;
    }

    /**
     * 日元折人民币
     * @return JPY_TO_CNY 日元折人民币
     */
    public BigDecimal getJpyToCny() {
        return jpyToCny;
    }

    /**
     * 日元折人民币
     * @param jpyToCny 日元折人民币
     */
    public void setJpyToCny(BigDecimal jpyToCny) {
        this.jpyToCny = jpyToCny;
    }

    /**
     * 其他币种折人名币
     * @return OTH_TO_CNY 其他币种折人名币
     */
    public BigDecimal getOthToCny() {
        return othToCny;
    }

    /**
     * 其他币种折人名币
     * @param othToCny 其他币种折人名币
     */
    public void setOthToCny(BigDecimal othToCny) {
        this.othToCny = othToCny;
    }

    /**
     * 合计
     * @return TOTAL 合计
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * 合计
     * @param total 合计
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
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

    public String getCnyString() {
        return cnyString;
    }

    public void setCnyString(String cnyString) {
        this.cnyString = cnyString;
    }

    public String getUsdToCnyString() {
        return usdToCnyString;
    }

    public void setUsdToCnyString(String usdToCnyString) {
        this.usdToCnyString = usdToCnyString;
    }

    public String getEurToCnyString() {
        return eurToCnyString;
    }

    public void setEurToCnyString(String eurToCnyString) {
        this.eurToCnyString = eurToCnyString;
    }

    public String getGbpToCnyString() {
        return gbpToCnyString;
    }

    public void setGbpToCnyString(String gbpToCnyString) {
        this.gbpToCnyString = gbpToCnyString;
    }

    public String getJpyToCnyString() {
        return jpyToCnyString;
    }

    public void setJpyToCnyString(String jpyToCnyString) {
        this.jpyToCnyString = jpyToCnyString;
    }

    public String getOthToCnyString() {
        return othToCnyString;
    }

    public void setOthToCnyString(String othToCnyString) {
        this.othToCnyString = othToCnyString;
    }

    public String getTotalString() {
        return totalString;
    }

    public void setTotalString(String totalString) {
        this.totalString = totalString;
    }

    public String getCcyAmount() {
        return ccyAmount;
    }

    public void setCcyAmount(String ccyAmount) {
        this.ccyAmount = ccyAmount;
    }
}