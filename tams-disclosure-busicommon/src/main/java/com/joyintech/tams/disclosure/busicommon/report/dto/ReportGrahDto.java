package com.joyintech.tams.disclosure.busicommon.report.dto;

/**
 * 曲线图对象
 */
public class ReportGrahDto {
    private String cDate;

    private String value;

    private String totalValue;

    public String getcDate() {
        return cDate;
    }

    public void setcDate(String cDate) {
        this.cDate = cDate;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(String totalValue) {
        this.totalValue = totalValue;
    }
}
