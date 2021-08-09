package com.joyintech.tams.svreport.pbc.classic.newpbcreport.dto;

import org.apache.poi.hssf.usermodel.HSSFSheet;

public class ReportDealParamDto {

    private String datZipCode;

    private String reportUUID;

    private String statusVdate;

    private HSSFSheet hssfSheet;

    private boolean hasRecord;

    private String orgCode;

    public String getDatZipCode() {
        return datZipCode;
    }

    public void setDatZipCode(String datZipCode) {
        this.datZipCode = datZipCode;
    }

    public String getReportUUID() {
        return reportUUID;
    }

    public void setReportUUID(String reportUUID) {
        this.reportUUID = reportUUID;
    }

    public HSSFSheet getHssfSheet() {
        return hssfSheet;
    }

    public void setHssfSheet(HSSFSheet hssfSheet) {
        this.hssfSheet = hssfSheet;
    }

    public boolean isHasRecord() {
        return hasRecord;
    }

    public void setHasRecord(boolean hasRecord) {
        this.hasRecord = hasRecord;
    }

    public String getStatusVdate() {
        return statusVdate;
    }

    public void setStatusVdate(String statusVdate) {
        this.statusVdate = statusVdate;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }
}
