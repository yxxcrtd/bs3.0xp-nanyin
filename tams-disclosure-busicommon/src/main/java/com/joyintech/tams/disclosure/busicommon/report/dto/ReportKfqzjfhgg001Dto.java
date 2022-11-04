package com.joyintech.tams.disclosure.busicommon.report.dto;

import java.util.Date;
import java.util.List;

/**
 * 开放、净值及分红公告
 * zhengbaosheng
 */
public class ReportKfqzjfhgg001Dto {
    /**
     * 标题名：普通产品：产品名称 分级产品：产品名称+（子产品代码）
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
    private String innerProdId;
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
     *公告日：XXXX年XX月XX日
     */
    private String announceT;
    /*增加公告期*/
    private String enddate;

    private String pprodId;

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }
    /**
     * 后续开放期安排
     */
    private List<ReportOpenPlanDto> openPlanList;
    /**
     * 历史净值数据
     */
    private List<ReportKfqHisBonusDto> list;

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

    public String getAnnounceT() {
        return announceT;
    }

    public void setAnnounceT(String announceT) {
        this.announceT = announceT;
    }

    public List<ReportOpenPlanDto> getOpenPlanList() {
        return openPlanList;
    }

    public void setOpenPlanList(List<ReportOpenPlanDto> openPlanList) {
        this.openPlanList = openPlanList;
    }

    public List<ReportKfqHisBonusDto> getList() {
        return list;
    }

    public void setList(List<ReportKfqHisBonusDto> list) {
        this.list = list;
    }

    public String getPprodId() {
        return pprodId;
    }

    public void setPprodId(String pprodId) {
        this.pprodId = pprodId;
    }
}
