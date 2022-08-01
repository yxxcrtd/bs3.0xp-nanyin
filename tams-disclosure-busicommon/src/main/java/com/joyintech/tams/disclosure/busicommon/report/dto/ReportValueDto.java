package com.joyintech.tams.disclosure.busicommon.report.dto;

/**
 * 净值相关数据
 */
public class ReportValueDto {
    /*净值日期*/
    private String valueDate;
    /*万份收益*/
    private String wYield;
    /*七日年化收率*/
    private String yield7;
    /*单位净值*/
    private String unitValue;
    /*单位累计净值*/
    private String unitValueT;
    /*申购价格*/
    private String subValue;
    /*赎回价格*/
    private String redValue;

    public String getValueDate() {
        return valueDate;
    }

    public void setValueDate(String valueDate) {
        this.valueDate = valueDate;
    }

    public String getwYield() {
        return wYield;
    }

    public void setwYield(String wYield) {
        this.wYield = wYield;
    }

    public String getYield7() {
        return yield7;
    }

    public void setYield7(String yield7) {
        this.yield7 = yield7;
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

    public String getSubValue() {
        return subValue;
    }

    public void setSubValue(String subValue) {
        this.subValue = subValue;
    }

    public String getRedValue() {
        return redValue;
    }

    public void setRedValue(String redValue) {
        this.redValue = redValue;
    }
}
