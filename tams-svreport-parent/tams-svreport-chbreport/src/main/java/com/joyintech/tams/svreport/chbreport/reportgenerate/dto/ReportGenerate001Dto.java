package com.joyintech.tams.svreport.chbreport.reportgenerate.dto;

import com.joyintech.framework.base.BaseEntity;

/**
 * @ClassName ReportGenerate001Dto
 * @Description: 非标投资运行报告查询dto
 * @Author yanya
 * @Date 2021-05-06
 * @Version V1.0
 **/
public class ReportGenerate001Dto extends BaseEntity {
    private static final long serialVersionUID = -6113174801016544464L;

    /**
     * 报告id
     */
    private String repId;
    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 开始日期
     */
    private String startDate;

    /**
     * 结束日期
     */
    private String endDate;

    /**
     * 报表类型
     */
    private String reportType;


    public String getRepId() {
        return repId;
    }

    public void setRepId(String repId) {
        this.repId = repId;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }
}
