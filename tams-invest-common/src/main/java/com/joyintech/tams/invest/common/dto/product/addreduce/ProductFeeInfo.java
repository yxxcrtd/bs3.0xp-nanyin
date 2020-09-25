package com.joyintech.tams.invest.common.dto.product.addreduce;

import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

import java.math.BigDecimal;

/**
 * @program: tams-invest-parent
 * @description: 产品费用信息
 * @author: 强成西
 * @create: 2019-01-09 13:33
 **/
public class ProductFeeInfo {

    /**
     * 期次号
     */
    private String indexNo;

    /**
     * 费用代码
     */
    private String feeId;

    /**
     * 费用名称
     */
    private String feeName;

    /**
     * 产品代码
     */
    private String finprodId;

    /**
     * 费用类型
     */
    private String feeType;

    /**
     * 费用开始日
     */
    private String vDate;

    /**
     * 费用结束日
     */
    private String mDate;

    /**
     * 本金
     */
    private BigDecimal prinAmt;

    /**
     * 计息基础
     */
    private String basis;

    /**
     * 费率
     */
    private BigDecimal yield;

    /**
     * 现金流代码
     */
    private String cashId;

    /**
     * 理论费用
     */
    private BigDecimal feeAmt;

    /**
     * 实付费率(%)
     */
    private BigDecimal actYield;

    /**
     * 实付费用
     */
    private BigDecimal actFeeAmt;

    /**
     * 备注
     */
    private String remark;

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
     * 获取 feeName
     *
     * @return feeName
     */
    public String getFeeName() {
        return feeName;
    }

    /**
     * 设置 feeName
     *
     * @param feeName feeName
     */
    public void setFeeName(String feeName) {
        this.feeName = feeName;
    }

    /**
     * 获取 feeType
     *
     * @return feeType
     */
    public String getFeeType() {
        return feeType;
    }

    /**
     * 设置 feeType
     *
     * @param feeType feeType
     */
    public void setFeeType(String feeType) {
        this.feeType = feeType;
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
     * 获取 prinAmt
     *
     * @return prinAmt
     */
    public BigDecimal getPrinAmt() {
        return prinAmt;
    }

    /**
     * 设置 prinAmt
     *
     * @param prinAmt prinAmt
     */
    public void setPrinAmt(BigDecimal prinAmt) {
        this.prinAmt = prinAmt;
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
     * 获取 yield
     *
     * @return yield
     */
    public BigDecimal getYield() {
        return yield;
    }

    /**
     * 设置 yield
     *
     * @param yield yield
     */
    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }

    /**
     * 获取 feeAmt
     *
     * @return feeAmt
     */
    public BigDecimal getFeeAmt() {
        return feeAmt;
    }

    /**
     * 设置 feeAmt
     *
     * @param feeAmt feeAmt
     */
    public void setFeeAmt(BigDecimal feeAmt) {
        this.feeAmt = feeAmt;
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
     * 获取 actFeeAmt
     *
     * @return actFeeAmt
     */
    public BigDecimal getActFeeAmt() {
        return actFeeAmt;
    }

    /**
     * 设置 actFeeAmt
     *
     * @param actFeeAmt actFeeAmt
     */
    public void setActFeeAmt(BigDecimal actFeeAmt) {
        this.actFeeAmt = actFeeAmt;
    }

    /**
     * 获取 remark
     *
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 remark
     *
     * @param remark remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取 feeId
     *
     * @return feeId
     */
    public String getFeeId() {
        return feeId;
    }

    /**
     * 设置 feeId
     *
     * @param feeId feeId
     */
    public void setFeeId(String feeId) {
        this.feeId = feeId;
    }

    /**
     * 获取 finprodId
     *
     * @return finprodId
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 finprodId
     *
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    /**
     * 获取 cashId
     *
     * @return cashId
     */
    public String getCashId() {
        return cashId;
    }

    /**
     * 设置 cashId
     *
     * @param cashId cashId
     */
    public void setCashId(String cashId) {
        this.cashId = cashId;
    }
}
