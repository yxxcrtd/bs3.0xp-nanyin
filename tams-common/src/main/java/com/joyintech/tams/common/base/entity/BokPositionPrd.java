package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class BokPositionPrd extends BokPositionPrdKey {
    /**
     * 当前本金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyPrinAmt;

    /**
     * 当前份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyShareAmt;

    /**
     * 当日单位净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyUnitValue;

    /**
     * 当日收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyYield;

    /**
     * 当日未分配利润，对于预期收益产品存应计客户收益
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyUnpaidIntincexp;

    /**
     * 当日资产净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyAssetAmt;

    /**
     * 当日累计净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyUnitValueSum;

    /**
     * 7日年化收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yield7;

    /**
     * 取得 当前本金
     * @return 当前本金
     */
    public BigDecimal getTdyPrinAmt() {
        return tdyPrinAmt;
    }

    /**
     * 设置 当前本金
     * @param tdyPrinAmt 当前本金
     */
    public void setTdyPrinAmt(BigDecimal tdyPrinAmt) {
        this.tdyPrinAmt = tdyPrinAmt;
    }

    /**
     * 取得 当前份额
     * @return 当前份额
     */
    public BigDecimal getTdyShareAmt() {
        return tdyShareAmt;
    }

    /**
     * 设置 当前份额
     * @param tdyShareAmt 当前份额
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
     * 取得 当日收益率
     * @return 当日收益率
     */
    public BigDecimal getTdyYield() {
        return tdyYield;
    }

    /**
     * 设置 当日收益率
     * @param tdyYield 当日收益率
     */
    public void setTdyYield(BigDecimal tdyYield) {
        this.tdyYield = tdyYield;
    }

    /**
     * 取得 当日未分配利润，对于预期收益产品存应计客户收益
     * @return 当日未分配利润，对于预期收益产品存应计客户收益
     */
    public BigDecimal getTdyUnpaidIntincexp() {
        return tdyUnpaidIntincexp;
    }

    /**
     * 设置 当日未分配利润，对于预期收益产品存应计客户收益
     * @param tdyUnpaidIntincexp 当日未分配利润，对于预期收益产品存应计客户收益
     */
    public void setTdyUnpaidIntincexp(BigDecimal tdyUnpaidIntincexp) {
        this.tdyUnpaidIntincexp = tdyUnpaidIntincexp;
    }

    /**
     * 取得 当日资产净值
     * @return 当日资产净值
     */
    public BigDecimal getTdyAssetAmt() {
        return tdyAssetAmt;
    }

    /**
     * 设置 当日资产净值
     * @param tdyAssetAmt 当日资产净值
     */
    public void setTdyAssetAmt(BigDecimal tdyAssetAmt) {
        this.tdyAssetAmt = tdyAssetAmt;
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

    /**
     * 取得 7日年化收益率
     * @return 7日年化收益率
     */
    public BigDecimal getYield7() {
        return yield7;
    }

    /**
     * 设置 7日年化收益率
     * @param yield7 7日年化收益率
     */
    public void setYield7(BigDecimal yield7) {
        this.yield7 = yield7;
    }
}