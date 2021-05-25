package com.joyintech.tams.svreport.chbreport.hxassetliabilitystatement.dto;


import com.joyintech.framework.base.BaseEntity;

/**
 * 理财产品资产负债表查询Dto
 * 
 * @author WanShiwei
 * @version 1.0 2021年4月20日
 * @see
 * @since 1.0
 */
public class HXAssetLiabilityStatementQuery001Dto extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 报告期
     */
    private String reportDate;
    
    /**
     * 业务日期
     */
    private String busiDate;

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getBusiDate() {
        return busiDate;
    }

    public void setBusiDate(String busiDate) {
        this.busiDate = busiDate;
    }
}
