package com.joyintech.tams.invest.common.dto.product.addreduce;

import java.math.BigDecimal;

import com.joyintech.tams.common.base.entity.PrdLayerParam;

/**
 * @program: tams-invest-parent
 * @description:
 * @author: 强成西
 * @create: 2019-01-08 17:26
 **/
public class ReduceInfoDto extends PrdLayerParam {
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 142891022584462717L;
    /**
     * 期次号
     */
    private String indexNo;
    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 剩余本金
     */
    private BigDecimal remainPrinAmt;

    /**
     * 强减本金
     */
    private BigDecimal reduceAmt;

    /**
     * 付息开始日
     */
    private String vDate;

    /**
     * 付息截止日
     */
    private String mDate;

    /**
     * 是否分层
     */
    private String isLay;

    /**
     * 分层类型
     */
    private String layType;
    /**
     * 应付利率
     */
    private BigDecimal yield;
    /**
     * 计息基础
     */
    private String basis;
    /**
     * 实付利率
     */
    private BigDecimal actYield;
    /**
     * 应付利息
     */
    private BigDecimal interest;
    /**
     * 实付利息
     */
    private BigDecimal actInterest;

    /**
     * 获取 indexNo
     *
     * @return indexNo
     */
    public String getIndexNo() {
        return indexNo;
    }

    /**
     * 设置 indexNo
     *
     * @param indexNo indexNo
     */
    public void setIndexNo(String indexNo) {
        this.indexNo = indexNo;
    }

    /**
     * 获取 finprodId
     *
     * @return finprodId
     */
    @Override
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 finprodId
     *
     * @param finprodId finprodId
     */
    @Override
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    /**
     * 获取 remainPrinAmt
     *
     * @return remainPrinAmt
     */
    public BigDecimal getRemainPrinAmt() {
        return remainPrinAmt;
    }

    /**
     * 设置 remainPrinAmt
     *
     * @param remainPrinAmt remainPrinAmt
     */
    public void setRemainPrinAmt(BigDecimal remainPrinAmt) {
        this.remainPrinAmt = remainPrinAmt;
    }

    /**
     * 获取 reduceAmt
     *
     * @return reduceAmt
     */
    public BigDecimal getReduceAmt() {
        return reduceAmt;
    }

    /**
     * 设置 reduceAmt
     *
     * @param reduceAmt reduceAmt
     */
    public void setReduceAmt(BigDecimal reduceAmt) {
        this.reduceAmt = reduceAmt;
    }

    /**
     * 获取 vDate
     *
     * @return vDate
     */
    public String getvDate() {
        return vDate;
    }

    /**
     * 设置 vDate
     *
     * @param vDate vDate
     */
    public void setvDate(String vDate) {
        this.vDate = vDate;
    }

    /**
     * 获取 mDate
     *
     * @return mDate
     */
    public String getmDate() {
        return mDate;
    }

    /**
     * 设置 mDate
     *
     * @param mDate mDate
     */
    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    /**
     * 获取 yield
     *
     * @return yield
     */
    @Override
    public BigDecimal getYield() {
        return yield;
    }

    /**
     * 设置 yield
     *
     * @param yield yield
     */
    @Override
    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }

    /**
     * 获取 basis
     *
     * @return basis
     */
    public String getBasis() {
        return basis;
    }

    /**
     * 设置 basis
     *
     * @param basis basis
     */
    public void setBasis(String basis) {
        this.basis = basis;
    }

    /**
     * 获取 actYield
     *
     * @return actYield
     */
    public BigDecimal getActYield() {
        return actYield;
    }

    /**
     * 设置 actYield
     *
     * @param actYield actYield
     */
    public void setActYield(BigDecimal actYield) {
        this.actYield = actYield;
    }

    /**
     * 获取 interest
     *
     * @return interest
     */
    public BigDecimal getInterest() {
        return interest;
    }

    /**
     * 设置 interest
     *
     * @param interest interest
     */
    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    /**
     * 获取 actInterest
     *
     * @return actInterest
     */
    public BigDecimal getActInterest() {
        return actInterest;
    }

    /**
     * 设置 actInterest
     *
     * @param actInterest actInterest
     */
    public void setActInterest(BigDecimal actInterest) {
        this.actInterest = actInterest;
    }

    /**
     * 设置 isLay
     *
     * @param isLay isLay
     */
    public void setIsLay(String isLay) {
        this.isLay = isLay;
    }

    /**
     * 设置 layType
     *
     * @param layType layType
     */
    public void setLayType(String layType) {
        this.layType = layType;
    }

    /**
     * 获取 isLay
     *
     * @return isLay
     */
    public String getIsLay() {
        if (isLay == null || "".equals(isLay))
            return "";
        return isLay;
    }

    /**
     * 获取 layType
     *
     * @return layType
     */
    public String getLayType() {
        if (layType == null || "".equals(layType))
            return "";
        return layType;
    }
}
