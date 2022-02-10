package com.joyintech.tams.svreport.pbc.assetdebt.dto;

import java.math.BigDecimal;

public class PbcReportAssetConfigurationDto {

    /**
     * 明细统计uuid
     */
    private String detailuuid;

    /**
     * 报表类型 4-1 资管产品资产配置情况
     */
    private String reporttype;

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

    public String getDetailuuid() {
        return detailuuid;
    }

    public void setDetailuuid(String detailuuid) {
        this.detailuuid = detailuuid;
    }

    public String getReporttype() {
        return reporttype;
    }

    public void setReporttype(String reporttype) {
        this.reporttype = reporttype;
    }

    public String getPropertycode() {
        return propertycode;
    }

    public void setPropertycode(String propertycode) {
        this.propertycode = propertycode;
    }

    public String getReportuuid() {
        return reportuuid;
    }

    public void setReportuuid(String reportuuid) {
        this.reportuuid = reportuuid;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
