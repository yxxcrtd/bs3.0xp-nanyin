package com.joyintech.tams.exchange.common.bean.investment;

public class RiskResult {
    /**
     * 指标名称
     */

    private String eventId;

    private String kriName;

    /**
     * 指标类型
     */
    private String kriType;

    /**
     * 组合（包）名称
     */
    private String portfolioName;

    /**
     * 审批线
     */
    private String approveLine;

    /**
     * 预警线
     */
    private String warningLine;

    /**
     * 禁止线
     */
    private String forbiddenLn;

    /**
     * 原值
     */
    private String oldValue;

    /**
     * 趋势
     */
    private String kriTrend;

    /**
     * 联合监控状态
     */
    private String uniteStatus;

    /**
     * 指标计算值
     */
    private String kriValue;

    /**
     * 指标状态
     */
    private String kriStatus;

    /**
     * 指标代码
     */
    private String kriCode;
    private String portfolioCode;//产品代码
    private String kriTypeCn;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getKriName() {
        return kriName;
    }

    public void setKriName(String kriName) {
        this.kriName = kriName;
    }

    public String getKriType() {
        return kriType;
    }

    public void setKriType(String kriType) {
        this.kriType = kriType;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public String getApproveLine() {
        return approveLine;
    }

    public void setApproveLine(String approveLine) {
        this.approveLine = approveLine;
    }

    public String getWarningLine() {
        return warningLine;
    }

    public void setWarningLine(String warningLine) {
        this.warningLine = warningLine;
    }

    public String getForbiddenLn() {
        return forbiddenLn;
    }

    public void setForbiddenLn(String forbiddenLn) {
        this.forbiddenLn = forbiddenLn;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getKriTrend() {
        return kriTrend;
    }

    public void setKriTrend(String kriTrend) {
        this.kriTrend = kriTrend;
    }

    public String getUniteStatus() {
        return uniteStatus;
    }

    public void setUniteStatus(String uniteStatus) {
        this.uniteStatus = uniteStatus;
    }

    public String getKriValue() {
        return kriValue;
    }

    public void setKriValue(String kriValue) {
        this.kriValue = kriValue;
    }

    public String getKriStatus() {
        return kriStatus;
    }

    public void setKriStatus(String kriStatus) {
        this.kriStatus = kriStatus;
    }

    public String getKriCode() {
        return kriCode;
    }

    public void setKriCode(String kriCode) {
        this.kriCode = kriCode;
    }

    public String getPortfolioCode() {
        return portfolioCode;
    }

    public void setPortfolioCode(String portfolioCode) {
        this.portfolioCode = portfolioCode;
    }

    public String getKriTypeCn() {
        return kriTypeCn;
    }

    public void setKriTypeCn(String kriTypeCn) {
        this.kriTypeCn = kriTypeCn;
    }
}
