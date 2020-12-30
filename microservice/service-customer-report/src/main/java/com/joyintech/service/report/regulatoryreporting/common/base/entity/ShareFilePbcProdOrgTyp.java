package com.joyintech.service.report.regulatoryreporting.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class ShareFilePbcProdOrgTyp extends BaseEntity {
    /**
     * 明细统计uuid
     */
    private String detailuuid;

    /**
     * 报表类型,B_4
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
     * 只数
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prodcounts;

    /**
     * 最高收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal highYield;

    /**
     * 最低收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal lowYield;

    /**
     * 平均收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal expYield;

    /**
     * 兑付收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dqYield;

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
     * 取得 报表类型,B_4
     * @return 报表类型,B_4
     */
    public String getReporttype() {
        return reporttype;
    }

    /**
     * 设置 报表类型,B_4
     * @param reporttype 报表类型,B_4
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
     * 取得 只数
     * @return 只数
     */
    public BigDecimal getProdcounts() {
        return prodcounts;
    }

    /**
     * 设置 只数
     * @param prodcounts 只数
     */
    public void setProdcounts(BigDecimal prodcounts) {
        this.prodcounts = prodcounts;
    }

    /**
     * 取得 最高收益率
     * @return 最高收益率
     */
    public BigDecimal getHighYield() {
        return highYield;
    }

    /**
     * 设置 最高收益率
     * @param highYield 最高收益率
     */
    public void setHighYield(BigDecimal highYield) {
        this.highYield = highYield;
    }

    /**
     * 取得 最低收益率
     * @return 最低收益率
     */
    public BigDecimal getLowYield() {
        return lowYield;
    }

    /**
     * 设置 最低收益率
     * @param lowYield 最低收益率
     */
    public void setLowYield(BigDecimal lowYield) {
        this.lowYield = lowYield;
    }

    /**
     * 取得 平均收益率
     * @return 平均收益率
     */
    public BigDecimal getExpYield() {
        return expYield;
    }

    /**
     * 设置 平均收益率
     * @param expYield 平均收益率
     */
    public void setExpYield(BigDecimal expYield) {
        this.expYield = expYield;
    }

    /**
     * 取得 兑付收益率
     * @return 兑付收益率
     */
    public BigDecimal getDqYield() {
        return dqYield;
    }

    /**
     * 设置 兑付收益率
     * @param dqYield 兑付收益率
     */
    public void setDqYield(BigDecimal dqYield) {
        this.dqYield = dqYield;
    }
}