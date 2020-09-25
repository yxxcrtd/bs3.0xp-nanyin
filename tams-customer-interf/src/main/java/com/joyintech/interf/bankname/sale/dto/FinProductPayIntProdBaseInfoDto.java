/*
 * 文件名：FinProductPayIntProdBaseInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2019年1月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.interf.bankname.sale.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品中间付息确认 产品基本信息
 *
 * @author etliu
 * @version 1.0 2019年1月7日
 * @see FinProductPayIntProdBaseInfoDto
 * @since 1.0
 */
public class FinProductPayIntProdBaseInfoDto extends FinProductPayIntDto {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -2880607285815825920L;

    /**
     * 产品名称
     */
    private String prodName;

    /**
     * 收益类型
     */
    private String profitType;

    /**
     * 付息方式
     */
    private String payIntRule;
    
    // 一体化对应etliu start
    /**
     * 支付频率值
     */
    private Long payCycleVal;
    
    /**
     * 现金流代码
     */
    private String cashId;
    
    /**
     * 获取 payCycleVal
     * @return payCycleVal.
     */
    public Long getPayCycleVal() {
        return payCycleVal;
    }

    /**
     * 设置 payCycleVal
     * @param payCycleVal payCycleVal
     */
    public void setPayCycleVal(Long payCycleVal) {
        this.payCycleVal = payCycleVal;
    }
    
    /**
     * 获取 cashId
     * @return cashId.
     */
    public String getCashId() {
        return cashId;
    }

    /**
     * 设置 cashId
     * @param cashId cashId
     */
    public void setCashId(String cashId) {
        this.cashId = cashId;
    }
    // 一体化对应etliu end

    /**
     * 产品起息日
     */
    private Date prodVdate;

    /**
     * 产品到期日
     */
    private Date prodMdate;

    /**
     * 本次计息天数
     */
    private long thisIntAccrDay;

    /**
     * 应付客户收益
     */
    private BigDecimal theoryCusProfit;

    /**
     * 实付客户收益
     */
    private BigDecimal actCusProfit;

    /**
     * 支付日
     */
    private Date payDate;

    /**
     * 交易备注
     */
    private String tradeRemark;

    /**
     * 分层类型
     */
    private String layType;

    /**
     * 产品登记编码
     */
    private String prodRegistCode;

    /**
     * 获取 prodName
     *
     * @return prodName.
     */
    public String getProdName() {
        return prodName;
    }

    /**
     * 设置 prodName
     *
     * @param prodName prodName
     */
    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    /**
     * 获取 profitType
     *
     * @return profitType.
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 设置 profitType
     *
     * @param profitType profitType
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

    /**
     * 获取 payIntRule
     *
     * @return payIntRule.
     */
    public String getPayIntRule() {
        return payIntRule;
    }

    /**
     * 设置 payIntRule
     *
     * @param payIntRule payIntRule
     */
    public void setPayIntRule(String payIntRule) {
        this.payIntRule = payIntRule;
    }

    /**
     * 获取 prodVdate
     *
     * @return prodVdate.
     */
    public Date getProdVdate() {
        return prodVdate;
    }

    /**
     * 设置 prodVdate
     *
     * @param prodVdate prodVdate
     */
    public void setProdVdate(Date prodVdate) {
        this.prodVdate = prodVdate;
    }

    /**
     * 获取 prodMdate
     *
     * @return prodMdate.
     */
    public Date getProdMdate() {
        return prodMdate;
    }

    /**
     * 设置 prodMdate
     *
     * @param prodMdate prodMdate
     */
    public void setProdMdate(Date prodMdate) {
        this.prodMdate = prodMdate;
    }

    /**
     * 获取 thisIntAccrDay
     *
     * @return thisIntAccrDay.
     */
    public long getThisIntAccrDay() {
        return thisIntAccrDay;
    }

    /**
     * 设置 thisIntAccrDay
     *
     * @param thisIntAccrDay thisIntAccrDay
     */
    public void setThisIntAccrDay(long thisIntAccrDay) {
        this.thisIntAccrDay = thisIntAccrDay;
    }

    /**
     * 获取 theoryCusProfit
     *
     * @return theoryCusProfit.
     */
    @Override
    public BigDecimal getTheoryCusProfit() {
        return theoryCusProfit;
    }

    /**
     * 设置 theoryCusProfit
     *
     * @param theoryCusProfit theoryCusProfit
     */
    @Override
    public void setTheoryCusProfit(BigDecimal theoryCusProfit) {
        this.theoryCusProfit = theoryCusProfit;
    }

    /**
     * 获取 actCusProfit
     *
     * @return actCusProfit.
     */
    @Override
    public BigDecimal getActCusProfit() {
        return actCusProfit;
    }

    /**
     * 设置 actCusProfit
     *
     * @param actCusProfit actCusProfit
     */
    @Override
    public void setActCusProfit(BigDecimal actCusProfit) {
        this.actCusProfit = actCusProfit;
    }

    /**
     * 获取 payDate
     *
     * @return payDate.
     */
    public Date getPayDate() {
        return payDate;
    }

    /**
     * 设置 payDate
     *
     * @param payDate payDate
     */
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    /**
     * 获取 tradeRemark
     *
     * @return tradeRemark.
     */
    public String getTradeRemark() {
        return tradeRemark;
    }

    /**
     * 设置 tradeRemark
     *
     * @param tradeRemark tradeRemark
     */
    public void setTradeRemark(String tradeRemark) {
        this.tradeRemark = tradeRemark;
    }

    /**
     * 获取 layType
     *
     * @return layType.
     */
    public String getLayType() {
        return layType;
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
     * 获取 prodRegistCode
     * @return prodRegistCode.
     */
    public String getProdRegistCode() {
        return prodRegistCode;
    }

    /**
     * 设置 prodRegistCode
     * @param prodRegistCode prodRegistCode
     */
    public void setProdRegistCode(String prodRegistCode) {
        this.prodRegistCode = prodRegistCode;
    }

}
