package com.joyintech.tams.disclosure.information.report.dto;

import java.util.Date;
import java.util.List;

/**
 * 周期型产品开放公告（公募产品）、周期型产品开放公告（公募产品-单赎回）
 */
public class ReportKfjzgg001Dto {
    private String titleName;
    private String openStartDateT;
    private String openEndDateT;
    private String prodName;
    private String regCode;
    private String prodId;
    private Date setDate;
    private String setDateT;
    private List<ReportOpenPlanDto> openPlanList;
    private List<ReportOpenPlanDto> hisValueList;
    private String announceT;
    private String times;

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getOpenStartDateT() {
        return openStartDateT;
    }

    public void setOpenStartDateT(String openStartDateT) {
        this.openStartDateT = openStartDateT;
    }

    public String getOpenEndDateT() {
        return openEndDateT;
    }

    public void setOpenEndDateT(String openEndDateT) {
        this.openEndDateT = openEndDateT;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getRegCode() {
        return regCode;
    }

    public void setRegCode(String regCode) {
        this.regCode = regCode;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public Date getSetDate() {
        return setDate;
    }

    public void setSetDate(Date setDate) {
        this.setDate = setDate;
    }

    public String getSetDateT() {
        return setDateT;
    }

    public void setSetDateT(String setDateT) {
        this.setDateT = setDateT;
    }

    public List<ReportOpenPlanDto> getOpenPlanList() {
        return openPlanList;
    }

    public void setOpenPlanList(List<ReportOpenPlanDto> openPlanList) {
        this.openPlanList = openPlanList;
    }

    public List<ReportOpenPlanDto> getHisValueList() {
        return hisValueList;
    }

    public void setHisValueList(List<ReportOpenPlanDto> hisValueList) {
        this.hisValueList = hisValueList;
    }

    public String getAnnounceT() {
        return announceT;
    }

    public void setAnnounceT(String announceT) {
        this.announceT = announceT;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }
}
