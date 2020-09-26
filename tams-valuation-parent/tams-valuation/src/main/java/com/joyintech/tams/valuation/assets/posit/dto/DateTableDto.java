/*
 * 文件名：DateTableDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.assets.posit.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.calculation.config.NumberEnum;
import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.json.BigDecimalSerializer;

/**
 * 利率表 付息计划表
 * 
 * @author HuangJian
 * @version 1.0 2018年10月23日
 * @see DateTableDto
 * @since 1.0
 */
public class DateTableDto {

    /**
     * 生效日
     */
    private String effDate;

    /**
     * 利率
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal yield;

    /**
     * 计息开始日
     */
    private String vdate;

    /**
     * 计息结束日
     */
    private String mdate;

    /**
     * 理论支付日
     */
    private String payDate;
    
    /** modify by dongjun on 20181203 start **/
    /**
     * 还本日
     */
    private String repayDate;
    
    /**
     * 还本金额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal prinAmt;
    /** modify by dongjun on 20181203 end **/
    
    /**
     * 应计利息
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal intAmt;
    
    /** modify by dongjun on 20181203 start **/
    /**
     * 获取 repayDate
     * @return repayDate.
     */
    public String getRepayDate() {
        return repayDate;
    }
  
    /**
     * 设置 repayDate
     * @param repayDate repayDate
     */
    public void setRepayDate(String repayDate) {
        this.repayDate = repayDate;
    }
    
    /**
     * 获取 prinAmt
     * @return prinAmt.
     */
    public BigDecimal getPrinAmt() {
        return prinAmt;
    }
 
    /**
     * 设置 prinAmt
     * @param prinAmt prinAmt
     */
    public void setPrinAmt(BigDecimal prinAmt) {
        this.prinAmt = prinAmt;
    }
    /** modify by dongjun on 20181203 end **/
    
    /**
     * 获取 effDate
     * 
     * @return effDate.
     */
    public String getEffDate() {
        return effDate;
    }

    /**
     * 设置 effDate
     * 
     * @param effDate effDate
     */
    public void setEffDate(String effDate) {
        this.effDate = effDate;
    }

    /**
     * 获取 yield
     * 
     * @return yield.
     */
    public BigDecimal getYield() {
        return ArithmeticUtil.round(yield, NumberEnum.MONEY);
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
     * 获取 vdate
     * 
     * @return vdate.
     */
    public String getVdate() {
        return vdate;
    }

    /**
     * 设置 vdate
     * 
     * @param vdate vdate
     */
    public void setVdate(String vdate) {
        this.vdate = vdate;
    }

    /**
     * 获取 mdate
     * 
     * @return mdate.
     */
    public String getMdate() {
        return mdate;
    }

    /**
     * 设置 mdate
     * 
     * @param mdate mdate
     */
    public void setMdate(String mdate) {
        this.mdate = mdate;
    }

    /**
     * 获取 payDate
     * 
     * @return payDate.
     */
    public String getPayDate() {
        return payDate;
    }

    /**
     * 设置 payDate
     * 
     * @param payDate payDate
     */
    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    /**
     * 获取 intAmt
     * 
     * @return intAmt.
     */
    public BigDecimal getIntAmt() {
        return intAmt;
    }

    /**
     * 设置 intAmt
     * 
     * @param intAmt intAmt
     */
    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
    }

    // public String getEffDateFt(){
    // return DateUtil.format(effDate);
    // }
    //

    // public String getMdateFt(){
    // return DateUtil.format(mdate);
    // }
    //
    // public String getVdateFt(){
    // return DateUtil.format(vdate);
    // }
    //
    // public String getPayDateFt(){
    // return DateUtil.format(payDate);
    // }
    //

}
