package com.joyintech.tams.disclosure.information.report.dto;

import java.util.List;

/**
 * 1.1.3.产品开放期公告/报告
 */
public class ReportKfqgg001Dto {
    /**/
    private String prodName;
    private String year;
    private String plateDate;
    private List<ReportOpenPlanDto> openList;

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPlateDate() {
        return plateDate;
    }

    public void setPlateDate(String plateDate) {
        this.plateDate = plateDate;
    }

    public List<ReportOpenPlanDto> getOpenList() {
        return openList;
    }

    public void setOpenList(List<ReportOpenPlanDto> openList) {
        this.openList = openList;
    }
}
