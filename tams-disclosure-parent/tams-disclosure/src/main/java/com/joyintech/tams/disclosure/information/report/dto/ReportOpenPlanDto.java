package com.joyintech.tams.disclosure.information.report.dto;

import java.math.BigDecimal;

/**
 * 开放、净值及分红公告-后续开放日数据
 */
public class ReportOpenPlanDto {
    /*开放、净值及分红公告-后续开放日数据 start*/
    /*申购/赎回起止日*/
    private String nextRange;
    /*申购/赎回确认日*/
    private String nextMdate;
    /*赎回资金到账日*/
    private String nextSettday;

    /*开放、净值及分红公告-后续开放日数据 end*/

    /*开放期起始日*/
    private String openStartDate;
    /*开放期结束日*/
    private String openEndDate;
    /*产品冷静期*/
    private String collingoffPeriod;
    /*运营起始日*/
    private String operationStartDate;
    /*自动赎回日*/
    private String autoRedeemDate;
    /*客户持有天数*/
    private String holdDays;
    /*申购确认单位净值（元)/申购价格（元/份）*/
    private String unitValue;
    /*每份额累计净值（元）*/
    private String unitValueT;
    /*赎回确认单位净值（元)/赎回价格（元/份）*/
    private String redValue;
    /*申购价格（元/份）*/
    private String subValue;
    /*年化收益率*/
    private String yield;
    /*本期封闭期*/
    private String range;
    /*资金到账日*/
    private String settDate;

    private BigDecimal aboveAmt;

    private BigDecimal affirmAmt;

    private BigDecimal affirmNum;

    public String getNextRange() {
        return nextRange;
    }

    public void setNextRange(String nextRange) {
        this.nextRange = nextRange;
    }

    public String getNextMdate() {
        return nextMdate;
    }

    public void setNextMdate(String nextMdate) {
        this.nextMdate = nextMdate;
    }

    public String getNextSettday() {
        return nextSettday;
    }

    public void setNextSettday(String nextSettday) {
        this.nextSettday = nextSettday;
    }

    public String getOpenStartDate() {
        return openStartDate;
    }

    public void setOpenStartDate(String openStartDate) {
        this.openStartDate = openStartDate;
    }

    public String getOpenEndDate() {
        return openEndDate;
    }

    public void setOpenEndDate(String openEndDate) {
        this.openEndDate = openEndDate;
    }

    public String getCollingoffPeriod() {
        return collingoffPeriod;
    }

    public void setCollingoffPeriod(String collingoffPeriod) {
        this.collingoffPeriod = collingoffPeriod;
    }

    public String getOperationStartDate() {
        return operationStartDate;
    }

    public void setOperationStartDate(String operationStartDate) {
        this.operationStartDate = operationStartDate;
    }

    public String getAutoRedeemDate() {
        return autoRedeemDate;
    }

    public void setAutoRedeemDate(String autoRedeemDate) {
        this.autoRedeemDate = autoRedeemDate;
    }

    public String getHoldDays() {
        return holdDays;
    }

    public void setHoldDays(String holdDays) {
        this.holdDays = holdDays;
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

    public String getRedValue() {
        return redValue;
    }

    public void setRedValue(String redValue) {
        this.redValue = redValue;
    }

    public String getSubValue() {
        return subValue;
    }

    public void setSubValue(String subValue) {
        this.subValue = subValue;
    }

    public String getYield() {
        return yield;
    }

    public void setYield(String yield) {
        this.yield = yield;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getSettDate() {
        return settDate;
    }

    public void setSettDate(String settDate) {
        this.settDate = settDate;
    }

    public BigDecimal getAboveAmt() {
        return aboveAmt;
    }

    public void setAboveAmt(BigDecimal aboveAmt) {
        this.aboveAmt = aboveAmt;
    }

    public BigDecimal getAffirmAmt() {
        return affirmAmt;
    }

    public void setAffirmAmt(BigDecimal affirmAmt) {
        this.affirmAmt = affirmAmt;
    }

    public BigDecimal getAffirmNum() {
        return affirmNum;
    }

    public void setAffirmNum(BigDecimal affirmNum) {
        this.affirmNum = affirmNum;
    }
}
