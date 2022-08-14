package com.joyintech.tams.disclosure.busicommon.report.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 每周净值公告（公募）/（私募）
 */
public class ReportMzjz001Dto {
   private String titleName;
   private String valueDateT;
   private String prodName;
   private String regCode;
   private String innerProdId;
   private String setDateT;
   private String mDateT;
   private String unitValue;
   private String unitValueT;
   private String assetsValue;
   private String announceT;
    /*增加公告期*/
    private String enddate;

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

   private Date setDate;
   private Date mDate;

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getValueDateT() {
        return valueDateT;
    }

    public void setValueDateT(String valueDateT) {
        this.valueDateT = valueDateT;
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

    public String getInnerProdId() {
        return innerProdId;
    }

    public void setInnerProdId(String innerProdId) {
        this.innerProdId = innerProdId;
    }

    public String getSetDateT() {
        return setDateT;
    }

    public void setSetDateT(String setDateT) {
        this.setDateT = setDateT;
    }

    public String getmDateT() {
        return mDateT;
    }

    public void setmDateT(String mDateT) {
        this.mDateT = mDateT;
    }

    public String getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(String unitValue) {
        this.unitValue = unitValue;
    }

    public String getUnitValueT() {
        return unitValueT;
    }

    public void setUnitValueT(String unitValueT) {
        this.unitValueT = unitValueT;
    }

    public String getAssetsValue() {
        return assetsValue;
    }

    public void setAssetsValue(String assetsValue) {
        this.assetsValue = assetsValue;
    }

    public String getAnnounceT() {
        return announceT;
    }

    public void setAnnounceT(String announceT) {
        this.announceT = announceT;
    }

    public Date getSetDate() {
        return setDate;
    }

    public void setSetDate(Date setDate) {
        this.setDate = setDate;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }
}
