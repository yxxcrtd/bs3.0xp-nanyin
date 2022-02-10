package com.joyintech.tams.svreport.pbc.assetdebtdat.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;

public class PbcReportAssetConfigDatDto extends BaseEntity {
    /**
     * 明细统计uuid
     */
    private String detailuuid;

    /**
     * 报表类型 4_1 资管产品资产配置情况
     */
    private String reporttype;

    /**
     * 属性代码
     */
    private String propertycode;

    /**
     * 余额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal balance;

    /**
     * 主表关联id
     */
    private String reportuuid;

    /**
     * 登记表余额
     */
    private BigDecimal balancePrd;
    /**
     * 余额是否不同
     */
    private Integer balanceDiff;

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
     * 取得 报表类型 4_1 资管产品资产配置情况
     * @return 报表类型 4_1 资管产品资产配置情况
     */
    public String getReporttype() {
        return reporttype;
    }

    /**
     * 设置 报表类型 4_1 资管产品资产配置情况
     * @param reporttype 报表类型 4_1 资管产品资产配置情况
     */
    public void setReporttype(String reporttype) {
        this.reporttype = reporttype == null ? null : reporttype.trim();
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
     * 取得 主表关联id
     * @return 主表关联id
     */
    public String getReportuuid() {
        return reportuuid;
    }

    /**
     * 设置 主表关联id
     * @param reportuuid 主表关联id
     */
    public void setReportuuid(String reportuuid) {
        this.reportuuid = reportuuid == null ? null : reportuuid.trim();
    }

    public BigDecimal getBalancePrd() {
        return balancePrd;
    }

    public void setBalancePrd(BigDecimal balancePrd) {
        this.balancePrd = balancePrd;
    }

    public Integer getBalanceDiff() {
        return balanceDiff;
    }

    public void setBalanceDiff(Integer balanceDiff) {
        this.balanceDiff = balanceDiff;
    }
}