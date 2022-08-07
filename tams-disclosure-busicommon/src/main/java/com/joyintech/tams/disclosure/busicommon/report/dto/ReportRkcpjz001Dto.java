package com.joyintech.tams.disclosure.busicommon.report.dto;

import java.util.Date;
import java.util.List;

/**
 * 日开产品净值公告
 */
public class ReportRkcpjz001Dto {
    /*标题名：普通产品：产品名称 分级产品：产品名称+（子产品代码）*/
    private String titleName;
    /*净值日期*/
    private String valueDate;
    /*产品名称：若“是否是分级产品”为“是”，取产品名称+子产品名称；若“是否分级产品”为“否”取产品名称*/
    private String prodName;
    /*产品登记编码*/
    private String regCode;
    /*内部销售代码*/
    private String prodId;
    /*公告日*/
    private String announceT;
    /*增加公告期*/
    private String enddate;

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }
    /*净值信息*/
    private List<ReportValueDto> list;

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getValueDate() {
        return valueDate;
    }

    public void setValueDate(String valueDate) {
        this.valueDate = valueDate;
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

    public List<ReportValueDto> getList() {
        return list;
    }

    public void setList(List<ReportValueDto> list) {
        this.list = list;
    }

    public String getAnnounceT() {
        return announceT;
    }

    public void setAnnounceT(String announceT) {
        this.announceT = announceT;
    }
}
