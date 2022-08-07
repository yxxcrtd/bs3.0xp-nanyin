package com.joyintech.tams.disclosure.busicommon.report.dto;

import java.util.Date;
import java.util.List;

/**
 * 周期型产品开放公告（公募产品）、周期型产品开放公告（公募产品-单赎回）
 */
public class ReportZqxkfgg001Dto {
    private String titleName;
    private String announceT;
    private String prodName;
    private String regCode;
    private String prodId;
    private Date setDate;
    private String setDateT;
    /*增加公告期*/
    private String enddate;

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    private List<ReportOpenPlanDto> openList;
    private List<ReportOpenPlanDto> openHisList;

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getAnnounceT() {
        return announceT;
    }

    public void setAnnounceT(String announceT) {
        this.announceT = announceT;
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

    public List<ReportOpenPlanDto> getOpenList() {
        return openList;
    }

    public void setOpenList(List<ReportOpenPlanDto> openList) {
        this.openList = openList;
    }

    public List<ReportOpenPlanDto> getOpenHisList() {
        return openHisList;
    }

    public void setOpenHisList(List<ReportOpenPlanDto> openHisList) {
        this.openHisList = openHisList;
    }
}
