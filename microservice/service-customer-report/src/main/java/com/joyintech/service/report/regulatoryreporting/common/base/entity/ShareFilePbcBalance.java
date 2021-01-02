package com.joyintech.service.report.regulatoryreporting.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class ShareFilePbcBalance extends BaseEntity {
    /**
     * 明细统计uuid
     */
    private String detailuuid;

    /**
     * 报表类型,B_1
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
     * 余额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal balance;

    /**
     * 今年比上月
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tymAmt;

    /**
     * 去年比上月
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal qymAmt;

    /**
     * 今年比上年
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tyyAmt;

    /**
     * 去年比上年
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal qyyAmt;

    /**
     * 今年同比增速%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tyYield;

    /**
     * 去年同比增速%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal qyYield;

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
     * 取得 报表类型,B_1
     * @return 报表类型,B_1
     */
    public String getReporttype() {
        return reporttype;
    }

    /**
     * 设置 报表类型,B_1
     * @param reporttype 报表类型,B_1
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
     * 取得 余额
     * @return 余额
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * 设置 余额
     * @param balance 余额
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * 取得 今年比上月
     * @return 今年比上月
     */
    public BigDecimal getTymAmt() {
        return tymAmt;
    }

    /**
     * 设置 今年比上月
     * @param tymAmt 今年比上月
     */
    public void setTymAmt(BigDecimal tymAmt) {
        this.tymAmt = tymAmt;
    }

    /**
     * 取得 去年比上月
     * @return 去年比上月
     */
    public BigDecimal getQymAmt() {
        return qymAmt;
    }

    /**
     * 设置 去年比上月
     * @param qymAmt 去年比上月
     */
    public void setQymAmt(BigDecimal qymAmt) {
        this.qymAmt = qymAmt;
    }

    /**
     * 取得 今年比上年
     * @return 今年比上年
     */
    public BigDecimal getTyyAmt() {
        return tyyAmt;
    }

    /**
     * 设置 今年比上年
     * @param tyyAmt 今年比上年
     */
    public void setTyyAmt(BigDecimal tyyAmt) {
        this.tyyAmt = tyyAmt;
    }

    /**
     * 取得 去年比上年
     * @return 去年比上年
     */
    public BigDecimal getQyyAmt() {
        return qyyAmt;
    }

    /**
     * 设置 去年比上年
     * @param qyyAmt 去年比上年
     */
    public void setQyyAmt(BigDecimal qyyAmt) {
        this.qyyAmt = qyyAmt;
    }

    /**
     * 取得 今年同比增速%
     * @return 今年同比增速%
     */
    public BigDecimal getTyYield() {
        return tyYield;
    }

    /**
     * 设置 今年同比增速%
     * @param tyYield 今年同比增速%
     */
    public void setTyYield(BigDecimal tyYield) {
        this.tyYield = tyYield;
    }

    /**
     * 取得 去年同比增速%
     * @return 去年同比增速%
     */
    public BigDecimal getQyYield() {
        return qyYield;
    }

    /**
     * 设置 去年同比增速%
     * @param qyYield 去年同比增速%
     */
    public void setQyYield(BigDecimal qyYield) {
        this.qyYield = qyYield;
    }
}