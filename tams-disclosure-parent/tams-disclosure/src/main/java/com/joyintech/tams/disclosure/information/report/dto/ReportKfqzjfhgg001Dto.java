package com.joyintech.tams.disclosure.information.report.dto;

/**
 * 开放、净值及分红公告
 * zhengbaosheng
 */
public class ReportKfqzjfhgg001Dto {
    /**
     * 标题名：普通产品：产品名称
     *       分级产品：产品名称+（子产品代码）
     */
    private String titleName;
    /**
     * 收益分配基准日：XXXX年XX月XX日
     * */
    private String baseDateT;
    /**
     * 产品名称：若“是否是分级产品”为“是”，取产品名称+子产品名称；若“是否分级产品”为“否”取产品名称
     */
    private String prodName;
    /**
     *产品登记编码
     */
    private String regCode;
    /**
     * 子产品代码
     */
    private String prodId;
    /**
     * 子产品成立日：XXXX年XX月XX日
     */
    private String setDateT;
    /**
     * 开放周期的开放起始日：XXXX年XX月XX日
     */
    private String subDateT;
    /**
     * 收益分配基准日份额净值（元）
     */
    private String unitValue;
    /**
     * 本次分红方案（元/1份份额）
     */
    private String unitProfit;
    /**
     * 收益分配基准日||除权日：XXXX-XX-XX
     */
    private String baseDate;
    /**
     * 现金红利发放日:XXXX-XX-XX
     */
    private String payDate;
    /**
     *后续申购/赎回起止日
     */
    private String nextRange;
    /**
     *后续申购/赎回确认日
     */
    private String nextMdate;
    /**
     *后续赎回资金到账日
     */
    private String nextSetday;
    /**
     *当期申购/赎回确认日
     */
    private String curMdate;
    /**
     *当期期末每份额净值（元）
     */
    private String munitValue;
    /**
     *当期每份额累计净值（元）
     */
    private String munitValueT;
    /**
     *当期赎回价格（元/份）
     */
    private String redValue;
    /**
     *当期申购价格（元/份）
     */
    private String subValue;
    /**
     *当期年化收益率
     */
    private String yield;
    /**
     *当期当期封闭期
     */
    private String curRange;
    /**
     *公告日：XXXX年XX月XX日
     */
    private String announceT;

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getBaseDateT() {
        return baseDateT;
    }

    public void setBaseDateT(String baseDateT) {
        this.baseDateT = baseDateT;
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

    public String getSetDateT() {
        return setDateT;
    }

    public void setSetDateT(String setDateT) {
        this.setDateT = setDateT;
    }

    public String getSubDateT() {
        return subDateT;
    }

    public void setSubDateT(String subDateT) {
        this.subDateT = subDateT;
    }

    public String getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(String unitValue) {
        this.unitValue = unitValue;
    }

    public String getUnitProfit() {
        return unitProfit;
    }

    public void setUnitProfit(String unitProfit) {
        this.unitProfit = unitProfit;
    }

    public String getBaseDate() {
        return baseDate;
    }

    public void setBaseDate(String baseDate) {
        this.baseDate = baseDate;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

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

    public String getNextSetday() {
        return nextSetday;
    }

    public void setNextSetday(String nextSetday) {
        this.nextSetday = nextSetday;
    }

    public String getCurMdate() {
        return curMdate;
    }

    public void setCurMdate(String curMdate) {
        this.curMdate = curMdate;
    }

    public String getMunitValue() {
        return munitValue;
    }

    public void setMunitValue(String munitValue) {
        this.munitValue = munitValue;
    }

    public String getMunitValueT() {
        return munitValueT;
    }

    public void setMunitValueT(String munitValueT) {
        this.munitValueT = munitValueT;
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

    public String getCurRange() {
        return curRange;
    }

    public void setCurRange(String curRange) {
        this.curRange = curRange;
    }

    public String getAnnounceT() {
        return announceT;
    }

    public void setAnnounceT(String announceT) {
        this.announceT = announceT;
    }
}
