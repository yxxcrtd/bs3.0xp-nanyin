package com.joyintech.service.report.regulatoryreporting.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class ShareFilePbcProdamtOrg extends BaseEntity {
    /**
     * 明细统计uuid
     */
    private String detailuuid;

    /**
     * 报表类型,B_7
     */
    private String reporttype;

    /**
     * 分组类型
     */
    private String grouptype;

    /**
     * 属性代码
     */
    private String propertycode;

    /**
     * 主表主键
     */
    private String reportuuid;

    /**
     * 今年(当月募集金额)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dymjtmAmt;

    /**
     * 去年(当月募集金额)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dymjymAmt;

    /**
     * 同比增减%(当月募集金额)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dymjtbYiled;

    /**
     * 今年(年累计募集金额)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal ljmjtmAmt;

    /**
     * 去年(年累计募集金额)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal ljmjymAmt;

    /**
     * 同比增减%(年累计募集金额)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal ljmjtbYiled;

    /**
     * 取得 明细统计uuid
     * @return 明细统计uuid
     */
    public String getDetailuuid() {
        return detailuuid;
    }

    /**
     * 设置 明细统计uuid
     * @param detailuuid 明细统计uuid
     */
    public void setDetailuuid(String detailuuid) {
        this.detailuuid = detailuuid == null ? null : detailuuid.trim();
    }

    /**
     * 取得 报表类型,B_7
     * @return 报表类型,B_7
     */
    public String getReporttype() {
        return reporttype;
    }

    /**
     * 设置 报表类型,B_7
     * @param reporttype 报表类型,B_7
     */
    public void setReporttype(String reporttype) {
        this.reporttype = reporttype == null ? null : reporttype.trim();
    }

    /**
     * 取得 分组类型
     * @return 分组类型
     */
    public String getGrouptype() {
        return grouptype;
    }

    /**
     * 设置 分组类型
     * @param grouptype 分组类型
     */
    public void setGrouptype(String grouptype) {
        this.grouptype = grouptype == null ? null : grouptype.trim();
    }

    /**
     * 取得 属性代码
     * @return 属性代码
     */
    public String getPropertycode() {
        return propertycode;
    }

    /**
     * 设置 属性代码
     * @param propertycode 属性代码
     */
    public void setPropertycode(String propertycode) {
        this.propertycode = propertycode == null ? null : propertycode.trim();
    }

    /**
     * 取得 主表主键
     * @return 主表主键
     */
    public String getReportuuid() {
        return reportuuid;
    }

    /**
     * 设置 主表主键
     * @param reportuuid 主表主键
     */
    public void setReportuuid(String reportuuid) {
        this.reportuuid = reportuuid == null ? null : reportuuid.trim();
    }

    /**
     * 取得 今年(当月募集金额)
     * @return 今年(当月募集金额)
     */
    public BigDecimal getDymjtmAmt() {
        return dymjtmAmt;
    }

    /**
     * 设置 今年(当月募集金额)
     * @param dymjtmAmt 今年(当月募集金额)
     */
    public void setDymjtmAmt(BigDecimal dymjtmAmt) {
        this.dymjtmAmt = dymjtmAmt;
    }

    /**
     * 取得 去年(当月募集金额)
     * @return 去年(当月募集金额)
     */
    public BigDecimal getDymjymAmt() {
        return dymjymAmt;
    }

    /**
     * 设置 去年(当月募集金额)
     * @param dymjymAmt 去年(当月募集金额)
     */
    public void setDymjymAmt(BigDecimal dymjymAmt) {
        this.dymjymAmt = dymjymAmt;
    }

    /**
     * 取得 同比增减%(当月募集金额)
     * @return 同比增减%(当月募集金额)
     */
    public BigDecimal getDymjtbYiled() {
        return dymjtbYiled;
    }

    /**
     * 设置 同比增减%(当月募集金额)
     * @param dymjtbYiled 同比增减%(当月募集金额)
     */
    public void setDymjtbYiled(BigDecimal dymjtbYiled) {
        this.dymjtbYiled = dymjtbYiled;
    }

    /**
     * 取得 今年(年累计募集金额)
     * @return 今年(年累计募集金额)
     */
    public BigDecimal getLjmjtmAmt() {
        return ljmjtmAmt;
    }

    /**
     * 设置 今年(年累计募集金额)
     * @param ljmjtmAmt 今年(年累计募集金额)
     */
    public void setLjmjtmAmt(BigDecimal ljmjtmAmt) {
        this.ljmjtmAmt = ljmjtmAmt;
    }

    /**
     * 取得 去年(年累计募集金额)
     * @return 去年(年累计募集金额)
     */
    public BigDecimal getLjmjymAmt() {
        return ljmjymAmt;
    }

    /**
     * 设置 去年(年累计募集金额)
     * @param ljmjymAmt 去年(年累计募集金额)
     */
    public void setLjmjymAmt(BigDecimal ljmjymAmt) {
        this.ljmjymAmt = ljmjymAmt;
    }

    /**
     * 取得 同比增减%(年累计募集金额)
     * @return 同比增减%(年累计募集金额)
     */
    public BigDecimal getLjmjtbYiled() {
        return ljmjtbYiled;
    }

    /**
     * 设置 同比增减%(年累计募集金额)
     * @param ljmjtbYiled 同比增减%(年累计募集金额)
     */
    public void setLjmjtbYiled(BigDecimal ljmjtbYiled) {
        this.ljmjtbYiled = ljmjtbYiled;
    }
}