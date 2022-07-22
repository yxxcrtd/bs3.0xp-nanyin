package com.joyintech.tams.disclosure.information.report.dto;

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
}
