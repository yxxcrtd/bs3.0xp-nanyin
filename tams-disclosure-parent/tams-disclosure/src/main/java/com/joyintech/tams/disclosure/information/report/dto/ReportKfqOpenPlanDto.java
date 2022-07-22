package com.joyintech.tams.disclosure.information.report.dto;

/**
 * 开放、净值及分红公告-后续开放日数据
 */
public class ReportKfqOpenPlanDto {
    /*申购/赎回起止日*/
    private String nextRange;
    /*申购/赎回确认日*/
    private String nextMdate;
    /*赎回资金到账日*/
    private String nextSettday;

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
}
