package com.joyintech.tams.svreport.chb.finassetliabilitystatement.dto;


import com.joyintech.framework.base.BaseEntity;

/**
 * 理财产品资产负债表查询Dto
 * 
 * @author WanShiwei
 * @version 1.0 2021年4月20日
 * @see
 * @since 1.0
 */
public class FinAssetLiabilityStatementQuery001Dto extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 产品名称
     */
    private String prodName;
    
    /**
     * 报告期
     */
    private String reportDate;
    
    /**
     * 业务日期
     */
    private String busiDate;

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

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
