package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class PtlPortfolioValuation extends PtlPortfolioValuationKey {
    /**
     * 估值币种
     */
    private String ccy;

    /**
     * 当日资产净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyAssetNetvalue;

    /**
     * 当日资产总值，暂时不用
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyAssetValue;

    /**
     * 当日份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyShareAmt;

    /**
     * 当日单位净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyUnitValue;

    /**
     * 当日累计净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyUnitValueSum;

    /**
     * 基准收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal benchmarkRate;

    /**
     * 7日年化收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yield7;

    /**
     * 当日应付客户收益
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyUnpaidIntincexp;

    /**
     * 当日未分配利润
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyUnpaidProfit;

    /**
     * 待清算资金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal delayPayAmt;

    /**
     * 累计分红金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal totBounsAmt;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyYield;

    /**
     * 取得 估值币种
     * @return 估值币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 估值币种
     * @param ccy 估值币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 当日资产净值
     * @return 当日资产净值
     */
    public BigDecimal getTdyAssetNetvalue() {
        return tdyAssetNetvalue;
    }

    /**
     * 设置 当日资产净值
     * @param tdyAssetNetvalue 当日资产净值
     */
    public void setTdyAssetNetvalue(BigDecimal tdyAssetNetvalue) {
        this.tdyAssetNetvalue = tdyAssetNetvalue;
    }

    /**
     * 取得 当日资产总值，暂时不用
     * @return 当日资产总值，暂时不用
     */
    public BigDecimal getTdyAssetValue() {
        return tdyAssetValue;
    }

    /**
     * 设置 当日资产总值，暂时不用
     * @param tdyAssetValue 当日资产总值，暂时不用
     */
    public void setTdyAssetValue(BigDecimal tdyAssetValue) {
        this.tdyAssetValue = tdyAssetValue;
    }

    /**
     * 取得 当日份额
     * @return 当日份额
     */
    public BigDecimal getTdyShareAmt() {
        return tdyShareAmt;
    }

    /**
     * 设置 当日份额
     * @param tdyShareAmt 当日份额
     */
    public void setTdyShareAmt(BigDecimal tdyShareAmt) {
        this.tdyShareAmt = tdyShareAmt;
    }

    /**
     * 取得 当日单位净值
     * @return 当日单位净值
     */
    public BigDecimal getTdyUnitValue() {
        return tdyUnitValue;
    }

    /**
     * 设置 当日单位净值
     * @param tdyUnitValue 当日单位净值
     */
    public void setTdyUnitValue(BigDecimal tdyUnitValue) {
        this.tdyUnitValue = tdyUnitValue;
    }

    /**
     * 取得 当日累计净值
     * @return 当日累计净值
     */
    public BigDecimal getTdyUnitValueSum() {
        return tdyUnitValueSum;
    }

    /**
     * 设置 当日累计净值
     * @param tdyUnitValueSum 当日累计净值
     */
    public void setTdyUnitValueSum(BigDecimal tdyUnitValueSum) {
        this.tdyUnitValueSum = tdyUnitValueSum;
    }

//    /**
//     * 取得 基准收益率
//     * @return 基准收益率
//     */
//    public BigDecimal getBenchmarkRate() {
//        return benchmarkRate;
//    }
//
//    /**
//     * 设置 基准收益率
//     * @param benchmarkRate 基准收益率
//     */
//    public void setBenchmarkRate(BigDecimal benchmarkRate) {
//        this.benchmarkRate = benchmarkRate;
//    }
//
//    /**
//     * 取得 7日年化收益率
//     * @return 7日年化收益率
//     */
//    public BigDecimal getYield7() {
//        return yield7;
//    }
//
//    /**
//     * 设置 7日年化收益率
//     * @param yield7 7日年化收益率
//     */
//    public void setYield7(BigDecimal yield7) {
//        this.yield7 = yield7;
//    }

    /**
     * 取得 当日应付客户收益
     * @return 当日应付客户收益
     */
    public BigDecimal getTdyUnpaidIntincexp() {
        return tdyUnpaidIntincexp;
    }

    /**
     * 设置 当日应付客户收益
     * @param tdyUnpaidIntincexp 当日应付客户收益
     */
    public void setTdyUnpaidIntincexp(BigDecimal tdyUnpaidIntincexp) {
        this.tdyUnpaidIntincexp = tdyUnpaidIntincexp;
    }

    /**
     * 取得 当日未分配利润
     * @return 当日未分配利润
     */
    public BigDecimal getTdyUnpaidProfit() {
        return tdyUnpaidProfit;
    }

    /**
     * 设置 当日未分配利润
     * @param tdyUnpaidProfit 当日未分配利润
     */
    public void setTdyUnpaidProfit(BigDecimal tdyUnpaidProfit) {
        this.tdyUnpaidProfit = tdyUnpaidProfit;
    }

    /**
     * 取得 待清算资金
     * @return 待清算资金
     */
    public BigDecimal getDelayPayAmt() {
        return delayPayAmt;
    }

    /**
     * 设置 待清算资金
     * @param delayPayAmt 待清算资金
     */
    public void setDelayPayAmt(BigDecimal delayPayAmt) {
        this.delayPayAmt = delayPayAmt;
    }

    /**
     * 取得 累计分红金额
     * @return 累计分红金额
     */
    public BigDecimal getTotBounsAmt() {
        return totBounsAmt;
    }

    /**
     * 设置 累计分红金额
     * @param totBounsAmt 累计分红金额
     */
    public void setTotBounsAmt(BigDecimal totBounsAmt) {
        this.totBounsAmt = totBounsAmt;
    }

//    /**
//     * 取得 null
//     * @return null
//     */
//    public BigDecimal getTdyYield() {
//        return tdyYield;
//    }
//
//    /**
//     * 设置 null
//     * @param tdyYield null
//     */
//    public void setTdyYield(BigDecimal tdyYield) {
//        this.tdyYield = tdyYield;
//    }
}