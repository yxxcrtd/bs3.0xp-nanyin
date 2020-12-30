package com.joyintech.service.report.regulatoryreporting.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class ShareFilePbcBalanceOrg extends BaseEntity {
    /**
     * 明细统计uuid
     */
    private String detailuuid;

    /**
     * 报表类型,B_2
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
     * 比上月
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal mAmt;

    /**
     * 比年初
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yAmt;

    /**
     * 同比增速%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tbYiled;

    /**
     * 资产类型
     */
    private String assettype;

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
     * 取得 报表类型,B_2
     * @return 报表类型,B_2
     */
    public String getReporttype() {
        return reporttype;
    }

    /**
     * 设置 报表类型,B_2
     * @param reporttype 报表类型,B_2
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
     * 取得 比上月
     * @return 比上月
     */
    public BigDecimal getmAmt() {
        return mAmt;
    }

    /**
     * 设置 比上月
     * @param mAmt 比上月
     */
    public void setmAmt(BigDecimal mAmt) {
        this.mAmt = mAmt;
    }

    /**
     * 取得 比年初
     * @return 比年初
     */
    public BigDecimal getyAmt() {
        return yAmt;
    }

    /**
     * 设置 比年初
     * @param yAmt 比年初
     */
    public void setyAmt(BigDecimal yAmt) {
        this.yAmt = yAmt;
    }

    /**
     * 取得 同比增速%
     * @return 同比增速%
     */
    public BigDecimal getTbYiled() {
        return tbYiled;
    }

    /**
     * 设置 同比增速%
     * @param tbYiled 同比增速%
     */
    public void setTbYiled(BigDecimal tbYiled) {
        this.tbYiled = tbYiled;
    }

    /**
     * 取得 资产类型
     * @return 资产类型
     */
    public String getAssettype() {
        return assettype;
    }

    /**
     * 设置 资产类型
     * @param assettype 资产类型
     */
    public void setAssettype(String assettype) {
        this.assettype = assettype == null ? null : assettype.trim();
    }
}